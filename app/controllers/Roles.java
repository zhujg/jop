package controllers;

import java.util.List;

import jui.Result;
import models.Role;
import play.data.validation.Valid;
import utils.PagedList;

public class Roles extends Application{

	public static void list(){
		PagedList<Role> pagedList = new PagedList(getPageIndex(),getPageSize());
		Role.findPage(pagedList);
		render(pagedList);
	}
	
	public static void add(){
		render();
	}
	
	public static void save(@Valid Role role){
		if(validation.hasErrors()){
			render("@add",role);
		}
		role.save();
		Result result = new Result("role_list");
		renderJSON(result);
	}
	
	public static void edit(Long id){
		Role role = Role.findById(id);
		if(role==null) {
			Result result = new Result(301,"数据不存在");
			renderJSON(result);
		}
		render(role);
	}
	 
	public static void update(@Valid Role role){
		if(validation.hasErrors()){
			render("@add",role);
		}
		
		role.save();
		Result result = new Result("role_list");
		renderJSON(result);
	}
	
	public static void delete(Long id){
		Role role = Role.findById(id);
		if(role!=null) role.delete();
		Result result = new Result("role_list",false);
		renderJSON(result);
	}
	
	public static void authorize(Long id){
		render();
	}
}
