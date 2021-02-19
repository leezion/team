package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.ConditionVO;
import db.JdbcUtil;
public class ConditionDAO {
	Connection con;
	private static ConditionDAO conditionDAO;
	
	private ConditionDAO() {}
	
	public ConditionDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized ConditionDAO getInstance() {
		if(conditionDAO == null) {
			conditionDAO = new ConditionDAO();
		}
		return conditionDAO;
	}
	
	// 기본키로 검색
	public ConditionVO select(int code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ConditionVO condtionVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM CONDITIONLIST WHERE CODE=?");
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				condtionVO = new ConditionVO();
				condtionVO.setCode(rs.getInt("CODE"));
				condtionVO.setStr(rs.getString("STR"));
				condtionVO.setDescription(rs.getString("DESCRIPTION"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return condtionVO;
		
	} // END ConditionVO select(int code)
	
	// 행번호 기준 검색
	public ArrayList<ConditionVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ConditionVO condtionVO = null;
		ArrayList<ConditionVO> list = new ArrayList<ConditionVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, CODE, STR, DESCRIPTION FROM (SELECT * FROM CONDITIONLIST ORDER BY STR ASC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				condtionVO = new ConditionVO();
				condtionVO.setCode(rs.getInt("CODE"));
				condtionVO.setStr(rs.getString("STR"));
				condtionVO.setDescription(rs.getString("DESCRIPTION"));

				list.add(condtionVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<ConditionVO> select(int startrow, int endrow)
	
	
	// 기본키로 삭제
	public int delete(int code) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM CONDITIONLIST WHERE CODE=?";
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
	public int insert(ConditionVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "{CALL PRO_AUTOCODE_CONDITIONLIST(?,?)}";
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
	}// END int insert(ConditionVO vo)

	
	
	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int code, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE CONDITIONLIST SET "+attributeName+" = ? WHERE CODE = ?";
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
			sql = "UPDATE CONDITIONLIST SET "+attributeName+" = ? WHERE CODE = ?";
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
	
	
}// END public class ConditionDAO
