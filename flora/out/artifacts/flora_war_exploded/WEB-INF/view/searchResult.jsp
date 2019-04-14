<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>PLANET SEARCH</title>
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/search.css">
</head>
<body>
    <jsp:include page="header.jsp"/>

	<div class="middle">
		<div class="search-block">
			<div class="search-type">
				<p>普通搜索</p>
				<img src="images/split.png">
				<p>概念搜索</p>
			</div>
			<input type="text" name="search-bar">
			<img src="images/searchbar.png" class="search-bar">
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
				<!--<li><a href="">ssdsbdhn</a></li>-->
				<!--<li><a href="">ssdsbdhn</a></li>-->
				<!--<li><a href="">ssdsbdhn</a></li>-->
				<!--<li><a href="">ssdsbdhn</a></li>-->
				<!--<li><a href="">ssdsbdhn</a></li>-->
			</ul>
		</div>
	</div>
	
	
</body>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
    <script type="text/javascript" src="js/NodeLink.js"></script>
    <script type="text/javascript" src="js/treeNode.js"></script>
</html>