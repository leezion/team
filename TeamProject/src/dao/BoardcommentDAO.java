package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtil;
import vo.BoardcommentVO;

public class BoardcommentDAO {
	Connection con = JdbcUtil.getConnection();
	private static BoardcommentDAO boardcommentDAO;

	private BoardcommentDAO() {
	}

	public static synchronized BoardcommentDAO getInstance() {
		if (boardcommentDAO == null) {
			synchronized (BoardcommentDAO.class) {
				boardcommentDAO = new BoardcommentDAO();
			}
		}
		return boardcommentDAO;
	}
	
	BoardcommentVO commentVO = new BoardcommentVO();
	
	public ArrayList<BoardcommentVO> getComment(int articleid){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		BoardcommentVO commentVO = null;
		try {
			pstmt =con.prepareStatement("select * from boardcomment where articleid = ?");
			pstmt.setInt(1, articleid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				commentVO = new BoardcommentVO();
				
				commentVO.setArticleid(rs.getInt("articleid"));
				commentVO.setCommentid(rs.getInt("commentid"));
				commentVO.setContent(rs.getString("content"));
				commentVO.setRegdate(rs.getTimestamp("regdate"));
				commentVO.setIp(rs.getString("ip"));
				commentVO.setUserid(rs.getString("userid"));
				commentVO.setUserid_off(rs.getString("userid_off"));
				commentVO.setPassword_off(rs.getString("password_off"));;
				list.add(commentVO);
			}
			
		} catch(SQLException se){
			se.printStackTrace();
			System.out.println("error occured in BoardcommentDAO class getComment method ");
		}
		
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error occured in BoardcommentDAO class getComment method ");
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return list;
	}
	
	public int insertComment(BoardcommentVO commentVO) {
		int check = -1;
		PreparedStatement pstmt  = null;
		
		try {
			pstmt = con.prepareStatement("insert into boardcomment values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, commentVO.getArticleid());
			pstmt.setInt(2, commentVO.getCommentid());
			pstmt.setString(3, commentVO.getContent());
			pstmt.setTimestamp(4, commentVO.getRegdate());
			pstmt.setString(5,commentVO.getIp());
			pstmt.setString(6, commentVO.getUserid());
			pstmt.setString(7, commentVO.getUserid_off());
			pstmt.setString(8, commentVO.getPassword_off());
			check = pstmt.executeUpdate();
			JdbcUtil.commit(con);
			if(check>0) {
				System.out.println("ok inserted");
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println("error occured in BoardcommentDAO class insertComment method ");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("error occured in BoardcommentDAO class insertComment method ");
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		
		return check;
	}
	
	public int deleteComment(int articleid,int commentid , String password) {
		int check = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memberPassword = BoardcommentDAO.getInstance().getMemberPassword(articleid, commentid);
		
		
		if(memberPassword == null || memberPassword.equals("")) {
			try {
				pstmt = con.prepareStatement("select password_off from boardcomment where articleid=? and commentid=?");
				pstmt.setInt(1, articleid);
				pstmt.setInt(2, commentid);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					memberPassword = rs.getString("password_off");
				}
				if(memberPassword.equals(password)) {
					pstmt = con.prepareStatement("delete from boardcomment where articleid=? and commentid=?");
					pstmt.setInt(1, articleid);
					pstmt.setInt(2, commentid);
					pstmt.executeUpdate();
					check = 1;
					
					JdbcUtil.commit(con);
				}else {
					System.out.println(memberPassword + "비회원 패스워드" + password + "회원 패스워드");
					check = 0;
				}
			
				
			}catch(SQLException se) {
				se.printStackTrace();
				System.out.println("deleteComment 127");
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("deleteComment 131");
			}finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(rs);
			}
			
		}else {
			if(memberPassword.equals(password)) {
				try {
					pstmt = con.prepareStatement("delete from boardcomment where articleid = ? and commentid=?");
					pstmt.setInt(1, articleid);
					pstmt.setInt(2, commentid);
					pstmt.executeUpdate();
					check = 1;
					
					JdbcUtil.commit(con);
				}catch(SQLException se) {
					se.printStackTrace();
				}catch(Exception e) {
					
				}finally {
					JdbcUtil.close(pstmt);
					JdbcUtil.close(rs);
				}
			}else {
				check = 0;
				System.out.println(memberPassword + "comment password " + password+"inputted password");
			}
		}
		
		return check;
	}
	
	
	public String getMemberPassword(int articleid, int commentid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userid = "";
		String password = "";
		try {
			pstmt = con.prepareStatement("select userid from boardcomment where articleid = ? and commentid = ?");
			pstmt.setInt(1, articleid);
			pstmt.setInt(2, commentid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userid = rs.getString("userid");
				System.out.println(commentid + "는 " + userid + "의 comment입니다.");
			}
			
			System.out.println(articleid + " " + commentid);
			
			if (userid!=null) {
				System.out.println(commentid + "는 loginned  comment입니다.");
				pstmt = con.prepareStatement("select password from memberlist where userid = ?");
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					password = rs.getString("password");
					System.out.println(articleid + "암호" + password);

				}
			} else {
				System.out.println(articleid + "는 unloginn comment입니다.");
			}

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("error occured getMemberPassword method");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error occured getMemberPassword method");
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return password;
	}
}
