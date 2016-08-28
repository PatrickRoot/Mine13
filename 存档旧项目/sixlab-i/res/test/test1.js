define("test1", [], function (require, exports) {
    Array.prototype.checkMy = function () {
        alert("111");
    }
    a = [11, 22];
    a.checkMy();
});