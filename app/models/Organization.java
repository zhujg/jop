package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
import utils.ModelUtils;
import utils.PagedList;

/**
 * 组织机构
 * @author Administrator
 */
@Entity(name="organizations")
public class Organization extends Model{

	public String code;
	public String name;				//部门名称
	@ManyToOne public Organization parent;		//上级部门
	public Integer sort;			//排序
	public String note;				//备注
	
	public static void findPage(PagedList<Organization> pagedList){
		long count = ModelUtils.count(Organization.class.getName(),null,null,null,null,null);
		pagedList.setRowCount(count);
		List<Organization> list = ModelUtils.findPage(pagedList.getFirstRowInThisPage(), pagedList.getPageSize(), Organization.class.getName(),null,null,null,null,null,null,null);
		pagedList.setList(list);
	}
}
