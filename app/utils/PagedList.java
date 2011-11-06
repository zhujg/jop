package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用于保存分页信息和负责分页逻辑运算的类，这个类配合标签使用最佳！<br />
 * <b>这个类不是线程安全的</b>
 * </p>
 * 
 * 使用方法： <br />
 * PagedList&lt;Person&gt; pList=new PagedList&lt;Person&gt;();<br />
 * pList.setPageIndex();//当前页的页码<br />
 * pList.setPageSize();//每页的大小<br />
 * <br />
 * pList.setRowCount(dao.count(...));//获取数据库里的总记录数<br />
 * pList.setList(dao.list(...));//从数据库获取当前页的数据<br />
 * <br />
 * pList.addParam("category","5");//设置其他的url参数<br />
 * request.setAttribute("pagedList",pList);//放入request<br />
 * 至此，PagedList的数据填充就完成了，下一步是在JSP页面内做显示<br />
 * <br />
 * 使用标签的时候，只需要这样：<br />
 * &lt;ext:defaultPageBar pagedList="${pagedList}" pageIndexKey="pageIndex"
 * /&gt;
 * 
 * @author Jonney
 */
public class PagedList<E> {
	/**
	 * 当前页码
	 */
	private int pageIndex;

	/**
	 * 页大小
	 */
	private int pageSize;

	/**
	 * 页数量
	 */
	private int pageCount;

	/**
	 * 行数量
	 */
	private long rowCount;

	/**
	 * 目标页的uri
	 */
	private String uri;

	/**
	 * 数据列表
	 */
	private List<E> list = new ArrayList<E>();

	private String paramEncoding = "utf-8";

	private List<Param> paramList = new ArrayList<Param>();

	/**
	 * 唯一的构造器，强制设置pageSize和pageIndex，这样才能保证setRowCount(int)不会出错
	 * 
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            页大小
	 */
	public PagedList(Integer pageIndex, int pageSize) {
		if(pageIndex == null) pageIndex = 0;
		this.pageIndex = pageIndex < 1 ? 1 : pageIndex;	
		this.pageSize = pageSize;
	}

	/**
	 * @return pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @return rowCount
	 */
	public long getRowCount() {
		return rowCount;
	}

	/**
	 * @param rowCount
	 *            要设置的 rowCount
	 */
	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
		pageCount = (int) (((rowCount + pageSize - 1) / pageSize));
		if(pageCount<=0){
			pageCount=1;
		}
		if (pageCount > 0 && pageIndex > pageCount) {
			pageIndex = pageCount;
		}
	}

	/**
	 * @return pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @return uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * 这个设置不是必须的，如果不设置，那么表示uri为当前页
	 * 
	 * @param uri
	 *            要设置的 uri
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return list
	 */
	public List<E> getList() {
		return list;
	}

	/**
	 * @param list
	 *            要设置的 list
	 */
	public void setList(List<E> list) {
		this.list = list;
	}

	/**
	 * @return paramEncoding
	 */
	public String getParamEncoding() {
		return paramEncoding;
	}

	/**
	 * @param paramEncoding
	 *            要设置的 paramEncoding
	 */
	public void setParamEncoding(String paramEncoding) {
		this.paramEncoding = paramEncoding;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void addParam(String key, String value) {
		if (key == null) {
			return;
		}
		if (value == null) {
			value = "";
		}
		Param p = new Param(key, value);
		paramList.add(p);
	}

	public void addParam(Map<String,String> params) {
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = params.get(key);
			Param p = new Param(key, value);
			paramList.add(p);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getParams() {
		StringBuilder sb = new StringBuilder();
		try {
			for (Param p : paramList) {
				sb.append('&');
				sb.append(URLEncoder.encode(p.key, paramEncoding));
				sb.append('=');
				sb.append(URLEncoder.encode(p.value, paramEncoding));
			}
		} catch (UnsupportedEncodingException e) {
			// throw new RuntimeException(e);
		}
		return sb.toString();
	}

	/**
	 * 取当前页的第一行数据在数据库中的行号，在操作数据库的时候有用
	 * 
	 * @return
	 */
	public int getFirstRowInThisPage() {
		return (pageIndex - 1) * pageSize;
	}

	/**
	 * 是否第一页
	 * 
	 * @return
	 */
	public boolean isFirstPage() {
		return pageIndex <= 1 ? true : false;
	}

	/**
	 * 是否最后一页
	 * 
	 * @return
	 */
	public boolean isLastPage() {
		return pageIndex >= pageCount ? true : false;
	}

	public class Param {

		public String key;

		public String value;

		public Param(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	public static void main(String[] args) {
		PagedList<String> p = new PagedList<String>(3, 4);
		p.addParam("1", "中文");
		p.addParam("2", "22");
		p.addParam("3", "33");
		System.out.println(p.getParams());
		p.setRowCount(40);
		System.out.println(p.getPageCount());
	
	}
}
