package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtil;
import vo.BoardVO;

public class BoardDAO {
	Connection con;
	private static BoardDAO boardDAO;
	
	private BoardDAO() {}
	
	public BoardDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized BoardDAO getInstance() {
		if(boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	
	// 기본키로 검색
	public BoardVO select(int articleid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM BOARD WHERE ARTICLEID=?");
			pstmt.setInt(1, articleid);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				boardVO = new BoardVO();
				
				boardVO.setArticleid(rs.getInt("ARTICLEID"));
				boardVO.setTitle(rs.getString("TITLE"));
				boardVO.setContent(rs.getString("CONTENT"));
				boardVO.setReadcount(rs.getInt("READCOUNT"));
				boardVO.setRegdate(rs.getTimestamp("REGDATE"));
				boardVO.setIp(rs.getString("IP"));
				boardVO.setUserid(rs.getString("USERID"));
				boardVO.setUserid_off(rs.getString("USERID_OFF"));
				boardVO.setPassword_off(rs.getString("PASSWORD_OFF"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return boardVO;
		
	} // END BoardVO select(int articleid)
	
	// 행번호 기준 검색
	public ArrayList<BoardVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVO = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, ARTICLEID, TITLE, CONTENT, READCOUNT, REGDATE, IP, USERID, USERID_OFF, PASSWORD_OFF FROM (SELECT * FROM BOARD ORDER BY REGDATE DESC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				boardVO = new BoardVO();
				
				boardVO.setArticleid(rs.getInt("ARTICLEID"));
				boardVO.setTitle(rs.getString("TITLE"));
				boardVO.setContent(rs.getString("CONTENT"));
				boardVO.setReadcount(rs.getInt("READCOUNT"));
				boardVO.setRegdate(rs.getTimestamp("REGDATE"));
				boardVO.setIp(rs.getString("IP"));
				boardVO.setUserid(rs.getString("USERID"));
				boardVO.setUserid_off(rs.getString("USERID_OFF"));
				boardVO.setPassword_off(rs.getString("PASSWORD_OFF"));
				
				list.add(boardVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<BoardVO> select(int startrow, int endrow)
	
	
	//시간기준 검색 : 형식 "YYYY-MM-DD HH24:MI:SS"
	public ArrayList<BoardVO> selectByTime(String fromTime, String toTime) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVO = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM BOARD WHERE REGDATE BETWEEN TO_DATE('"+fromTime+"', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+toTime+"', 'YYYY-MM-DD HH24:MI:SS')");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				boardVO = new BoardVO();
				
				boardVO.setArticleid(rs.getInt("ARTICLEID"));
				boardVO.setTitle(rs.getString("TITLE"));
				boardVO.setContent(rs.getString("CONTENT"));
				boardVO.setReadcount(rs.getInt("READCOUNT"));
				boardVO.setRegdate(rs.getTimestamp("REGDATE"));
				boardVO.setIp(rs.getString("IP"));
				boardVO.setUserid(rs.getString("USERID"));
				boardVO.setUserid_off(rs.getString("USERID_OFF"));
				boardVO.setPassword_off(rs.getString("PASSWORD_OFF"));
				
				list.add(boardVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<BoardVO> selectByTime(String fromTime, String toTime)
	
	// 기본키로 삭제
	public int delete(int articleid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM BOARD WHERE ARTICLEID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int delete(int articleid)
	
		
	// 데이타 삽입
	public int insert(BoardVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO BOARD(ARTICLEID, TITLE, CONTENT, READCOUNT, REGDATE, IP, USERID, USERID_OFF, PASSWORD_OFF) "
					+ "VALUES(SEQ_ARTICLE.NEXTVAL,?,?,?,SYSDATE,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getReadcount());
			
			pstmt.setString(4, vo.getIp());
			pstmt.setString(5, vo.getUserid());
			pstmt.setString(6, vo.getUserid_off());
			pstmt.setString(7, vo.getPassword_off());

			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int insert(BoardVO vo)
		
		
	
	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int articleid, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE BOARD SET "+attributeName+" = ? WHERE ARTICLEID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setInt(2, articleid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int articleid, String attributeName, String inputString)
	public int update(int articleid, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE BOARD SET "+attributeName+" = ? WHERE ARTICLEID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setInt(2, articleid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int articleid, String attributeName, int inputInt)
	
	
	
	// 필요하다면 이 밑에 DAO관련 메소드 추가하기 바람---------------------------------------------------------------------------------------------------------
	
	
	
	
}// END public class BoardDAO
