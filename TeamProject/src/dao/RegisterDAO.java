package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.JdbcUtil;
import vo.MemberVO;

public class RegisterDAO {
	
	
	Connection con;
	private static RegisterDAO registerDAO;
	private RegisterDAO() {}
	public RegisterDAO setConnection(Connection con) {
		this.con = con;
		return this;
	}

	public static synchronized RegisterDAO getInstance() {
		if(registerDAO == null) {
			registerDAO = new RegisterDAO();
		}
		return registerDAO;
	}
	
	public int registing(MemberVO memberVO) {
		
		PreparedStatement pstmt = null;
		int flag = -1;
		
		try {
			con = JdbcUtil.getConnection();
			String strQuery = "insert into memberlist values(?,?,?,?,sysdate,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(strQuery);
			
			System.out.println("in RegisterDAO method --");
			System.out.println(memberVO.getUserid());
			System.out.println(memberVO.getSalt());
			System.out.println("in RegisterDAO method --");
			pstmt.setString(1, memberVO.getUserid());
			pstmt.setString(2, memberVO.getPassword());
			pstmt.setString(3, memberVO.getSalt());
			pstmt.setString(4, memberVO.getName());
			pstmt.setString(5, memberVO.getEmail());
			pstmt.setString(6, memberVO.getPhone());
			pstmt.setString(7, memberVO.getKakao_open());
			pstmt.setString(8, memberVO.getKakao_id());
			pstmt.setString(9, memberVO.getZipcode());
			pstmt.setString(10, memberVO.getAddress1());
			pstmt.setString(11, memberVO.getAddress2());
			pstmt.setInt(12, memberVO.getState());
			pstmt.setInt(13, memberVO.getRole());
			
			flag = pstmt.executeUpdate();
			
			JdbcUtil.commit(con);
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println("RegisterDAO class registing method exception");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("some Error in RegisterDAO");
		}
		finally {
			JdbcUtil.close(con);
			JdbcUtil.close(pstmt);
		}
		return flag;
	}
	
	
	
}
