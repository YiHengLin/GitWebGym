package event.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import event.model.EventBean;
import event.model.EventService;
import location.model.LocationService;
import room.model.RoomBean;
import room.model.RoomService;

import org.json.*;

@RestController
@RequestMapping("/json")
public class EventJson {

	@Autowired
	private EventService eventService;

	private List<EventBean> events;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> listEvents(
			@RequestParam("identity") String identity,
			@RequestParam("location") String location, 
			@RequestParam("kind") String kind,
			@RequestParam("trainersearch") String trainersearch) {

		System.out.println("---\n---\nlistEvents is called, identity=" + identity + " ,location=" + location + " ,kind=" + kind +" ,trainersearch="+trainersearch+ "\n");


		JSONArray jsonArray = new JSONArray();
		JSONObject jSONObject = null;
		
		identity = (identity=="")? "member" :identity;
		trainersearch = trainersearch.toLowerCase();
		System.out.println("identity="+identity+", trainersearch="+trainersearch);
		
		
		// Events depend on Member or Trainer or Admin, Only Admin can see Events of e_status of 'y','n' 
		if ("admin".equals(identity)) {
			events = eventService.selectAll();
		} else {
			events = eventService.select();
		}
		
		if("all".equals(trainersearch)){
			// Search without Trainer Criteria
			// Filter ouput events according to 'location' and 'kind'
			for (EventBean event : events) {
				if (!("all".equals(location)) && "all".equals(kind)) {
					if (event.getRoomBean().getLocationBean().getLocationno().equals(Integer.parseInt(location))) {
						jSONObject = new JSONObject();
						jSONObject.put("id", event.getEventno());
						jSONObject.put("title", event.getTitle());
						jSONObject.put("trainerId", event.getTrainerBean().getTrainerID());
						jSONObject.put("resourceId", event.getRoomBean().getRoomno());
						jSONObject.put("coursekind", event.getCoursekind());
						jSONObject.put("e_status", event.getE_status());
						jSONObject.put("start", event.getEventstart());
						jSONObject.put("end", event.getEventend());
						jSONObject.put("enroll", event.getEnroll());
						jSONObject.put("charge", event.getCharge());
						
						switch(event.getCoursekind()){
							case "aerobic":
								jSONObject.put("color", "#002147");
								break;
							case "weighttrain":
								jSONObject.put("color", "#990000");
								break;
							case "cycle":
								jSONObject.put("color", "#96B6B8");
								break;
							case "other":
								jSONObject.put("color", "#DAC99D");
								break;
							default:
								continue;	
						}
						if( !(event.getTrainerBean().getTrainerID().toLowerCase().equals(identity)) ){
							jSONObject.put("editable",false);
						}

						jsonArray.put(jSONObject);
					} else {
						// do nothing...
					}
				} else if ("all".equals(location) && "all".equals(kind)) {
					jSONObject = new JSONObject();
					jSONObject.put("id", event.getEventno());
					jSONObject.put("title", event.getTitle());
					jSONObject.put("trainerId", event.getTrainerBean().getTrainerID());
					jSONObject.put("resourceId", event.getRoomBean().getRoomno());
					jSONObject.put("coursekind", event.getCoursekind());
					jSONObject.put("e_status", event.getE_status());
					jSONObject.put("start", event.getEventstart());
					jSONObject.put("end", event.getEventend());
					jSONObject.put("enroll", event.getEnroll());
					jSONObject.put("charge", event.getCharge());
					
					switch(event.getCoursekind()){
					case "aerobic":
						jSONObject.put("color", "#002147");
						break;
					case "weighttrain":
						jSONObject.put("color", "#990000");
						break;
					case "cycle":
						jSONObject.put("color", "#96B6B8");
						break;
					case "other":
						jSONObject.put("color", "#DAC99D");
						break;
					default:
						continue;	
					}
					
					if( !(event.getTrainerBean().getTrainerID().toLowerCase().equals(identity)) ){
						jSONObject.put("editable",false);
					}
					
					jsonArray.put(jSONObject);

				} else if ("all".equals(location) && !("all".equals(kind))) {
					if (event.getCoursekind().equals(kind)) {
						jSONObject = new JSONObject();
						jSONObject.put("id", event.getEventno());
						jSONObject.put("title", event.getTitle());
						jSONObject.put("trainerId", event.getTrainerBean().getTrainerID());
						jSONObject.put("resourceId", event.getRoomBean().getRoomno());
						jSONObject.put("coursekind", event.getCoursekind());
						jSONObject.put("e_status", event.getE_status());
						jSONObject.put("start", event.getEventstart());
						jSONObject.put("end", event.getEventend());
						jSONObject.put("enroll", event.getEnroll());
						jSONObject.put("charge", event.getCharge());

						switch(event.getCoursekind()){
						case "aerobic":
							jSONObject.put("color", "#002147");
							break;
						case "weighttrain":
							jSONObject.put("color", "#990000");
							break;
						case "cycle":
							jSONObject.put("color", "#96B6B8");
							break;
						case "other":
							jSONObject.put("color", "#DAC99D");
							break;
						default:
							continue;	
						}	
						if( !(event.getTrainerBean().getTrainerID().toLowerCase().equals(identity)) ){
							jSONObject.put("editable",false);
						}
						jsonArray.put(jSONObject);
					}
				} else {
					if (event.getRoomBean().getLocationBean().getLocationno().equals(Integer.parseInt(location))
							&& event.getCoursekind().equals(kind)) {
						jSONObject = new JSONObject();
						jSONObject.put("id", event.getEventno());
						jSONObject.put("title", event.getTitle());
						jSONObject.put("trainerId", event.getTrainerBean().getTrainerID());
						jSONObject.put("resourceId", event.getRoomBean().getRoomno());
						jSONObject.put("coursekind", event.getCoursekind());
						jSONObject.put("e_status", event.getE_status());
						jSONObject.put("start", event.getEventstart());
						jSONObject.put("end", event.getEventend());
						jSONObject.put("enroll", event.getEnroll());
						jSONObject.put("charge", event.getCharge());
						
						switch(event.getCoursekind()){
						case "aerobic":
							jSONObject.put("color", "#002147");
							break;
						case "weighttrain":
							jSONObject.put("color", "#990000");
							break;
						case "cycle":
							jSONObject.put("color", "#96B6B8");
							break;
						case "other":
							jSONObject.put("color", "#DAC99D");
							break;
						default:
							continue;	
						}
						if( !(event.getTrainerBean().getTrainerID().toLowerCase().equals(identity)) ){
							jSONObject.put("editable",false);
						}
						jsonArray.put(jSONObject);
					} else {
						// do nothing...
					}
				}
			}
		}else{
			// Search with Trainer Criteria		
			for (EventBean event : events) {
				if (!("all".equals(location)) && "all".equals(kind)) {
					if (event.getRoomBean().getLocationBean().getLocationno().equals(Integer.parseInt(location))) {
						if(event.getTrainerBean().getTrainerID().toLowerCase().equals(trainersearch)){
							jSONObject = new JSONObject();
							jSONObject.put("id", event.getEventno());
							jSONObject.put("title", event.getTitle());		
							jSONObject.put("trainerId", event.getTrainerBean().getTrainerID());
							jSONObject.put("resourceId", event.getRoomBean().getRoomno());
							jSONObject.put("coursekind", event.getCoursekind());
							jSONObject.put("e_status", event.getE_status());
							jSONObject.put("start", event.getEventstart());
							jSONObject.put("end", event.getEventend());
							jSONObject.put("enroll", event.getEnroll());
							jSONObject.put("charge", event.getCharge());
							
							switch(event.getCoursekind()){
								case "aerobic":
									jSONObject.put("color", "#002147");
									break;
								case "weighttrain":
									jSONObject.put("color", "#990000");
									break;
								case "cycle":
									jSONObject.put("color", "#96B6B8");
									break;
								case "other":
									jSONObject.put("color", "#DAC99D");
									break;
								default:
									continue;	
							}
							if( !(event.getTrainerBean().getTrainerID().toLowerCase().equals(identity)) ){
								jSONObject.put("editable",false);
							}
							jsonArray.put(jSONObject);
							
						}else{
							// do nothing...
						}

					} else {
						// do nothing...
					}
				} else if ("all".equals(location) && "all".equals(kind)) {
					if(event.getTrainerBean().getTrainerID().toLowerCase().equals(trainersearch)){
						jSONObject = new JSONObject();
						jSONObject.put("id", event.getEventno());
						jSONObject.put("title", event.getTitle());
						jSONObject.put("trainerId", event.getTrainerBean().getTrainerID());
						jSONObject.put("resourceId", event.getRoomBean().getRoomno());
						jSONObject.put("coursekind", event.getCoursekind());
						jSONObject.put("e_status", event.getE_status());
						jSONObject.put("start", event.getEventstart());
						jSONObject.put("end", event.getEventend());
						jSONObject.put("enroll", event.getEnroll());
						jSONObject.put("charge", event.getCharge());
						
						switch(event.getCoursekind()){
						case "aerobic":
							jSONObject.put("color", "#002147");
							break;
						case "weighttrain":
							jSONObject.put("color", "#990000");
							break;
						case "cycle":
							jSONObject.put("color", "#96B6B8");
							break;
						case "other":
							jSONObject.put("color", "#DAC99D");
							break;
						default:
							continue;	
						}
						if( !(event.getTrainerBean().getTrainerID().toLowerCase().equals(identity)) ){
							jSONObject.put("editable",false);
						}
						jsonArray.put(jSONObject);
					}else{
						// do nothing...
					}

				} else if ("all".equals(location) && !("all".equals(kind))) {
					if (event.getCoursekind().equals(kind)) {
						if(event.getTrainerBean().getTrainerID().toLowerCase().equals(trainersearch)){
							jSONObject = new JSONObject();
							jSONObject.put("id", event.getEventno());
							jSONObject.put("title", event.getTitle());
							jSONObject.put("trainerId", event.getTrainerBean().getTrainerID());
							jSONObject.put("resourceId", event.getRoomBean().getRoomno());
							jSONObject.put("coursekind", event.getCoursekind());
							jSONObject.put("e_status", event.getE_status());
							jSONObject.put("start", event.getEventstart());
							jSONObject.put("end", event.getEventend());
							jSONObject.put("enroll", event.getEnroll());
							jSONObject.put("charge", event.getCharge());

							switch(event.getCoursekind()){
							case "aerobic":
								jSONObject.put("color", "#002147");
								break;
							case "weighttrain":
								jSONObject.put("color", "#990000");
								break;
							case "cycle":
								jSONObject.put("color", "#96B6B8");
								break;
							case "other":
								jSONObject.put("color", "#DAC99D");
								break;
							default:
								continue;	
							}	
							if( !(event.getTrainerBean().getTrainerID().toLowerCase().equals(identity)) ){
								jSONObject.put("editable",false);
							}
							jsonArray.put(jSONObject);
						}else{
							// do nothing...
						}
					}
				} else {
					if (event.getRoomBean().getLocationBean().getLocationno().equals(Integer.parseInt(location))
							&& event.getCoursekind().equals(kind)) {
						if(event.getTrainerBean().getTrainerID().toLowerCase().equals(trainersearch)){
							jSONObject = new JSONObject();
							jSONObject.put("id", event.getEventno());
							jSONObject.put("title", event.getTitle());
							jSONObject.put("trainerId", event.getTrainerBean().getTrainerID());
							jSONObject.put("resourceId", event.getRoomBean().getRoomno());
							jSONObject.put("coursekind", event.getCoursekind());
							jSONObject.put("e_status", event.getE_status());
							jSONObject.put("start", event.getEventstart());
							jSONObject.put("end", event.getEventend());
							jSONObject.put("enroll", event.getEnroll());
							jSONObject.put("charge", event.getCharge());
							
							switch(event.getCoursekind()){
							case "aerobic":
								jSONObject.put("color", "#002147");
								break;
							case "weighttrain":
								jSONObject.put("color", "#990000");
								break;
							case "cycle":
								jSONObject.put("color", "#96B6B8");
								break;
							case "other":
								jSONObject.put("color", "#DAC99D");
								break;
							default:
								continue;	
							}
							if( !(event.getTrainerBean().getTrainerID().toLowerCase().equals(identity)) ){
								jSONObject.put("editable",false);
							}
							jsonArray.put(jSONObject);
						}else{
							// do nothing
						}
				
					} else {
						// do nothing...
					}
				}
			}
			
			
		}

		System.out.println("---\nEvent jsonArray=" + jsonArray + "\n");

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<String>(jsonArray.toString(), responseHeaders, HttpStatus.OK);

	}
}
