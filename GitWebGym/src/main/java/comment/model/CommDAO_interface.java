package comment.model;

import java.sql.SQLException;
import java.util.List;


public interface CommDAO_interface {
	public int PostComm(CommBean cb) throws SQLException;
	public int ModifyComm(CommBean cb)throws SQLException;
	public int DeletComm(int commNo) throws SQLException;
	public CommBean findbyCommNO(int commNo) throws SQLException;
	public List<CommBean> getCommemts() throws SQLException;
	
}
