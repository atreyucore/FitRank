package br.com.fitrank.util;

public class StringUtil {

	public static boolean isEmptyOrNull(String string){
		if(string == null || string.isEmpty()){
			return true;
		}
		return false;
	}
}
