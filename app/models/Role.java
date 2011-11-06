package models;

import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;
import utils.ModelUtils;
import utils.PagedList;

/**
 * 角色管理
 * @author Administrator
 */
@Entity(name="roles")
public class Role extends Model{

	public String name;		//名称
	public Integer flag;	//状态
	public String note;		//备注
	
	public static void findPage(PagedList<Role> pagedList){
		long count = ModelUtils.count(Role.class.getName(),null,null,null,null,null);
		pagedList.setRowCount(count);
		List<Role> list = ModelUtils.findPage(pagedList.getFirstRowInThisPage(), pagedList.getPageSize(), Role.class.getName(),null,null,null,null,null,null,null);
		pagedList.setList(list);
	}
}
