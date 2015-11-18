JavaScript.load('./javascript/videoPlayerShow.js',function(){});
function bindingWithPlayer(str,title,viewcount,description) {//Lin
    title = unescape(title);
    console.log("bindingWithPlayer -> "+title);
    viewcount = unescape(viewcount);
    console.log("bindingWithPlayer -> "+viewcount);
    description = unescape(description);
    $("#answer").hide();
    $(".mainpage").hide();
    videoPlayerFrameShow(str,title,viewcount,description);
    //window.location='http://education-star.appspot.com/?id='+str;
    //window.location='http://localhost:8888/?id='+str;
}
function searchVideo(queryString){
    var ansMsg = document.getElementById('ansMsg');
    var answer = document.getElementById('answer');
    var videoList = document.getElementById('videoList');
    videoPlayerFrameHide();
    ansMsg.innerHTML="";
    $("#answer").show();
    $(".mainpage").hide();
    $("#loading").show();
    $("#firstUploadBtn").hide();
        ajaxRequest('GET', encodeURI('search?queryString='+queryString), {
            'Accept': 'application/json'
        }, null, function (status, headers, body) {
            var ans = eval('(' + body + ')');// success callback
            if (ans.length == 0) {
                ansMsg.innerHTML ='<div style="font-size:30px;color:#ff7f50; width: 200px;margin: 0 auto;">沒有找到!</div>';
                //$("#firstUploadBtn").show();
                $("#loading").hide();
            }
            var data = new Array(ans.length);
            var htmlFrag;
            var i = 0;
            for ( j = 0; j < ans.length; j++) {
                data[j]=new Array(7);
               (function(j)    //closure
                {
                    var ut = "https://www.googleapis.com/youtube/v3/videos?id=" +
                        escapeHtml(unescape(ans[j].id)) +
                        "&key=AIzaSyBaDrIDUqfPRMaTl7X3bg1ekPLQAp9FhBI%20" +
                        "&part=snippet,statistics" +
                        "&fields=items(id,snippet,statistics)"
                        ajaxRequest('GET', ut, {
                            'Accept': 'application/json'
                        }, null, function (status, headers, body) {
                            var s = JSON.parse(body);
                            if(s.items.length==0) {
                                data[j][3]=-1;
                                // alert("刪除:"+ans[j].id);
                                //videoDelete(ans[j].id);
                            }
                            else{
                                data[j][0]= s.items[0].id;
                                data[j][1]= s.items[0].snippet.thumbnails.medium.url;
                                data[j][2]= s.items[0].snippet.title;
                                data[j][3]= s.items[0].statistics.viewCount;
                                data[j][4]= s.items[0].snippet.description;
                                data[j][5]= s.items[0].snippet.publishedAt;
                                data[j][6]= s.items[0].snippet.channelTitle;
                            }
                            i++;
                            if(i== ans.length){
                                htmlFrag=sortData(data,htmlFrag);
                                if(htmlFrag!=0){
                                    //bindingWithPlayer(data[0][0],data[0][2],data[0][3],data[0][4]);
                                    ansMsg.innerHTML = htmlFrag;
                                    $("#loading").hide();
                                }
                                else{
                                    ansMsg.innerHTML ='<div style="font-size:30px;color:#ff7f50; width: 200px;margin: 0 auto;">沒有找到!</div>';
                                   // $("#firstUploadBtn").show();
                                    $("#loading").hide();
                                }
                            }
                        }, function (status, headers, body) { // error callback
                            switch (status) {
                                case 404:
                                    status404Handler();
                                default:
                                // do nothing
                            }
                        }, null);// run callbacks in global scope
                })(j);
            }
        }, function (status, headers, body) { // error callback
            switch (status) {
                case 404:
                    status404Handler();
                default:
                // do nothing
            }
        }, null); // run callbacks in global scope
    function sortData(data,htmlFrag){
//               bubbleSort -> improve plz;
        var temp;
        for (var i = data.length - 1; i >= 0; --i)
            for (var j = 0; j < i-1; ++j){
                if (parseInt(data[j][3]) < parseInt(data[j+1][3])){
                    temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
            }
//            pack
        htmlFrag = "";
        var delVideo=0;
        htmlFrag += '<div id="result"><ol id="search-result">';
        for (var i = 0; i < data.length; i++){
            if(data[i][3]!=-1){
                htmlFrag += '<li class="v-frame"> ' +
                    '<div class="v-img-frame"> ' +
    //                            '<div class="v-duration">'+
    //                            s.items[0].statistics.likeCount+
    //                           '</div>'+
                    '<div>' +
                    "<a" + "' onclick='string" + '="' + data[i][0]  + '";bindingWithPlayer("'+data[i][0]+ '",' + '"' + escape(data[i][2]) +'"'+ ',' +'"'+ escape(data[i][3]) +'"'+',' +'"'+ escape(data[i][4])+'"'+');' + "' >" +
                    '<img class="v-img" src="' +
                    data[i][1] +
                    '"/>' +
                    '</a>' +
                    '</div>' +
                    '</div>' +
                    '<div class="v-content">' +
                    // '<a class="title" id="'+s.items[0].id+'" onclick="string'+'='+s.items[0].id+';bindingWithPlayer();'+'" >'+
                    //                                            '<a class="title" href="http://www.youtube.com/watch?v='+ s.items[0].id+'">'+ ori
    //Chen's version"<a class='title' id='" + data[i][0]  + "' onclick='string" + '="' + data[i][0]  + '";bindingWithPlayer("'+data[i][0]+ '",' + JSON.stringify(data[i][2])+',' + JSON.stringify(data[i][3])+',' + JSON.stringify(data[i][4])+');' + "' >" +
                    "<a class='title' id='" + data[i][0]  + "' onclick='string" + '="' + data[i][0]  + '";bindingWithPlayer("'+data[i][0]+ '",' + '"' + escape(data[i][2]) +'"'+ ',' +'"'+ escape(data[i][3]) +'"'+',' +'"'+ escape(data[i][4])+'"'+');' + "' >" +
                    data[i][2]  +
                    '</a>' +
                    '<div class="publishedAt">上傳時間:' +
                    data[i][5]  +
                    '</div>' +
                    '<div class="channelTitle">上傳者:' +
                    data[i][6]  +
                    '</div>' +
                    '<div class="viewCount">已觀看:'+
                    data[i][3] +'</div>'+
                    '<div class="description">' +
                    data[i][4]  +
                    '</div>' +
                    '</div>' +
                    '</li>';
            }
            else{
                delVideo++;
            }
        }
        if(data.length-delVideo==0)
            htmlFrag=0;
        else
            htmlFrag += '</ol></div>';
        return htmlFrag;
        }
    }
