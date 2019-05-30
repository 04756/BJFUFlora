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
    <div class="full-width-block">
        <div class="middle">
            <div class="left">
                <div class="title">${plant.cName}<button id="open">查看该植物的图谱</button></div>
                <div class="title">${plant.eName}</div>
                <div class="contain">
                    ${plant.content}
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
        <%--知识图谱可视化--%>
        <div id="graph" style="width:100%;min-width:1200px;height: 100%;min-height:1400px;position: absolute;background-color: rgba(0,0,0,0.6);display: none;top:0px;">
            <div id="Container" style="overflow: scroll;height: 60%;margin: 0 auto;top: 80px;background-color: #f0e8e8;width:  80%;position:  relative;">
                <img id="close" style="position: fixed;right: 12%;" src="../images/close.png"/>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/d3.v4.min.js"></script>
    <script type="text/javascript" src="../js/Graph.js"></script>
    <script type="text/javascript" src="../js/graph.activity.js"></script>
    <script type="text/javascript">
        var temp = {
            "cName" : "${plant.cName}"
        }
        $.ajax({
            type : "POST",
            contentType : 'application/json;charset=UTF-8',
            url : '../graph',
            data : JSON.stringify(temp),
            dataType : 'json',
            success : function(datas){
                var tpoption = {
                    container:'#Container',
                    data: datas,
                    width: window.innerWidth || document.documentElement.clientWidth,
                    height: 1.4 * window.innerHeight || document.documentElement.innerHeight
                };
                var tp = new Chart(tpoption);
                tp.init();
            },
            error : function(e){
                console.log(e);
            }
        });

    </script>
</body>

</html>