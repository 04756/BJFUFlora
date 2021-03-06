<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>PLANET SEARCH</title>
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="full-width-block">
        <div class="middle">
            <div class="search-type">
                <p class="select">普通搜索</p>
                <img src="images/split.png">
                <p>概念搜索</p>
            </div>
            <input type="text" name="search-bar">
            <div class="result_box">
                <ul>
                </ul>
            </div>
            <img src="images/searchbar.png" class="search-bar">
            <ul class="popular-search">
                <li>热门搜索:</li>
                <a href="plant/伞花六道木（中国高等植物图鉴）图版32:6-7"><li>伞花六道木</li></a>
                <a href="plant/古城玫瑰树（新拟）"><li>古城玫瑰树</li></a>
                <a href="plant/玫瑰茄（岭南农刊）山茄子（广州）图版22：5-8"><li>玫瑰茄</li></a>
            </ul>
            <img src="images/bac.png" class="search-logo">
        </div>
    </div>
	<div class="intro-area">
		<div class="block">
			项目植物数据共计
			<br/>
			<p style="font-size: 38px;margin-top: 10px;">35,000+条</p>
		</div>
        <div class="block">
            植物图片来源
            <br/>
            <p style="font-size: 38px;margin-top: 10px;margin-left: 60px;">百度图片</p>
        </div>
        <div class="block">
            开发团队
            <br/>
            <p style="font-size: 38px;margin-top: 10px;margin-left: 100px;">北京林业大学</p>
        </div>
	</div>
</body>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
</html>