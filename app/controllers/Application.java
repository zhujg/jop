package controllers;

import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static Integer getPageSize(){
    	return 30;
    }
    
    public static Integer getPageIndex(){
    	Integer page = params.get("pageNum",Integer.class);
    	return page == null ? 1 : page;
    }
}