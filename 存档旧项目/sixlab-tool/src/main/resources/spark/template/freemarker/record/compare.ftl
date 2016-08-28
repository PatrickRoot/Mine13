<!DOCTYPE html>
<html>
<head>
    <title>sixlab tool | Record</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="/thr/css/pure-min.css">
    <link rel="stylesheet" href="/thr/css/forms-min.css">
    <link rel="stylesheet" href="/thr/css/grids-responsive-min.css">
</head>
<body>

<div style="margin: 50px;">
    <form class="pure-form pure-form-stacked" action="compare" method="post">
        <fieldset>
            <legend>111</legend>
            <div class="pure-g">
            <#list times as item>
                <div class="pure-u-1-2 pure-u-sm-1-3 pure-u-md-1-4 pure-u-lg-1-5 pure-u-xl-1-6">
                    <label for="terms_${item_index}" class="pure-checkbox">
                        <input id="terms_${item_index}" type="checkbox" name="selectTime"
                               class="time-check"
                               value="${item.id}">${item.recordDate?string("yyyy-MM-dd HH:mm:ss")}
                    </label>
                </div>
            </#list>
            </div>
            <button type="button" class="pure-button pure-button-primary">比较</button>
        </fieldset>
    </form>
</div>

<script src="/thr/js/jquery.min.js" type="text/javascript"></script>
<script src="/tool/js/list.js" type="text/javascript"></script>
</body>
</html>