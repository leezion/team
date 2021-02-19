package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.JdbcUtil;

public class LoginDAO {
   Connection con;
   private static LoginDAO loginDAO;
   
   private LoginDAO() {}
   
   public LoginDAO setConnection(Connection con) {
      this.con = con;
      return this;
   }
   
   public static synchronized LoginDAO getInstance() {
      if(loginDAO == null) {
         loginDAO = new LoginDAO();
      }
      return loginDAO;
   }
   public int login(String userid, String password) {
      int check = -1;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = JdbcUtil.getConnection();
         pstmt = con.prepareStatement("select password from memberlist where userid = ?");
         pstmt.setString(1, userid);
         rs = pstmt.executeQuery();
         if(rs.next()) {
            if(rs.getString("password").equals(password)) {
               System.out.println("비밀번호 ㅇㅇ");
               return check=1;
            }else {
               System.out.println("비밀번호 ㄴㄴ");
               return check=0;
            }
         }else {
            System.out.println("아이디 ㄴㄴ");
            return check;
         }
      } catch (Exception e) {
         // TODO: handle exception
      }finally {
         JdbcUtil.close(con);
         JdbcUtil.close(pstmt);
         JdbcUtil.close(rs);
      }
      return check;
   }
}