//文件类型管理页面向后翻页
function getNextPageForFileType(){
	var page_index=document.getElementById('page_index').innerHTML;
	var total_page=$('#total_page').val();
	if(page_index==total_page){
		alert("本页已经是最后一页");
	}else{
		var target_page=parseInt(page_index)+1;
		getPageForFileType(target_page);
	}
}

//文件类型管理页面向前翻页
function getPreviousPageForFileType(){
	var page_index=document.getElementById('page_index').innerHTML;
	var total_page=$('#total_page').val();
	if(page_index==1){
		alert("本页已经是第一页");
	}else{
		var target_page=parseInt(page_index)-1;
		getPageForFileType(target_page);
	}
}

//跳转至文件类型管理页面的第一页
function getFirstPageForFileType(){
	getPageForFileType(1);
}

//跳转至文件类型管理页面的最后一页
function getLastPageForFileType(){
	var total_page=$('#total_page').val();
	getPageForFileType(total_page);
}

//模糊查询符合条件的数据
function searchFileType(){
	getPageForFileType(1);
}

//刷新文件类型列表
function refreshFileTypeList(data){
	$("#showTable").empty();
	$.each(data.file_type_list,function(i,value){ 
		var _tr = $("<tr><td><img src='"+value.img+"' style='width: 40px;height: 40px;' /></td>"+
				"<td class='align-center'>"+value.postfix+"</td>"+
				"<td class='align-center'>"+value.category_name+"</td>"+
				"<td class='align-center'><a id='"+value.id+"' onclick='javascript:deleteFileType(this.id)' href='#' >删除</a></td></tr>"); 
		$("#showTable").append(_tr); 
	}) 
}

//获取指定页数的文件类型数据
function getPageForFileType(page_index){
	var search_postfix=$('#search_postfix').val();
	var url = "FileTypePageAction.action?page_index="+page_index+"&&postfix="+trim(search_postfix); 
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//刷新文件类型列表
			refreshFileTypeList(data);
			//填写总页数和当前页数
			$('#total_page').val(data.total_page);
			document.getElementById('page_index').innerHTML=page_index;
		} 
	})
}

//验证后缀格式
function validatePostfix(){
	var postfix = $('#postfix').val();
	if (postfix != '') {//判断
	    var reg = /^[A-Za-z0-9]+$/;
	    if (!reg.test(postfix)) {
	        return false;      
	    }else{
	        return true;
	    }
	}
}

//验证后缀是否存在
function ifPostfixExist(){
	var postfix = $('#postfix').val();
	var postfix_msg=document.getElementById('postfix_msg');
	if(validatePostfix()){
		var url = "ValidatePostfixAction.action?postfix="+postfix; 
		$.ajax({ 
			type:'get', 
			url:url, 
			dataType: 'json', 
			success:function(data){ 
				if(data.exist==0){
					postfix_msg.innerHTML="<font color='green'>后缀合法</font>";
				}else{
					postfix_msg.innerHTML="<font color='red'>后缀已存在</font>";
				}
			} 
		})	
	}else{
		postfix_msg.innerHTML="<font color='red'>后缀只能由字母和数字组成</font>";
	}
}

//添加文件类型时当选择不同图片后显示不同图片的预览图
function showIcon(value){
	document.getElementById("icon_preview").innerHTML="<br><span class='span3'>图标预览：</span><img src='/HTP/pages/img/icon/"+
	value+"' style='width: 40px;height: 40px;' />";
}

//检测添加文件类型的表单数据
function addFileType(){
	var postfix = $('#postfix').val();
	var img_type = $('#img_type').val();
	var postfix_msg=document.getElementById('postfix_msg');
	if(validatePostfix()){
		var url = "ValidatePostfixAction.action?postfix="+postfix; 
		$.ajax({ 
			type:'get', 
			url:url, 
			dataType: 'json', 
			success:function(data){ 
				if(data.exist==0){
					postfix_msg.innerHTML="<font color='green'>后缀合法</font>";
					//开始添加文件类型
					var add_url = "FileTypeAddAction.action?page_index=1&&postfix="+postfix+"&&img_type="+img_type;
					$.ajax({ 
						type:'get', 
						url:add_url, 
						dataType: 'json', 
						success:function(data){ 
							//刷新文件类型列表
							refreshFileTypeList(data);
							//清空添加文件类型填单的数据
							$('#postfix').val("");
							document.getElementById('postfix_msg').innerHTML="";
							//填写总页数和当前页数
							$('#total_page').val(data.total_page);
							document.getElementById('page_index').innerHTML=1;
						} 
					})
				}else{
					postfix_msg.innerHTML="<font color='red'>后缀已存在</font>";
				}
			} 
		})	
	}else{
		postfix_msg.innerHTML="<font color='red'>后缀只能由字母和数字组成</font>";
	}
}

//删除文件类型
function deleteFileType(delete_id){
	var url = "FileTypeDeleteAction.action?id="+delete_id; 
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(data){ 
			//刷新文件类型列表
			refreshFileTypeList(data);
			//填写总页数和当前页数
			$('#total_page').val(data.total_page);
			document.getElementById('page_index').innerHTML=1;
		} 
	})
}
