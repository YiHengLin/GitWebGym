package room.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomService {
	
	@Autowired
	private RoomDAO_interface dao;
	
	public RoomBean insert(RoomBean roomBean) {
		RoomBean result = null;
		if (roomBean != null) {
			result = dao.insert(roomBean);
		}
		return result;
	}
	
	public RoomBean update(RoomBean roomBean) {
		RoomBean result = null;
		if (roomBean != null) {
			result = dao.update(roomBean);
		}
		return result;
	}
	
	public boolean delete(RoomBean roomBean) {
		boolean result = false;
		if (roomBean != null) {
			result = dao.delete(roomBean.getRoomno());
		}
		return result;
	}
	
	public RoomBean select(Integer roomno) {
		return dao.select(roomno);
	}
	
	public List<RoomBean> select() {
		return dao.select();
	}

}
