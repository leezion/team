package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.CategoryVO;
import db.JdbcUtil;
public class CategoryDAO {
	Connection con;
	private static CategoryDAO categoryDAO;
	
	private CategoryDAO() {}
	
	public CategoryDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized CategoryDAO getInstance() {
		if(categoryDAO == null) {
			categoryDAO = new CategoryDAO();
		}
		return categoryDAO;
	}
	
	// 기본키로 검색
	public CategoryVO select(int code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CategoryVO categoryVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM CATEGORYLIST WHERE CODE=?");
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				categoryVO = new CategoryVO();
				categoryVO.setCode(rs.getInt("CODE"));
				categoryVO.setStr(rs.getString("STR"));
				categoryVO.setDescription(rs.getString("DESCRIPTION"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return categoryVO;
		
	} // END CategoryVO select(int code)
	
	// 행번호 기준 검색
	public ArrayList<CategoryVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CategoryVO categoryVO = null;
		ArrayList<CategoryVO> list = new ArrayList<CategoryVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, CODE, STR, DESCRIPTION FROM (SELECT * FROM CATEGORYLIST ORDER BY STR ASC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				categoryVO = new CategoryVO();
				categoryVO.setCode(rs.getInt("CODE"));
				categoryVO.setStr(rs.getString("STR"));
				categoryVO.setDescription(rs.getString("DESCRIPTION"));

				list.add(categoryVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<CategoryVO> select(int startrow, int endrow)
	
	// 기본키로 삭제
	public int delete(int code) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM CATEGORYLIST WHERE CODE=?";
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
	public int insert(CategoryVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "{CALL PRO_AUTOCODE_CATEGORYLIST(?,?)}";
			
			
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
	}// END int insert(CategoryVO vo)


	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int code, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE CATEGORYLIST SET "+attributeName+" = ? WHERE CODE = ?";
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
			sql = "UPDATE CATEGORYLIST SET "+attributeName+" = ? WHERE CODE = ?";
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
	
	
}// END public class CategoryDAO
