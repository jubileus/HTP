<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

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
                    <a href="javascript:queren()" class="btn-flat white" style="margin: 10px">删除群组</a>
                    <a href="#addMember" data-toggle="modal" onclick="javascript:openAddGroupMemberMenu()" class="btn-flat white" style="margin: 10px 10px 10px 0">添加成员</a>
                    <a href="" class="btn-flat white" style="margin: 10px 10px 10px 0">删除成员</a>
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
                                            c语言进阶 <a>下载</a>
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
                                            c语言进阶2 <a>下载</a>
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
                                            c语言进阶2 <a>下载</a>
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
                                            c语言进阶2 <a>下载</a>
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
                                            c语言进阶 <a>下载</a>
                                        </div>
                                    </div>
                                </li>
                                <li class="even">
                                    <a class="user" href="#"><img class="img-responsive avatar_" src="img/avatar-1.png"
                                                                  alt=""><span class="user-name">大哥</span></a>

                                    <div class="reply-content-box">
                                        <span class="reply-time">03-08 15：30</span>

                                        <div class="reply-content pr">
                                            <span class="arrow">&nbsp;</span>
                                            c语言进阶6 <a>下载</a>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>

                    </div>

                    <div style="border:thin solid #d3d3d3;padding: 5px; margin-top: 5px; text-align: center">
                        <a href="#shareFile" data-toggle="modal" class="btn-flat default ui-link" style="margin-left: 15px;">分享文件</a>
                        <a href="javascript:goToGroupShare()" class="btn-flat default">文件库</a>
                    </div>

                    <div class="modal hide fade" id="shareFile" tabindex="-1" role="dialog"   aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"  aria-hidden="true" onclick="closeShareFile()">×</button>
                            <h4>分享文件</h4>
                        </div>

                            <div class="modal-body">
                                <div class="control-group">
                                    <div class="table-wrapper products-table">
                                        <table class="table table-hover">
                                            <thead>
                                            <tr>
                                                <th class="span9">
                                                    <input type="checkbox" />文件名
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <!-- row -->
                                            <tr>
                                                <td>
                                                    <input type="checkbox" />
                                                    <div class="img">
                                                        <img src="img/table-img.png" />
                                                    </div>
                                                    <a href="#" class="name">算法精解-c语言描述</a>
                                                </td>
                                            </tr>
                                            <!-- row -->
                                            <tr>
                                                <td>
                                                    <input type="checkbox" />
                                                    <div class="img">
                                                        <img src="img/table-img.png" />
                                                    </div>
                                                    <a href="#" class="name">算法精解-c语言描述 </a>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-small btn-success">分享</button>
                            </div>
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
        }
    };

    var zNodes = [
        {id: 1, pId: 0, name: "组1", click:"alert('haha');", open: true},
        {id: 101, pId: 1, name: "张三", user_id: "user_id_1"},
        {id: 102, pId: 1, name: "李四", user_id: "user_id_2"},
        {id: 103, pId: 1, name: "王五", user_id: "user_id_3"}
    ];

    $(document).ready(function () {
        var t = $("#tree");
        t = $.fn.zTree.init(t, setting, zNodes);
        demoIframe = $("#testIframe");
        demoIframe.bind("load", loadReady);
        var zTree = $.fn.zTree.getZTreeObj("tree");
        //zTree.selectNode(zTree.getNodeByParam("id", 101));

    });
    
    function refreshGroup(){
    	var treeObj = $.fn.zTree.getZTreeObj("tree");
    	var treeNode = treeObj.getCheckedNodes(true);
    	var IDs;
    	for (x in treeNode) {
    	    if (!treeNode[x].isParent) {
    	    	alert(treeNode[x].user_id);
    	    }
    	}
    	
    	/* zNodes = [
    	              {id: 1, pId: 0, name: "组1", open: true},
    	              {id: 101, pId: 1, name: "张三", file: "core/standardData"},
    	              {id: 102, pId: 1, name: "李四", file: "core/simpleData"},
    	              {id: 103, pId: 1, name: "王五", file: "core/noline"},
    	              
    	              {id: 2, pId: 0, name: "组2", open: true},
    	              {id: 201, pId: 2, name: "张三", file: "core/standardData"},
    	              {id: 202, pId: 2, name: "李四", file: "core/simpleData"},
    	              {id: 203, pId: 2, name: "王五", file: "core/noline"}
    	];
    	 var t = $("#tree");
         t = $.fn.zTree.init(t, setting, zNodes);
         demoIframe = $("#testIframe");
         demoIframe.bind("load", loadReady);
         var zTree = $.fn.zTree.getZTreeObj("tree"); */
    }

    function loadReady() {
        var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
                htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
                maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
                h = demoIframe.height() >= maxH ? minH : maxH;
        if (h < 530) h = 530;
        demoIframe.height(h);
    }

    function queren()
    {
        var se = confirm("请确认删除?");
        if (se==true)
        {
            alert("已删除！");
        }
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