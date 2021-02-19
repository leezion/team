package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberVO;
import db.JdbcUtil;
public class MemberDAO {
	Connection con;
	private static MemberDAO memberDAO;
	
	private MemberDAO() {}
	
	public MemberDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}
	
	public static synchronized MemberDAO getInstance() {
		if(memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}
	
	
	// 기본키로 검색
	public MemberVO select(String userid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM MEMBERLIST WHERE USERID=?");
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				memberVO = new MemberVO();
				
				memberVO.setUserid(rs.getString("USERID"));
				memberVO.setPassword(rs.getString("PASSWORD"));
				memberVO.setSalt(rs.getString("SALT"));
				memberVO.setName(rs.getString("NAME"));
				memberVO.setRegdate(rs.getTimestamp("REGDATE"));
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setPhone(rs.getString("PHONE"));
				memberVO.setKakao_open(rs.getString("KAKAO_OPEN"));
				memberVO.setKakao_id(rs.getString("KAKAO_ID"));
				memberVO.setZipcode(rs.getString("ZIPCODE"));
				memberVO.setAddress1(rs.getString("ADDRESS1"));
				memberVO.setAddress2(rs.getString("ADDRESS2"));
				memberVO.setState(rs.getInt("STATE"));
				memberVO.setRole(rs.getInt("ROLE"));
				
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return memberVO;
		
	} // END MemberVO select(String userid)
	
	// 행번호 기준 검색
	// 기본키로 검색
	public ArrayList<MemberVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM (SELECT ROWNUM RNUM, USERID, PASSWORD, SALT, NAME, REGDATE, EMAIL, PHONE, KAKAO_OPEN, KAKAO_ID, ZIPCODE, ADDRESS1, ADDRESS2, STATE, ROLE FROM (SELECT * FROM MEMBERLIST ORDER BY REGDATE DESC)) WHERE RNUM BETWEEN "+startrow+" AND "+endrow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				memberVO = new MemberVO();
				
				memberVO.setUserid(rs.getString("USERID"));
				memberVO.setPassword(rs.getString("PASSWORD"));
				memberVO.setSalt(rs.getString("SALT"));
				memberVO.setName(rs.getString("NAME"));
				memberVO.setRegdate(rs.getTimestamp("REGDATE"));
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setPhone(rs.getString("PHONE"));
				memberVO.setKakao_open(rs.getString("KAKAO_OPEN"));
				memberVO.setKakao_id(rs.getString("KAKAO_ID"));
				memberVO.setZipcode(rs.getString("ZIPCODE"));
				memberVO.setAddress1(rs.getString("ADDRESS1"));
				memberVO.setAddress2(rs.getString("ADDRESS2"));
				memberVO.setState(rs.getInt("STATE"));
				memberVO.setRole(rs.getInt("ROLE"));

				list.add(memberVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<MemberVO> select(int startrow, int endrow)
	
	
	//시간기준 검색 : 형식 "YYYY-MM-DD HH24:MI:SS"
	public ArrayList<MemberVO> selectByTime(String fromTime, String toTime) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM MEMBERLIST WHERE REGDATE BETWEEN TO_DATE('"+fromTime+"', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+toTime+"', 'YYYY-MM-DD HH24:MI:SS')");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				memberVO = new MemberVO();
				
				memberVO.setUserid(rs.getString("USERID"));
				memberVO.setPassword(rs.getString("PASSWORD"));
				memberVO.setSalt(rs.getString("SALT"));
				memberVO.setName(rs.getString("NAME"));
				memberVO.setRegdate(rs.getTimestamp("REGDATE"));
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setPhone(rs.getString("PHONE"));
				memberVO.setKakao_open(rs.getString("KAKAO_OPEN"));
				memberVO.setKakao_id(rs.getString("KAKAO_ID"));
				memberVO.setZipcode(rs.getString("ZIPCODE"));
				memberVO.setAddress1(rs.getString("ADDRESS1"));
				memberVO.setAddress2(rs.getString("ADDRESS2"));
				memberVO.setState(rs.getInt("STATE"));
				memberVO.setRole(rs.getInt("ROLE"));

				list.add(memberVO);
			}
			
		}catch(SQLException expn) {
			expn.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
		
	} // END ArrayList<MemberVO> selectByTime(String fromTime, String toTime)
		
	
	// 기본키로 삭제
	public int delete(String userid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "DELETE FROM MEMBERLIST WHERE USERID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int delete(int userid)


	// 데이타 삽입
	public int insert(MemberVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO MEMBERLIST(USERID, PASSWORD, SALT, NAME, REGDATE, EMAIL, PHONE, KAKAO_OPEN, KAKAO_ID, ZIPCODE, ADDRESS1, ADDRESS2, STATE, ROLE) "
					+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getSalt());
			pstmt.setString(4, vo.getName());
			
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getPhone());
			pstmt.setString(7, vo.getKakao_open());
			pstmt.setString(8, vo.getKakao_id());
			pstmt.setString(9, vo.getZipcode());
			pstmt.setString(10, vo.getAddress1());
			pstmt.setString(11, vo.getAddress2());
			pstmt.setInt(12, vo.getState());
			pstmt.setInt(13, vo.getRole());
			
			
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}// END int insert(MemberVO vo)
	
	
	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(String userid, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE MEMBERLIST SET "+attributeName+" = ? WHERE USERID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setString(2, userid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(String userid, String attributeName, String inputString)
	public int update(String userid, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE MEMBERLIST SET "+attributeName+" = ? WHERE USERID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setString(2, userid);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(String userid, String attributeName, int inputInt)

	
	// 필요하다면 이 밑에 DAO관련 메소드 추가하기 바람---------------------------------------------------------------------------------------------------------	
	
	
}// END public class MemberDAO
