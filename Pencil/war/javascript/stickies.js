


function sendNote(videoId,title,content,left,top) {
    ajaxRequest('GET','login', {
        'Accept': 'application/json'
    }, null, function (status, headers, body) {
        var s = JSON.parse(body);
        var id=s.id;
        ajaxRequest('POST', encodeURI('note'), {
            'Content-Type': 'application/json;charset=UTF-8'
        }, '{' +
            '"key":"' + "key" + '"' + ',' +
            '"id":"' + id + '"' + ',' +
            '"videoId":"' + videoId + '"' +',' +
            '"title":'   + JSON.stringify(title) + ','+
            '"content":' + JSON.stringify(content) + ',' +
            '"left":' + JSON.stringify(left)+ ',' +
            '"top":'  + JSON.stringify(top) +
            '}', function (status, headers, body) { // success
            // callback
        }, function (status, headers, body) { // error callback
            alert('error: ' + status);
        }, null); // run callbacks in global scope
    }, function (status, headers, body) { // error callback
        switch (status) {
            case 406:
                //notLoginHandling();
            default:
            // do nothing
        }
    }, null);
}
function notLoginHandling(){
//        document.location.href="https://accounts.google.com/o/oauth2/auth?"+
//                "client_id=511406630946.apps.googleusercontent.com"+
//                "&response_type=code&scope=https://www.googleapis.com/auth/plus.login"+
//                "&redirect_uri=http://localhost:8888/oauth2callback"+
//                "&state=/profile";
//        getNote();
    alert("請先登入");
}
var STICKIES = (function () {
    var initStickies = function initStickies() {
            initStickies = null;
        },
        openStickies = function openStickies() {
            initStickies && initStickies();
            ajaxRequest('GET', encodeURI('note?videoId='+ videoId), {
                'Accept': 'application/json'
            }, null, function (status, headers, body) {// success callback
                var ans = eval('(' + body + ')');

                for(var i = 0; i < ans.length; i++){
                    try{
                        createSticky(ans[i]);
                    }catch(e){
                        alert("ERROR ON"+ans.length);
                    }
                }
            }, function (status, headers, body) { // error callback
                switch (status) {
                    case 401:
                        //notLoginHandling();
                    default:
                    // do nothing
                }
            }, null); // run callbacks in global scope

        },
        createSticky = function createSticky(data) { // when users enter a post we have to create a empty sticky or read stickies from server
            data = data || {
                key : +new Date(),    // if empty
                videoId:  videoId,
                top : "300px",
                left : "40px",
                content : "你可以在這打字",
                title: "New Title"
            }
            return $("<div />", {
                "class" : "sticky",   //alan
                'id' : data.key
            })
                .prepend($("<div />", { "class" : "sticky-header"} )
                    .append($("<span />", {
                        "class" : "sticky-status",
                        click : saveSticky
                    }))
//                    .append("<input type='text' class='sticky-title' placeholder='新增標題'/>")
                    .append($("<input class='sticky-title' />")
                        .attr({type:'text',value:data.title,placeholder:'新增一個標題'})
                    )
                    .append($("<span />", {
                        "class" : "close-sticky",
                        text : "刪除筆記",
                        click : function () {
                            deleteSticky($(this).parents(".sticky").attr("id"));
                        }
                    }))
                )
                .append($("<div />", {  //span alan
                    html : data.content,
                    contentEditable : true,
                    "class" : "sticky-content",
                    keypress : markUnsaved
                }))
                .draggable({
                    handle : "div.sticky-header",
                    stack : ".sticky", //use
                    start : markUnsaved,
                    stop  : saveSticky
                })
                .css({
                    position: "absolute",
                    "top" : data.top,
                    "left": data.left
                })
                .focusout(saveSticky)
                .appendTo(document.body);
        },
        deleteSticky = function deleteSticky(id) {	// Need to modify
            localStorage.removeItem("sticky-" + id);
            $("#" + id).fadeOut(200, function () { $(this).remove(); });
        },
        saveSticky = function saveSticky() { // Need to modify: send sticky to server
            var that = $(this),  sticky = (that.hasClass("sticky-status") || that.hasClass("sticky-content")) ? that.parents('div.sticky'): that,
                obj = {
//                    id  : sticky.attr("id"),
//                    userId: '6677777',
//                    videoId:videoId,
//                    top : sticky.css("top"),
//                    left: sticky.css("left"),
//                    content: sticky.children(".sticky-content").html(),
//                    title: sticky.find(".sticky-title").val()
                }
//            alert(JSON.stringify(sticky.children(".sticky-content").html()));
            sendNote(videoId,sticky.find(".sticky-title").val(), sticky.children(".sticky-content").html(),sticky.css("left"),sticky.css("top"));
            sticky.find(".sticky-status").text("已存檔");
        },
        markUnsaved = function markUnsaved() {
            var that = $(this), sticky = that.hasClass("sticky-content") ? that.parents("div.sticky") : that;
            sticky.find(".sticky-status").text("未存檔");
        },
        closeStickies = function closeStickies() {
            $(".sticky").fadeOut(200,function(){$(this).close();});
        }
    return {
        open   : openStickies,
        init   : initStickies,
        "new"  : createSticky,
        remove : deleteSticky,
        close  : closeStickies
    };
}());

