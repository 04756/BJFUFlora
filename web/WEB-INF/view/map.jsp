<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/3/14
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>植物地图</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/map.css">
</head>
<body>
<div class="header">
    <div class="guid">
        <ul class="left-guid">
            <li><a href="">首页</a></li>
            <li><a href="">植物地图</a></li>
            <li><a href="">植物搜索</a></li>
            <li><a href="">植物图谱</a></li>
        </ul>
        <ul class="right-guid" style="float: right;">
            <li><a href="">联系我们</a></li>
        </ul>
    </div>
</div>

<div class="middle">
    <div class="left">
        <ul class="tree" id="god">

        </ul>
    </div><!--
		<hr style="border-left:1px solid black;width:0px;height:500px;display: inline-block;margin-left: 20px;" /> -->
    <div class="right">
        <div id="map"></div>
    </div>
</div>


</body>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/map.js"></script>
<script type="text/javascript" src="js/NodeLink.js"></script>
<script type="text/javascript" src="js/treeNode.js"></script>
</html>
