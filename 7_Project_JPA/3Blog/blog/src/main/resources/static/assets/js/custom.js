jQuery(document).ready(function ($) {
    "use strict";

    $(window).scroll(function () {
        let scroll = $(window).scrollTop();
        let box = $('.header-text').height();
        let header = $('header').height();

        if (scroll >= box - header) {
            $("header").addClass("background-header");
        } else {
            $("header").removeClass("background-header");
        }
    });

    // Active menu
    const menuEls = document.querySelectorAll("#navbarResponsive li");
    const activeMenu = () => {
        let path = window.location.pathname;

        Array.from(menuEls).map(e => e.classList.remove("active"));
        menuEls.forEach(e => {
            if(e.firstElementChild.getAttribute("href") === path) {
                e.classList.add("active")
            }
        })
    }

    activeMenu();

});
