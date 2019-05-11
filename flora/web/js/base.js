$(function(){
    $(".left-guid").slideDown(1000);
    $(".t").animate({marginLeft:'200px'},1000);

	// 搜索方式选择
	$(".search-type p").click(function(){
		$(".search-type p").removeClass("select");
		$(this).addClass("select");
	})

    $("#more").on("click", function () {
        searchAction(0);
    });

	if(window.location.href.search("keyWords") >= 0) {
        var temp = {
            type : getUrlParam("type"),
            keywords :getUrlParam("keyWords"),
            page : 0
        }
        getResultData(temp);
        // 搜索方法
        $(".search-bar").click(function(){
            searchAction(1);
        });

        $('input[name=\'search-bar\']').on('keypress',function(){
            var evt = window.event || e;
            if(evt.keyCode == 13)
                searchAction(1);
        });
    }else{
        // 搜索方法
        $(".search-bar").click(function(){
            searchTurnTo($("input[name='search-bar']").val(),$(".select").text()=="普通搜索"?'commonSearch':'graphSearch')
        });

        $('input[name=\'search-bar\']').on('keypress',function(){
            var evt = window.event || e;
            if(evt.keyCode == 13)
                searchTurnTo($("input[name='search-bar']").val(),$(".select").text()=="普通搜索"?'commonSearch':'graphSearch')
        });
    }

})

function getResultData(temp) {
    $.ajax({
        type : "POST",
        contentType : 'application/json;charset=UTF-8',
        url : 'search',
        data : JSON.stringify(temp),
        dataType : 'json',
        success : function(data){
            for ( i =0; i < data.length; ++i ){
                $("ul.result").append('<li><img src="'+data[i].imageLink+'"><a href="'+ data[i].resultLink +'" title="'+data[i].resultName+'">'+ data[i].resultName +'</a></li>')
            }
            if(data.length == 0 && $(".result").children("li").length == 0) {
                $("ul.result").append("无搜索结果......");
                $("#more").hide();
            }
            else if(data.length <20 ){
                $("#more").hide();
                $("#over").show();
            }
        },
        error : function(){
            alert("error");
            $("ul.result").append("无搜索结果......");
        }
    });
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

function searchTurnTo(key, type) {
    var url = "searchResult?type=" + type + "&keyWords=" + key;
    window.location.href = url;
}

function searchAction(clean) {
    if(clean == 1)
        $("ul.result").empty();
    var temp = {
        type : $(".select").text()=="普通搜索"?'commonSearch':'graphSearch',
        keywords :$("input[name='search-bar']").val(),
        page : parseInt($(".result").children("li").length / 20)
    }
    getResultData(temp);
}