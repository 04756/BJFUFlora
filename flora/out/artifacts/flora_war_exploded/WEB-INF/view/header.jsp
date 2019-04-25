<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/3/15
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <div class="header">
        <div class="guid">
            <ul class="left-guid" style="display: none;">
                <li style="float: left;"><img src="${pageContext.request.contextPath}/images/logo.png"></li>
                <li class="t"><a href="${pageContext.request.contextPath}/hello">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/planetMap">植物地图</a></li>
                <li><a href="${pageContext.request.contextPath}/searchResult">植物搜索</a></li>
                <li><a href="${pageContext.request.contextPath}/searchResult">植物分类</a></li>
                <%--<li><a href="">植物图谱</a></li>--%>
            </ul>
            <ul class="right-guid" style="float: right;">
                <li><a href="">联系我们</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
