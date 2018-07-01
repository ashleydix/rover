$(window).scroll(function() {
    if ($(document).scrollTop() > 50) {
        $('nav.navbar').addClass('shrink');
    } else {
        $('nav.navbar').removeClass('shrink');
    }
});