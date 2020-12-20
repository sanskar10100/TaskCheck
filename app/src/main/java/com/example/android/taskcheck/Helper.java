package com.example.android.taskcheck;

public class Helper {
	/**
	 * Reverses String containing date
	 *
	 * @param originalString in this format: "DD/MM/YYYY"
	 * @return reversedString in this format: "YYYY/MM/DD"
	 */
	public static String reverseDateString(final String originalString) {
		String[] dateFields = originalString.split("/");
		return dateFields[2] + "/" + dateFields[1] + "/" + dateFields[0];
	}
}
