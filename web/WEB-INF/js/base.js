$(function(){
	// 搜索方式选择
	$(".search-type p").click(function(){
		$(".search-type p").removeClass("select");
		$(this).addClass("select");
	})	

	// 搜索方法
	$(".search-bar").click(function(){
		var temp = {
			type : $(".select").text(),
			keyWords : $("input[name='search-bar']").text()
		}

		$.ajax({
            type : "POST",
            contentType : 'application/json;charset=UTF-8',
            url : '',
            data : JSON.stringify(temp),
            dataType : 'json',
            success : function(data){
                
            },
            error : function(){
                alert("error");
            }
        });
	});

})