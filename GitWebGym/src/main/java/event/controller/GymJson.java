package event.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import location.model.LocationBean;
import location.model.LocationService;


@RestController
@RequestMapping("/gyms")
public class GymJson {
	
	@Autowired
	private LocationService locationService;
	
	private List<LocationBean> gyms;
	
    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> listGyms() {
    	
    	System.out.println("---\nList Gyms is called");
    	
    	JSONArray jsonArray = new JSONArray();
    	JSONObject jSONObject = null;
    	
    	gyms = locationService.select();
    	
		for (LocationBean gym : gyms) {
    		jSONObject = new JSONObject();
    		jSONObject.put("locationno", gym.getLocationno());
    		jSONObject.put("locationname", gym.getLocationname());
    		jSONObject.put("l_address", gym.getL_address());
    		jSONObject.put("openhour", gym.getOpenhour());
    		jSONObject.put("phone", gym.getPhone());
    		
    		jsonArray.put(jSONObject);
		}
    	
    	System.out.println("---\nGym jsonArray=" + jsonArray+"\n");
    	
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.add("Content-Type", "application/json; charset=utf-8");
    	return new ResponseEntity<String>(jsonArray.toString(), responseHeaders, HttpStatus.OK);	
	
    }
}
