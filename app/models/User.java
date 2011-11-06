package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
import utils.ModelUtils;
import utils.PagedList;

@Entity(name="users")
public class User extends Model{

	public String name;						//人员名称
	@ManyToOne public Organization depart;	//所属部门
	public String account;					//登录帐户
	public String pass;						//密码
	public Integer flag;					//状态
	public Integer sex;						//性别
	public String note;						//备注
	
	
	public static void findPage(PagedList<User> pagedList){
		long count = ModelUtils.count(User.class.getName(),null,null,null,null,null);
		pagedList.setRowCount(count);
		List<User> list = ModelUtils.findPage(pagedList.getFirstRowInThisPage(), pagedList.getPageSize(), User.class.getName(),null,null,null,null,null,null,null);
		pagedList.setList(list);
	} 
}
