$(document).ready(function () {

    function setSectionSize() {
        var height = $(window).height();
        var groupHeight = height - 100;
        $(".s-container").height(groupHeight);
        $(".s-group").height(groupHeight);
        var groupContainerHeight = groupHeight - 80;
        $(".s-group-container").height(groupContainerHeight);
    }

    setSectionSize();

    $(window).resize(function () {
        setSectionSize();
    });


    $(".s-container").each(function () {
        var group = Sortable.create(this, {
            group: "group",
            draggable: '.s-group',
            handle: '.s-group-title',
            onUpdate: function (evt) {
                var item = evt.item;
            }
        });
    });

    $(".s-group-container").each(function () {
        var group = Sortable.create(this, {
            group: "item",
            onUpdate: function (evt) {
                var item = evt.item;
            }
        });
    });

});