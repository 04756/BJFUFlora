$(function(){
	// 搜索方式选择
	$(".search-type p").click(function(){
		$(".search-type p").removeClass("select");
		$(this).addClass("select");
	})	

	// 搜索方法
	$(".search-bar").click(function(){
        var url = "searchResult?type=" + ($(".select").text()=="普通搜索"?'commonSearch':'graphSearch') + "&keyWords=" + $("input[name='search-bar']").text();
		window.location.href = url;
	});

	if(window.location.href.search("keyWords") >= 0)
	    getResultData();

})

function getResultData() {
    var temp = {
    	type : getUrlParam("type"),
    	keyWords :getUrlParam("keyWords")
    }
    $.ajax({
        type : "POST",
        contentType : 'application/json;charset=UTF-8',
        url : 'search',
        data : JSON.stringify(temp),
        dataType : 'json',
        success : function(data){
            for ( i =0; i < data.length; ++i ){
                $("ul.result").append('<li><a href="'+ data[i].resultLink +'">'+ data[i].resultName +'</a></li>')
            }
        },
        error : function(){
            alert("error");
        }
    });
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}