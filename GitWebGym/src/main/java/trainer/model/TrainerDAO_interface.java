package trainer.model;

import java.util.List;



public interface TrainerDAO_interface {
	
    public TrainerBean insert(TrainerBean trainerBean);
    public TrainerBean update(TrainerBean trainerBean);
    public boolean delete(String trainerID);
    public TrainerBean select(String trainerID);
    public List<TrainerBean> select();

}
