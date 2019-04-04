package com.personal.springboot.web.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestConsumer {

	private final String USER_AGENT = "Mozilla/5.0";
	private final int TIMEOUT = 300000;
	private Map<String, String> dataMap = null;
	private String header = ""; 
	
	
	@PostConstruct
	private void sendGet() throws Exception {

		String url = "https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		con.setReadTimeout(TIMEOUT);
		con.setConnectTimeout(TIMEOUT);
		// add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);
		
		if (con.getResponseCode() == 200) {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			String jsonResponse = response.toString();

			// print result
			if (!StringUtils.isEmpty(jsonResponse)) {
				ObjectMapper mapper = new ObjectMapper();
				/*byte[] mapData = response.toString().getBytes();
				JsonNode jsonNode = mapper.readTree(mapData);
				Map<String, Object> allData = mapper.convertValue(jsonNode, Map.class);
				for (Entry<String, Object> pair : allData.entrySet()) {
					System.out.println(pair);
				}*/
				JsonNode jsonNode = mapper.readTree(jsonResponse);
				String nlmDisclaimer = jsonNode.get("nlmDisclaimer").asText();
				setHeader(nlmDisclaimer);
				
				JsonNode interactionTypeGroup =  jsonNode.get("interactionTypeGroup");
				if(interactionTypeGroup.isArray()) {
					for (JsonNode itgNode : interactionTypeGroup) {
							JsonNode interactionType = itgNode.get("interactionType");
							if(interactionType.isArray()) {
								for (JsonNode itNode : interactionType) {
										JsonNode minConceptItemObject = itNode.get("minConceptItem");
										Map<String, String> newDataMap = new HashMap<String,String>();
										newDataMap.put("rxcui", minConceptItemObject.get("rxcui").asText());
										newDataMap.put("name", minConceptItemObject.get("name").asText());
										newDataMap.put("tty", minConceptItemObject.get("tty").asText());
										setDataMap(newDataMap);
										
									break;
								}
							}						
							break;
					}
				}
				
				// https://viralpatel.net/blogs/spring-mvc-hashmap-form-example/
			}
		}

		

	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}
	
	

}
