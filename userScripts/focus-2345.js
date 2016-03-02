// ==UserScript==
// @name         Focus
// @namespace    http://sixlab.cn/
// @version      0.0.6
// @description  去掉网页上影响关注的内容!
// @author       loki/六楼的雨
// @include      *
// @grant        none
// @require      https://raw.githubusercontent.com/nianqinianyi/sixlab/master/userScripts/jquery.min.js
// ==/UserScript==
/* jshint -W097 */

// Your code here...
var className = "sixlab-focus";

var host = window.location.host;

//2345
if ('www.2345.com' === host) {
    $j(".hotw_line1").addClass(className).hide();
    $j("#content").addClass(className).hide();
    $j("#map_game").addClass(className).hide();
    $j("#map_buy").addClass(className).hide();
    $j("#sidenav").addClass(className).hide();
    $j("#map_video").addClass(className).hide();
    $j("#map_these").addClass(className).hide();
    $j("#map_life").addClass(className).hide();
    $j("#J_category").addClass(className).hide();
    $j("#footer").addClass(className).hide();
    $j(".top_news").addClass(className).hide();
}