package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDAO;
import vo.ActionForward;

public class LoginAction implements Action{
   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      String userid = request.getParameter("userid");
      String password = request.getParameter("password");
      
      LoginDAO loginDAO = LoginDAO.getInstance();
      
      
      System.out.println("execute메소드:"+userid);
      System.out.println("execute메소드:"+password);
      
      int check = loginDAO.login(userid, password);
      
      if(check==1) {
    	  HttpSession session = request.getSession();
    	  session.setAttribute("loginID", userid);
      }else if (userid!= null & password !=null) {
    	  request.setAttribute("check", check);
      }
      System.out.println("execute메소드:"+check);
      
      return new ActionForward("main.jsp", true);
   }
}