package models;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import play.data.validation.Required;
import play.db.jpa.Model;
import utils.Tree;
import utils.TreeUtils;

@MappedSuperclass
public class TreeModel extends Model{
	
	@Required
	public String name;
	public String cid;
	public String pid;
	public Integer sort;
	
	@PrePersist
	@PreUpdate
	public void plan(){
		cid = "01";
		pid = "0";
		System.out.println(cid);
	}
	
	public String toString() {
		return name;
	}
}
