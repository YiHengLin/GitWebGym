package event.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import room.model.RoomBean;
import trainer.model.TrainerBean;


@Repository
public class EventDAO implements EventDAO_interface {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public EventBean insert(EventBean eventBean) {
		if(eventBean!=null){			
			//EventBean result = (EventBean) this.getSession().get(EventBean.class, eventBean.getEventno());			
//			if(result==null){
				this.getSession().save(eventBean);
				return eventBean;
//			}
		}
		return null;
	}

	@Override
	public EventBean update(EventBean eventBean) {
		EventBean result = this.getSession().get(EventBean.class, eventBean.getEventno());
		if(result!=null){
			result.setTrainerBean(eventBean.getTrainerBean());
			result.setRoomBean(eventBean.getRoomBean());
			result.setTitle(eventBean.getTitle());
			result.setCoursekind(eventBean.getCoursekind());
			result.setE_status(eventBean.getE_status());
			result.setEventstart(eventBean.getEventstart());
			result.setEventend(eventBean.getEventend());
			result.setEnroll(eventBean.getEnroll());
			result.setCharge(eventBean.getCharge());
		}
		return result;
	}

	@Override
	public boolean delete(Integer eventno) {
		EventBean eventBean=(EventBean) this.getSession().get(EventBean.class, eventno);
		if(eventBean!=null){
			this.getSession().delete(eventBean);
			return true;
		}
		return false;
	}

	@Override
	public EventBean select(Integer eventno) {
		EventBean eventBean=(EventBean) this.getSession().get(EventBean.class, eventno);
		return eventBean;
	}
	
	@Override
	public List<EventBean> select(String trainerid) {
		String str =  "from EventBean where trainerid='"+trainerid+"'";
		return this.getSession().createQuery(str, EventBean.class).getResultList();
	}

	@Override
	public List<EventBean> select() {
		return  this.getSession().createQuery("from EventBean where e_status='y'", EventBean.class).getResultList();
	}

	@Override
	public List<EventBean> selectAll() {
		return  this.getSession().createQuery("from EventBean", EventBean.class).getResultList();
	}
	
	

}
