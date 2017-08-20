package trainer.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TrainerService {
	
	@Autowired
	private TrainerDAO_interface dao;
	
	public TrainerBean insert(TrainerBean trainerBean) {
		TrainerBean result = null;
		if (trainerBean != null) {
			result = dao.insert(trainerBean);
		}
		return result;
	}
	
	public TrainerBean update(TrainerBean trainerBean) {
		TrainerBean result = null;
		if (trainerBean != null) {
			result = dao.update(trainerBean);
		}
		return result;
	}
	
	public boolean delete(TrainerBean trainerBean) {
		boolean result = false;
		if (trainerBean != null) {
			result = dao.delete(trainerBean.getTrainerID());
		}
		return result;
	}

	public TrainerBean select(String trainerID) {
		return dao.select(trainerID);
	}

	public List<TrainerBean> select() {
		return dao.select();
	}
}
