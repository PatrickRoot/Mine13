// ==UserScript==
// @name         Focus
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @match        http://www.2345.com/*
// @grant        none
// @require      http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js
// ==/UserScript==
/* jshint -W097 */

// Your code here...

var className = "sixlab-focus";

//2345
var host = window.location.host;
if ('www.2345.com' === host) {
    $(".hotw_line1").addClass(html).hide();
    $("#content").addClass(html).hide();
    $("#map_game").addClass(html).hide();
    $("#map_buy").addClass(html).hide();
    $("#sidenav").addClass(html).hide();
    $("#map_video").addClass(html).hide();
    $("#map_these").addClass(html).hide();
    $("#map_life").addClass(html).hide();
    $("#J_category").addClass(html).hide();
    $("#footer").addClass(html).hide();
    $(".top_news").addClass(html).hide();
}