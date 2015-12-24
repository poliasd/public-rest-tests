/*******************************************************************************
 * Basic tests for testing a public Rest API 
 *
 ******************************************************************************/
package com.polina.rest.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.json.simple.parser.ParseException;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;

@RunWith(HttpJUnitRunner.class)
public class CountryInfoTest extends TestCase{

	@Rule
	public Destination restfuse = new Destination(this,
			"https://restcountries.eu/rest/v1");
	
	@Context
	public Response response; // will be injected after every request
	
	/**
	 * Checks if the returned response is OK.
	 */
	@HttpTest(method = Method.GET, path = "/")
	public void testGetResponse() {
		assertEquals("Response code is not OK.", 200, response.getStatus());
	}
	
	/**
	 * Verifies whether the capital of Bulgaria is Sofia.
	 */
	@HttpTest(method = Method.GET, path = "/name/bulgaria")
	public void testCapitalName() throws ParseException {
		assertEquals("Bulgaria doesn't have Sofia as a capital.", "Sofia", CommonJSON.getJSON(response.getBody()).get("capital"));	
	}
	
	/**
	 * Checks if Kabuls is capital of Afghanistan.
	 */
	@HttpTest(method = Method.GET, path = "/capital/Kabul")
	public void testCapitalCountry() throws ParseException {
		
		assertEquals("Kabul is not a capital of Afghanistan", "Afghanistan", CommonJSON.getJSON(response.getBody()).get("name"));	
	
	}
	
	/**
	 * Checks if Andorra is a border of Spain.
	 */
	@HttpTest(method = Method.GET, path = "name/Spain")
	public void testBorders() throws ParseException {
		ArrayList<String> timezones = (ArrayList<String>) CommonJSON.getJSON(response.getBody()).get("borders");
		assertTrue("Andorra is not a border of Spain.", timezones.contains("AND"));
	}
	
	/**
	 * Checks if Solomon Islands are in Oceania.
	 */
	@HttpTest(method = Method.GET, path = "name/Solomon Islands")
	public void testRegion() throws ParseException {
		
		assertEquals("The region of Solomon islands is not correct.", "Oceania", CommonJSON.getJSON(response.getBody()).get("region"));	
	
	}
	
	/**
	 * Checks the population of Switzerland.
	 */
	@HttpTest(method = Method.GET, path = "name/switzerland")
	public void testPopulation() throws ParseException {
		
		assertEquals("The population in Switzerland is not correct.", "8256000", CommonJSON.getJSON(response.getBody()).get("population").toString());	
	
	}
	
	
}
