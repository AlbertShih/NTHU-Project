// JavaScript Document

 $(document).ready(function() {
         	$("#booking-button").mouseover(
				function(){
    				$("#eclr-clickcard-event").css({  
						  display: "block" 
					});
					$("#clickcard-event").css({
						  display: "none" 
					});
					$("#eclr-clickcard-event3").css({  
						  display: "none" 
					}); 
				}
			);
             $("#eclr-clickcard-event").mouseleave(
                 function(){
                     $("#eclr-clickcard-event").css({
                         display: "none"
                     });
                     $("#clickcard-event").css({
                         display: "none"
                     });
                     $("#clickcard-event1").css({
                         display: "none"
                     });
                 }
             );

            $("#like-video").mouseover(
				function(){
    				$("#clickcard-event").css({
						display: "block"
					});
                    $("#clickcard-event1").css({
                        display: "none"
                    });
				}
			);
             $("#like-video").mouseleave(
                 function(){
                     $("#clickcard-event").css({
                         display: "none"
                     });
                     $("#clickcard-event1").css({
                         display: "none"
                     });
                 }
             );
             $("#total-watched").mouseover(
                 function(){
                     $("#clickcard-event1").css({
                         display: "block"
                     });
                     $("#clickcard-event").css({
                         display: "none"
                     });
                 }
             );
             $("#total-watched").mouseleave(
                 function(){
                     $("#clickcard-event1").css({
                         display: "none"
                     });
                     $("#clickcard-event").css({
                         display: "none"
                     });
                 }
             );
			 
 });

function booking(){
    if($("#booking-button").attr("booking-state") == "false"){
        $("#booking-button").css({
            color : "#a9382e",
            background: "#fff",
            border: "1px solid #a9382e"
        });
        $("#booking-button").attr("booking-state","true");
        $("#uix-booking-icon").attr("src","./image/main/booked_picture.png");

    }
    else{
        $('#booking-button').css('backgroundImage', "linear-gradient(to bottom,#CC6666,#a9382e)");
        $("#booking-button").css({
            color : "#fff",
            border: "1px solid #880904"
        });
        $("#booking-button").attr("booking-state","false");
        $("#uix-booking-icon").attr("src","./image/main/booking_picture.png");
    }
}

function likeEvent(){
    if($("#like-button").attr("like-state") == "false"){
        $("#like-button").css({
            color : "#a9382e",
            background: "#fff",
            border: "1px solid #a9382e"
        });
        $("#dislike-button").css({
            color : "#fff",
            border: "1px solid #880904"
        });
        $('#dislike-button').css('backgroundImage', "linear-gradient(to bottom,#CC6666,#a9382e)");
        $("#like-button").attr("like-state","true");
        $("#dislike-button").attr("dislike-state","false");
        $("#uix-like-button").attr("src","./image/main/ok-like-button.png");
        $("#uix-dislike-button").attr("src","./image/main/oko-dislike-button.png");

    }
    else{
        $('#like-button').css('backgroundImage', "linear-gradient(to bottom,#CC6666,#a9382e)");
        $("#like-button").css({
            color : "#fff",
            border: "1px solid #880904"
        });
        $("#like-button").attr("like-state","false");
        $("#uix-like-button").attr("src","./image/main/oko-like-button.png");

    }
}

function dislikeEvent(){
    if($("#dislike-button").attr("dislike-state") == "false"){
        $("#dislike-button").css({
            color : "#a9382e",
            background: "#fff",
            border: "1px solid #a9382e"
        });
        $("#like-button").css({
            color : "#fff",
            border: "1px solid #880904"
        });
        $('#like-button').css('backgroundImage', "linear-gradient(to bottom,#CC6666,#a9382e)");
        $("#dislike-button").attr("dislike-state","true");
        $("#like-button").attr("like-state","false");
        $("#uix-dislike-button").attr("src","./image/main/ok-dislike-button.png");
        $("#uix-like-button").attr("src","./image/main/oko-like-button.png");

    }
    else{
        $('#dislike-button').css('backgroundImage', "linear-gradient(to bottom,#CC6666,#a9382e)");
        $("#dislike-button").css({
            color : "#fff",
            border: "1px solid #880904"
        });
        $("#dislike-button").attr("dislike-state","false");
        $("#uix-dislike-button").attr("src","./image/main/oko-dislike-button.png");
    }
}

 