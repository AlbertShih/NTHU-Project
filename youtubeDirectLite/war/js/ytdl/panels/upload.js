/*
  Copyright 2012 Google Inc. All Rights Reserved.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/
var currentPath=0;
	function setCookie(c_name, value, exdays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var c_value = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
    document.cookie = c_name + "=" + c_value;
	}
	function getCookie(c_name) {
		var i, x, y, ARRcookies = document.cookie.split(";");
		for (i = 0; i < ARRcookies.length; i++) {
			x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
			y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
			x = x.replace(/^\s+|\s+$/g, "");
			if (x == c_name) {
				return unescape(y);
			}
		}
	}
	function deleteAllCookies() {
		var cookies = document.cookie.split(";");

		for (var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i];
			var eqPos = cookie.indexOf("=");
			var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
			document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
		}
		 location.reload();
	}
	function askForPath(){
		  var message = {'case': '3'};
		  //alert("ask!");
		  window.parent.postMessage(message, '*'); 
		};

	

		if (typeof window.addEventListener != 'undefined') {             //Using HTML5 postMessage
				window.addEventListener('message', receiveMessage, false);
			} else if (typeof window.attachEvent != 'undefined') {
				window.attachEvent('onmessage', receiveMessage);
			}
			
		function receiveMessage(e) {			//receiveMessage
			var data = e.data;
			switch (data.case) {					//case1 : receive current path(=title)
				case '1':
					currentPath=data.title;
					setCookie("currentPath", currentPath, 365);
					//alert("RECEIVE&setcookie:"+currentPath);
					break;
				default:
					break;
			}
			;
		}
		
define(['jquery', '../utils', '../constants', '../globals', '../config'], function($, utils, constants, globals, config) {
  return {
    needsPlaylist: true,
    init: function() {
      function populateCategoriesSelect(xml) {
        var assignableCategories = xml.find('atom\\:category:has(yt\\:assignable), category:has(assignable)');
        var options = [];
        $.each(assignableCategories, function() {
          var term = $(this).attr('term');
          var label = $(this).attr('label');
          options.push(utils.format('<option data-label="{0}" value="{1}">{0}</option>', label, term));
        });
        $('#category').html(options.sort().join(''));

      }

      if (!config.VIDEO_CATEGORY) {
        $('#category-container').show();
        var categoriesXml = lscache.get(constants.CATEGORIES_CACHE_KEY);
        if (categoriesXml) {
          populateCategoriesSelect($(new DOMParser().parseFromString(categoriesXml, 'text/xml')));
        } else {
          $.ajax({
            dataType: 'xml',
            type: 'GET',
            url: utils.format('{0}/schemas/2007/categories.cat', constants.GDATA_SERVER),
            headers: utils.generateYouTubeApiHeaders(),
            success: function(responseXml) {
              var xmlString = new XMLSerializer().serializeToString(responseXml);
              lscache.set(constants.CATEGORIES_CACHE_KEY, xmlString, constants.CATEGORIES_CACHE_EXPIRATION_MINUTES);
              populateCategoriesSelect($(responseXml));
            },
            error: function(jqXHR) {
              utils.showMessage('The list of categories could not be loaded: ' + jqXHR.responseText);
            }
          });
        }
      }

      if (!config.VIDEO_TITLE) {
        $('#title-container').show();
      }
      if (!config.VIDEO_DESCRIPTION) {
        $('#description-container').show();
      }
		
	  askForPath();		//ask(some bug)
	
      $('#upload').click(function() {
        $('#upload').attr('disabled', true);
        $('#upload').val('Uploading...');
		
		currentPath = getCookie("currentPath");
		
        var keywordList = [ constants.DEFAULT_KEYWORD ];
        if (globals.hashParams.playlist) {
          var playlistId = globals.hashParams.playlist;
          var keyword = utils.generateKeywordFromPlaylistId(playlistId);
          keywordList.push(keyword);
		  keywordList.push("pencil");
		  keywordList.push("pencil教學平台");
		  keywordList.push(currentPath);
        }
		
		var wParent = window.parent;
		var message = {'case': '1', 'videoId': '', 'title': $('#title').val(), 'description':$('#description').val()};
		wParent.postMessage(message, '*');      //Chrome, Opera...
        //alert("videoId:" + message.videoId +"  title:"+ message.title +"  description:"+ message.description);

		//alert("currentPath:"+currentPath);
		
        var jsonBody = {
          data: {
			title: currentPath,													//alert
            //title: config.VIDEO_TITLE || $('#title').val(),
            category: 'Education',
			//category: Education,
            description: $('#description').val()+'\
			\
			----PENCIL教學平台 '+
			'http://education-star.appspot.com/',
            tags: keywordList
          }
        }

        $.ajax({
          dataType: 'xml',
          type: 'POST',
          url: utils.format('{0}/action/GetUploadToken', constants.GDATA_SERVER),
          headers: utils.generateYouTubeApiHeaders(),
          data: JSON.stringify(jsonBody),
          contentType: 'application/json',
          processData: false,
          success: function(responseXml) {
            utils.hideMessage();
            var xml = $(responseXml);
            var nextUrl = utils.format('{0}#state=postupload&playlist={1}', utils.currentUrlWithoutParams(), globals.hashParams.playlist);
            var submissionUrl = utils.format('{0}?nexturl={1}', xml.find('url').text(), encodeURIComponent(nextUrl));
            var token = xml.find('token').text();

            $('#upload-form').attr('action', submissionUrl);
            $('<input>').attr({
              type: 'hidden',
              name: 'token',
              value: token
            }).appendTo('#upload-form');

            $('#upload-form').submit();
          },
          error: function(jqXHR) {
            utils.showMessage('Metadata submission failed: ' + jqXHR.responseText);
            $('#upload').removeAttr('disabled');
            $('#upload').val('Upload');
          }
        });
      });

      $('[required]').change(function() {
        var disabled = false;

        $.each($('[required]:visible'), function() {
          if (!$(this).val()) {
            disabled = true;
          }
        });

        if (disabled) {
          $('#upload').attr('disabled', true);
        } else {
          $('#upload').removeAttr('disabled');
        }
      });
    }
  };
});




		

