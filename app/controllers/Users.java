package controllers;

import java.util.List;

import jui.Result;
import models.User;
import play.data.validation.Valid;
import utils.PagedList;

public class Users extends Application{

	public static void list(){
		PagedList<User> pagedList = new PagedList(getPageIndex(),getPageSize());
		User.findPage(pagedList);
		render(pagedList);
	}
	
	public static void add(){
		render();
	}
	
	public static void save(@Valid User user){
		if(validation.hasErrors()){
			render("@add",user);
		}
		user.save();
		Result result = new Result("user_list");
		renderJSON(result);
	}
	
	public static void edit(Long id){
		User user = User.findById(id);
		if(user==null) {
			Result result = new Result(301,"数据不存在");
			renderJSON(result);
		}
		render(user);
	}
	 
	public static void update(@Valid User user){
		if(validation.hasErrors()){
			render("@add",user);
		}
		
		user.save();
		Result result = new Result("user_list");
		renderJSON(result);
	}
	
	public static void delete(Long id){
		User user = User.findById(id);
		if(user!=null) user.delete();
		Result result = new Result("user_list",false);
		renderJSON(result);
	}
	
}
