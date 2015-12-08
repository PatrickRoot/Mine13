$(document).ready(function () {
    var white = true;

    $(document).off("click", ".column");
    $(document).on("click", ".column", function () {
        if($(this).hasClass("white") || $(this).hasClass("black")){
            return;
        }
        if(white){
            $(this).addClass("white");
            $(this).removeClass("black");
            checkFive($(this),"white");
        }else{
            $(this).addClass("black");
            $(this).removeClass("white");
            checkFive($(this), "black");
        }
        white = !white;
    });

    function confirmSuccess(currClass) {
        alert(currClass + "胜利");
        $(".column").removeClass("black");
        $(".column").removeClass("white");
    }

    function checkFive(element, currClass) {
        var row = parseInt(element.attr("row"));
        var column = parseInt(element.attr("column"));
        if(checkLeft(row, column, currClass)){
            confirmSuccess(currClass);
            return ;
        }

        if (checkRight(row, column, currClass)) {
            confirmSuccess(currClass);
            return;
        }

        if (checkDown(row, column, currClass)) {
            confirmSuccess(currClass);
            return;
        }

        if (checkUp(row, column, currClass)) {
            confirmSuccess(currClass);
            return;
        }

        if (checkRightDown(row, column, currClass)) {
            confirmSuccess(currClass);
            return;
        }

        if (checkRightUp(row, column, currClass)) {
            confirmSuccess(currClass);
            return;
        }

        if (checkLeftDown(row, column, currClass)) {
            confirmSuccess(currClass);
            return;
        }

        if (checkLeftUp(row, column, currClass)) {
            confirmSuccess(currClass);
            return;
        }
        console.log("继续");
    }

    function checkLeftUp(row, column, currClass) {
        for (var i = 0; i < 5; i++) {
            var newRow = row - i;
            var newColumn = column - i;
            while (newRow < 1 || newRow > 25 || newColumn < 1 || newColumn > 25 || !$("div[column=" + newColumn + "][row=" + newRow + "]").hasClass(currClass)) {
                return false;
            }
        }
        return true;
    }

    function checkLeftDown(row, column, currClass) {
        for (var i = 0; i < 5; i++) {
            var newRow = row - i;
            var newColumn = column + i;
            while (newRow < 1 || newRow > 25 || newColumn < 1 || newColumn > 25 || !$("div[column=" + newColumn + "][row=" + newRow + "]").hasClass(currClass)) {
                return false;
            }
        }
        return true;
    }

    function checkRightUp(row, column, currClass) {
        for (var i = 0; i < 5; i++) {
            var newRow = row + i;
            var newColumn = column - i;
            while (newRow < 1 || newRow > 25 || newColumn < 1 || newColumn > 25 || !$("div[column=" + newColumn + "][row=" + newRow + "]").hasClass(currClass)) {
                return false;
            }
        }
        return true;
    }

    function checkRightDown(row, column, currClass) {
        for (var i = 0; i < 5; i++) {
            var newRow = row + i;
            var newColumn = column + i;
            while(newRow < 1 || newRow > 25 || newColumn < 1 || newColumn > 25 || !$("div[column=" + newColumn + "][row=" + newRow + "]").hasClass(currClass)){
                return false;
            }
        }
        return true;
    }

    function checkUp(row, column, currClass) {
        for (var i = column - 4; i <= column; i++) {
            while (i < 1 || i > 25 || !$("div[column=" + i + "][row=" + row + "]").hasClass(currClass)) {
                return false;
            }
        }
        return true;
    }

    function checkDown(row, column, currClass) {
        for (var i = column + 4; i >= column; i--) {
            while (i < 1 || i > 25 || !$("div[column=" + i + "][row=" + row + "]").hasClass(currClass)) {
                return false;
            }
        }
        return true;
    }

    function checkLeft(row, column, currClass){
        for (var i = row - 4; i <= row; i++) {
            while (i<1 || i>25 ||!$("div[column=" + column + "][row=" + i + "]").hasClass(currClass)) {
                return false;
            }
        }
        return true;
    }

    function checkRight(row, column, currClass) {
        for (var i = row + 4; i >= row; i--) {
            while (i < 1 || i > 25 || !$("div[column=" + column + "][row=" + i + "]").hasClass(currClass)) {
                return false;
            }
        }
        return true;
    }
});