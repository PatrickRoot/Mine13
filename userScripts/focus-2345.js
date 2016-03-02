// ==UserScript==
// @name         Focus
// @namespace    http://sixlab.cn/
// @version      0.0.3
// @description  去掉网页上影响关注的内容!
// @author       loki/六楼的雨
// @match        http://www.2345.com/*
// @grant        none
// @require      http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js
// ==/UserScript==
/* jshint -W097 */

// Your code here...

var className = "sixlab-focus";

//2345
$(".hotw_line1").addClass(className).hide();
$("#content").addClass(className).hide();
$("#map_game").addClass(className).hide();
$("#map_buy").addClass(className).hide();
$("#sidenav").addClass(className).hide();
$("#map_video").addClass(className).hide();
$("#map_these").addClass(className).hide();
$("#map_life").addClass(className).hide();
$("#J_category").addClass(className).hide();
$("#footer").addClass(className).hide();
$(".top_news").addClass(className).hide();
