<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="TestMongoAction.action" id="test">
		<input type="submit" value="添加">
	</form>
	
	<form method="post" action="AllFileInGalleryAction.action" id="test">
		<input type="text" id="page_index" name="page_index" value="1" />
		<input type="text" id="path" name="path" value="/user/hadoop/user/402824814cb03d52014cb03dbc190001/file/" />
		<input type="text" id="search_name" name="search_name" value="" />
		<input type="submit" value="测试">
	</form>
</body>
</html>