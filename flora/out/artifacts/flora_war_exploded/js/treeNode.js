DELAYQUEUE = new NodeLink("","","",null);

$(function(){
		// jQuery.getJSON( url [, data ] [, success ] )
    tranditionalLayer();
});

function classifyType(val) {
    $(".tree").empty();
    if(val == 1)
        tranditionalLayer();
    else
        untraditionalLayer();
}

function tranditionalLayer() {
    $.getJSON("raceJson", "", function(data) {
        //each循环 使用$.each方法遍历返回的数据date
        $.each(data, function(i, item) {
            addTreeNode(item.parent, item.text, item.id);
        })
        while(DELAYQUEUE.isEmpty() != true){
            var temp = DELAYQUEUE.next;
            addTreeNode(temp.parentId, temp.text, temp.id);
            temp.removeSelf();
        }
        $(".tree li").children("ul.sub-tree").slideUp("fast");
        $(".node").click(function(){
            if($(this).parent().children(".plus").length != 0)
                $(this).parent().children("ul.sub-tree").slideToggle("slow");
        })

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
    });
}

function untraditionalLayer() {
    $.getJSON("untraditionalRaceJson", "", function(data) {
        //each循环 使用$.each方法遍历返回的数据date
        $.each(data, function(i, item) {
            addTreeNode(item.parent, item.text, item.id);
        })
        while(DELAYQUEUE.isEmpty() != true){
            var temp = DELAYQUEUE.next;
            addTreeNode(temp.parentId, temp.text, temp.id);
            temp.removeSelf();
        }
        $(".tree li").children("ul.sub-tree").slideUp("fast");
        $(".node").click(function(){
            if($(this).parent().children(".plus").length != 0)
                $(this).parent().children("ul.sub-tree").slideToggle("slow");
        })
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
                $(".leaf").click(function () {
                    $.get("planetGrowing?planet="+$(this).text(), function (data) {
                        addMapMarker(data);
                    })
                });
            });
        }
    });
}

function addTreeNode(parentId, text, id){
	var node = "<li id='"+id+"'><span class='minus'></span><div class='leaf'>"+text+"</div></li>";
	if(parentId == "")
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
			$('li#'+parentId).children("span").removeClass("minus");
			$('li#'+parentId).children("span").addClass("plus");
			$('li#'+parentId).children("div").removeClass("leaf");
			$('li#'+parentId).children("div").addClass("node");
		}
		$('li#'+parentId).children("ul.sub-tree").append(node);
	}
}
