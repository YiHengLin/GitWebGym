package location.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {
	
	@Autowired
	private LocationDAO_interface dao;
	
	public LocationBean insert(LocationBean locationBean) {
		LocationBean result = null;
		if (locationBean != null) {
			result = dao.insert(locationBean);
		}
		return result;
	}
	
	public LocationBean update(LocationBean locationBean) {
		LocationBean result = null;
		if (locationBean != null) {
			result = dao.update(locationBean);
		}
		return result;
	}
	
	public boolean delete(LocationBean locationBean) {
		boolean result = false;
		if (locationBean != null) {
			result = dao.delete(locationBean.getLocationno());
		}
		return result;
	}
	
	public LocationBean select(Integer locationno) {
		return dao.select(locationno);
	}
	
	public List<Integer> selectLocationno(String location) {
		return dao.selectLocationno(location);
	}
	
	public List<Integer> selectAllLocationno() {
		return dao.selectAllLocationno();
	}
	
	public List<LocationBean> select() {
		return dao.select();
	}

}
