JavaScript.load('./javascript/videoPlayerShow.js',function(){});
function bindingWithPlayer(str,title,viewcount,description,author) {//Lin
    author = unescape(author);
    title = unescape(title);
    console.log("bindingWithPlayer -> "+title);
    viewcount = unescape(viewcount);
    console.log("bindingWithPlayer -> "+viewcount);
    description = unescape(description);
    $("#answer").hide();
    $(".mainpage").hide();
    videoPlayerFrameShow(str,title,viewcount,description,author);

}


var getCourseCount = function(){
    var pathQuery = [
        'JH/7thGrader/math/numberAndQuantity/sub1',
        'JH/7thGrader/math/numberAndQuantity/sub2',
        'JH/7thGrader/math/numberAndQuantity/sub3',
        'JH/7thGrader/math/numberAndQuantity/sub4',
        'JH/7thGrader/math/numberAndQuantity/sub5',
        'JH/7thGrader/math/numberAndQuantity/sub6',
        'JH/7thGrader/math/numberAndQuantity/sub7',
        'JH/7thGrader/math/numberAndQuantity/sub8',
        'JH/7thGrader/math/numberAndQuantity/sub9',
        'JH/7thGrader/math/numberAndQuantity/sub10',
        'JH/7thGrader/math/numberAndQuantity/sub11',
        'JH/7thGrader/math/numberAndQuantity/sub12',
        'JH/7thGrader/math/numberAndQuantity/sub13',
        'JH/7thGrader/math/numberAndQuantity/sub14',
        'JH/7thGrader/math/numberAndQuantity/sub15',
        'JH/7thGrader/math/algebra/sub1',
        'JH/7thGrader/math/algebra/sub2',
        'JH/7thGrader/math/algebra/sub3',
        'JH/7thGrader/math/algebra/sub4',
        'JH/7thGrader/math/algebra/sub5',
        'JH/7thGrader/math/algebra/sub6',
        'JH/7thGrader/math/algebra/sub7',
        'JH/7thGrader/math/algebra/sub8',
        'JH/7thGrader/math/algebra/sub9',
        'JH/7thGrader/math/algebra/sub10',
        'JH/7thGrader/math/algebra/sub11',
        'JH/7thGrader/math/algebra/sub12',
        'JH/7thGrader/math/algebra/sub13',
        'JH/7thGrader/math/algebra/sub14',
        'JH/7thGrader/math/algebra/sub15',
        'JH/7thGrader/math/algebra/sub16',
        'JH/7thGrader/math/algebra/sub17',
        'JH/7thGrader/math/algebra/sub18',  //33 request
        'JH/8thGrader/math/numberAndQuantity/sub1',
        'JH/8thGrader/math/numberAndQuantity/sub2',
        'JH/8thGrader/math/numberAndQuantity/sub3',
        'JH/8thGrader/math/numberAndQuantity/sub4',
        'JH/8thGrader/math/numberAndQuantity/sub5',
        'JH/8thGrader/math/numberAndQuantity/sub6',//39
        'JH/8thGrader/math/algebra/sub1',
        'JH/8thGrader/math/algebra/sub2',
        'JH/8thGrader/math/algebra/sub3',
        'JH/8thGrader/math/algebra/sub4',
        'JH/8thGrader/math/algebra/sub5',
        'JH/8thGrader/math/algebra/sub6',
        'JH/8thGrader/math/algebra/sub7',
        'JH/8thGrader/math/algebra/sub8',
        'JH/8thGrader/math/algebra/sub9',
        'JH/8thGrader/math/algebra/sub10',
        'JH/8thGrader/math/algebra/sub11',//50
        'JH/8thGrader/math/geometry/sub1',
        'JH/8thGrader/math/geometry/sub2',
        'JH/8thGrader/math/geometry/sub3',
        'JH/8thGrader/math/geometry/sub4',
        'JH/8thGrader/math/geometry/sub5',
        'JH/8thGrader/math/geometry/sub6',
        'JH/8thGrader/math/geometry/sub7',
        'JH/8thGrader/math/geometry/sub8',
        'JH/8thGrader/math/geometry/sub9',
        'JH/8thGrader/math/geometry/sub10',
        'JH/8thGrader/math/geometry/sub11',
        'JH/8thGrader/math/geometry/sub12',
        'JH/8thGrader/math/geometry/sub13',
        'JH/8thGrader/math/geometry/sub14',
        'JH/8thGrader/math/geometry/sub15',
        'JH/8thGrader/math/geometry/sub16',
        'JH/8thGrader/math/geometry/sub17',
        'JH/8thGrader/math/geometry/sub18',
        'JH/8thGrader/math/geometry/sub19',
        'JH/8thGrader/math/geometry/sub20', //70
        'JH/9thGrader/math/geometry/sub1',
        'JH/9thGrader/math/geometry/sub2',
        'JH/9thGrader/math/geometry/sub3',
        'JH/9thGrader/math/geometry/sub4',
        'JH/9thGrader/math/geometry/sub5',
        'JH/9thGrader/math/geometry/sub6',
        'JH/9thGrader/math/geometry/sub7',
        'JH/9thGrader/math/geometry/sub8',
        'JH/9thGrader/math/geometry/sub9',
        'JH/9thGrader/math/geometry/sub10',
        'JH/9thGrader/math/geometry/sub11',
        'JH/9thGrader/math/geometry/sub12',
        'JH/9thGrader/math/geometry/sub13',
        'JH/9thGrader/math/geometry/sub14',
        'JH/9thGrader/math/geometry/sub15',
        'JH/9thGrader/math/geometry/sub16',//86
        'JH/9thGrader/math/algebra/sub1',
        'JH/9thGrader/math/algebra/sub2',
        'JH/9thGrader/math/algebra/sub3',
        'JH/9thGrader/math/algebra/sub4',//90
        'JH/9thGrader/math/statistics/sub1',
        'JH/9thGrader/math/statistics/sub2',
        'JH/9thGrader/math/statistics/sub3',
        'JH/9thGrader/math/statistics/sub4',
        'JH/9thGrader/math/statistics/sub5'//95
    ];
    var offset = 1;
    for(var i = 0; i < 95; i++) //how many request
        (function (i) {
            ajaxRequest('GET', encodeURI('video?queryString=' + pathQuery[i]), {
                'Accept': 'application/json'
            }, null, function (status, headers, body) {
                var ans = eval('(' + body + ')');// success callback
                var counting = i + offset; // offset to avoid the item under constructing
                var last = $("#courseCnt" + counting);
                last.text(ans.length);
                last.parents().siblings('a').children('span').each(function(){// each parent's sibling span will add the value
                    var oldParentCntCount = parseInt($(this).text());
                    $(this).text(parseInt(oldParentCntCount + ans.length));
                });
            }, function (status, headers, body) { // error callback
                switch (status) {
                    case 404:
                    //status404Handler();
                    default:

                }
            }, null);
        }(i));


}
var getCourseFromServer = function (path) {
    var answer = document.getElementById('answer');
    var ansMsg = document.getElementById('ansMsg');
    videoPlayerFrameHide();
    console.log("getCourseFromServer has done videoPlayerFrameHide()");
    ansMsg.innerHTML="";
    $("#answer").show();
    $(".mainpage").hide();
    $("#loading").show();
    $("#firstUploadBtn").hide();
    ajaxRequest('GET', encodeURI('video?queryString=' + path), {
        'Accept': 'application/json'
    }, null, function (status, headers, body) {
        var videoList = document.getElementById('videoList');
        var ans = eval('(' + body + ')');// success callback
        if (ans.length == 0) {
            ansMsg.innerHTML ='<font size="15px" color="#ff7f50">還沒有影片!</font>';
            $("#firstUploadBtn").show();
            $("#loading").hide();
        }
        var data = new Array(ans.length);
        var htmlFrag;
        var i=0;
        for (j = 0; j < ans.length; j++) {
            data[j]=new Array(7);
            (function(j)    //closure
            {
                var ut = "https://www.googleapis.com/youtube/v3/videos?id="+
                    escapeHtml(unescape(ans[j].id)) +
                    "&key=AIzaSyBaDrIDUqfPRMaTl7X3bg1ekPLQAp9FhBI%20" +
                    "&part=snippet,statistics" +
                    "&fields=items(id,snippet,statistics)";
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
                            videoList.innerHTML = htmlFrag;
                            bindingWithPlayer(data[0][0],data[0][2],data[0][3],data[0][4],data[0][6]);
                            $("#loading").hide();
                        }
                        else{
                            ansMsg.innerHTML ='<font size="15px" color="#ff7f50">還沒有影片!</font>';
                            $("#firstUploadBtn").show();
                            $("#loading").hide();
                        }
                    }

                    $('.bxslider').bxSlider({
                        randomStart: true,
                        minSlides: 4,
                        maxSlides: 4,
                        slideWidth: 200,
                        captions:true,
                        slideMargin: 3

                    });
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
            //status404Handler();
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
        htmlFrag += '<ul class="bxslider">';   //original is div and also the end of the tag
        for (var i = 0; i < data.length; i++){

            if(data[i][3]!=-1){
                htmlFrag+=
                    $("<div />", {
                    }).append($("<li />",{}).append($("<img />", {
                            "class" : "v-img",
                            "src":    data[i][1],
                            "title":  data[i][4],
                            "onclick": "bindingWithPlayer('" +data[i][0]+ "','" +escape(data[i][2])+ "','" + escape(data[i][3])+"','"+escape(data[i][4])+"','"+escape(data[i][6]) +"');"
                        }))).html();
            }
            else{
                delVideo++;
            }
        }
//            htmlFrag += '</ol></div>';
        if(data.length-delVideo==0)
            htmlFrag=0;
        else
            htmlFrag += '</ul>';
        return htmlFrag;
    }
}


