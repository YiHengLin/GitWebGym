package event.model;

import java.util.List;


public interface EventDAO_interface {
    public EventBean insert(EventBean eventBean);
    public EventBean update(EventBean eventBean);
    public boolean delete(Integer eventno);
    public EventBean select(Integer eventno);
    public List<EventBean> select(String trainerid);
    public List<EventBean> select();
    public List<EventBean> selectAll();

}
