
        var checkLogin =function() {
            ajaxRequest('GET','login', {
                'Accept': 'application/json'
            }, null, function (status, headers, body) {
                if(body==null)
                    return;
                var s = JSON.parse(body);
                var userPic=document.getElementById("userPic");
                userPic.setAttribute("width","30px");
                userPic.setAttribute("height","30px");
                $("#userPic").attr("src", s.imageUrl);
                var name = document.getElementById("name");
                name.innerHTML = s.displayName;
                $("#login").hide();
                $("#logout").show();
            }, function (status, headers, body) { // error callback
//                switch (status) {
//                    case 404:
//                        status404Handler();
//                    default:
//                    // do nothing
//                }
                $("#userPicIcon").hide();
                $("#name").hide();
            }, null);
          }