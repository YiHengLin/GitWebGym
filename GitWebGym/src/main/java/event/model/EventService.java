package event.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import room.model.RoomBean;


@Service
public class EventService {
	
	@Autowired
	private EventDAO_interface dao;
	
	public EventBean insert(EventBean eventBean) {
		EventBean result = null;
		if (eventBean != null) {
			result = dao.insert(eventBean);
		}
		return result;
	}

	public EventBean update(EventBean eventBean) {
		EventBean result = null;
		if (eventBean != null) {
			result = dao.update(eventBean);
		}
		return result;
	}
	
	
	public boolean delete(EventBean eventBean) {
		boolean result = false;
		if (eventBean != null) {
			result = dao.delete(eventBean.getEventno());
		}
		return result;
	}
	
	public EventBean select(Integer eventno) {
		return dao.select(eventno);
	}
	
	public List<EventBean> select(String trainerid) {
		return dao.select(trainerid);
	}
	
	public List<EventBean> select() {
		return dao.select();
	}
	
	public List<EventBean> selectAll() {
		return dao.selectAll();
	}
}
