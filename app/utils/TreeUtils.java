package utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import models.TreeModel;
import play.db.jpa.JPA;

public class TreeUtils {
	
	public static List<Tree> setTree(String className){
		List<Tree> trees = new ArrayList<Tree>();
		String sql = "from "+className+" order by cid asc";
		Query query = JPA.em().createQuery(sql);
		List<TreeModel> list = query.getResultList();
		//获取list遍历为|-格式
		for(int i =0;i<list.size();i++){
			TreeModel tree = list.get(i);
			StringBuffer sb = new StringBuffer();
			for(int j = 0; j < tree.cid.length(); j++) {
			    sb.append("&#160;");
			}
			tree.name=sb.toString()+"|-"+tree.name;
			Tree t = new Tree(tree.id,tree.name,tree.pid,tree.cid);
			trees.add(t);
		}
		return trees;
	}

	
	
	public static String getCid(String cid,String pid,String className){
		//是否是修改
		String sql="from "+className+ " e where e.cid = '"+cid+"' and e.pid = '"+pid+"'";
		Query query = JPA.em().createQuery(sql);
		//如果不存在,cid改变
		if(query.getResultList().isEmpty())
		{
			//是否存在该节点最大cid
			String sql2 = "select max(cid) from "+className + " e where e.pid = '"+pid+"'";
			Query query2 = JPA.em().createQuery(sql2);
			//存在
			if(query2.getSingleResult()!=null)
			{
				String newcid=query2.getSingleResult().toString();
				String id= TreeUtils.plan(newcid, pid);
				//入栈
				stack(cid,id,className);
				return id;
			}//不存在
			else{
				//如果pid为0,数据不存在
				if(!"0".equals(pid))
				{
					String id=pid+"001";
					//入栈
					stack(cid,id,className);
					return id;
				}
				else
					return "001";
			}
		}
		return cid;
	}
	
	
	//计算
	public static String plan(String cid,String pid){
		String str=String.valueOf(Long.parseLong(cid)+1);
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < cid.length()-str.length(); i++) {
		    sb.append("0");
		}
		sb.append(str);
		return sb.toString();
	}
	
	//入栈
	public static void stack(String cid,String pid,String className){
		String sql = "from "+className+" e where e.pid = '"+cid+"'";
		Query query = JPA.em().createQuery(sql);
		List<TreeModel> list = query.getResultList();
		for(int i =0;i<list.size();i++){
			String oldcid=list.get(i).cid;
			String newcid=list.get(i).cid.replace(list.get(i).cid.substring(0,list.get(i).cid.length()-3),pid);
			String sql2="update "+className+" e set e.cid = '"+newcid+"' , e.pid = '"+pid+"' where e.id = "+list.get(i).id;
			//if(!cid.equals(list.get(0).cid))	
			JPA.execute(sql2);
			stack(oldcid,newcid,className);
		}
	}
	
}
