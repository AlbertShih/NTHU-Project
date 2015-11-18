
    var login=function(){
        ajaxRequest('GET','login', {
            'Accept': 'application/json'
        }, null, function (status, headers, body) {
            var s = JSON.parse(body);
            var userPic=document.getElementById("userPic");
            userPic.setAttribute("width","30px");
            userPic.setAttribute("height","30px");
            $("#userPicIcon").show();
            $("#name").show();
            $("#userPic").attr("src", s.imageUrl);
            alert(s.imageUrl);
            var name = document.getElementById("name");
             name.innerHTML = s.displayName;
             $("#login").hide();
             $("#logout").show();

        }, function (status, headers, body) { // error callback
            //alert("123");
            //login();
                switch (status) {
                    case 406:
                            //delay function
                        setTimeout("login()" , 2000);
                    default:
                    // do nothing
                }
        }, null);
    }