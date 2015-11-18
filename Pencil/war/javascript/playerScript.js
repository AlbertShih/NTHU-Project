var tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
var player;

function onYouTubeIframeAPIReady() {
    player = new YT.Player('player', {
        height: '450',
        width: '800',
        events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange
        },
        playerVars: {
            'rel': 0
        }
    });
    videoPlayerFrameShow = function videoPlayerFrameShow(str,title,viewcount,description,author){
        //No use to load API again so overwrite the function
        //document.title="►|PENCIL| 給中小學生邊教邊學的免費平台:)";
        console.log("vpfShow ver2");
        console.log(title+str);
        videoId = str;
        DISQUS.reset({      //DISQUS is defined
            reload: true,
            config: function () {
                this.page.identifier = str;
                this.page.title = title;
                this.page.url = "http://education-star.appspot.com/#!" + str;
            }
        });
        $(".video-title").empty().append($("<h1 />", {
            text : title
        })).prepend($("<img />", {
                "class" : "showInfo",  //alan
                "src": './image/main/lctrlPen.png'
            })
                .click(function(e){
                    alert("資料整理中，在此將會新增課程參考資料！");
                })
         );
        $("#penciler").empty().append(author);
        $(".video-subtitle").empty().append(selfTitle);
        $("#total-watched").empty().append(viewcount);
        try{
            STICKIES.open();
        }catch(e){
            //doNothing
        }
        player.loadVideoById(str,0,'large');
        $(".videoPlayerFrame").show();

    }
}
var videoDuration, durationQuarter = 0;
var currentDurationRate = 0.1;
function onPlayerReady(event) {
    event.target.loadVideoById(hashCode,0,'large');
    event.target.stopVideo(); // Here call the loadVideoById so that player can load before play correctly.
    videoDuration = player.getDuration();
}
function onPlayerStateChange(event) {
//    if (player.getPlayerState() == 2 || player.getPlayerState() == 0) { // when video is paused or ended record
//        if (durationQuarter < currentDurationRate) {						//the duration rate in durationQuarter
//            durationQuarter = currentDurationRate; 						//player.getCurrentTime() / videoDuration;
//        }
//    }
//    // the following prototyping code change the picture of the left control depending on the duration rate.
//    // The future will query the server to get the user's learning progress table and show the picture.
//    if (durationQuarter > 0.9) {
////							   		progress.value = 100;
//        document.JH_math1_1_1.src = "./image/main/progSet/prog4.png";
//    }
//    else if (durationQuarter > 0.75) {
////						      		progress.value = 75;
//        document.JH_math1_1_1.src = "./image/main/progSet/prog3.png";
//    }
//    else if (durationQuarter > 0.5) {
////						      		progress.value = 50;
//        document.JH_math1_1_1.src = "./image/main/progSet/prog2.png";
//    }
//    else if (durationQuarter > 0.25) {
////							  		progress.value = 25;
//        document.JH_math1_1_1.src = "./image/main/progSet/prog1.png";
//    }
//    else {
////						      		progress.value = 0;
//        document.JH_math1_1_1.src = "./image/main/progSet/prog0.png";
//    }
//    currentDurationRate = player.getCurrentTime() / videoDuration;
}
