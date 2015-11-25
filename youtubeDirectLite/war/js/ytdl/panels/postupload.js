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

define(['jquery', '../utils', '../globals'], function($, utils, globals) {
  return {
    init: function() {
      var status = globals.parsedUrl.queryKey.status;
      if (status == 200) {
		var videoId = globals.parsedUrl.queryKey.id;
        var youtubeUrl = utils.format('http://youtu.be/{0}', videoId);
        $('#youtube-link').attr('href', youtubeUrl);
        $('#upload-success').show();
		document.writeln("上傳成功，您的影片正在與YouTube連結，可能需數分鐘才出現");
		//alert('Video URL ' + youtubeUrl);	//new
		//sendCourseToServer
		var wParent = window.parent;
		var message = {'case': '2', 'videoId': videoId, 'title': '', 'description':''};
		//alert( "case2 " + message.videoId + message.title + message.description);
		//if(!wParent.sendCourseToServer)//If not support, using HTML5 postMessage  
        //      {            
                wParent.postMessage(message, '*');      //Chrome, Opera...
                
        //        alert("GOOD! POST!");
                
        //      }  
        //      else  
        //      {   
        //        wParent.sendCourseToServer(message); 	//IE ...							 
        //      }  
		
		//wParent.sendCourseToServer(videoId, $('#title').val(), $('#description').val());
		
        window._gaq.push(['_trackEvent', 'Submission', 'Upload', 'Success']);
      } else {
        $('#upload-success').hide();
        utils.showMessage(utils.format('Your video could not be submitted. (Error: {0})', globals.parsedUrl.queryKey.code));

        window._gaq.push(['_trackEvent', 'Submission', 'Upload', 'Error']);
      }
    }
  };
});