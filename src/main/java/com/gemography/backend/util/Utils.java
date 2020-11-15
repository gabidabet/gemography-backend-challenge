package com.gemography.backend.util;

import java.util.Map;

import org.springframework.util.MultiValueMap;
/**
* utility class
*
* @author  Youness EL AACHIQ
* @version 1.0
* @since   2020-11-14
*/
public class Utils {
	public static void mergeMaps(MultiValueMap<String,String> defaultParams,MultiValueMap<String, String> customParams) {
		defaultParams.forEach((key, value) -> {
			customParams.put(key,value);
		});
	}
}
