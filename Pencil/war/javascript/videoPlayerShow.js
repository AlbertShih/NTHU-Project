/*playScript the script is loaded when user press the youtube link*/
var hashCode = "";
var videoId =  "";
//$(".videoPlayerFrame").show()
var videoPlayerFrameShow = function videoPlayerFrameShow(str, title, viewcount, description,author) {
    videoId = hashCode = str;
    //Load the playerScript.js
    var s = document.createElement("script");
    s.type = "text/javascript";
    s.src = "./javascript/playerScript.js";
    $("#player").append(s);

    $(".video-title").empty().prepend($("<img />", {
            "class" : "showInfo",  //alan
            "src": './image/main/lctrlPen.png'
        })
            .click(function(e){
                alert("資料整理中，在此將會新增課程參考資料！");
            })
        ).append($("<h1 />", {
            text : title
        }));

    console.log(title);
    $("#total-watched").empty().append(viewcount);
    $("#penciler").empty().append(author);
    $(".video-subtitle").empty().append(selfTitle);
    try{
        STICKIES.open();
    }catch(e){
        //doNothing
    }
    DISQUS.reset({
        reload: true,
        config: function () {
            this.page.identifier = str;
            this.page.title = title;
            this.page.url = "http://education-star.appspot.com/#!" + str;
        }
    });

    $(".mainpage").hide();
    $(".videoPlayerFrame").show();


    console.log("vpfShow version1");
};

//$(".videoPlayerFrame").hide()
var videoPlayerFrameHide = function videoPlayerFrameHide(){
    console.log("vpfHide version2");
    try{
        player.stopVideo();
        STICKIES.close();
    }catch (e){
        console.log("stopVideo method in videoPlayerShow.js");
    }
    finally{
        $(".videoPlayerFrame").hide();
    }
}