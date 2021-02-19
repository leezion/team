package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.ItemVO;
import db.JdbcUtil;
public class ItemDAO {
	Connection con;
	private static ItemDAO itemDAO;
	
	private ItemDAO() {}
	
	public ItemDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized ItemDAO getInstance() {
		if(itemDAO == null) {
			itemDAO = new ItemDAO();
		}
		return itemDAO;
	}
	
	// 기본키로 검색
	public ItemVO select(int itemid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itemVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM ITEMLIST WHERE ITEMID=?");
			pstmt.setInt(1, itemid);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				itemVO = new ItemVO();
				
				itemVO.setItemid(rs.getInt("ITEMID"));
				itemVO.setCategory(rs.getInt("CATEGORY"));
				itemVO.setGeneralgoods(rs.getInt("GENERALGOODS"));
				itemVO.setCode(rs.getString("CODE"));
				itemVO.setRarity(rs.getInt("RARITY"));
				itemVO.setFirstlimited(rs.getString("FIRSTLIMITED"));
				itemVO.setCondition(rs.getInt("CONDITION"));
				itemVO.setPrice(rs.getInt("PRICE"));
				itemVO.setRegdate(rs.getTimestamp("REGDATE"));
				itemVO.setDescription(rs.getString("DESCRIPTION"));
				itemVO.setQuantity(rs.getInt("QUANTITY"));
				itemVO.setVisible(rs.getString("VISIBLE"));
				itemVO.setDeleted(rs.getString("DELETED"));
				itemVO.setImage1(rs.getString("IMAGE1"));
				itemVO.setImage2(rs.getString("IMAGE2"));
				itemVO.setImage3(rs.getString("IMAGE3"));
				itemVO.setImage4(rs.getString("IMAGE4"));
				itemVO.setSellerid(rs.getString("SELLERID"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return itemVO;
		
	} // END ItemVO select(int itemid)
	
	// 행번호 기준 검색
	public ArrayList<ItemVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itemVO = null;
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, ITEMID, CATEGORY, GENERALGOODS, CODE, RARITY, FIRSTLIMITED, CONDITION, PRICE, REGDATE, DESCRIPTION, QUANTITY, VISIBLE, DELETED, IMAGE1, IMAGE2, IMAGE3, IMAGE4, SELLERID FROM (SELECT * FROM ITEMLIST ORDER BY REGDATE DESC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				itemVO = new ItemVO();
				
				itemVO.setItemid(rs.getInt("ITEMID"));
				itemVO.setCategory(rs.getInt("CATEGORY"));
				itemVO.setGeneralgoods(rs.getInt("GENERALGOODS"));
				itemVO.setCode(rs.getString("CODE"));
				itemVO.setRarity(rs.getInt("RARITY"));
				itemVO.setFirstlimited(rs.getString("FIRSTLIMITED"));
				itemVO.setCondition(rs.getInt("CONDITION"));
				itemVO.setPrice(rs.getInt("PRICE"));
				itemVO.setRegdate(rs.getTimestamp("REGDATE"));
				itemVO.setDescription(rs.getString("DESCRIPTION"));
				itemVO.setQuantity(rs.getInt("QUANTITY"));
				itemVO.setVisible(rs.getString("VISIBLE"));
				itemVO.setDeleted(rs.getString("DELETED"));
				itemVO.setImage1(rs.getString("IMAGE1"));
				itemVO.setImage2(rs.getString("IMAGE2"));
				itemVO.setImage3(rs.getString("IMAGE3"));
				itemVO.setImage4(rs.getString("IMAGE4"));
				itemVO.setSellerid(rs.getString("SELLERID"));

				list.add(itemVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<ItemVO> select(int startrow, int endrow)
	
	
	//시간기준 검색 : 형식 "YYYY-MM-DD HH24:MI:SS"
	public ArrayList<ItemVO> selectByTime(String fromTime, String toTime) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itemVO = null;
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM ITEMLIST WHERE REGDATE BETWEEN TO_DATE('"+fromTime+"', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+toTime+"', 'YYYY-MM-DD HH24:MI:SS')");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				itemVO = new ItemVO();
				
				itemVO.setItemid(rs.getInt("ITEMID"));
				itemVO.setCategory(rs.getInt("CATEGORY"));
				itemVO.setGeneralgoods(rs.getInt("GENERALGOODS"));
				itemVO.setCode(rs.getString("CODE"));
				itemVO.setRarity(rs.getInt("RARITY"));
				itemVO.setFirstlimited(rs.getString("FIRSTLIMITED"));
				itemVO.setCondition(rs.getInt("CONDITION"));
				itemVO.setPrice(rs.getInt("PRICE"));
				itemVO.setRegdate(rs.getTimestamp("REGDATE"));
				itemVO.setDescription(rs.getString("DESCRIPTION"));
				itemVO.setQuantity(rs.getInt("QUANTITY"));
				itemVO.setVisible(rs.getString("VISIBLE"));
				itemVO.setDeleted(rs.getString("DELETED"));
				itemVO.setImage1(rs.getString("IMAGE1"));
				itemVO.setImage2(rs.getString("IMAGE2"));
				itemVO.setImage3(rs.getString("IMAGE3"));
				itemVO.setImage4(rs.getString("IMAGE4"));
				itemVO.setSellerid(rs.getString("SELLERID"));

				list.add(itemVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<ItemVO> selectByTime(String fromTime, String toTime)
	
	// 기본키로 삭제
	public int delete(int itemid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM ITEMLIST WHERE ITEMID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int delete(int itemid)


	// 데이타 삽입
	public int insert(ItemVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO ITEMLIST(ITEMID, CATEGORY, GENERALGOODS, CODE, RARITY, FIRSTLIMITED, CONDITION, PRICE, REGDATE, DESCRIPTION, QUANTITY, VISIBLE, DELETED, IMAGE1, IMAGE2, IMAGE3, IMAGE4, SELLERID) "
					+ "VALUES(SEQ_ITEMLIST.NEXTVAL,?,?,?,?,?,?,?,SYSDATE,?,?,'N','N',?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setInt(1, vo.getCategory());
			pstmt.setInt(2, vo.getGeneralgoods());
			pstmt.setString(3, vo.getCode());
			pstmt.setInt(4, vo.getRarity());
			pstmt.setString(5, vo.getFirstlimited());
			pstmt.setInt(6, vo.getCondition());
			pstmt.setInt(7, vo.getPrice());
			
			pstmt.setString(8, vo.getDescription());
			pstmt.setInt(9, vo.getQuantity());
			
			
			pstmt.setString(10, vo.getImage1());
			pstmt.setString(11, vo.getImage2());
			pstmt.setString(12, vo.getImage3());
			pstmt.setString(13, vo.getImage4());
			pstmt.setString(14, vo.getSellerid());
			
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int insert(ItemVO vo)
	
	
	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int itemid, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE ITEMLIST SET "+attributeName+" = ? WHERE ITEMID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setInt(2, itemid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int itemid, String attributeName, String inputString)
	public int update(int itemid, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE ITEMLIST SET "+attributeName+" = ? WHERE ITEMID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setInt(2, itemid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int itemid, String attributeName, int inputInt)
	
	// 필요하다면 이 밑에 DAO관련 메소드 추가하기 바람---------------------------------------------------------------------------------------------------------	
	
	
}// END public class ItemDAO
