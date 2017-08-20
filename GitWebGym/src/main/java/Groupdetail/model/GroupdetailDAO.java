package Groupdetail.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import Groups.model.GroupsBean;

public class GroupdetailDAO {
	static private List<String> memberIDList = new ArrayList<String>();
	
	DataSource ds = null;
	public GroupdetailDAO() throws IOException {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public int isInGroup(String groupid,String memberid)  {
		Connection conn = null;
		int n=0;
		try {
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT groupdetailno from groupdetail where groupid =? and memberid=?");
			stmt.setString(1, groupid);
			stmt.setString(2, memberid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if(rs.getString(1)==null){
					return 0;
				}
				return 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
		
		

	}
	
	
	public int createGroupDetail(String groupid,String memberid)  {
		Connection conn = null;
		int n=0;
		try {
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement("Insert into groupdetail values(?,?)");
			stmt.setString(1, groupid);
			stmt.setString(2, memberid);
			n = stmt.executeUpdate();
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	public int deleteDetailsOfGroup(String groupid){
		Connection conn = null;
		int n=0;
		try {
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement("delete from groupdetail where groupid = ? ");
			stmt.setString(1, groupid);
			n = stmt.executeUpdate();
			System.out.println("DAO' deleteDetailsOfGroup , n="+n);
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return n;
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	
		}
	}
	
	
	public int deleteGroupDetail(String groupid,String memberid)  {
		Connection conn = null;
		int n=0;
		try {
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement("delete from groupdetail where groupid = ? and memberid = ?");
			stmt.setString(1, groupid);
			stmt.setString(2, memberid);
			n = stmt.executeUpdate();
			System.out.println("in deleteGroupDetail DAO, n="+n);
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return n;
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	
		}
	}
}
