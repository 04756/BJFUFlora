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
<jsp:include page="header.jsp"/>

<div class="middle">
    <div class="left">
        <ul class="tree">

        </ul>
    </div><!--
		<hr style="border-left:1px solid black;width:0px;height:500px;display: inline-block;margin-left: 20px;" /> -->
    <div class="right">
        <div id="map"></div>
    </div>
</div>

<script>

</script>
</body>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/map.js" defer="defer"></script>
<script type="text/javascript" src="js/NodeLink.js"></script>
<script type="text/javascript" src="js/treeNode.js"></script>
</html>
