package com.clinic.common.bo;
import java.util.*;

public class RequestParams {

	Map<String, String[]> ReqMap;
	
	public RequestParams() {
		// TODO Auto-generated constructor stub
	}

	public RequestParams(Map<String, String[]> iReqMap) {
		// TODO Auto-generated constructor stub
		ReqMap = iReqMap;
		for (Map.Entry<String, String[]> entry : ReqMap.entrySet())
		{
			String sa[] = entry.getValue();
			String sp[] = new String[sa.length];
			int i = 0;
			for (String s1 : sa) {
				sp[i++] =  s1.replaceAll("<(.|\n)*?>","").replace("'","").replace("\"", "");
			}
			entry.setValue(sp);
		}
	}

	public String getValue(String iparam) {
		String vl = null;
		String[] sa = ReqMap.get(iparam);
		//System.out.println("getValue: "+iparam+" num values = "+Integer.toString(sa.length));
		if((sa != null) && (sa.length > 0)) {
			vl = sa[0];
		}
		
		return vl;
	}
	
	public String[] getValues(String iparam) {
		return ReqMap.get(iparam);
	}
	
	public void put(String key, String value) {
		String ss[] = new String[1];
		ss[0] = value;
		ReqMap.put(key, ss);
	}

	public void put(String key, String[] value) {
		ReqMap.put(key, value);
	}
	
	public boolean containsParam(String iparam) {
		return ReqMap.containsKey(iparam);
	}
	
	public String toString() {
		//return ReqMap.toString();
		String s = "{";
		
		for (Map.Entry<String, String[]> entry : ReqMap.entrySet())
		{
			s +="("+entry.getKey()+", ";
			String sa[] = entry.getValue();
			s += "[";
			for (String s1 : sa) {
				s += s1+", ";
			}
			s += "]),";
		}
		s += "}";
		return s;
	}
}
