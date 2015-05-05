<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head_css.jsp" %>
	<!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <a class="brand" href="index.html"><img src="/HTP/pages/img/logo.png" /></a>
            <!--产品logo图片-->
            <ul class="nav pull-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle hidden-phone" data-toggle="dropdown">
                        <%
                    	Tb_user user=(Tb_user)session.getAttribute("user");
                    	if(user==null){
	               		%>	
	               			<jsp:forward page="/pages/login.jsp" />
	               		<%
	                    	}else{
	                    %>
	                    	<%=user.getNickname() %>
	                    <%
	                    	}
	                    %>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="UserInfoAction.action">个人资料</a></li>
                        <li><a href="LogoutAction.action">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!-- end navbar -->
<%@ include file="head_sidebar.jsp" %>

<!-- main container -->
<style type="text/css">
        .scrollspy-example {
            height: 500px;
            overflow: auto;
            position: relative;
        }
        #upload div{
            display: inline;
        }
</style>
<div class="content">
    <!-- settings changer -->
    <div class="container-fluid">
        <!-- upper main stats -->
        <div id="main-stats">
            <div class="row-fluid stats-row">
                <div class="span6">
                    <a href="#createGroup" data-toggle="modal" class="btn-flat white" style="margin-left: 15px;">创建群组</a>
                    <a href="#deleteGroup" data-toggle="modal" onclick="javascript:openDeleteGroupMenu()" class="btn-flat white" style="margin: 10px">删除群组</a>
                    <a href="#addMember" data-toggle="modal" onclick="javascript:openAddGroupMemberMenu()" class="btn-flat white" style="margin: 10px 10px 10px 0">添加成员</a>
                    <a href="javascript:deleteMember()" class="btn-flat white" style="margin: 10px 10px 10px 0">删除成员</a>
                </div>
                <div class="span5">
                </div>
            </div>
			
            <div class="modal hide fade" id="createGroup">
	            <div class="modal-dialog">
	            	<div class="modal-content">
	            		<div class="modal-header">
                   			 <button type="button" id="close_create_group" class="close" aria-label="Close" data-dismiss="modal">×</button>
                   			 <h4>创建群组</h4>
               			</div>
		                <div align="center" class="modal-body">
	                        <div class="control-group">
	                            <div class="controls">群组名 
	                                <input name="group_name" id="group_name" type="text">
	                            </div>
	                        </div>
		                </div>
		                <div class="modal-footer">
	                        <button onclick="javascript:createGroup()" class="btn btn-small btn-success">创建</button>
	                    </div>
	            	</div>
	            </div>
            </div>

			<div class="modal hide fade" id="deleteGroup">
	            <div class="modal-dialog">
	            	<div class="modal-content">	
	            		<div class="modal-header">
	                		<button type="button" id="close_delete_group" class="close" aria-label="Close" data-dismiss="modal">×</button>
	                		<h4>删除群组</h4>
	           			 </div>
            			 <div align="center" class="modal-body">
		                    <div class="control-group">
		                        <div class="controls" id="select_to_delete_div">
		                        </div>
		                    </div>
                		</div>
		                <div class="modal-footer">
		                    <button onclick="javascript:deleteGroup()" class="btn btn-small btn-success">删除</button>
		                </div>
	            	</div>
	            </div>
        	</div>            

            <div class="modal hide fade" id="addMember">
	            <div class="modal-dialog">
	            	<div class="modal-content">	
	            		<div class="modal-header">
	                		<button type="button" id="close_add_member" class="close" aria-label="Close" data-dismiss="modal">×</button>
	                		<h4>添加成员</h4>
	           			 </div>
            			 <div align="center" class="modal-body">
		                    <div class="control-group">
		                        <div class="controls" id="select_to_add_div">
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <div class="controls">
		                            <input type="text" placeholder="用户邮箱" name="member_email" id="member_email" />
		                        </div>
		                    </div>
                		</div>
		                <div class="modal-footer">
		                    <button onclick="javascript:addGroupMember()" class="btn btn-small btn-success">添加</button>
		                </div>
	            	</div>
	            </div>
        	</div>
        	
            <div class="row">
                <div class="span4" style="border: thin solid #d3d3d3;margin-left: 50px">
                    <div style="min-height: 538px;min-width: 200px" id="context" data-toggle="context"
                         data-target="#context-menu">
                        <h3 style="margin: 10px;text-align: center; padding: 5px">我的群组</h3>

                        <ul id="tree" class="ztree" style="width:560px; overflow:auto;margin-left: 50px"></ul>
                    </div>
                </div>
                <div class="span6">
                    <div style="border: thin solid #d3d3d3; min-height: 462px;">
                        <div data-spy="scroll" data-target="#navbarExample" data-offset="50" class="scrollspy-example">
                            <br/>
                            <ul class="content-reply-box span2">
                                <li class="odd">
                                    <a class="user" href="#"><img class="img-responsive avatar_" src="img/avatar-1.png"
                                                                  alt=""><span class="user-name">大毛</span></a>

                                    <div class="reply-content-box">
                                        <span class="reply-time">03-08 15：00</span>

                                        <div class="reply-content pr">
                                            <span class="arrow">&nbsp;</span>
                                            c语言进阶 <a>下载</a>&nbsp;&nbsp;<a>删除</a>
                                        </div>
                                    </div>
                                </li>
                                <li class="even">
                                    <a class="user" href="#"><img class="img-responsive avatar_" src="img/avatar-1.png"
                                                                  alt=""><span class="user-name">二毛</span></a>

                                    <div class="reply-content-box">
                                        <span class="reply-time">03-08 15：10</span>

                                        <div class="reply-content pr">
                                            <span class="arrow">&nbsp;</span>
                                            c语言进阶2 <a>下载</a>&nbsp;&nbsp;<a>删除</a>
                                        </div>
                                    </div>
                                </li>
                                <li class="even">
                                    <a class="user" href="#"><img class="img-responsive avatar_" src="img/avatar-1.png"
                                                                  alt=""><span class="user-name">二毛</span></a>

                                    <div class="reply-content-box">
                                        <span class="reply-time">03-08 15：10</span>

                                        <div class="reply-content pr">
                                            <span class="arrow">&nbsp;</span>
                                            c语言进阶2 <a>下载</a>&nbsp;&nbsp;<a>删除</a>
                                        </div>
                                    </div>
                                </li>
                                <li class="even">
                                    <a class="user" href="#"><img class="img-responsive avatar_" src="img/avatar-1.png"
                                                                  alt=""><span class="user-name">二毛</span></a>

                                    <div class="reply-content-box">
                                        <span class="reply-time">03-08 15：10</span>

                                        <div class="reply-content pr">
                                            <span class="arrow">&nbsp;</span>
                                            c语言进阶2 <a>下载</a>&nbsp;&nbsp;<a>删除</a>
                                        </div>
                                    </div>
                                </li>
                                <li class="odd">
                                    <a class="user" href="#"><img class="img-responsive avatar_" src="img/avatar-1.png"
                                                                  alt=""><span class="user-name">三毛</span></a>

                                    <div class="reply-content-box">
                                        <span class="reply-time">03-08 15：20</span>

                                        <div class="reply-content pr">
                                            <span class="arrow">&nbsp;</span>
                                            c语言进阶 <a>下载</a>&nbsp;&nbsp;<a>删除</a>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>

                    </div>

                    <div style="border:thin solid #d3d3d3;padding: 5px; margin-top: 5px; text-align: center">
                        <a href="" class="btn-flat default">上一页</a>
                        <a href="" class="">1</a>
                        <a href="" class="btn-flat default">下一页</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end upper main stats -->


<!-- scripts -->
<script src="/HTP/pages/js/jquery-latest.js"></script>
<script src="/HTP/pages/js/bootstrap.min.js"></script>
<script src="/HTP/pages/project_js/common.js"></script>
<script src="/HTP/pages/project_js/group.js"></script>
<!-- knob -->
<script src="/HTP/pages/js/jquery.ztree.all-3.5.min.js"></script>

<script>
    var zTree;
    var demoIframe;
    
    var setting = {
        check: {
            enable: true
        },
        view: {
            dblClickExpand: false,
            showLine: false,
            selectedMulti: false
        }, 
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
    		onClick: zTreeOnClick
    	}
    };
    
    function zTreeOnClick(event, treeId, treeNode) {
    	var group_id=treeNode.group_id;
    	if(group_id.length>0){
    		//点击的是群组，加载该群组第一页的分享
    		alert(group_id);
    	}else{
    		//点击的是组员，不做任何操作
    	}
    };

    //页面初始加载方法
    $(document).ready(function () {
    	//调整右侧边框栏选中情况
    	refreshTree();
    });
    
    //刷新树状图
    function refreshTree(){
    	var url = "GetTreeAction.action"; 
    	$.ajax({ 
    		type:'get', 
    		url:url, 
    		dataType: 'json', 
    		async: false,    
    		success:function(data){ 
    			var node=data;
    			var t = $("#tree");
    	        t = $.fn.zTree.init(t, setting, node);
    	        demoIframe = $("#testIframe");
    	        demoIframe.bind("load", loadReady);
    	        var zTree = $.fn.zTree.getZTreeObj("tree");
    		} 
    	})
    }
    
    function loadReady() {
        var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
                htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
                maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
                h = demoIframe.height() >= maxH ? minH : maxH;
        if (h < 530) h = 530;
        demoIframe.height(h);
    }

    function closeShareFile(){
        $('#shareFile').css("display","none");
    }

    function goToGroupShare(){
        window.location.href = "group_share.html";
    }
</script>

</body>
</html>