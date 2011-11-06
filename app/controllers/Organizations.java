package controllers;

import java.util.List;

import jui.Result;
import models.Organization;
import play.data.validation.Valid;
import utils.PagedList;

public class Organizations extends Application{

	public static void list(){
		PagedList<Organization> pagedList = new PagedList(getPageIndex(),getPageSize());
		Organization.findPage(pagedList);
		render(pagedList);
	}
	
	public static void add(){
		render();
	}
	
	public static void save(@Valid Organization organization){
		if(validation.hasErrors()){
			render("@add",organization);
		}
		organization.save();
		Result result = new Result("organization_list");
		renderJSON(result);
	}
	
	public static void edit(Long id){
		Organization organization = Organization.findById(id);
		if(organization==null) {
			Result result = new Result(301,"数据不存在");
			renderJSON(result);
		}
		render(organization);
	}
	 
	public static void update(@Valid Organization organization){
		if(validation.hasErrors()){
			render("@add",organization);
		}
		
		organization.save();
		Result result = new Result("organization_list");
		renderJSON(result);
	}
	
	public static void delete(Long id){
		Organization organization = Organization.findById(id);
		if(organization!=null) organization.delete();
		Result result = new Result("organization_list",false);
		renderJSON(result);
	}
	
}
