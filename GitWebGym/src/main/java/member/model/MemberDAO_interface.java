package member.model;

import java.sql.SQLException;
import java.util.List;

import member.model.MemberBean;

public interface MemberDAO_interface {
    public MemberBean insert(MemberBean memberBean) throws SQLException;
    public MemberBean update(MemberBean memberBean)throws SQLException;
    public int delete(String memberid)throws SQLException;
    public MemberBean SelectByPrimaryKey(String memberid)throws SQLException;
    public List<MemberBean> selectALL()throws SQLException;
}
