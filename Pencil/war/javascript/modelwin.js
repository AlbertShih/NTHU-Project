$(function() {
    "use strict";

    /*
     ----------------------------
     Tooltip
     ----------------------------
     */
    var $window = $(window);

    if ( $window.width() < 360 ) {
        $(document.getElementById('tooltip')).css('right', 0);
    }

    if( navigator.appVersion.indexOf("MSIE 8.") == -1 ) {
        $(document.getElementById('tooltip')).animate({
            top:    '-45px',
            opacity: 1
        }, 600);
    }

    /*
     ----------------------------
     Show code without jQuery
     ----------------------------
     */
    $(document.getElementById('actiontutorial')).on('click', function () {
        $(document.getElementById('tutorial')).slideToggle();
    });

    /*
     ----------------------------
     Custom sidebar
     ----------------------------
     */
    var $sidebar = $(document.getElementById('sidebar')),
        sidebar_width = $sidebar.width();

    if ( $window.width() > 1170 && $sidebar.height() < $window.height() ) {
        $window.on('scroll', function () {
            var $this = $(this);
            if( $this.scrollTop() > 155 && $this.width() > 980 ) {
                $(document.getElementById('tooltip')).hide();
                $sidebar.css({
                    position:   'fixed',
                    top:        '20px',
                    width:      sidebar_width + 'px'
                });
            } else if ( $this.scrollTop() <= 155 ) {
                $(document.getElementById('tooltip')).show();
                $sidebar.removeAttr('style');
            }
        });
    }




    /*
     ----------------------------
     Blur
     ----------------------------
     */
    $('#blur').on('click', function () {
        $.fn.custombox( this, {
            effect: 'blur'
        });
        return false;
    });
	$('#blur1').on('click', function () {
        $.fn.custombox( this, {
            effect: 'blur'
        });
        return false;
    });
 

});