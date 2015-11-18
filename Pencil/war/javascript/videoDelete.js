function showUserVideo() {
    ajaxRequest('GET', 'login', {
        'Accept': 'application/json'
    }, null, function (status, headers, body) {

        var answer = document.getElementById('answer');
        var ansMsg = document.getElementById('ansMsg');
        ansMsg.innerHTML = "";
        $(".mainpage").hide();
        $(".videoPlayerFrame").hide();
        $("#firstUploadBtn").hide();
        $("#answer").show();
        $("#loading").show();

        var s = JSON.parse(body);
        var id = s.id;
        ajaxRequest('GET', encodeURI('video?userId=' + id), {
            'Accept': 'application/json'
        }, null, function (status, headers, body) {
            var ans = eval('(' + body + ')');// success callback
            if (ans.length == 0) {
                $(".mainpage").hide();
                $("#loading").hide();
                ansMsg.innerHTML += '<font size="15px" color="#ff7f50">你沒有上傳影片!</font>';
            }
            var data = new Array(ans.length);
            var htmlFrag;
            var i = 0;
            for (j = 0; j < ans.length; j++) {
                data[j] = new Array(7);
                (function (j)    //closure
                {
                    var ut = "https://www.googleapis.com/youtube/v3/videos?id=" +
                        escapeHtml(unescape(ans[j].id)) +
                        "&key=AIzaSyBaDrIDUqfPRMaTl7X3bg1ekPLQAp9FhBI%20" +
                        "&part=snippet,statistics" +
                        "&fields=items(id,snippet,statistics)";
                    ajaxRequest('GET', ut, {
                        'Accept': 'application/json'
                    }, null, function (status, headers, body) {
                        var s = JSON.parse(body);
                        if (s.items.length == 0) {
                            data[j][3] = -1;
                            // alert("刪除:"+ans[j].id);
                            videoDelete(ans[j].id);
                        }
                        else {
                            data[j][0] = s.items[0].id;
                            data[j][1] = s.items[0].snippet.thumbnails.medium.url;
                            data[j][2] = s.items[0].snippet.title;
                            data[j][3] = s.items[0].statistics.viewCount;
                            data[j][4] = s.items[0].snippet.description;
                            data[j][5] = s.items[0].snippet.publishedAt;
                            data[j][6] = s.items[0].snippet.channelTitle;
                        }
                        i++;
                        if (i == ans.length) {
                            htmlFrag = sortData(data, htmlFrag);
                            // bindingWithPlayer(data[0][0],data[0][2],data[0][3],data[0][4]);
                            $("#loading").hide();
                            ansMsg.innerHTML = '<div style="color:#B8B8B8; width:450px;margin:0 auto;">刪除的影片只會在pencil網站消失，該影片還是存在youtube</div><br>'
                            ansMsg.innerHTML += htmlFrag;
                            //alert(htmlFrag);

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

    }, function (status, headers, body) { // error callback
        switch (status) {
            case 406:
                alert("必須登入");
            default:
            // do nothing
        }
    }, null);
}
function sortData(data, htmlFrag) {
//               bubbleSort -> improve plz;
    var temp;
    for (var i = data.length - 1; i >= 0; --i)
        for (var j = 0; j < i - 1; ++j) {
            if (parseInt(data[j][3]) < parseInt(data[j + 1][3])) {
                temp = data[j];
                data[j] = data[j + 1];
                data[j + 1] = temp;
            }
        }
    htmlFrag = "";
        htmlFrag = '<div style="width:200px;margin:0 auto;">'
    var delVideo=0;
        for (var i = 0; i < data.length; i++) {
            if(data[i][3]!=-1){
                htmlFrag +=
                    '<div>' +
                        '<input type="checkbox" class="deleteCheck" id="' +
                        data[i][0] +
                        '"/>' +
                        data[i][2] +
                        '</div>' +
                        '<a>' +
                        '<img class="v-img" src="' +
                        data[i][1] +
                        '"/>' +
                        '</a>'
            }
            else{
                delVideo++;
            }
        }
    if(delVideo!=0)
        htmlFrag += '<div style="color:#1570cd;">'+delVideo+'部影片已遺失，可能是在youtube被刪除了</div>'
    if(data.length-delVideo>0)
        htmlFrag += '<input type="button" id="deleteBtn" value="刪除影片" onclick="videoSelectDelete()"></div>'
    return htmlFrag;
}
function videoSelectDelete() {
    var videoList = document.getElementsByClassName("deleteCheck");
    var videoId;
    for (var i = 0; i < videoList.length; i++) {
        if (videoList[i].checked == true) {
            videoId = videoList[i].id;
            videoDelete(videoId);
        }
    }
}
function videoDelete(videoId) {
    ajaxRequest('GET', encodeURI('videoDelete?videoId=' + videoId), {
        'Accept': 'application/json'
    }, null, function (status, headers, body) {// success callback
        showUserVideo();
    }, function (status, headers, body) { // error callback
        switch (status) {
            case 401:
            default:
            // do nothing
        }
    }, null);
}