$(function(){
    var interfa =$("fieldset");
    var listhtml="";
    interfa.each(function(index,obj){
        $(obj).attr("id","interface_"+index);
        listhtml+='<a href="'+"#interface_"+index+'">'+(index+1)+'.'+$("legend",$(obj)).html()+'</a>';
    });
    $("#list").html(listhtml);
});
var submitFileAct = function (obj) {
    var $btn =$(obj);
    var $table=$btn.parent().parent().parent();
    var url =$("td[name='url']",$table).html();
    var data ={};
    var $form =$("form",$table);
    var data = new FormData($form[0]);
    $.ajax({
        type: "post",
        dataType: "html",
        url: url,
        data: data,
        cache: false,
        processData: false,
        contentType: false,
        success: function (data) {
           $("textarea[name='resp']",$table).val(data);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert("请求错误:"+XMLHttpRequest.status);
        }
    });

}
var downFileAct = function (obj) {
    var $btn =$(obj);
    var $table=$btn.parent().parent().parent();
    var url =$("td[name='url']",$table).html();
    var data ={};
    var $form =$("input[type='text']",$table);
    url+="?1=1"
    $form.each(function(index,obj){
        var $field =$(obj);
        url+="&"+$field.attr("name")+"="+$field.val();
    });
    window.open(url);

}
var submitAct = function (obj) {
    var $btn =$(obj);
    var $table=$btn.parent().parent().parent();
    var url =$("td[name='url']",$table).html();
    var type =$("td[name='url']",$table).attr("type");
    var data ={};
    var $form =$("input[type='text']",$table);
    $form.each(function(index,obj){
        var $field =$(obj);
        data[$field.attr("name")]=$field.val();
    });
    $.ajax({
        type: type,
        dataType: "html",
        url: url,
        data: data,
        success: function (data) {
            $("textarea[name='resp']",$table).val(formatJson(data));
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert("请求错误:"+XMLHttpRequest.status);
        }
    });

}
var submitHtmlAct = function (obj) {
    var $btn =$(obj);
    var $table=$btn.parent().parent().parent();
    var url =$("td[name='url']",$table).html();
    var data ={};
    var $form =$("input[type='text']",$table);
    $form.each(function(index,obj){
        var $field =$(obj);
        data[$field.attr("name")]=$field.val();
    });
    $.ajax({
        type: "post",
        dataType: "html",
        url: url,
        data: data,
        success: function (data) {
            $("textarea[name='resp']",$table).val(data);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert("请求错误:"+XMLHttpRequest.status);
        }
    });

}
var formatJson = function(json, options) {
    var reg = null,
        formatted = '',
        pad = 0,
        PADDING = '    '; // one can also use '\t' or a different number of spaces

    // optional settings
    options = options || {};
    // remove newline where '{' or '[' follows ':'
    options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true : false;
    // use a space after a colon
    options.spaceAfterColon = (options.spaceAfterColon === false) ? false : true;

    // begin formatting...
    if (typeof json !== 'string') {
        // make sure we start with the JSON as a string
        json = JSON.stringify(json);
    } else {
        // is already a string, so parse and re-stringify in order to remove extra whitespace
        json = JSON.parse(json);
        json = JSON.stringify(json);
    }

    // add newline before and after curly braces
    reg = /([\{\}])/g;
    json = json.replace(reg, '\r\n$1\r\n');

    // add newline before and after square brackets
    reg = /([\[\]])/g;
    json = json.replace(reg, '\r\n$1\r\n');

    // add newline after comma
    reg = /(\,)/g;
    json = json.replace(reg, '$1\r\n');

    // remove multiple newlines
    reg = /(\r\n\r\n)/g;
    json = json.replace(reg, '\r\n');

    // remove newlines before commas
    reg = /\r\n\,/g;
    json = json.replace(reg, ',');

    // optional formatting...
    if (!options.newlineAfterColonIfBeforeBraceOrBracket) {
        reg = /\:\r\n\{/g;
        json = json.replace(reg, ':{');
        reg = /\:\r\n\[/g;
        json = json.replace(reg, ':[');
    }
    if (options.spaceAfterColon) {
        reg = /\:/g;
        json = json.replace(reg, ':');
    }

    $.each(json.split('\r\n'), function(index, node) {
        var i = 0,
            indent = 0,
            padding = '';

        if (node.match(/\{$/) || node.match(/\[$/)) {
            indent = 1;
        } else if (node.match(/\}/) || node.match(/\]/)) {
            if (pad !== 0) {
                pad -= 1;
            }
        } else {
            indent = 0;
        }

        for (i = 0; i < pad; i++) {
            padding += PADDING;
        }

        formatted += padding + node + '\r\n';
        pad += indent;
    });

    return formatted;
};