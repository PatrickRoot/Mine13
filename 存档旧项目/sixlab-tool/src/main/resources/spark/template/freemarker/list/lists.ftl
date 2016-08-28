<!DOCTYPE html>
<html>
<head>
    <title>sixlab tool | List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="/thr/css/semantic.min.css">
    <link rel="stylesheet" href="/tool/css/lists.css">
</head>
<body>

<div>
    <#list models as model>
        <div>
            <a href="/list/edit/list/${model[0]}">${model[0]}</a> 共有${model[1]}组
            <button type="text" class="delListBtn" data-list-name="${model[0]}"> X </button>
        </div>
    </#list>

    <input type="text" id="listName">
    <button type="text" id="addListBtn">进入任务</button>
</div>

<script src="/thr/js/jquery.min.js" type="text/javascript"></script>
<script src="/thr/js/Sortable.min.js"></script>
<script src="/thr/js/semantic.min.js" type="text/javascript"></script>
<script src="/tool/js/lists.js" type="text/javascript"></script>
</body>
</html>