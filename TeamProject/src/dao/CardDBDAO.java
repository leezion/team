package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.CardDBVO;
import db.JdbcUtil;
public class CardDBDAO {
	Connection con;
	private static CardDBDAO cardDBDAO;
	
	private CardDBDAO() {}
	
	public CardDBDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized CardDBDAO getInstance() {
		if(cardDBDAO == null) {
			cardDBDAO = new CardDBDAO();
		}
		return cardDBDAO;
	}
	

	// 기본키로 검색
	public CardDBVO select(int cardid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CardDBVO cardDBVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM CARD_DB WHERE CARDID = ?");
			pstmt.setInt(1, cardid);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				cardDBVO = new CardDBVO();
				
				cardDBVO.setCardid(rs.getInt("CARDID"));
				cardDBVO.setCode_first(rs.getString("CODE_FIRST"));
				cardDBVO.setNation(rs.getString("NATION"));
				cardDBVO.setNcode(rs.getString("NCODE"));
				cardDBVO.setYear(rs.getString("YEAR"));
				cardDBVO.setPackage_type(rs.getString("PACKAGE_TYPE"));
				cardDBVO.setPackage_kr(rs.getString("PACKAGE_KR"));
				cardDBVO.setPackage_jp(rs.getString("PACKAGE_JP"));
				cardDBVO.setPackage_en(rs.getString("PACKAGE_EN"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return cardDBVO;
		
	} // END CardDBVO select(String cardid)
	
	
	
	// 앞자리 코드 기준 검색
	public ArrayList<CardDBVO> selectByCodeFirst(String code_first) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CardDBVO cardDBVO = null;
		ArrayList<CardDBVO> list = new ArrayList<CardDBVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM CARD_DB WHERE CODE_FIRST = ?");
			pstmt.setString(1, code_first);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				cardDBVO = new CardDBVO();
				
				cardDBVO.setCardid(rs.getInt("CARDID"));
				cardDBVO.setCode_first(rs.getString("CODE_FIRST"));
				cardDBVO.setNation(rs.getString("NATION"));
				cardDBVO.setNcode(rs.getString("NCODE"));
				cardDBVO.setYear(rs.getString("YEAR"));
				cardDBVO.setPackage_type(rs.getString("PACKAGE_TYPE"));
				cardDBVO.setPackage_kr(rs.getString("PACKAGE_KR"));
				cardDBVO.setPackage_jp(rs.getString("PACKAGE_JP"));
				cardDBVO.setPackage_en(rs.getString("PACKAGE_EN"));

				list.add(cardDBVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<CardDBVO> selectByCodeFirst(String code_first)
	
	
	// 행번호 기준 검색
	public ArrayList<CardDBVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CardDBVO cardDBVO = null;
		ArrayList<CardDBVO> list = new ArrayList<CardDBVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, CARDID, CODE_FIRST, NATION, NCODE, YEAR, PACKAGE_TYPE, PACKAGE_KR, PACKAGE_JP, PACKAGE_EN FROM (SELECT * FROM CARD_DB ORDER BY YEAR DESC, CODE_FIRST ASC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				cardDBVO = new CardDBVO();
				
				cardDBVO.setCardid(rs.getInt("CARDID"));
				cardDBVO.setCode_first(rs.getString("CODE_FIRST"));
				cardDBVO.setNation(rs.getString("NATION"));
				cardDBVO.setNcode(rs.getString("NCODE"));
				cardDBVO.setYear(rs.getString("YEAR"));
				cardDBVO.setPackage_type(rs.getString("PACKAGE_TYPE"));
				cardDBVO.setPackage_kr(rs.getString("PACKAGE_KR"));
				cardDBVO.setPackage_jp(rs.getString("PACKAGE_JP"));
				cardDBVO.setPackage_en(rs.getString("PACKAGE_EN"));

				list.add(cardDBVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<CardDBVO> select(int startrow, int endrow)
	
	
	

	// 기본키로 삭제
	public int delete(int cardid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM CARD_DB WHERE CARDID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cardid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int delete(String code_first, String ncode)
	
	
	// 데이타 삽입
	public int insert(CardDBVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO CARD_DB(CARDID, CODE_FIRST, NATION, NCODE, YEAR, PACKAGE_TYPE, PACKAGE_KR, PACKAGE_JP, PACKAGE_EN) "
					+ "VALUES(SEQ_CARD_DB.NEXTVAL,?,?,?,?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getCode_first());
			pstmt.setString(2, vo.getNation());
			pstmt.setString(3, vo.getNcode());
			pstmt.setString(4, vo.getYear());
			pstmt.setString(5, vo.getPackage_type());
			pstmt.setString(6, vo.getPackage_kr());
			pstmt.setString(7, vo.getPackage_jp());
			pstmt.setString(8, vo.getPackage_en());
			
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int insert(CardDBVO vo)
	
	

	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int cardid, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE CARD_DB SET "+attributeName+" = ? WHERE CARDID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setInt(2, cardid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int cardid, String attributeName, String inputString)
	public int update(int cardid, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE CARD_DB SET "+attributeName+" = ? WHERE CARDID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setInt(2, cardid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int cardid, String attributeName, int inputInt)
	
	
	
	// 필요하다면 이 밑에 DAO관련 메소드 추가하기 바람---------------------------------------------------------------------------------------------------------	
	
	
	
}// END public class CardDBDAO
