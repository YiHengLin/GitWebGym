package location.model;

import java.util.List;


public interface LocationDAO_interface {
	
    public LocationBean insert(LocationBean locationBean);
    public LocationBean update(LocationBean locationBean);
    public boolean delete(Integer locationno);
    public LocationBean select(Integer locationno);
    public List<Integer> selectLocationno(String location);
    public List<Integer> selectAllLocationno();
    public List<LocationBean> select();

}
