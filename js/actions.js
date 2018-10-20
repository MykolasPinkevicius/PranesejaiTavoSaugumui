"use strict";
$(document).ready(function (){

    pageScroll();
    changeActiveMenuItem();
    aboutUs( aboutItems );

    $(window).scroll(function(){
        pageScroll();
        changeActiveMenuItem();
    });

    $('.hamburgerMenuIcon').click(function(){
        $('nav').toggleClass('expand-menu');
    });

    $(window).scroll(function() {
        parallax();
    })
    
    function parallax() {
    
        var wScroll = $(window).scrollTop();
    
        $('.parallax--bg').css('background-position', 'center '+(wScroll*0.85)+'px')
        
    }
    


});