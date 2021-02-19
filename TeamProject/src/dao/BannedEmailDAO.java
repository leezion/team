package dao;
import db.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vo.BannedEmailVO;

public class BannedEmailDAO {
	Connection con;
	private static BannedEmailDAO bannedEmailDAO;
	
	private BannedEmailDAO() {}
	
	public BannedEmailDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized BannedEmailDAO getInstance() {
		if(bannedEmailDAO == null) {
			bannedEmailDAO = new BannedEmailDAO();
		}
		return bannedEmailDAO;
	}
	
	// 기본키로 검색
	public BannedEmailVO select(String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BannedEmailVO bannedEmailVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM BANNED_EMAIL WHERE EMAIL=?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				bannedEmailVO = new BannedEmailVO();
				
				bannedEmailVO.setEmail(rs.getString("EMAIL"));
				bannedEmailVO.setRegdate(rs.getTimestamp("REGDATE"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return bannedEmailVO;
		
	} // END BannedEmailVO select(String email)
	
	// 행번호 기준 검색
	public ArrayList<BannedEmailVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BannedEmailVO bannedEmailVO = null;
		ArrayList<BannedEmailVO> list = new ArrayList<BannedEmailVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, EMAIL, REGDATE FROM (SELECT * FROM BANNED_EMAIL ORDER BY REGDATE DESC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				bannedEmailVO = new BannedEmailVO();
				
				bannedEmailVO.setEmail(rs.getString("EMAIL"));
				bannedEmailVO.setRegdate(rs.getTimestamp("REGDATE"));
				
				list.add(bannedEmailVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<BannedEmailVO> select(int startrow, int endrow)

	
	//시간기준 검색 : 형식 "YYYY-MM-DD HH24:MI:SS"
	public ArrayList<BannedEmailVO> selectByTime(String fromTime, String toTime) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BannedEmailVO bannedEmailVO = null;
		ArrayList<BannedEmailVO> list = new ArrayList<BannedEmailVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM BANNED_EMAIL WHERE REGDATE BETWEEN TO_DATE('"+fromTime+"', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+toTime+"', 'YYYY-MM-DD HH24:MI:SS')");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				bannedEmailVO = new BannedEmailVO();
				
				bannedEmailVO.setEmail(rs.getString("EMAIL"));
				bannedEmailVO.setRegdate(rs.getTimestamp("REGDATE"));
				
				list.add(bannedEmailVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<BannedEmailVO> selectByTime(String fromTime, String toTime)
	
	// 기본키로 삭제
	public int delete(String email) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM BANNED_EMAIL WHERE EMAIL = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int delete(int email)
	
	
	// 데이타 삽입
	public int insert(BannedEmailVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO BANNED_EMAIL(EMAIL, REGDATE) "
					+ "VALUES(?,SYSDATE) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int insert(BannedEmailVO vo)
	
	
	
	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(String email, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE BANNED_EMAIL SET "+attributeName+" = ? WHERE EMAIL = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setString(2, email);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int email, String attributeName, String inputString)
	public int update(String email, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE BANNED_EMAIL SET "+attributeName+" = ? WHERE EMAIL = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setString(2, email);// 수정
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int email, String attributeName, int inputInt)
	
	
	// 필요하다면 이 밑에 DAO관련 메소드 추가하기 바람---------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
}// END public class BannedEmailDAO
