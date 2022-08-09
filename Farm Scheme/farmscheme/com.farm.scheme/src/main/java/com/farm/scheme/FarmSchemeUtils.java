package com.farm.scheme;

public class FarmSchemeUtils {

	private FarmSchemeUtils() {

	}

	public static String getId(String prefix) {
		Long nanoTime = System.nanoTime();
		return prefix + nanoTime;
	}

}
