//加载Gallery样式的数据
function loadGalleryData(){
	var index=$('#index').val();
	var path=$('#path').val();
	var search_name=$('#search_name').val();
	var total_page=$('#total_page').val();
	var url = "AllFileInGalleryAction.action?index="+index+"&&search_name="+trim(search_name)+"&&path="+path; 
	
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
				//刷新刷新用户信息列表
				refreshGallery(data);
				//刷新路径条
				refreshPath(data);
				
				//绑定右击菜单
				bindRightMenu();
				
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
	
	loadGalleryData();
}

//点击刷新按钮刷新数据
function doRefresh(){
	//重置页数为1
	$('#index').val(1);
	//重新加载数据
	loadGalleryData();
}

//属性路径条
function refreshPath(data){
	$("#path_content").empty();
	var _tr = "<ol class='breadcrumb'>"+
		"<li><input type='checkbox' id='check_all' onclick='javascript:check_all(this.id)'></li>"; 
	var path_num=data.path_num;
	var num=0;
	$.each(data.path_list,function(i,value){ 
		num++;
		//显示根目录
		if(num==path_num){
			//是最后一层
			_tr =_tr + "<li class='active'>"+value.name+"</li>"; 
		}else{
			//不是最后一层
			_tr =_tr + "<li><a href='#' id='"+value.path+"' onclick='javascript:jumpTo(this.id)'>"+value.name+"</a></li>"; 
		}
	}) 
	_tr+"</ol>";
	var str=$(_tr);
	$("#path_content").append(str); 
}

//通过路径条进行跳转
function jumpTo(path){
	//重置页数为1
	$('#index').val(1);
	//设置path
	$('#path').val(path);
	//重新加载数据
	loadGalleryData();
}

//刷新Gallery样式的数据
function refreshGallery(data){
	$.each(data.file_list,function(i,value){ 
		var _tr = $("<div class='"+value.right_class+" span1 img-container' id='"+value.file_id+"'>"+
				"<div class='img-box'>"+
				"<span class='ltt-icon' >"+
				"<i class='gallery-check' onclick='javascript:check_item(this.id)' id='"+value.check_id+"'></i>"+
				"</span>"+
				"<img id='"+value.img_id+"' src='"+value.img+"' onclick='javascript:intoFolder(this.id)' />"+
				"<div id='"+value.file_name_id+"'>"+
				"<div id='"+value.show_name_id+"'>"+value.name+"</div>"+
				"</div>"+
				"</div>"+
				"</div>"); 
		$("#showTable").append(_tr); 
	}) 
}

//查看文件夹中的内容
function intoFolder(id){
	if(id.split("_")[0]!="not"){
		//重置页数为1
		$('#index').val(1);
		var path=$('#path').val();
		$('#path').val(path+id+"/");
		loadGalleryData();
	}
}

//检测滚动条是否到达底端
$(window).scroll(function(){
	var scrollTop = $(this).scrollTop();
	var scrollHeight = $(document).height();
	var windowHeight = $(this).height();
	if(scrollTop + windowHeight == scrollHeight){
		//到达底端，如果有没有加载的数据，就加载下一页数据
		loadGalleryData();
	}
});

//绑定右击菜单
function bindRightMenu(){
	//文件右击菜单
	$('div.file_item').contextMenu('file_menu', {
	      bindings: {
	        'download': function(t) {
	          alert('Trigger was '+t.id+'\nAction was download');
	        },
	        'share': function(t) {
	          alert('Trigger was '+t.id+'\nAction was share');
	        },
	        'rename': function(t) {
	        	rename_start(t);
	        },
	        'delete': function(t) {
		          alert('Trigger was '+t.id+'\nAction was delete');
	        }
	      }
	    });

	//文件夹右击菜单
	$('div.folder_item').contextMenu('folder_menu', {
	      bindings: {
	        'download': function(t) {
	          alert('Trigger was '+t.id+'\nAction was download');
	        },
	        'delete': function(t) {
	          alert('Trigger was '+t.id+'\nAction was delete');
	        },
	        'rename': function(t) {
	        	rename_start(t);
	        }
	      }
	});
}

function rename_start(file){
		var file_name_id="#file_name_"+file.id.split("_")[1];
		var new_name_id="new_name_"+file.id.split("_")[1];
		
		var _str=$("<input style='width:55px;height:20px;' onblur='javascript:rename_over(this.id)' value='"+$(file_name_id).html()+"' type='text' id='"+new_name_id+"' name='"+new_name_id+"' />");
		$(file_name_id).empty();
		$(file_name_id).append(_str); 
		$("#"+new_name_id).focus();
	}
	
function rename_over(new_name_id){
	//暂存旧名称
	var old_name=$("#"+new_name_id).val();
	//删除输入框
	var file_name_id="#file_name_"+new_name_id.split("_")[2];
	var _str=$(""+old_name);
	
	$(file_name_id).empty();
	$(file_name_id).html(old_name); 
}

function check_all(check_button_id){
	if ($("#"+check_button_id).is(":checked")){
		//执行全选操作
		$('i[id^="check_"]').each(function () {
        	var file_id="#file_"+this.id.split("_")[1];
        	if($(file_id).hasClass("ui-selected")){
        		//已经被选中
        	}else{
        		//尚未被选中
        		$("#"+this.id).removeClass("gallery-check");	
    			$(file_id).addClass("ui-selected");
    			$("#"+this.id).parent().removeClass("ltt-icon");
    			$("#"+this.id).parent().addClass("ltt-icon-checked");
        	}
        });
	}else{
		//取消全选操作
		$('i[id^="check_"]').each(function () {
        	var file_id="#file_"+this.id.split("_")[1];
        	if($(file_id).hasClass("ui-selected")){
        		//已经被选中
        		$(file_id).removeClass("ui-selected");
    			$("#"+this.id).addClass("gallery-check");	
    			$("#"+this.id).parent().addClass("ltt-icon");
    			$("#"+this.id).parent().removeClass("ltt-icon-checked");		
        	}else{
        		//尚未被选中
        	}
        });
	}
}

/* function findCheckedFile(){
	//用于查看那些被选中
    $('i[id^="check_"]').each(function () {
    	var file_id="#file_"+this.id.split("_")[1];
    	if($(file_id).hasClass("ui-selected")){
    		alert(file_id);
    	}
    });
} */

function check_item(id){
	var check_id="#"+id;
	var file_id="#file_"+id.split("_")[1];
	if($(file_id).hasClass("ui-selected")){
		$(file_id).removeClass("ui-selected");
		$(check_id).addClass("gallery-check");	
		$(check_id).parent().addClass("ltt-icon");
		$(check_id).parent().removeClass("ltt-icon-checked");		
	}else{
		$(check_id).removeClass("gallery-check");	
		$(file_id).addClass("ui-selected");
		$(check_id).parent().removeClass("ltt-icon");
		$(check_id).parent().addClass("ltt-icon-checked");		
	}		  
}

 function change(type){
	$("li").removeClass("active");
	$(".share_display").css("display","none"); 
	$("#"+type).addClass("active"); 
	$("#div_"+type).css("display","block"); 
};

$("#btn_newFolder").click(function(){
    //$("#file6").after('<div class="span1 img-container" id="file7"><div class="img-box"><span class="ltt-icon" ><i class="gallery-check" id="check7"></i></span><img src="img/icon/icon_folder.png" /><p class="title"> 新建文件夹</p></div></div>');
	//$("#file7").append($(".gallery-row"));  
	var newFile='<div class="span1 img-container" id="file7"><div class="img-box"><span class="ltt-icon" ><i class="gallery-check" id="check7"></i></span><img src="img/icon/icon_folder.png" /><p class="title"> 新建文件夹</p></div></div>';              
	$(".gallery-row").prepend(newFile);   
		});
		
	$("#file4").click(function(){
        var myPDF = new PDFObject({ url: "test.pdf" }).embed("show-file");
		$('#fileModal').modal('toggle');
});