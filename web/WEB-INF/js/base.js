$(function(){
	// 搜索方式选择
	$(".search-type p").click(function(){
		$(".search-type p").removeClass("select");
		$(this).addClass("select");
	})	

	// 搜索方法
	$(".search-bar").click(function(){
        var url = "result.htm?type=" + $(".select").text() + "&keyWords=" + ($("input[name='search-bar']").text()=="普通搜索")?CommonSearch:GraphSearch;
		window.location.href = url;
	});

	if(window.location.href.search("result"))
	    getResultData();

})

function getResultData() {
    var temp = {
    	type : $.query.get("type"),
    	keyWords :$.query.get("keyWords")
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