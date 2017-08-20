package location.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class LocationDAO implements LocationDAO_interface {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public LocationBean insert(LocationBean locationBean) {
		if(locationBean!=null){			
			LocationBean result = (LocationBean) this.getSession().get(LocationBean.class, locationBean.getLocationno());			
			if(result==null){
				this.getSession().save(locationBean);
				return locationBean;
			}
		}
		return null;
	}

	@Override
	public LocationBean update(LocationBean locationBean) {
		
		LocationBean result = this.getSession().get(LocationBean.class, locationBean.getLocationno());
		if(result!=null){
			result.setLocationname(locationBean.getLocationname());
			result.setPhone(locationBean.getPhone());	
			result.setL_address(locationBean.getL_address());
			result.setOpenhour(locationBean.getOpenhour());
			result.setRooms(locationBean.getRooms());
		}
		return result;
	}

	@Override
	public boolean delete(Integer locationno) {
		LocationBean locationBean=(LocationBean) this.getSession().get(LocationBean.class, locationno);
		if(locationBean!=null){
			this.getSession().delete(locationBean);
			return true;
		}
		return false;	
	}

	@Override
	public LocationBean select(Integer locationno) {
		LocationBean locationBean=(LocationBean) this.getSession().get(LocationBean.class, locationno);
		return locationBean;
	}
	
	@Override
	public List<Integer> selectLocationno(String location) {
		return  this.getSession().createQuery("select locationno from LocationBean where locationno="+location, Integer.class).getResultList();
	}
	

	@Override
	public List<Integer> selectAllLocationno() {
		return  this.getSession().createQuery("select locationno from LocationBean", Integer.class).getResultList();
	}

	@Override
	public List<LocationBean> select() {
		return  this.getSession().createQuery("from LocationBean", LocationBean.class).getResultList();
	}

}
