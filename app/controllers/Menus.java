package controllers;

import java.util.List;

import jui.Result;
import models.Menu;
import play.data.validation.Valid;
import utils.PagedList;

public class Menus extends Application{

	public static void list(){
		PagedList<Menu> pagedList = new PagedList(getPageIndex(),getPageSize());
		Menu.findPage(pagedList);
		render(pagedList);
	}
	
	public static void add(){
		render();
	}
	
	public static void save(@Valid Menu menu){
		if(validation.hasErrors()){
			render("@add",menu);
		}
		menu.save();
		Result result = new Result("menu_list");
		renderJSON(result);
	}
	
	public static void edit(Long id){
		Menu menu = Menu.findById(id);
		if(menu==null) {
			Result result = new Result(301,"数据不存在");
			renderJSON(result);
		}
		render(menu);
	}
	 
	public static void update(@Valid Menu menu){
		if(validation.hasErrors()){
			render("@add",menu);
		}
		
		menu.save();
		Result result = new Result("menu_list");
		renderJSON(result);
	}
	
	public static void delete(Long id){
		Menu menu = Menu.findById(id);
		if(menu!=null) menu.delete();
		Result result = new Result("menu_list",false);
		renderJSON(result);
	}
	
}
