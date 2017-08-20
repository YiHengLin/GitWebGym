package admin_event.model;

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



public class EventDAO implements EventDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"INSERT INTO event VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_STMT = 
			"UPDATE event SET trainerid=?, roomno=?, title=?, "
			+ "coursekind=? , e_status=? ,eventstart=? ,eventend=? ,enroll=? ,charge=? WHERE eventno=?";

	private static final String DELETE_STMT = "DELETE FROM event WHERE eventno=?";

	
	private static final String GET_ONE_STMT = 
			"SELECT eventno,trainerid,roomno,title,coursekind,e_status,eventstart,eventend,"
			+ "enroll,charge FROM event where eventno=?";
	
	
	private static final String GET_ALL_STMT = 
			"SELECT eventno,trainerid,roomno,title,coursekind,e_status,eventstart,eventend,"
			+ "enroll,charge FROM event order by eventno";

	private EventBean EventBean;


	
//------------------------------------------------------
	
//INSERT_STMT
	@Override
	public void insert(EventBean eventBean){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
		

			pstmt.setInt(1, eventBean.getEventno());
			pstmt.setString(2, eventBean.getTrainerid());
			pstmt.setInt(3, eventBean.getRoomno());
			pstmt.setString(4, eventBean.getTitle());
			pstmt.setString(5, eventBean.getCoursekind());
			pstmt.setString(6, eventBean.getE_status());
			pstmt.setDate(7, eventBean.getEventstart());
			pstmt.setDate(8, eventBean.getEventend());
			pstmt.setInt(9, eventBean.getEnroll());		
			pstmt.setInt(10, eventBean.getCharge());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
//UPDATE_STMT
	
	@Override
	public void update(EventBean eventBean) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
		


			pstmt.setString(1, eventBean.getTrainerid());
			pstmt.setInt(2, eventBean.getRoomno());
			pstmt.setString(3, eventBean.getTitle());
			pstmt.setString(4, eventBean.getCoursekind());
			pstmt.setString(5, eventBean.getE_status());
			pstmt.setDate(6, eventBean.getEventstart());
			pstmt.setDate(7, eventBean.getEventend());
			pstmt.setInt(8, eventBean.getEnroll());
			pstmt.setInt(9, eventBean.getCharge());
			pstmt.setInt(10, eventBean.getEventno());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	

	//DELETE_STMT
@Override
	public void delete(EventBean eventBean) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
		

			pstmt.setInt(1, eventBean.getEventno());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	
	
	


	
	
	
////////////////////////////////////
//GET_ONE_STMT

	@Override
	public EventBean findByPrimaryKey(int eventno) {
		
		EventBean eventBean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, eventno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// EventBean 也稱為 Domain objects
				eventBean = new EventBean();
				
				eventBean.setEventno(rs.getInt("eventno"));
				eventBean.setTrainerid(rs.getString("trainerid"));
				eventBean.setRoomno(rs.getInt("roomno"));
				eventBean.setTitle(rs.getString("title"));
				eventBean.setCoursekind(rs.getString("coursekind"));
				eventBean.setE_status(rs.getString("e_status"));
				eventBean.setEventstart(rs.getDate("eventstart"));
				eventBean.setEventend(rs.getDate("eventend"));
				eventBean.setEnroll(rs.getInt("enroll"));
				eventBean.setCharge(rs.getInt("charge"));	
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return eventBean;
	}
	
	//GET_ALL_STMT

		@Override
		public List<EventBean> getAll() {
			List<EventBean> list = new ArrayList<EventBean>();
			EventBean eventBean = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// EventBean 亦為 Domain objects
					eventBean = new EventBean();
					
					eventBean.setEventno(rs.getInt("eventno"));
					eventBean.setTrainerid(rs.getString("trainerid"));
					eventBean.setRoomno(rs.getInt("roomno"));
					eventBean.setTitle(rs.getString("title"));
					eventBean.setCoursekind(rs.getString("coursekind"));
					eventBean.setE_status(rs.getString("e_status"));
					eventBean.setEventstart(rs.getDate("eventstart"));
					eventBean.setEventend(rs.getDate("eventend"));
					eventBean.setEnroll(rs.getInt("enroll"));
					eventBean.setCharge(rs.getInt("charge"));	
					
					list.add(eventBean); // Store the row in the list 
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}
}
