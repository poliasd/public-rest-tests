/*******************************************************************************
 * This class contains basic functions for manipulating JSON data
 *
 ******************************************************************************/
package com.polina.rest.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CommonJSON {

	/**
	 * 
	 * Converts the response to a JSON object
	 */
	public static JSONObject getJSON(String responseTxt) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(responseTxt);
		return (JSONObject) jsonArray.get(0);

	}

}
