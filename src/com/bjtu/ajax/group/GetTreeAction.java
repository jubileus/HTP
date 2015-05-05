package com.bjtu.ajax.group;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.bjtu.model.bo.TreeNode;
import com.bjtu.model.pojo.Tb_group;
import com.bjtu.model.pojo.Tb_member;
import com.bjtu.model.pojo.Tb_user;
import com.bjtu.service.group.IGroupService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class GetTreeAction extends ActionSupport{
	private IGroupService group_service;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
        HttpSession session = request.getSession();
        Tb_user user=(Tb_user)session.getAttribute("user");
        
        List<TreeNode> tree=getTree(user);
        
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        String str = JSONArray.fromObject(tree).toString();
        pw.write(str);
        pw.flush();
        pw.close();
		return SUCCESS;
	}
	
	private List<TreeNode> getTree(Tb_user user){
		//声明结果List
        List<TreeNode> tree=new ArrayList<TreeNode>();
        TreeNode node;
        //获取用户全部群组
        List<Tb_group> group_list=group_service.getGroup(user.getId());
        if(group_list!=null&&group_list.size()>0){
        	int parent_index=0;
        	Tb_group group;
        	List<Tb_member> member_list;
        	for(int i=0;i<group_list.size();i++){
        		group=group_list.get(i);
        		member_list=group_service.getMemberByGroupId(group.getId());
        		//写入父节点信息
        		parent_index++;
        		node=new TreeNode();
        		node.setpId(0);
        		node.setId(parent_index);
        		node.setGroup_id(group.getId());
        		node.setOpen(true);
        		node.setName(group.getName());
        		node.setUser_id("");
        		tree.add(node);
        		//循环写入子节点信息
        		if(member_list!=null&&member_list.size()>0){
        			Tb_member member;
        			int child_index=100*parent_index;
        			for(int j=0;j<member_list.size();j++){
        				member=member_list.get(j);
        				child_index++;
        				node=new TreeNode();
                		node.setpId(parent_index);
                		node.setId(child_index);
                		node.setGroup_id("");
                		node.setOpen(false);
                		node.setName(member.getNickname());
                		node.setUser_id(member.getId());
                		tree.add(node);
        			}
        		}
        	}
        }
        return tree;
	}

	@JSON(serialize=false)
	public IGroupService getGroup_service() {
		return group_service;
	}

	public void setGroup_service(IGroupService group_service) {
		this.group_service = group_service;
	}

}
