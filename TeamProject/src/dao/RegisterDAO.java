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
	
	public boolean registing(MemberVO memberVO) {
		
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			con = JdbcUtil.getConnection();
			String strQuery = "insert into memberlist values(?,?,?,?,sysdate,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(strQuery);
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
			
			flag = pstmt.execute();
			
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println("RegisterDAO class registing method exception");
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(pstmt);
		}
		return flag;
	}
	
	
	
}
