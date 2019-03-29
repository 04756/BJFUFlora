// Chart类
function Chart(option){
    var _defaultOption = {
        width:300,
        height:300,
        data:'',
        container:''
    };

// 将一个或多个对象的内容合并到目标对象
    option = $.extend(true, _defaultOption,option);

    this.width  = option.width;
    this.height = option.height;
    this.data   = option.data;//数据url或对象,必填
    this.container = option.container;//svg容器(node或者选择器)，必填
}

Chart.prototype.drawing = function(nodes, lines){
    var _this = this;
    var width = _this.width;
    var height = _this.height;
    //初始化画板
    var svg = d3.select(_this.container).append("svg")
        .attr("width",width)
        .attr("height",height)
        .style("pointer-events", "all");
// .call(d3.zoom().on("zoom", function () {
//         d3.event.transform.x = Math.min(0, Math.max(d3.event.transform.x, width - width * d3.event.transform.k/10));
// d3.event.transform.y = Math.min(0, Math.max(d3.event.transform.y, height - height * d3.event.transform.k/10));
// console.log("x:"+d3.event.transform.x+"   y:"+d3.event.transform.y, "  k:"+d3.event.transform.k)
// svg.attr("transform", d3.event.transform);
// }));
    var graph = svg.append("g").attr("class", "graph");


    // 力导向图
    // .force("link",[name]) name 如果有指定回傳的值的話，就是依據指定的内容來找，
    // 如果沒有的話，就預設為 index 來檢索。
    var simulation = d3.forceSimulation()
        .force("link", d3.forceLink().id(function(d) { return d.name; }))
        .force("charge", d3.forceManyBody())
        .force("center", d3.forceCenter(width / 2, height / 2));

    function dragstarted(d) {
        if (!d3.event.active) simulation.alphaTarget(0.3).restart();
        d.fx = d.x;
        d.fy = d.y;
        if(d.x > width)
            d.fx = width;
        if(d.y <= 0)
            d.fy = 0;
    }
    function dragged(d) {
        d.fx = d3.event.x;
        d.fy = d3.event.y;
        if(d3.event.x > width)
            d.fx = width;
        if(d3.event.y <= 0)
            d.fy = d.r;
        if(d3.event.y > height)
            svg.attr("height", d3.event.y);
    }

    function dragended(d) {
        if (!d3.event.active) simulation.alphaTarget(0);
        d.fx = null;
        d.fy = null;
    }


    //每条连线独自一个g组
    var link = graph.selectAll("line")
        .data(lines)
        .enter()
        .append("g")
        .attr("class", "link");
    link.append("line")
        .style("stroke-width",2)
        .style("stroke", "black");
    var node = graph
        .selectAll("circle")
        .attr("class", "nodes")
        .data(nodes)
        .enter()
        .append("g");
    node.append("circle")
        .attr("r", function(d){return d.r;})
        .attr("class", "node")
        .call(d3.drag()
            .on("start", dragstarted)
            .on("drag", dragged)
            .on("end", dragended));
    link.append("text")
        .text(function(d){return d.text;});

    // 每一个节点添加文字
    node.append("text")
        .text(function(d){return d.name});

    //将节点和连线导入力导向图
    simulation.nodes(nodes)
        .on("tick", ticked);
    //添加节点拖拽行为
    // node.call(getDragBehavior(simulation));
    f_link = simulation.force("link")
        .links(lines);
    f_link.distance(function(d){//指定连线长度
        var distance = 120;
        distance += distance*Math.random()*1;
        return distance;
    });

    //添加svg缩放行为
    // svg.call(getZoomBehavior(graph));

    function ticked() {
        link.select("line").attr("x1", function(d) { return d.source.x; })
            .attr("y1", function(d) { return d.source.y; })
            .attr("x2", function(d) { return d.target.x; })
            .attr("y2", function(d) { return d.target.y; });
        link.select("text").attr('x', function(d){
            var x1 = d.source.x,
                x2 = d.target.x,
                halfX  = x1-(x1-x2)/2,
                x3 = x1-(x1-halfX)/2;

            //线段3分之-处
            return x3;
            //return d.source.x-(d.source.x-d.target.x)/2;
        })
            .attr('y',function(d){
                var y1 = d.source.y,
                    y2 = d.target.y,
                    halfY = y1-(y1-y2)/2,
                    y3 = y1-(y1-halfY)/2;

                //使文本与线段之间有一定间隙
                y3 = y3 - 5;

                return y3;
                //return y1-(y1-y2)/2;
            })
            .attr("transform",function(d){
                var x1 = d.source.x,
                    x2 = d.target.x,
                    y1 = d.source.y,
                    y2 = d.target.y,
                    x  = x1-(x1-x2)/2,
                    y  = y1-(y1-y2)/2,
                    rightAngleSide1 = Math.abs(y2-y1),
                    rightAngleSide2 = Math.abs(x2-x1),
                    _asin = 0,
                    _rotateAngle = 0,
                    x3 = x1-(x1-x)/2 ,
                    y3 = y1-(y1-y)/2;
                if(x1 < x2){
                    _asin = (y2-y1)/Math.sqrt(Math.pow(rightAngleSide1,2)+Math.pow(rightAngleSide2,2));
                    _rotateAngle = Math.asin(_asin)*180/Math.PI;
                }else{
                    _asin = (y1-y2)/Math.sqrt(Math.pow(rightAngleSide1,2)+Math.pow(rightAngleSide2,2));
                    _rotateAngle = Math.asin(_asin)*180/Math.PI;
                    _rotateAngle = _rotateAngle < 0?(180+_rotateAngle):-(180-_rotateAngle);
                }


                return 'rotate('+(_rotateAngle)+','+x3+' '+y3+')';//以线段的三分之一出为旋转焦点
                //return 'rotate('+(_rotateAngle)+','+x+' '+y+')';//以线段的二分之一处为旋转焦点
            });
        node.select("circle").attr("cx", function(d) { return d.x; })
            .attr("cy", function(d) { return d.y; });

        // 位置的变化
// node.attr("transform",function(d){

// 	halfWidth = parseFloat(d.style.width)/2,
// 	halfHeight = parseFloat(d.style.height)/2;

// 	return 'translate('+(d.x-halfWidth)+','+(d.y-halfHeight)+')';

// });

            node.select("text").attr('dy',function(d){
                return d.y+6;
            })
                .attr('dx',function(d){
                    return d.x-d.r+10;
                });
        }

        // 创建一个缩放行为
        function getZoomBehavior(g){

            return d3.zoom().scaleExtent([1,20]).on("zoom",zoomEvtFn);

            function zoomEvtFn(){
                g.attr("transform","translate("+d3.event.translate+")scale(" + d3.event.transform.k + ")");
            }

        }
    }
    function typeStr(obj){
        return Object.prototype.toString.call(obj).toLowerCase();
    }

    Chart.prototype.init = function(){

        var _this = this;
        if(typeStr(_this.data)=='[object string]'){
            $.getJSON(_this.data,"",function(data){
                // if(error) throw error ;
                _this.data = data;
                _this.drawing(_this.data.nodes,_this.data.lines);
            });
        }

}