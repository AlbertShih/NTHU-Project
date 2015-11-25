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

define(['jquery', '../utils', '../constants', '../globals', '../config'], function($, utils, constants, globals, config) {
  var webcam = {
    needsPlaylist: true,
    init: function() {
      if (typeof(YT) == 'undefined' || typeof(YT.UploadWidget) == 'undefined') {
        window.onYouTubeIframeAPIReady = function() {
          webcam.loadUploadWidget();
        };

        $.getScript('//www.youtube.com/iframe_api');
      } else {
        webcam.loadUploadWidget();
      }
    },

    loadUploadWidget: function() {
      new YT.UploadWidget('webcam-widget', {
        webcamOnly: true,
        events: {
          onApiReady: function(event) {
            //event.target.setVideoTitle(config.VIDEO_TITLE || constants.WEBCAM_VIDEO_TITLE);
            //event.target.setVideoDescription(config.VIDEO_DESCRIPTION || constants.WEBCAM_VIDEO_DESCRIPTION);
			//alert($('#wCamVideoTitle').val());
			if(getCookie("OKorNOT")){
				$("#submitTitleDes").attr('disabled', true);
				$("#submitTitleDes").val('Already submit.');
				$("#wCamVideoTitle").attr('disabled', true);
				$("#wCamVideoDescription").attr('disabled', true);
				$("#resetTitleDes").attr('disabled', false);
			}
			event.target.setVideoTitle( getCookie("vTitle") );
			event.target.setVideoDescription( getCookie("vDes") + '----PENCIL教學平台 '+
			'http://education-star.appspot.com/');
            event.target.setVideoKeywords([utils.generateKeywordFromPlaylistId(globals.hashParams.playlist)]);
          },
          onUploadSuccess: function(event) {
            utils.showMessage('Your webcam submission was received.');
			//alert('Video URL http://youtu.be/' + event.data.videoId);
			
			var wParent = window.parent;
			var message = {'case': '3', 'videoId': event.data.videoId, 'title': getCookie("vTitle"), 'description': getCookie("vDes")};
			//alert("case3 " + message.videoId + message.title + message.description);
			
			//if(!wParent.sendCourseToServer)//If not support, using HTML5 postMessage  
            // {            
                wParent.postMessage(message, '*');      //Chrome, Opera...         
             //}  
            // else  
            // {   
            //    wParent.sendCourseToServer(message); 	//IE ...							 
            // }  
			 
			//wParent.sendCourseToServer(event.data.videoId, $('#wCamVideoTitle').val(), $('#wCamVideoDescription').val());
			
			//alert($('#wCamVideoTitle').val() +"  "+ $('#wCamVideoDescription').val()+ '----Excelsior教學平台 '+
			//'http://knockbusterx.appspot.com/');
            window._gaq.push(['_trackEvent', 'Submission', 'Webcam', 'Success']);
          },
          onStateChange: function(event) {
            if (event.data.state == YT.UploadWidgetState.ERROR) {
              window._gaq.push(['_trackEvent', 'Submission', 'Webcam', 'Error']);
            }
          }
        }
      });
    }
  };

  return webcam;
});

function submitTitleDes() {
    setCookie("vTitle", $("#wCamVideoTitle").val(), 365);
	setCookie("vDes", $("#wCamVideoDescription").val(), 365);
	setCookie("OKorNOT", "1", 365);
    location.reload();
}

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