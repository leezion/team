package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.DealEventVO;
import db.JdbcUtil;
public class DealEventDAO {
	Connection con;
	private static DealEventDAO dealEventDAO;
	
	private DealEventDAO() {}
	
	public DealEventDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized DealEventDAO getInstance() {
		if(dealEventDAO == null) {
			dealEventDAO = new DealEventDAO();
		}
		return dealEventDAO;
	}
	
	// 기본키로 검색
	public DealEventVO select(int dealid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DealEventVO dealEventVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM DEAL_EVENT WHERE DEALID=?");
			pstmt.setInt(1, dealid);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				dealEventVO = new DealEventVO();
				
				dealEventVO.setDealid(rs.getInt("DEALID"));
				dealEventVO.setRegdate(rs.getTimestamp("REGDATE"));
				dealEventVO.setSellerid(rs.getString("SELLERID"));
				dealEventVO.setBuyerid(rs.getString("BUYERID"));
				dealEventVO.setItemid(rs.getInt("ITEMID"));
				dealEventVO.setReviewid(rs.getInt("REVIEWID"));
				dealEventVO.setCanceled(rs.getString("CANCELED"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return dealEventVO;
		
	} // END DealEventVO select(int dealid)
	
	// 행번호 기준 검색
	public ArrayList<DealEventVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DealEventVO dealEventVO = null;
		ArrayList<DealEventVO> list = new ArrayList<DealEventVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, DEALID, REGDATE, SELLERID, BUYERID, ITEMID, REVIEWID, CANCELED FROM (SELECT * FROM DEAL_EVENT ORDER BY REGDATE DESC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				dealEventVO = new DealEventVO();
				
				dealEventVO.setDealid(rs.getInt("DEALID"));
				dealEventVO.setRegdate(rs.getTimestamp("REGDATE"));
				dealEventVO.setSellerid(rs.getString("SELLERID"));
				dealEventVO.setBuyerid(rs.getString("BUYERID"));
				dealEventVO.setItemid(rs.getInt("ITEMID"));
				dealEventVO.setReviewid(rs.getInt("REVIEWID"));
				dealEventVO.setCanceled(rs.getString("CANCELED"));

				list.add(dealEventVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<DealEventVO> select(int startrow, int endrow)
	
	
	//시간기준 검색 : 형식 "YYYY-MM-DD HH24:MI:SS"
	public ArrayList<DealEventVO> selectByTime(String fromTime, String toTime) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DealEventVO dealEventVO = null;
		ArrayList<DealEventVO> list = new ArrayList<DealEventVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM DEAL_EVENT WHERE REGDATE BETWEEN TO_DATE('"+fromTime+"', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+toTime+"', 'YYYY-MM-DD HH24:MI:SS')");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				dealEventVO = new DealEventVO();
				
				dealEventVO.setDealid(rs.getInt("DEALID"));
				dealEventVO.setRegdate(rs.getTimestamp("REGDATE"));
				dealEventVO.setSellerid(rs.getString("SELLERID"));
				dealEventVO.setBuyerid(rs.getString("BUYERID"));
				dealEventVO.setItemid(rs.getInt("ITEMID"));
				dealEventVO.setReviewid(rs.getInt("REVIEWID"));
				dealEventVO.setCanceled(rs.getString("CANCELED"));

				list.add(dealEventVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<DealEventVO> selectByTime(String fromTime, String toTime)
	
	
	// 기본키로 삭제
	public int delete(int dealid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM DEAL_EVENT WHERE DEALID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dealid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int delete(int dealid)

	
	// 데이타 삽입
	public int insert(DealEventVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO DEAL_EVENT(DEALID, REGDATE, SELLERID, BUYERID, ITEMID, REVIEWID, CANCELED) "
					+ "VALUES(SEQ_DEAL_EVENT.NEXTVAL,SYSDATE,?,?,?,?,'N') ";
			pstmt = con.prepareStatement(sql);
			
			
			
			pstmt.setString(1, vo.getSellerid());
			pstmt.setString(2, vo.getBuyerid());
			pstmt.setInt(3, vo.getItemid());
			pstmt.setInt(4, vo.getReviewid());
			
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int insert(DealEventVO vo)

	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int dealid, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE DEAL_EVENT SET "+attributeName+" = ? WHERE DEALID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setInt(2, dealid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int dealid, String attributeName, String inputString)
	public int update(int dealid, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE DEAL_EVENT SET "+attributeName+" = ? WHERE DEALID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setInt(2, dealid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int dealid, String attributeName, int inputInt)
	
	
	// 필요하다면 이 밑에 DAO관련 메소드 추가하기 바람---------------------------------------------------------------------------------------------------------	
	
	
}// END public class DealEventDAO
