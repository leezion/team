package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.ReplyVO;
import db.JdbcUtil;
public class ReplyDAO {
	Connection con;
	private static ReplyDAO replyDAO;
	
	private ReplyDAO() {}
	
	public ReplyDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized ReplyDAO getInstance() {
		if(replyDAO == null) {
			replyDAO = new ReplyDAO();
		}
		return replyDAO;
	}
	
	// 기본키로 검색
	public ReplyVO select(int replyid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReplyVO replyVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM REPLY WHERE REPLYID=?");
			pstmt.setInt(1, replyid);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				replyVO = new ReplyVO();
				
				replyVO.setReplyid(rs.getInt("REPLYID"));
				replyVO.setContent(rs.getString("CONTENT"));
				replyVO.setRegdate(rs.getTimestamp("REGDATE"));
				replyVO.setArticleid(rs.getInt("ARTICLEID"));
				replyVO.setIp(rs.getString("IP"));
				replyVO.setUserid(rs.getString("USERID"));
				replyVO.setUserid_off(rs.getString("USERID_OFF"));
				replyVO.setPassword_off(rs.getString("PASSWORD_OFF"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return replyVO;
		
	} // END ReplyVO select(int replyid)
	
	// 행번호 기준 검색
	public ArrayList<ReplyVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReplyVO replyVO = null;
		ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, REPLYID, CONTENT, REGDATE, ARTICLEID, IP, USERID, USERID_OFF, PASSWORD_OFF FROM (SELECT * FROM REPLY ORDER BY REGDATE DESC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				replyVO = new ReplyVO();
				
				replyVO.setReplyid(rs.getInt("REPLYID"));
				replyVO.setContent(rs.getString("CONTENT"));
				replyVO.setRegdate(rs.getTimestamp("REGDATE"));
				replyVO.setArticleid(rs.getInt("ARTICLEID"));
				replyVO.setIp(rs.getString("IP"));
				replyVO.setUserid(rs.getString("USERID"));
				replyVO.setUserid_off(rs.getString("USERID_OFF"));
				replyVO.setPassword_off(rs.getString("PASSWORD_OFF"));

				list.add(replyVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<ReplyVO> select(int startrow, int endrow)	
	
	
	//시간기준 검색 : 형식 "YYYY-MM-DD HH24:MI:SS"
	public ArrayList<ReplyVO> selectByTime(String fromTime, String toTime) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReplyVO replyVO = null;
		ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM REPLY WHERE REGDATE BETWEEN TO_DATE('"+fromTime+"', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+toTime+"', 'YYYY-MM-DD HH24:MI:SS')");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				replyVO = new ReplyVO();
				
				replyVO.setReplyid(rs.getInt("REPLYID"));
				replyVO.setContent(rs.getString("CONTENT"));
				replyVO.setRegdate(rs.getTimestamp("REGDATE"));
				replyVO.setArticleid(rs.getInt("ARTICLEID"));
				replyVO.setIp(rs.getString("IP"));
				replyVO.setUserid(rs.getString("USERID"));
				replyVO.setUserid_off(rs.getString("USERID_OFF"));
				replyVO.setPassword_off(rs.getString("PASSWORD_OFF"));

				list.add(replyVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<ReplyVO> selectByTime(String fromTime, String toTime)
	
	
	// 기본키로 삭제
	public int delete(int replyid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM REPLY WHERE REPLYID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, replyid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int delete(int replyid)


	// 데이타 삽입
	public int insert(ReplyVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO REPLY(REPLYID, CONTENT, REGDATE, ARTICLEID, IP, USERID, USERID_OFF, PASSWORD_OFF) "
					+ "VALUES(SEQ_REPLY.NEXTVAL,?,SYSDATE,?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			
			
			
			pstmt.setString(1, vo.getContent());
			
			pstmt.setInt(2, vo.getArticleid());
			pstmt.setString(3, vo.getIp());
			pstmt.setString(4, vo.getUserid());
			pstmt.setString(5, vo.getUserid_off());
			pstmt.setString(6, vo.getPassword_off());

			
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int insert(ReplyVO vo)
	
	
	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int replyid, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE REPLY SET "+attributeName+" = ? WHERE REPLYID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setInt(2, replyid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int replyid, String attributeName, String inputString)
	public int update(int replyid, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE REPLY SET "+attributeName+" = ? WHERE REPLYID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setInt(2, replyid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int replyid, String attributeName, int inputInt)
	
	
	// 필요하다면 이 밑에 DAO관련 메소드 추가하기 바람---------------------------------------------------------------------------------------------------------	
	
	
}// END public class ReplyDAO
