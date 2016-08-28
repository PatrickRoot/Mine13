$(document).ready(function(){

    $(document).off("mouseover", ".block1");
    $(document).on("mouseover", ".block1",function(){
        var $this = $(this);
        if(1!== $this.attr("data-6-trans")){
            $this.attr("data-6-trans", "1");
            var rotate = 0;
            var block1Id = setInterval(function () {
                console.log(rotate);
                rotate += 5;
                $($this).css({'transform': 'rotateY(' + rotate + 'deg)'});
                if (rotate == 135) {
                    clearInterval(block1Id);
                    block1Id = null;
                    $this.attr("data-6-trans", "0");
                }
            }, 30);
            $this.one("mouseout",function () {
                if (null != block1Id) {
                    clearInterval(block1Id);
                }
                $(this).css({'transform': 'rotateY(0deg)'});
            });
        }
    });

});