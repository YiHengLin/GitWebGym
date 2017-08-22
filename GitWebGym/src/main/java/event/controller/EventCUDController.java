package event.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import event.model.EventBean;
import event.model.EventService;
import room.model.RoomBean;
import trainer.model.TrainerBean;

@RestController
@RequestMapping("/do")
public class EventCUDController {
	
	@Autowired
	private EventService eventService;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	private EventBean eventBean;
	private RoomBean roomBean;
	private TrainerBean trainerBean;	
	private String sqlstart;
	private String sqlend;	
	private List<Date> daysBetweenDates;

	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate)
	{

	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    dates.add(enddate);
	    return dates;
	}
	
	private void addEvent(String resourceId,String title,String trainerId, String coursekind,String start, String end, String charge){
		eventBean = new EventBean();
		roomBean = new RoomBean();
		trainerBean = new TrainerBean();
		roomBean.setRoomno(Integer.parseInt(resourceId.split(":")[0]));
		eventBean.setRoomBean(roomBean);
		eventBean.setTitle(title);
		trainerBean.setTrainerID(trainerId);
		eventBean.setTrainerBean(trainerBean);
		eventBean.setCoursekind(coursekind);
		eventBean.setE_status("y");
		eventBean.setEnroll(0);
		try {
			eventBean.setEventstart(new java.sql.Timestamp(sdf.parse(start).getTime()));
			eventBean.setEventend(new java.sql.Timestamp(sdf.parse(end).getTime()));
			eventBean.setCharge(Integer.parseInt(charge));
			EventBean event = eventService.insert(eventBean);
			System.out.println("Add EventBean="+event);

		} catch (ParseException e) {
			e.printStackTrace();
		} 
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String eventCUD(
			@RequestParam("action") String action,
			@RequestParam("eventid") String eventid,
			@RequestParam("title") String title,
			@RequestParam("room") String resourceId,
			@RequestParam("trainerId") String trainerId,
			@RequestParam("start") String start,
			@RequestParam("end") String end,
			@RequestParam("coursekind") String coursekind,
			@RequestParam("e_status") String e_status,
			@RequestParam("enroll") String enroll,
			@RequestParam("charge") String charge,
			@RequestParam("multiple") String multiple)
	{

		
		if("add".equals(action)){
			System.out.println("---\naddEvents is called");
			System.out.println("title="+ title+", start="+start+", end="+end+", resourceId="+resourceId+", trainerId="+trainerId+", coursekind="+coursekind);
			System.out.println("multiple="+multiple);

			
			if(multiple.split(",")[0].equals(multiple.split(",")[1]) && (start.length() > 5) ){  // add One-Day Event in 'agendaWeek View'

				System.out.println("agendaWeek View");
				addEvent(resourceId,title,trainerId,coursekind,start,end,charge);

	
			}else if(multiple.split(",")[0].equals(multiple.split(",")[1]) && (start.length()==5)){ // add One-Day Event in 'month View'
				System.out.println("month View one day");
				sqlstart = multiple.split(",")[0]+" "+start;  
				sqlend = multiple.split(",")[0]+" "+end;
				
				addEvent(resourceId,title,trainerId,coursekind,sqlstart,sqlend,charge);

			}
			else{  // add Multiple-Days Event in 'month View'
				
				System.out.println("month View multiple days");
				try {
					
					
					Date startdate = sdf2.parse(multiple.split(",")[0]);
					Date enddate = sdf2.parse(multiple.split(",")[1]);
					daysBetweenDates = EventCUDController.getDaysBetweenDates(startdate, enddate);
					
					for (Date date : daysBetweenDates) {
						
						System.out.println("date="+date);
						
						sqlstart = sdf2.format(date)+" "+start;  
						sqlend = sdf2.format(date)+" "+end;
						addEvent(resourceId,title,trainerId,coursekind,sqlstart,sqlend,charge);
						
					}	
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			return "add success";
		}
		else if("edit".equals(action)){
			
			System.out.println("---\neditEvents is called");
			System.out.println("title="+ title+", start="+start+", end="+end+", resourceId="+resourceId+", trainerId="+trainerId+", coursekind="+coursekind);

			try {
				EventBean eventBean  = new EventBean();
				RoomBean roomBean = new RoomBean();
				TrainerBean trainerBean = new TrainerBean();
				eventBean.setEventno(Integer.parseInt(eventid));
				roomBean.setRoomno(Integer.parseInt(resourceId));
				eventBean.setRoomBean(roomBean);
				eventBean.setTitle(title);
				trainerBean.setTrainerID(trainerId);;
				eventBean.setTrainerBean(trainerBean);
				eventBean.setCoursekind(coursekind);
				eventBean.setE_status(e_status);
				eventBean.setEnroll(Integer.parseInt(enroll));			
				eventBean.setCharge(Integer.parseInt(charge));
				eventBean.setEventstart(new java.sql.Timestamp(sdf.parse(start).getTime()));
				eventBean.setEventend(new java.sql.Timestamp(sdf.parse(end).getTime()));	
				
				EventBean event = eventService.update(eventBean);
				System.out.println("Update EventBean="+event);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			if("n".equals(e_status)){
				return "delete success";
			}else{
				return "edit success";
			}
			
			
		}else{

			System.out.println("---\ndelteEvents is called");
			System.out.println("title="+ title+" ,start="+start+" ,end="+end+" ,resourceId="+resourceId+" ,trainerId="+trainerId+" ,enroll="+enroll);
			
			EventBean eventBean  = new EventBean();
			eventBean.setEventno(Integer.parseInt(eventid));
	    	boolean done = eventService.delete(eventBean);
	    	System.out.println("Event's id="+eventid+" is deleted");
	
			return "delete success";
		}
	}
}
