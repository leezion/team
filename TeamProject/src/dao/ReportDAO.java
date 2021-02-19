package dao;

import db.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.ReportVO;



public class ReportDAO {
	Connection con;
	private static ReportDAO reportDAO;
	
	private ReportDAO() {}
	
	public ReportDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized ReportDAO getInstance() {
		if(reportDAO == null) {
			reportDAO = new ReportDAO();
		}
		return reportDAO;
	}
	
	// 기본키로 검색
	public ReportVO select(int reportid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReportVO reportVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM REPORTLIST WHERE REPORTID=?");
			pstmt.setInt(1, reportid);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				reportVO = new ReportVO();
				
				reportVO.setReportid(rs.getInt("REPORTID"));
				reportVO.setCategory(rs.getInt("CATEGORY"));
				reportVO.setTitle(rs.getString("TITLE"));
				reportVO.setContent(rs.getString("CONTENT"));
				reportVO.setRegdate(rs.getTimestamp("REGDATE"));
				reportVO.setUserid(rs.getString("USERID"));
				reportVO.setReadconfirm(rs.getString("READCONFIRM"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return reportVO;
		
	} // END ReportVO select(int reportid)
	
	// 행번호 기준 검색
	public ArrayList<ReportVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReportVO reportVO = null;
		ArrayList<ReportVO> list = new ArrayList<ReportVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, REPORTID, CATEGORY, TITLE, CONTENT, REGDATE, USERID, READCONFIRM FROM (SELECT * FROM REPORTLIST ORDER BY REGDATE DESC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				reportVO = new ReportVO();
				
				reportVO.setReportid(rs.getInt("REPORTID"));
				reportVO.setCategory(rs.getInt("CATEGORY"));
				reportVO.setTitle(rs.getString("TITLE"));
				reportVO.setContent(rs.getString("CONTENT"));
				reportVO.setRegdate(rs.getTimestamp("REGDATE"));
				reportVO.setUserid(rs.getString("USERID"));
				reportVO.setReadconfirm(rs.getString("READCONFIRM"));

				list.add(reportVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<ReportVO> select(int startrow, int endrow)
	
	
	//시간기준 검색 : 형식 "YYYY-MM-DD HH24:MI:SS"
	public ArrayList<ReportVO> selectByTime(String fromTime, String toTime) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReportVO reportVO = null;
		ArrayList<ReportVO> list = new ArrayList<ReportVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM REPORTLIST WHERE REGDATE BETWEEN TO_DATE('"+fromTime+"', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+toTime+"', 'YYYY-MM-DD HH24:MI:SS')");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				reportVO = new ReportVO();
				
				reportVO.setReportid(rs.getInt("REPORTID"));
				reportVO.setCategory(rs.getInt("CATEGORY"));
				reportVO.setTitle(rs.getString("TITLE"));
				reportVO.setContent(rs.getString("CONTENT"));
				reportVO.setRegdate(rs.getTimestamp("REGDATE"));
				reportVO.setUserid(rs.getString("USERID"));
				reportVO.setReadconfirm(rs.getString("READCONFIRM"));

				list.add(reportVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<ReportVO> selectByTime(String fromTime, String toTime)
	
	
	// 기본키로 삭제
	public int delete(int reportid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM REPORTLIST WHERE REPORTID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reportid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int delete(int reportid)

	
	// 데이타 삽입
	public int insert(ReportVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO REPORTLIST(REPORTID, CATEGORY, TITLE, CONTENT, REGDATE, USERID, READCONFIRM) "
					+ "VALUES(SEQ_REPORTLIST.NEXTVAL,?,?,?,SYSDATE,?,'N') ";
			pstmt = con.prepareStatement(sql);
			
			
			
			pstmt.setInt(1, vo.getCategory());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			
			pstmt.setString(4, vo.getUserid());
			
			
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int insert(ReportVO vo)
	
	
	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int reportid, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE REPORTLIST SET "+attributeName+" = ? WHERE REPORTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setInt(2, reportid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int reportid, String attributeName, String inputString)
	public int update(int reportid, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE REPORTLIST SET "+attributeName+" = ? WHERE REPORTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setInt(2, reportid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int reportid, String attributeName, int inputInt)
	
	
	// 필요하다면 이 밑에 DAO관련 메소드 추가하기 바람---------------------------------------------------------------------------------------------------------	
	
	
}// END public class ReportDAO
