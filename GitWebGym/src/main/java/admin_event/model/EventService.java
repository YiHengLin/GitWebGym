package admin_event.model;

import java.util.List;



public class EventService {

	private EventDAO_interface dao;

	public EventService() {

		dao = new EventDAO();

	}

	// INSERT_STMT
	public void insertEvent(EventBean eventBean) {

		dao.insert(eventBean);

	}

	// UPDATE_STMT

	public void updatePost(EventBean eventBean) {

		dao.update(eventBean);

	}
	
	public void updateEvent(EventBean eventBean) {

		dao.update(eventBean);

	}

	// DELETE_STMT
	public void deletePost(EventBean eventBean) {

		dao.delete(eventBean);

	}
	
	public void deleteEvent(EventBean eventBean) {

		dao.delete(eventBean);

	}

	// GET_ONE_STMT

	public EventBean getOneEvent(int eventno) {
		return dao.findByPrimaryKey(eventno);

	}

	// GET_ALL_STMT

	public List<EventBean> getAll() {

		return dao.getAll();
	}

}
