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
	
	<input type="file" id="file" name="file" />
	<input type="button" onclick='upload()' value="上传测试">
	
	<script src="/HTP/pages/js/jquery-latest.js"></script>
    <script src="/HTP/pages/js/bootstrap.min.js"></script>
<script>
	function upload(){
		var file = $("#file")[0].files[0]; //文件对象
		var name = file.name;
		var size = file.size;
		var shardSize = 2 * 1024 * 1024   //以2MB为一个分片
        var shardCount = Math.ceil(size / shardSize);  //总片数
        for(var i = 0;i < shardCount;++i){
            //计算每一片的起始与结束位置
            var start = i * shardSize;
            var end = Math.min(size, start + shardSize);

            //构造一个表单，FormData是HTML5新增的
            var form = new FormData();
            form.append("data", file.slice(start,end));  //slice方法用于切出文件的一部分
            form.append("name", name);
            form.append("total", shardCount);  //总片数
            form.append("index", i + 1);        //当前是第几片

            //Ajax提交
            $.ajax({
                url: "FileUploadAction.action",
                type: "POST",
                data: form,
                async: false,        //异步
                processData: false,  //很重要，告诉jquery不要对form进行处理
                contentType: false,  //很重要，指定为false才能形成正确的Content-Type
                success: function(){
					
                }
            });

        }
	}
</script>
</body>
</html>