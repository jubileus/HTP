<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head_css.jsp" %>
<!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <a class="brand" href="index.html"><img src="/HTP/pages/img/logo.png" /></a>
            <!--产品logo图片-->
            <ul class="nav pull-right">
                <li class="hidden-phone">
                    <input class="search" onblur="javascript:searchData()" id="search_name" name="search_name" type="text" />
                    <!--内有默认文字-->
                </li>
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
	<div class="content">
    <!-- settings changer -->
	    <div class="container-fluid">
		    <div id="pad-wrapper">
		    	<div class="table-wrapper products-table section">
			        <div class="row-fluid head">
				        <div class="span12">
				            <h4>我的分享</h4>
				        </div>
			        </div>
			        <div class="row-fluid stats-row">
				        <div class="span6">
				        	<a class="btn-flat white" onclick="javascript:cancelMulti()" style="margin: 15px;">取消分享</a>
				        	<button type="button" hidden="true" id="share_menu" data-toggle="modal" href="#shareModal">分享菜单 </button>
				        </div>
			            <div class="span5"></div>
			            <input type="hidden" id="total_page" value="<s:property value="total_page" />" />
			            <input type="hidden" id="index" value="<s:property value="index" />" />
			            <input type="hidden" id="share_path" value="<s:property value="share_path" />" />
			        </div>
			        <div class="row-fluid">
			            <table class="table table-hover">
			                <thead>
				                <tr>
					                <th class="span3"><input type="checkbox" /></th>
					                <th class="span3">文件名</th>
					                <th class="span3">分享日期</th>
					                <th class="span3">类型</th>
				                </tr>
			            	</thead>
			            	<tbody id="showTable">
			                </tbody>
			            </table>
			        </div>
		    	</div>
		    	
		    	<!--share Modal -->
				<div class="modal hide fade" id="shareModal" >			
				  <div class="modal-dialog">
					<div class="modal-content">
					  <div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">文件链接如下</h4>
					  </div>
					  <div id="progress_msg" class="field-box" align="center">
					  	  <textarea id="share_link" class="form-control" rows="3" ></textarea><br />
					  </div>
					  <!--end modal body -->
					  <div class="modal-footer">
						 <button type="button" id="close_share_menu" data-dismiss="modal" class="btn btn-primary">关闭</button>
				      </div>				  
					</div>
				  </div>
				</div>
				<!--end share modal-->	
		        <!-- end products table -->
		    </div>
	    </div>
  		<!-- end orders table -->
	</div>
  <!-- scripts -->
  <script src="/HTP/pages/js/jquery-latest.js"></script> 
  <script src="/HTP/pages/js/bootstrap.min.js"></script> 
  <script src="/HTP/pages/js/jquery-ui-1.10.2.custom.min.js"></script> 
  <!-- knob -->
   
  <script src="/HTP/pages/js/jquery.knob.js"></script> 
  <!-- flot charts -->
   
  <script src="/HTP/pages/js/jquery.flot.js"></script> 
  <script src="/HTP/pages/js/jquery.flot.stack.js"></script> 
  <script src="/HTP/pages/js/jquery.flot.resize.js"></script> 
  <script src="/HTP/pages/js/theme.js"></script> 
  <script src="/HTP/pages/project_js/common.js"></script> 
  <script src="/HTP/pages/project_js/share.js"></script> 
  <script type="text/javascript">
    $(document).ready(function() {
		loadShareData();
	});
  </script>
  
  </body>
</html>