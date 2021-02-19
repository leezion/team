package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.ReviewVO;
import db.JdbcUtil;


public class ReviewDAO {
	Connection con;
	private static ReviewDAO reviewDAO;
	
	private ReviewDAO() {}
	
	public ReviewDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized ReviewDAO getInstance() {
		if(reviewDAO == null) {
			reviewDAO = new ReviewDAO();
		}
		return reviewDAO;
	}
	
	// 기본키로 검색
	public ReviewVO select(int reviewid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVO reviewVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM REVIEWLIST WHERE REVIEWID=?");
			pstmt.setInt(1, reviewid);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				reviewVO = new ReviewVO();
				
				reviewVO.setReviewid(rs.getInt("REVIEWID"));
				reviewVO.setRegdate(rs.getTimestamp("REGDATE"));
				reviewVO.setContent(rs.getString("CONTENT"));
				reviewVO.setImage1(rs.getString("IMAGE1"));
				reviewVO.setImage2(rs.getString("IMAGE2"));
				reviewVO.setImage3(rs.getString("IMAGE3"));
				reviewVO.setImage4(rs.getString("IMAGE4"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return reviewVO;
		
	} // END ReviewVO select(int reviewid)
	
	// 행번호 기준 검색
	public ArrayList<ReviewVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVO reviewVO = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, REVIEWID, REGDATE, CONTENT, IMAGE1, IMAGE2, IMAGE3, IMAGE4 FROM (SELECT * FROM REVIEWLIST ORDER BY REGDATE DESC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				reviewVO = new ReviewVO();
				
				reviewVO.setReviewid(rs.getInt("REVIEWID"));
				reviewVO.setRegdate(rs.getTimestamp("REGDATE"));
				reviewVO.setContent(rs.getString("CONTENT"));
				reviewVO.setImage1(rs.getString("IMAGE1"));
				reviewVO.setImage2(rs.getString("IMAGE2"));
				reviewVO.setImage3(rs.getString("IMAGE3"));
				reviewVO.setImage4(rs.getString("IMAGE4"));

				list.add(reviewVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<ReviewVO> select(int startrow, int endrow)	
	
	
	//시간기준 검색 : 형식 "YYYY-MM-DD HH24:MI:SS"
	public ArrayList<ReviewVO> selectByTime(String fromTime, String toTime) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVO reviewVO = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM REVIEWLIST WHERE REGDATE BETWEEN TO_DATE('"+fromTime+"', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+toTime+"', 'YYYY-MM-DD HH24:MI:SS')");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				reviewVO = new ReviewVO();
				
				reviewVO.setReviewid(rs.getInt("REVIEWID"));
				reviewVO.setRegdate(rs.getTimestamp("REGDATE"));
				reviewVO.setContent(rs.getString("CONTENT"));
				reviewVO.setImage1(rs.getString("IMAGE1"));
				reviewVO.setImage2(rs.getString("IMAGE2"));
				reviewVO.setImage3(rs.getString("IMAGE3"));
				reviewVO.setImage4(rs.getString("IMAGE4"));

				list.add(reviewVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<ReviewVO> selectByTime(String fromTime, String toTime)
	
	
	// 기본키로 삭제
	public int delete(int reviewid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM REVIEWLIST WHERE REVIEWID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reviewid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int delete(int reviewid)


	// 데이타 삽입
	public int insert(ReviewVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO REVIEWLIST(REVIEWID, REGDATE, CONTENT, IMAGE1, IMAGE2, IMAGE3, IMAGE4) "
					+ "VALUES(SEQ_REVIEWLIST.NEXTVAL,SYSDATE,?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getImage1());
			pstmt.setString(3, vo.getImage2());
			pstmt.setString(4, vo.getImage3());
			pstmt.setString(5, vo.getImage4());
			
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int insert(ReviewVO vo)
	
	
	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int reviewid, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE REVIEWLIST SET "+attributeName+" = ? WHERE REVIEWID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setInt(2, reviewid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int reviewid, String attributeName, String inputString)
	public int update(int reviewid, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE REVIEWLIST SET "+attributeName+" = ? WHERE REVIEWID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setInt(2, reviewid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int reviewid, String attributeName, int inputInt)
	
	
	// 필요하다면 이 밑에 DAO관련 메소드 추가하기 바람---------------------------------------------------------------------------------------------------------	
	
	
}// END public class ReviewDAO
