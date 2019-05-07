DELAYQUEUE = new NodeLink("","","",null);

$(function(){
    PlanetLayer("raceJson", "")
    $(document).on('click', '.node', function(){
        $(this).parent().children("ul.sub-tree").empty();
        var temp = {
            node:$(this).parent("li").attr("id")
        }
        var childsLength = 0;
        if($("select").val() == 1)
            childsLength = PlanetLayer("getChildsRaceJson", temp);
        else
            childsLength = PlanetLayer("getUntranChildsRaceJson", temp);

        if(childsLength == 0){
            $(this).parent().children("span").removeClass("plus");
            $(this).parent().children("span").addClass("minus");
            $(this).removeClass("node");
            $(this).addClass("leaf");
            $(this).click();
        }
        // if ($(this).parent().children(".plus").length != 0)
        //     $(this).parent().children("ul.sub-tree").slideToggle("slow");
        if($(this).parent().children("ul.sub-tree").children("li").length > 0){
            $(this).parent().children("ul.sub-tree").toggle("slow");
        }
    });
    $(".tree").children("li").each(function () {
       $(this).click();
    });
});

function classifyType(val) {
    $(".tree").empty();
    if(val == 1)
        PlanetLayer("raceJson", "");
    else
        PlanetLayer("untraditionalRaceJson", "");
}

function PlanetLayer(url, d){
    var resultLength = 0;
    $.ajaxSettings.async = false;
    $.getJSON(url, d, function(data) {
        //each循环 使用$.each方法遍历返回的数据date
        $.each(data, function(i, item) {
            addTreeNode(item.parent, item.text, item.id);
        })
        while(DELAYQUEUE.isEmpty() != true){
            var temp = DELAYQUEUE.next;
            addTreeNode(temp.parentId, temp.text, temp.id);
            temp.removeSelf();
        }



        if(window.location.href.match("Map") != null && window.location.href.match("Map").index < 0){
            // 点击显示植物
            $(".leaf").click(function () {
                $("ul.result").empty();
                var temp = {
                    type : $(this).text(),
                    keyWords : "graphSearch"
                }
                getResultData(temp);
            });
        }
        else{
            $(".leaf").click(function () {
                $.get("planetGrowing?planet="+$(this).text(), function (data) {
                    addMapMarker(data);
                })
            });
        }
        resultLength = data.length;
    });
    $.ajaxSettings.async = true;
    return resultLength;
}


function addTreeNode(parentId, text, id){
	var node = "<li id='"+id+"'><span class='plus'></span><div class='node'>"+text+"</div></li>";
	if(parentId == "" || parentId == id)
		$("ul.tree").append(node);
	else{
		if($('li#'+parentId).length == 0){
			var nodeHead = DELAYQUEUE;
			while(nodeHead.next != null)
				nodeHead = nodeHead.next;
			nodeHead.addNode(new NodeLink(id, parentId, text, null));
			return;
		}
		if($('li#'+parentId).children("ul.sub-tree").length <= 0){
			$('li#'+parentId).append("<ul class='sub-tree'></ul>");
			// $('li#'+parentId).children("span").removeClass("minus");
			// $('li#'+parentId).children("span").addClass("plus");
			// $('li#'+parentId).children("div").removeClass("leaf");
			// $('li#'+parentId).children("div").addClass("node");
		}
		$('li#'+parentId).children("ul.sub-tree").append(node);
	}
}
