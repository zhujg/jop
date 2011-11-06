package models;

import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;
import utils.ModelUtils;
import utils.PagedList;

/**
 * 菜单资源
 * @author Administrator
 */
@Entity(name="menus")
public class Menu extends TreeModel{

	public String code;		//菜单编码
	public String url;		//请求地址
	public Integer flag;	//状态
	public String note;		//备注
	
	public static void findPage(PagedList<Menu> pagedList){
		long count = ModelUtils.count(Menu.class.getName(),null,null,null,null,null);
		pagedList.setRowCount(count);
		List<Menu> list = ModelUtils.findPage(pagedList.getFirstRowInThisPage(), pagedList.getPageSize(), Menu.class.getName(),null,null,null,null,null,null,null);
		pagedList.setList(list);
	} 
}
