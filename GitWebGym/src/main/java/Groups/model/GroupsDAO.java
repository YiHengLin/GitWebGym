package Groups.model;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import java.util.List;
import java.util.HashMap;
import java.util.LinkedList; 
import java.util.Map;
import Groups.model.*;
import YuiGlobalParam.yuiGlobalService;

public class GroupsDAO extends HttpServlet{
	
	static private List<String> memberIDList = new ArrayList<String>();
	
	DataSource ds = null;
	public GroupsDAO() throws IOException {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	public int createGroup(String groupid,String memberid,String title)  {
	
		Connection conn = null;
		int n = 0;
		try {
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement("Insert into groups values(?, ?, ?)");
			stmt.setString(1, groupid);
			stmt.setString(2, memberid);
			stmt.setString(3, title);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return n;
	}
	

	public int deleteMyGroup(String groupid)  {
		Connection conn = null;
		int n = 0;
		try {
			conn = ds.getConnection();
			System.out.println("in dao groupid:"+groupid);
			PreparedStatement stmt = conn.prepareStatement("delete from groups where groupid = ?");
			stmt.setString(1, groupid);
			n = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} 
		return n;
	}
	
	
	public int updateGroup(String newtitle,String groupid)  {
		Connection conn = null;
		int n = 0;
		try {
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement("update groups set tilte= ? where groupid = ?");
			stmt.setString(1, newtitle);
			stmt.setString(2, groupid);
			n = stmt.executeUpdate();
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	// Done
	public List<Map<String, String>> getMyGroups(String memberid)  {
		Connection conn = null;
		Collection<GroupsBean> allMembers = new ArrayList<GroupsBean>();
		try {
			conn = ds.getConnection();
			String memberlist =""; 
			List<Map<String, String>> allMyGroups  = new ArrayList<Map<String, String>>();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * from  groups where memberid = '"+memberid+"'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				 Map group = new HashMap();       
				 group.put("groupid",rs.getString(1));   
				 group.put("creator", rs.getString(2)); 
				 group.put("title",rs.getString(3)); 
				 PreparedStatement stmt1 = conn.prepareStatement("select memberid from groupdetail where groupid = '"+rs.getString(1)+"'");
				 ResultSet rs1 = stmt1.executeQuery();				
				 while(rs1.next()){		
					 memberlist = memberlist.concat(rs1.getString(1)).concat(",");					 
				 }
				 group.put("joins", memberlist);
				 allMyGroups.add(group);
				 memberlist="";
		
			 }

			return allMyGroups;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	// Done
	public List<Map<String, String>> getAllGroups()  {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String memberlist =""; 
			List<Map<String, String>> allGroups  = new ArrayList<Map<String, String>>();
	
			PreparedStatement stmt = conn.prepareStatement("SELECT * from  groups");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				 Map group = new HashMap();       
				 group.put("groupid",rs.getString(1));   
				 group.put("creator", rs.getString(2)); 
				 group.put("title",rs.getString(3)); 
				 PreparedStatement stmt1 = conn.prepareStatement("select memberid from groupdetail where groupid = '"+rs.getString(1)+"'");
				 ResultSet rs1 = stmt1.executeQuery();				
				 while(rs1.next()){		
					 memberlist = memberlist.concat(rs1.getString(1)).concat(",");	
				 }
				 group.put("joins", memberlist);
				 allGroups.add(group);
				 memberlist="";
				 
			 }

			return allGroups;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
