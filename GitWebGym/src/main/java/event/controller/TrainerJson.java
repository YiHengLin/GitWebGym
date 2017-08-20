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
import trainer.model.TrainerBean;
import trainer.model.TrainerService;


@RestController
@RequestMapping("/trainers")
public class TrainerJson {
	
	@Autowired
	private TrainerService trainerService;
	
	private List<TrainerBean> trainers;
	
    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> listGyms() {
    	
    	System.out.println("---\nList Trainers is called");
    	
    	JSONArray jsonArray = new JSONArray();
    	JSONObject jSONObject = null;
    	
    	trainers = trainerService.select();
    	
    	for (TrainerBean trainer : trainers) {
    		jSONObject = new JSONObject();
    		jSONObject.put("trainerid", trainer.getTrainerID());
    		jSONObject.put("is_blacklist", trainer.getIs_blacklist());
    		jSONObject.put("field", trainer.getT_field());
    		jSONObject.put("experience", trainer.getT_experience());
    		jSONObject.put("licence", trainer.getT_licence());
    		
    		jsonArray.put(jSONObject);
		}
    	
    	System.out.println("---\nTrainer jsonArray=" + jsonArray+"\n");
    	
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.add("Content-Type", "application/json; charset=utf-8");
    	return new ResponseEntity<String>(jsonArray.toString(), responseHeaders, HttpStatus.OK);	
	
		
	}

}
