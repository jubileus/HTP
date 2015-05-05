//刷新数据
function refreshData(data){
	$.each(data.share_list,function(i,value){ 
		var _tr = $("<tr><td><input type='checkbox' id='check_"+value.id+"' name='share_check' /><img style='width:30px;height:30px;' src='"+value.img+"' /></td>" +
				"<td>"+value.name+"</td>" +
				"<td class='description'>"+value.date+"</td>" +
				"<td><span class='label "+value.span_class+"'>"+value.status+"</span><ul class='actions'>" +
				"<li><a id='share_"+value.id+"' onclick='javascript:viewShareLink(this.id)' href='#'>查看分享链接</a></li>" +
				"<li><a id='cancel_"+value.id+"' onclick='javascript:cancelSingle(this.id)' href='#'>取消分享</a></li>" +
				"</ul></td></tr>"); 
		$("#showTable").append(_tr); 
	}) 
}

//显示分享链接
function viewShareLink(share){
	var share_id=share.split("_")[1];
	var share_path=$('#share_path').val();
	$('#share_link').val(share_path+share_id);
	$('#share_menu').click();
}

//取消多个分享
function cancelMulti(){
	var id_list="_";
	$("input[name='share_check']").each(function(){
		if(this.checked==true){
			//文件被选中
			var file_id=this.id.split("_")[1];
			id_list+=file_id+"_";
		}
	});
	var url = "CancelMultiAction.action?id_list="+id_list;
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(){ 
			//成功删除
			//重置页数为1
			$('#index').val(1);
			//重新加载数据
			loadShareData();
		} 
	})
}

//取消单个分享
function cancelSingle(share){
	var share_id=share.split("_")[1];
	var url="CancelSingleAction.action?share_id="+share_id;
	$.ajax({ 
		type:'get', 
		url:url, 
		dataType: 'json', 
		success:function(){ 
			//重置页数为1
			$('#index').val(1);
			//重新加载数据
			loadShareData();
		} 
	})
}

//加载数据
function loadShareData(){
	var index=$('#index').val();
	var search_name=$('#search_name').val();
	var total_page=$('#total_page').val();
	
	search_name=encodeURI(encodeURI(search_name));
	var url = "SharePageAction.action?index="+index+"&&search_name="+search_name; 
	var page_max=parseInt(total_page)+1;
	//查看是否已经加载完数据
	if(index<page_max){
		//尚未加载完所有页的数据
		$.ajax({ 
			type:'get', 
			url:url, 
			dataType: 'json', 
			success:function(data){ 
				
				//如果是加载第一页，则清空已有数据
				if(index==1){
					$("#showTable").empty();
				}
				//刷新列表
				refreshData(data);
				
				//填写总页数和当前页数
				$('#total_page').val(data.total_page);
				var new_index=parseInt(index)+1;
				$('#index').val(new_index);
			} 
		})
	}
}

//模糊查询
function searchData(){
	var search_name=$('#search_name').val();
	//重置页数为1
	$('#index').val(1);
	//重置总页数为1，以加载新数据
	$('#total_page').val(1);
	
	loadShareData();
}

//检测滚动条是否到达底端
$(window).scroll(function(){
	var scrollTop = $(this).scrollTop();
	var scrollHeight = $(document).height();
	var windowHeight = $(this).height();
	if(scrollTop + windowHeight == scrollHeight){
		//到达底端，如果有没有加载的数据，就加载下一页数据
		loadShareData();
	}
});