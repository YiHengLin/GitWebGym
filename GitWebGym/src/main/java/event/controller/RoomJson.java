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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import location.model.LocationBean;
import location.model.LocationService;
import room.model.RoomBean;
import room.model.RoomService;

@RestController
@RequestMapping("/resources")
public class RoomJson {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private LocationService locationService;
	
    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> listRooms(@RequestParam("location") String location) {
    
    	System.out.println("---\nList Rooms is called, location="+location+"\n");
    	

    	
    	List<RoomBean> rooms = roomService.select();
    	
    	
    	JSONArray jsonArray = new JSONArray();
    	JSONObject jSONObject = null;
    	
    	if("all".equals(location)){
    		for (RoomBean room : rooms) {
        		jSONObject = new JSONObject();
        		jSONObject.put("id", room.getRoomno());
        		jSONObject.put("title", (room.getLocationBean().getLocationname()+" "+room.getTitle()));
        		jSONObject.put("capacity", room.getCapacity());
        		jSONObject.put("gym", room.getLocationBean().getLocationname());
        		jsonArray.put(jSONObject);
    		}
    	}else{
    		// Search for specified gym
        	for (RoomBean room : rooms) {
        		if(room.getLocationBean().getLocationno().equals(Integer.parseInt(location))){
            		jSONObject = new JSONObject();
            		jSONObject.put("id", room.getRoomno());
            		jSONObject.put("title", (room.getLocationBean().getLocationname()+" "+room.getTitle()));
            		jSONObject.put("capacity", room.getCapacity());
            		jSONObject.put("gym", room.getLocationBean().getLocationname());
            		jsonArray.put(jSONObject);
        		}else{
        			//do nothing...
        		}
        	}    		
    	}
    	
    	System.out.println("---\nRoom jsonArray=" + jsonArray+"\n");
    	
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.add("Content-Type", "application/json; charset=utf-8");
    	return new ResponseEntity<String>(jsonArray.toString(), responseHeaders, HttpStatus.OK);	
	
    }
}
