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
    <jsp:include page="header.jsp"/>

	<div class="middle">
		<div class="left">
            <div class="title">${planet.cName}<button id="open">查看该植物的图谱</button></div>
			<div class="title">${planet.eName}</div>
			<div class="contain">
                ${planet.content}
		    </div>
            <c:forEach items="${imgList}" var="im">
                <img src="${im}">
            </c:forEach>
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
    <%--知识图谱可视化--%>
    <div id="graph" style="position: absolute;width:100%;height: 150%;background-color: rgba(0,0,0,0.6);display: none;top:0px;">
        <div id="Container" style="overflow: scroll;height: 600px;width: 1200px;margin: 0 auto;margin-top: 80px;background-color: #f0e8e8;">
            <img id="close" style="position: absolute;z-index: 9999;right: 8%;" src="../images/close.png"/>
        </div>
    </div>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/detail.js"></script>
    <script type="text/javascript" src="../js/d3.v4.min.js"></script>
    <script type="text/javascript" src="../js/Graph.js"></script>
    <script type="text/javascript" src="../js/graph.activity.js"></script>
    <script type="text/javascript">
        var tpoption = {
            container:'#Container',
            data:'../graph',
            width:window.innerWidth || document.documentElement.clientWidth,
            height:window.innerHeight || document.documentElement.innerHeight
        };
        var tp = new Chart(tpoption);
        tp.init();
    </script>
</body>

</html>