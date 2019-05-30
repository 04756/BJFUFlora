<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>PLANET SEARCH</title>
    <link rel="stylesheet" type="text/css" href="css/animation.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/search.css">
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="full-width-block">
        <div class="middle">
            <div class="search-block">
                <div class="search-type">
                    <p class="select">普通搜索</p>
                    <img src="images/split.png">
                    <p>概念搜索</p>
                </div>
                <div class="search-input-block">
                    <input type="text" name="search-bar">
                    <img src="images/searchbar.png" class="search-bar">
                    <div class="result_box">
                        <ul>
                            <li>cwec冒出来的冒出来我们</li>
                            <li>cwec冒出来的冒出来我们</li>
                            <li>cwec冒出来的冒出来我们</li>
                            <li>cwec冒出来的冒出来我们</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="folder">
                <select onchange="classifyType(this.value)">
                    <option value="1">生物分类法</option>
                    <option value="2">新维度分类</option>
                </select>
                <ul class="tree">

                </ul>
            </div>
            <div class="search-result">
                <p>搜索结果:</p>
                <hr style="border: 0.5px solid #c0baba;">
                <ul class="result">
                </ul>
                <div id="more">获取更多</div>
                <div id="over">无更多显示啦~</div>
            </div>
        </div>
    </div>
	
	
</body>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
    <script type="text/javascript" src="js/NodeLink.js"></script>
    <script type="text/javascript" src="js/treeNode.js"></script>
</html>