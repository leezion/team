package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.RarityVO;
import db.JdbcUtil;
public class RarityDAO {
	Connection con;
	private static RarityDAO rarityDAO;
	
	private RarityDAO() {}
	
	public RarityDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized RarityDAO getInstance() {
		if(rarityDAO == null) {
			rarityDAO = new RarityDAO();
		}
		return rarityDAO;
	}
	
	// 기본키로 검색
	public RarityVO select(int code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RarityVO rarityVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM RARITYLIST WHERE CODE=?");
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				rarityVO = new RarityVO();
				
				rarityVO.setCode(rs.getInt("CODE"));
				rarityVO.setStr(rs.getString("STR"));
				rarityVO.setDescription(rs.getString("DESCRIPTION"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return rarityVO;
		
	} // END RarityVO select(int code)
	
	// 행번호 기준 검색
	public ArrayList<RarityVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RarityVO rarityVO = null;
		ArrayList<RarityVO> list = new ArrayList<RarityVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, CODE, STR, DESCRIPTION FROM (SELECT * FROM RARITYLIST ORDER BY STR ASC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				rarityVO = new RarityVO();
				
				rarityVO.setCode(rs.getInt("CODE"));
				rarityVO.setStr(rs.getString("STR"));
				rarityVO.setDescription(rs.getString("DESCRIPTION"));

				list.add(rarityVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<RarityVO> select(int startrow, int endrow) 
	
	// 기본키로 삭제
	public int delete(int code) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM RARITYLIST WHERE CODE=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int delete(int code)


	// 데이타 삽입
	public int insert(RarityVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "{CALL PRO_AUTOCODE_RARITYLIST(?,?)}";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getStr());
			pstmt.setString(2, vo.getDescription());
			
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int insert(RarityVO vo)
	
	
	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int code, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE RARITYLIST SET "+attributeName+" = ? WHERE CODE = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setInt(2, code);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int code, String attributeName, String inputString)
	public int update(int code, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE RARITYLIST SET "+attributeName+" = ? WHERE CODE = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setInt(2, code);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int code, String attributeName, int inputInt)
	
	
	// 필요하다면 이 밑에 DAO관련 메소드 추가하기 바람---------------------------------------------------------------------------------------------------------	
	
	
}// END public class RarityDAO
