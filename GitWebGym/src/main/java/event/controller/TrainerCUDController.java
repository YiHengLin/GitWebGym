package event.controller;


import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import event.model.EventBean;
import trainer.model.TrainerBean;
import trainer.model.TrainerService;

@RestController
@RequestMapping("/cudtrainers")
public class TrainerCUDController {
	
	@Autowired
	private TrainerService trainerService;
	
	private SimpleDateFormat sdf;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String trainerCUD(
			@RequestParam("trainerID") String trainerID,
			@RequestParam("t_password") String t_password,
			@RequestParam("t_name") String t_name,
			@RequestParam("t_mail") String t_mail,
			@RequestParam("t_bday") String t_bday,
			@RequestParam("t_mobile") String t_mobile,
			@RequestParam("t_tel") String t_tel,
			@RequestParam("t_address") String t_address,
			@RequestParam("t_field") String t_field,
			@RequestParam("t_experience") String t_experience,
			@RequestParam("t_licence") String t_licence,
			@RequestParam("t_id_number") String t_id_number,
			@RequestParam("t_gender") String t_gender,
			@RequestParam("t_register") String t_register,
			@RequestParam("t_identity") String t_identity,
			@RequestParam("is_blacklist") String is_blacklist,
			HttpSession session
			){
		
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println("In Controller trainerCUD");
		System.out.println("Form client="+trainerID+" "+t_password+" "+t_name+" "+t_mail+" "+t_bday+" "+t_register+" "+t_tel);
		//System.out.println("t_photo="+t_photo);
		
		
		TrainerBean bean = new TrainerBean();
		Set<EventBean> events = new HashSet<EventBean>();
		bean.setEvents(events);
		bean.setTrainerID(trainerID);
		bean.setT_password(t_password);
		bean.setT_name(t_name);
		bean.setT_mobile(t_mobile);
		bean.setT_mail(t_mail);
		bean.setT_bday(java.sql.Date.valueOf(t_bday));
		bean.setT_register(java.sql.Date.valueOf(t_register));
		bean.setT_tel(t_tel);
		bean.setT_address(t_address);
		bean.setT_field(t_field);
		bean.setT_experience(t_experience);
		bean.setT_licence(t_licence);
		bean.setT_id_number(t_id_number);
		bean.setT_gender(t_gender);
		bean.setT_identity(t_identity);
		bean.setIs_blacklist(is_blacklist);
		
		TrainerBean result = trainerService.update(bean);
		System.out.println("result="+result);
		session.setAttribute("trainerLoginOK", result);

		
		if(result==null){
			return"TrainerCUD failed";
		}else{
			return"TrainerCUD success";
		}

	}
}
