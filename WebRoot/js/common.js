$.fn.select2Remote = function(options)  
{  
    var opts = $.extend({},$.fn.select2Remote.defaults, options);  
    this.select2({
        allowClear:true,  
        placeholder: opts.blankMsg,  
        minimumInputLength:opts.minLength,  
        id:function(obj){return obj[opts.valueField]},  
        ajax: {  
            url: opts.url,  
            dataType: 'json',  
            quietMillis: opts.delay ,  
            data: function (term, page) {return {q: term};},  
            results: function (data, page) { return {results: data};}  
        },  
        initSelection: function(element, callback) {  
            var id=$(element).val();  
            if (id!=="") {  
                $.ajax(opts.initUrl, {  
                    data: {  
                        q:id  
                    },  
                    dataType: "json"  
                }).done(function(data) { callback(data); });  
            }  
        },  
        formatResult: function(obj){return obj[opts.textField]},  
        formatSelection:function(obj){return obj[opts.textField]},  
        dropdownCssClass: "bigdrop",  
        escapeMarkup: function (m) { return m; },
    });  
}  

$.fn.select2Remote.defaults = {  
    blankMsg: "请输入",  
    minLength: 1,  
    url:'',  
    initUrl:'',  
    delay:1000,  
    valueField:'id',  
    textField:'text'  
}  

var ChinaInfo = {
	 formatNoMatches: function () { return "没有找到匹配项"; },
    formatInputTooShort: function (input, min) { var n = min - input.length; return "请再输入" + n + "个字符";},
    formatInputTooLong: function (input, max) { var n = input.length - max; return "请删掉" + n + "个字符";},
    formatSelectionTooBig: function (limit) { return "你只能选择最多" + limit + "项"; },
    formatLoadMore: function (pageNumber) { return "加载结果中…"; },
    formatSearching: function () { return "搜索中…"; }  
}
$.fn.select2.defaults = $.extend($.fn.select2.defaults, ChinaInfo);

Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 

var chinaLanage = {
	"sSearch": "搜索",
	"sLengthMenu": "_MENU_",
	"sZeroRecords": "抱歉， 没有找到",
	"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
	"sInfoEmpty": "",
	"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
	"oPaginate": {  
	    "sFirst": "首页",  
	    "sPrevious": "前一页",
	    "sNext": "后一页",  
	    "sLast": "尾页"  
	}, 
	"sZeroRecords": "没有检索到数据",  
	"sProcessing": "加载中...",
	"sEmptyTable": "无数据"
}
$.fn.dataTable.defaults.oLanguage = $.extend($.fn.dataTable.defaults.oLanguage,chinaLanage);
