package trainer.model;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class TrainerDAO implements TrainerDAO_interface {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public TrainerBean insert(TrainerBean trainerBean) {
		if(trainerBean!=null){			
			TrainerBean result = (TrainerBean) this.getSession().get(TrainerBean.class, trainerBean.getTrainerID());			
			if(result==null){
				this.getSession().save(trainerBean);
				return trainerBean;
			}
		}
		return null;
	}

	@Override
	public TrainerBean update(TrainerBean trainerBean) {
		TrainerBean result = this.getSession().get(TrainerBean.class, trainerBean.getTrainerID());
		if(result!=null){
			result.setEvents(trainerBean.getEvents());
			result.setT_password(trainerBean.getT_password());
			result.setT_name(trainerBean.getT_name());
			result.setT_id_number(trainerBean.getT_id_number());
			result.setT_gender(trainerBean.getT_gender());
			result.setT_bday(trainerBean.getT_bday());
			result.setT_mail(trainerBean.getT_mail());
			result.setT_mobile(trainerBean.getT_mobile());
			result.setT_tel(trainerBean.getT_tel());
			result.setT_address(trainerBean.getT_address());
			result.setT_register(trainerBean.getT_register());
			result.setIs_blacklist(trainerBean.getIs_blacklist());
			result.setT_identity(trainerBean.getT_identity());
			result.setT_field(trainerBean.getT_field());
			result.setT_experience(trainerBean.getT_experience());
			result.setT_licence(trainerBean.getT_licence());	
			//result.setT_photo(trainerBean.getT_photo());
		}
		return result;
	}

	@Override
	public boolean delete(String trainerID) {
		TrainerBean trainerBean=(TrainerBean) this.getSession().get(TrainerBean.class, trainerID);
		if(trainerBean!=null){
			this.getSession().delete(trainerBean);
			return true;
		}
		return false;	
	}

	@Override
	public TrainerBean select(String trainerID) {
		TrainerBean trainerBean=(TrainerBean) this.getSession().get(TrainerBean.class, trainerID);
		return trainerBean;
	}

	@Override
	public List<TrainerBean> select() {
		return  this.getSession().createQuery("from TrainerBean", TrainerBean.class).getResultList();
	}

}
