package room.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoomDAO implements RoomDAO_interface {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public RoomBean insert(RoomBean roomBean) {
		if(roomBean!=null){			
			RoomBean result = (RoomBean) this.getSession().get(RoomBean.class, roomBean.getRoomno());			
			if(result==null){
				this.getSession().save(roomBean);
				return roomBean;
			}
		}
		return null;
	}

	@Override
	public RoomBean update(RoomBean roomBean) {
		RoomBean result = this.getSession().get(RoomBean.class, roomBean.getRoomno());
		if(result!=null){
			result.setTitle(roomBean.getTitle());
			result.setRoomstatus(roomBean.getRoomstatus());
			result.setCapacity(roomBean.getCapacity());
			result.setKind(roomBean.getKind());
			result.setLocationBean(roomBean.getLocationBean());
			result.setEvents(roomBean.getEvents());
		}
		return result;
	}

	@Override
	public boolean delete(Integer roomno) {
		RoomBean roomBean=(RoomBean) this.getSession().get(RoomBean.class, roomno);
		if(roomBean!=null){
			this.getSession().delete(roomBean);
			return true;
		}
		return false;
	}

	@Override
	public RoomBean select(Integer roomno) {
		RoomBean roomBean=(RoomBean) this.getSession().get(RoomBean.class, roomno);
		return roomBean;
	}

	@Override
	public List<RoomBean> selectByLocation(Integer locationno) {
		return  this.getSession().createQuery("from RoomBean where locationno='"+locationno+"'", RoomBean.class).getResultList();
		
	}

	@Override
	public List<RoomBean> select() {
		return  this.getSession().createQuery("from RoomBean", RoomBean.class).getResultList();
	}

}
