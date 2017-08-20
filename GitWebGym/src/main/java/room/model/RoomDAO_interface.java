package room.model;

import java.util.List;


public interface RoomDAO_interface {
    public RoomBean insert(RoomBean roomBean);
    public RoomBean update(RoomBean roomBean);
    public boolean delete(Integer roomno);
    public RoomBean select(Integer roomno);
    public List<RoomBean> selectByLocation(Integer locationno);
    public List<RoomBean> select();
}
