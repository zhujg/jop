package utils;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class MyUtils {

	public static final String Exception_Head = "\n非常遗憾的通知您,程序发生了异常.\n" + "异常信息如下:\n";
	
	public static String tree_code(String pid,Object cid) {
		if(cid == null){
			return "0".equals(pid) ? "001" : pid+"001";
		}
		String code = cid.toString();
		if("0".equals(code))return pid+"001";
		String cidfirst = code.substring(0, code.length() - 3);
		String cidend = code.substring(code.length() - 3, code.length());
		Integer icid = Integer.parseInt(cidend); 
		icid += 1;
		String cidresult = String.valueOf(icid);
		int i = 3 - cidresult.length();
		for (int j = 0; j < i; j++) {
			cidresult = "0" + cidresult;
		}
		return cidfirst + cidresult;
	}
	
	public static String makeAllWordFirstLetterUpperCase(String name) {
		String[] strs = name.split("_");
		String result = "";
		String preStr = "";
		for (int i = 0; i < strs.length; i++) {
			if (preStr.length() == 1) {
				result += strs[i];
			} else {
				result += capitalize(strs[i]);
			}
			preStr = strs[i];
		}
		return result;
	}
	
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}
	
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}
	
	private static String changeFirstCharacterCase(String str,
			boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		} else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}
	
	public static String datepath(){
		return DateUtils.format(new Date(), "yyyy-MM/dd") + "/";
	}
	
	public static boolean isEmpty(Object pObj) {
		if (pObj == null)
			return true;
		if (pObj == "")
			return true;
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return true;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return true;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNotEmpty(Object pObj){
		return !isEmpty(pObj);
	}

}
