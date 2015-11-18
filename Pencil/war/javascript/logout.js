 ajaxRequest('GET', 'logout', {
        }, null, function (status, headers, body) {  //success
            window.location.reload();
        }, function (status, headers, body) { // error callback
            switch (status) {
                case 406:
                    alert("發生錯誤");
                default:
                // do nothing
            }
        }, null);