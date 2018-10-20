"use strict";

function pageScroll() {
    var top = $(window).scrollTop();
    if ( top > 80 ) {
        $('nav').addClass('scroll');
    } else {        
        $('nav').removeClass('scroll');
        console.log(top)
    }return;
}    
   

//services 
function aboutUs ( aboutItems ) {
    var items = aboutItems.length,
        HTML           = '';
    if (items > 0) {
        for (var e=0; e<items; e++) {
            HTML += '<div class="aboutItem">\
                        <i class="fa '+aboutItems[e].icon+'"></i>\
                        <h3>'+aboutItems[e].title+'</h3>\
                        <p>'+aboutItems[e].description+'</p>\
                    </div>'  
        }
        $('.about').html(HTML);
    }
    return;
}

function changeActiveMenuItem() {
    var top              = $(window).scrollTop() + 140,
        anchorLinkAmount = $('.hamburgerMenu > a').length,
        anchorLinkIdText = '';

    for ( var n=0; n<anchorLinkAmount; n++ ) {
        anchorLinkIdText = $('.hamburgerMenu > a').eq(n).attr('href');  
        
        if ( $(anchorLinkIdText).position().top <= top ) {
            $('.hamburgerMenu > a').removeClass('active');
            $('.hamburgerMenu > a').eq(n).addClass('active');
        }
    }
    return;
}