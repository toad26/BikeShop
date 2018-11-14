package com.bikeshop.demo.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

public class BikeUtils {
	
	public static void printMap(Map<String,String[]> params) {
		String text = params.entrySet()
                .stream()
                .map(e -> e.getKey() + "=\"" + Arrays.toString(e.getValue()) + "\"")
                .collect(Collectors.joining(", "));
		System.out.println(text);
	}
	
	/**
	 * This method returns true if the collection is null or is empty.
	 * @param collection
	 * @return true | false
	 */
	public static boolean isEmpty( Collection<?> collection ){
		if( collection == null || collection.isEmpty() ){
			return true;
		}
		return false;
	}

	/**
	 * This method returns true of the map is null or is empty.
	 * @param map
	 * @return true | false
	 */
	public static boolean isEmpty( Map<?, ?> map ){
		if( map == null || map.isEmpty() ){
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the objet is null.
	 * @param object
	 * @return true | false
	 */
	public static boolean isEmpty( Object object ){
		if( object == null ){
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input array is null or its length is zero.
	 * @param array
	 * @return true | false
	 */
	public static boolean isEmpty( Object[] array ){
		if( array == null || array.length == 0 ){
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input string is null or its length is zero.
	 * @param string
	 * @return true | false
	 */
	public static boolean isEmpty( String string ){
		if( string == null || string.trim().length() == 0 ){
			return true;
		}
		return false;
	}
	
	public static String getRole(HttpSession session) {
		try {
			String k = (String) session.getAttribute("role");
			if (k.equalsIgnoreCase("admin"))
				return "admin";
			else
				return "user";
		} catch (NullPointerException n) {
			return "user";
		}
	}
}
