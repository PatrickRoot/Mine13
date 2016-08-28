<!DOCTYPE html>
<html>
<head>
    <title>sixlab tool | List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="/thr/css/semantic.min.css">
    <link rel="stylesheet" href="/tool/css/list.css">
</head>
<body>

<input name="${listName}" type="hidden" id="listName" />

<div class="s-header">
    <input id="addGroupName" type="text">
    <button id="addGroupBtn" type="button">加</button>
</div>

<div class="s-container">

    <#list models as model>
        <div class="s-group">
            <div class="s-group-title">
                Group A
                <button class="delGroupBtn" type="button" data-group-id="${model.id}"> X </button>
            </div>
            <div class="s-group-container">
                <#list model.toolListItemList as item>
                    <div class="s-group-block">
                        ${item.itemName}
                        <button class="delItemBtn" type="button" data-item-id="${item.id}"> X </button>
                    </div>
                </#list>
            </div>
            <div class="s-group-bottom">
                <input id="addItemText${model.id}" type="text">
                <button class="addItemBtn" type="button" data-group-id="${model.id}">加</button>
            </div>
        </div>
    </#list>

</div>

<div class="s-footer">

</div>

<script src="/thr/js/jquery.min.js" type="text/javascript"></script>
<script src="/thr/js/Sortable.min.js"></script>
<script src="/thr/js/semantic.min.js" type="text/javascript"></script>
<script src="/tool/js/list.js" type="text/javascript"></script>
</body>
</html>