package com.cts.rabo.controller.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 550072
 *
 */
public class ScenarioContext {

	private Map<String, Object> scenarioContext;

	public ScenarioContext() {
		scenarioContext = new HashMap<>();
	}

	/**
	 * @param key
	 * @param value
	 */
	public void setContext(String key, Object value) {
		scenarioContext.put(key, value);
	}

	/**
	 * @param key
	 * @return
	 */
	public Object getContext(String key) {
		return scenarioContext.get(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public Boolean isContains(String key) {
		return scenarioContext.containsKey(key.toString());
	}

}
