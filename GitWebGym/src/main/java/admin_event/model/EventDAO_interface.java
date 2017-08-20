package admin_event.model;

import java.util.List;

public interface EventDAO_interface {

	void insert(EventBean eventBean);

	void update(EventBean eventBean);

	void delete(EventBean eventBean);

	List<EventBean> getAll();

	EventBean findByPrimaryKey(int eventno);

	

}
