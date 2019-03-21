<%@ page import="javax.xml.ws.Response" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%response.setHeader("Access-Control-Allow-Origin", "http://www.baidu.com");%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>PLANET SEARCH</title>
	<link rel="stylesheet" type="text/css" href="../css/base.css">
	<link rel="stylesheet" type="text/css" href="../css/detail.css">
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
			<div class="title">${planet.cName}</div>
			<div class="title">${planet.eName}</div>
			<%--<img src="images/flower1.jpg">--%>
			<%--<img src="images/flower1.jpg">--%>
			<%--<img src="images/leaf1.jpg">--%>
			<div class="contain">
                ${planet.content}
            </div>
		</div>
		<hr style="border-left:1px solid black;width:0px;height:500px;display: inline-block;margin-left: 20px;" />
		<div class="right">
			<p style="font-weight: bold;line-height: 24px;">所属类群：</p>
			<ul>
                <c:forEach items="${race}" var="re">
				<li><a href="${re.resultLink}">${re.resultName}</a></li>
                </c:forEach>
			</ul>
		</div>
	</div>
    <iframe id="imgData" src="https://image.baidu.com/search/index?tn=resultjson_com&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=index&fr=&hs=0&xthttps=111111&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=%E5%85%AD%E9%81%93%E6%9C%A8%E5%B1%9E&oq=s&rsp=-1" style="display: none;">
    </iframe>

</body>
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/detail.js"></script>
</html>