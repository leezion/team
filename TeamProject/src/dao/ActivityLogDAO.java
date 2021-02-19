package dao;

import db.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.ActivityLogVO;

public class ActivityLogDAO {
	Connection con;
	private static ActivityLogDAO activityLogDAO;
	
	private ActivityLogDAO() {}
	
	public ActivityLogDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized ActivityLogDAO getInstance() {
		if(activityLogDAO == null) {
			activityLogDAO = new ActivityLogDAO();
		}
		return activityLogDAO;
	}
	
	// 기본키로 검색
	public ActivityLogVO select(int eventid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityLogVO activityLogVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM ACTIVITY_LOG WHERE EVENTID=?");
			pstmt.setInt(1, eventid);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				activityLogVO = new ActivityLogVO();
				
				activityLogVO.setEventid(rs.getInt("EVENTID"));
				activityLogVO.setRegdate(rs.getTimestamp("REGDATE"));
				activityLogVO.setUserid(rs.getString("USERID"));
				activityLogVO.setUser_off(rs.getString("USER_OFF"));
				activityLogVO.setIp(rs.getString("IP"));
				activityLogVO.setReplyid(rs.getInt("REPLYID"));
				activityLogVO.setArticleid(rs.getInt("ARTICLEID"));
				activityLogVO.setItemid(rs.getInt("ITEMID"));
				activityLogVO.setDealid(rs.getInt("DEALID"));
				activityLogVO.setReviewid(rs.getInt("REVIEWID"));
				activityLogVO.setReportid(rs.getInt("REPORTID"));
				activityLogVO.setEvent(rs.getString("EVENT"));
						
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return activityLogVO;
		
	} // END ActivityLogVO select(int eventid)
	
	// 행번호 기준 검색
	public ArrayList<ActivityLogVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityLogVO activityLogVO = null;
		ArrayList<ActivityLogVO> list = new ArrayList<ActivityLogVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, EVENTID, REGDATE, USERID, USER_OFF, IP, REPLYID, ARTICLEID, ITEMID, DEALID, REVIEWID, REPORTID, EVENT FROM (SELECT * FROM ACTIVITY_LOG ORDER BY REGDATE DESC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				activityLogVO = new ActivityLogVO();
				
				activityLogVO.setEventid(rs.getInt("EVENTID"));
				activityLogVO.setRegdate(rs.getTimestamp("REGDATE"));
				activityLogVO.setUserid(rs.getString("USERID"));
				activityLogVO.setUser_off(rs.getString("USER_OFF"));
				activityLogVO.setIp(rs.getString("IP"));
				activityLogVO.setReplyid(rs.getInt("REPLYID"));
				activityLogVO.setArticleid(rs.getInt("ARTICLEID"));
				activityLogVO.setItemid(rs.getInt("ITEMID"));
				activityLogVO.setDealid(rs.getInt("DEALID"));
				activityLogVO.setReviewid(rs.getInt("REVIEWID"));
				activityLogVO.setReportid(rs.getInt("REPORTID"));
				activityLogVO.setEvent(rs.getString("EVENT"));
				
				list.add(activityLogVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<ActivityLogVO> select(int startrow, int endrow)
	
	
	//시간기준 검색 : 형식 "YYYY-MM-DD HH24:MI:SS"
	public ArrayList<ActivityLogVO> selectByTime(String fromTime, String toTime) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityLogVO activityLogVO = null;
		ArrayList<ActivityLogVO> list = new ArrayList<ActivityLogVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM ACTIVITY_LOG WHERE REGDATE BETWEEN TO_DATE('"+fromTime+"', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+toTime+"', 'YYYY-MM-DD HH24:MI:SS')");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				activityLogVO = new ActivityLogVO();
				
				activityLogVO.setEventid(rs.getInt("EVENTID"));
				activityLogVO.setRegdate(rs.getTimestamp("REGDATE"));
				activityLogVO.setUserid(rs.getString("USERID"));
				activityLogVO.setUser_off(rs.getString("USER_OFF"));
				activityLogVO.setIp(rs.getString("IP"));
				activityLogVO.setReplyid(rs.getInt("REPLYID"));
				activityLogVO.setArticleid(rs.getInt("ARTICLEID"));
				activityLogVO.setItemid(rs.getInt("ITEMID"));
				activityLogVO.setDealid(rs.getInt("DEALID"));
				activityLogVO.setReviewid(rs.getInt("REVIEWID"));
				activityLogVO.setReportid(rs.getInt("REPORTID"));
				activityLogVO.setEvent(rs.getString("EVENT"));
				
				list.add(activityLogVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<ActivityLogVO> selectByTime(String fromTime, String toTime)
	
	
	// 기본키로 삭제
	public int delete(int eventid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM ACTIVITY_LOG WHERE EVENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, eventid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int delete(int eventid)
	
	// 데이타 삽입
	public int insert(ActivityLogVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO ACTIVITY_LOG(EVENTID, REGDATE, USERID, USER_OFF, IP, REPLYID, ARTICLEID, ITEMID, DEALID, REVIEWID, REPORTID, EVENT) "
					+ "VALUES(SEQ_ACTIVITY_LOG.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setTimestamp(1, vo.getRegdate());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getUser_off());
			pstmt.setString(4, vo.getIp());
			pstmt.setInt(5, vo.getReplyid());
			pstmt.setInt(6, vo.getArticleid());
			pstmt.setInt(7, vo.getItemid());
			pstmt.setInt(8, vo.getDealid());
			pstmt.setInt(9, vo.getReviewid());
			pstmt.setInt(10, vo.getReportid());
			pstmt.setString(11, vo.getEvent());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int insert(ActivityLogVO vo)
	
	
	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int eventid, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE ACTIVITY_LOG SET "+attributeName+" = ? WHERE EVENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setInt(2, eventid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int eventid, String attributeName, String inputString)
	public int update(int eventid, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE ACTIVITY_LOG SET "+attributeName+" = ? WHERE EVENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setInt(2, eventid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int eventid, String attributeName, int inputInt)
	
	
	// 필요하다면 이 밑에 DAO관련 메소드 추가하기 바람---------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
} // END public class ActivityLogDAO
