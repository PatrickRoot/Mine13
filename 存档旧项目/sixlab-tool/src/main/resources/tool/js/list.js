$(document).ready(function () {

    function setSectionSize() {
        var height = $(window).height();
        var groupHeight = height - 100;
        $(".s-container").height(groupHeight);
        $(".s-group").height(groupHeight);
        var groupContainerHeight = groupHeight - 80;
        $(".s-group-container").height(groupContainerHeight);
    }

    function sortOrder() {
        var listName = $("#listName");
        $.ajax({
            url: "/list/sort/" + listName,
            type: "post",
            data: {
                listName: listName
            },
            success: function (data) {
                if (data.success) {
                    location.reload();
                }
            }
        });
    }

    setSectionSize();

    $(window).resize(function () {
        setSectionSize();
    });

    $(document).off("click", "#addGroupBtn");
    $(document).on("click", "#addGroupBtn", function () {
        var input = $("#addGroupName").val();
        var listName = $("#listName");
        $.ajax({
            url: "/list/edit/group/" + input,
            type: "post",
            data: {
                listName: listName
            },
            success: function (data) {
                if(data.success){
                    location.reload();
                }
            }
        });
    });

    $(document).off("click", ".delGroupBtn");
    $(document).on("click", ".delGroupBtn", function () {
        var groupId = $(this).attr("data-group-id");
        $.ajax({
            url: "/list/edit/group/" + groupId,
            type: "post",
            success: function (data) {
                if (data.success) {
                    location.reload();
                }
            }
        });
    });

    $(document).off("click", ".addItemBtn");
    $(document).on("click", ".addItemBtn", function () {
        var groupId = $(this).attr("data-group-id");
        var input = $("#addItemText"+groupId).val();
        $.ajax({
            url: "/list/edit/item/" + input,
            type: "post",
            data: {
                groupId: groupId
            },
            success: function (data) {
                if (data.success) {
                    location.reload();
                }
            }
        });
    });

    $(document).off("click", ".delItemBtn");
    $(document).on("click", ".delItemBtn", function () {
        var itemId = $(this).attr("data-item-id");
        $.ajax({
            url: "/list/del/item/" + itemId,
            type: "post",
            success: function (data) {
                if (data.success) {
                    location.reload();
                }
            }
        });
    });

    $(".s-container").each(function () {
        Sortable.create(this, {
            group: "group",
            draggable: '.s-group',
            handle: '.s-group-title',
            onUpdate: function (evt) {
                sortOrder();
            }
        });
    });

    $(".s-group-container").each(function () {
        Sortable.create(this, {
            group: "item",
            onUpdate: function (evt) {
                sortOrder();
            }
        });
    });

});