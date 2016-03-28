// ==UserScript==
// @name         Focus on Baidu
// @namespace    http://sixlab.cn/
// @version      0.0.7
// @description  去掉网页上影响关注的内容!
// @author       loki/六楼的雨
// @include      http://www.baidu.com/s?*
// @include      https://www.baidu.com/s?*
// @grant        none
// @require      https://raw.githubusercontent.com/nianqinianyi/sixlab/master/userScripts/jquery.min.js
// ==/UserScript==
/* jshint -W097 */

// Your code here...
var className = "sixlab-focus";

$j("a[href='http://e.baidu.com/?id=1']").parent().parent().parent().addClass(className).hide();