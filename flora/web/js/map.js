var provinces = [
	{'name' : '北京', 'px': '116.395645', 'py' : '39.929986'},
	{'name' : '上海', 'px': '121.487899', 'py' : '31.249162'},
	{'name' : '天津', 'px': '117.210813', 'py' : '39.14393'},
	{'name' : '重庆', 'px': '106.530635', 'py' : '29.544606'},
	{'name' : '香港', 'px': '114.186124', 'py' : '22.293586'},
	{'name' : '澳门', 'px': '113.557519', 'py' : '22.204118'},
	{'name' : '台湾', 'px': '120.961454', 'py' : '23.80406'},
	{'name' : '安徽', 'px': '117.216005', 'py' : '31.859252'},
	{'name' : '福建', 'px': '117.984943', 'py' : '26.050118'},
	{'name' : '甘肃', 'px': '102.457625', 'py' : '38.103267'},
	{'name' : '广东', 'px': '113.394818', 'py' : '23.408004'},
	{'name' : '广西', 'px': '108.924274', 'py' : '23.552255'},
	{'name' : '贵州', 'px': '106.734996', 'py' : '26.902826'},
	{'name' : '海南', 'px': '109.733755', 'py' : '19.180501'},
	{'name' : '河北', 'px': '115.661434', 'py' : '38.61384'},
	{'name' : '河南', 'px': '113.486804', 'py' : '34.157184'},
	{'name' : '黑龙江', 'px': '128.047414', 'py' : '47.356592'},
	{'name' : '湖北', 'px': '112.410562', 'py' : '31.209316'},
	{'name' : '湖南', 'px': '111.720664', 'py' : '27.695864'},
	{'name' : '江苏', 'px': '119.368489', 'py' : '33.013797'},
	{'name' : '江西', 'px': '115.676082', 'py' : '27.757258'},
	{'name' : '吉林', 'px': '126.262876', 'py' : '43.678846'},
	{'name' : '辽宁', 'px': '122.753592', 'py' : '41.6216'},
	{'name' : '内蒙古', 'px': '114.415868', 'py' : '43.468238'},
	{'name' : '宁夏', 'px': '106.155481', 'py' : '37.321323'},
	{'name' : '青海', 'px': '96.202544', 'py' : '35.499761'},
	{'name' : '山东', 'px': '118.527663', 'py' : '36.09929'},
	{'name' : '山西', 'px': '112.515496', 'py' : '37.866566'},
	{'name' : '陕西', 'px': '109.503789', 'py' : '35.860026'},
	{'name' : '四川', 'px': '102.89916', 'py' : '30.367481'},
	{'name' : '西藏', 'px': '89.137982', 'py' : '31.367315'},
	{'name' : '新疆', 'px': '85.614899', 'py' : '42.127001'},
	{'name' : '云南', 'px': '101.592952', 'py' : '24.864213'},
	{'name' : '浙江', 'px': '119.957202', 'py' : '29.159494'}
]


function initialize(){
	map = new BMap.Map('map');
	//默认一个中心坐标
    map.centerAndZoom(new BMap.Point(107.164226, 31.859637), 6);
    // 在地图中使用鼠标滚轮控制缩放
 	map.enableScrollWheelZoom(true);

  	//单击获取点击的经纬度
	// map.addEventListener("click",function(e){
	// 	alert(e.point.lng + "," + e.point.lat);
	// });

    // 通过JavaScript的prototype属性继承于BMap.Control
	ZoomControl.prototype = new BMap.Control();
	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
	ZoomControl.prototype.initialize = function(map){
	  // 创建一个DOM元素
	  var div = document.createElement("div");
	  // 添加文字说明
	  div.appendChild(document.createTextNode("清除所有标记点"));
	  // 设置样式
	  div.style.cursor = "pointer";
	  div.style.border = "1px solid gray";
	  div.style.backgroundColor = "white";
	  // 绑定控件click事件,点击清除所有标记
	  div.onclick = function(e){
		remove_overlay();
	  }
	  // 添加DOM元s素到地图中
	  map.getContainer().appendChild(div);
	  // 将DOM元素返回
	  return div;
	}
	// 创建控件
	var myZoomCtrl = new ZoomControl();
	// 添加到地图当中
	map.addControl(myZoomCtrl);

}

function loadScript() {  
  	var script = document.createElement("script");  
  	script.src = "http://api.map.baidu.com/api?v=2.0&ak=eNLHuNo69hyGHuYc7v8tZgfPeV6EbZdA&callback=initialize";
  	document.body.appendChild(script);  
}     

function addMarker(point, mp){
	// 创建标注
 	var maeker = new BMap.Marker(point);
 	// 标注添加到地图上
 	mp.addOverlay(maeker);
}

// 清除所有标注点
function remove_overlay(){
	map.clearOverlays();         
}

// 定义一个控件类,即function
function ZoomControl(){
	// 默认停靠位置和偏移量
	this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
	this.defaultOffset = new BMap.Size(10, 10);
}

function addMapMarker(arrs){
    remove_overlay();
    $.each(arrs, function (i, item) {
        var g = provinces.find((el)=>(el.name==item));
        var point = new BMap.Point(g.px, g.py);
        addMarker(point, map)
    })
}

window.onload = loadScript;