function noteTable() {
        ajaxRequest('GET', encodeURI('note?videoId=' + 'ALL'), {
            'Accept': 'application/json'
        }, null, function (status, headers, body) {// success callback
            var answer = document.getElementById('answer');
            var ansMsg = document.getElementById('ansMsg');
            ansMsg.innerHTML = "";
            $(".mainpage").hide();
            $(".videoPlayerFrame").hide();
            $("#firstUploadBtn").hide();
            $("#answer").show();
            $("#loading").show();
            var ans = eval('(' + body + ')');
             if (ans.length == 0) {
                 $("#loading").hide();
                 ansMsg.innerHTML = '<div style="font-size:30px;color:#ff7f50; width: 200px;margin: 0 auto;">還沒有筆記!</div>';
            }
            else {
                var htmlFrag = "<table border='1px'>";
                htmlFrag += "<tr><td>title</td>";
                htmlFrag += "<td>video</td>";
                htmlFrag += "<td>contnet</td></tr>";
                for (var i = 0; i < ans.length; i++) {
                    htmlFrag += "<tr><td>";
                    htmlFrag += ans[i].title;
                    htmlFrag += "</td>"
                    htmlFrag += "<td>"
                    htmlFrag += "<img src='https://i1.ytimg.com/vi/"+ans[i].videoId+"/mqdefault.jpg' width='185px' height='100px' />";
                    htmlFrag += "</td>"
                    htmlFrag += "<td>"

                    //length >=20 show first 0~20 character
                    if (ans[i].content.length >= 20) {
                        htmlFrag += ans[i].content.substring(0, 20);
                        htmlFrag += "...";
                    }
                    else {
                        htmlFrag += ans[i].content;
                    }
                    htmlFrag += "</td>"
                    htmlFrag += "<td><input type='button' value='delete'></td>"
                    htmlFrag += "</tr>";
                }
                htmlFrag += "</table>";
                 $("#loading").hide();
                ansMsg.innerHTML = htmlFrag;

            }
        }, function (status, headers, body) { // error callback
            switch (status) {
                case 401:
                    alert("必須登入");
                default:
                // do nothing
            }
        }, null); // run callbacks in global scope
    }