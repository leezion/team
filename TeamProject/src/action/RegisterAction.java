package action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegisterDAO;
import vo.ActionForward;
import vo.MemberVO;

public class RegisterAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String userid;// varchar2(20)
	 String password;// varchar2(100) not null
		 String salt;// varchar2(100)
		 String name;// varchar2(20) not null
		 Timestamp regdate;// timestamp default sysdate not null
		 String email;// varchar2(40) not null
		 String phone;// varchar2(20) not null
		 String kakao_open;// varchar2(20)
		 String kakao_id;// varchar2(20)
		String zipcode;// varchar2(7) not null
	 String address1;// varchar2(120) not null
		 String address2;// varchar2(60) not null
		 int state;// number(1) not null
		 int role;// number(1) not null

		 System.out.println(userid=request.getParameter("userid"));
		 System.out.println(password=request.getParameter("password"));
		 //System.out.println(salt=request.getParameter("salt"));
		 System.out.println(name=request.getParameter("name"));
		 //System.out.println(regdate=request.getParameter("regdate"));
		 System.out.println(email=request.getParameter("emailss")+request.getParameter("emails"));
		 System.out.println(phone=request.getParameter("phones")+request.getParameter("phoness")+request.getParameter("phonesss"));
		 System.out.println(kakao_open=request.getParameter("kakao_open"));
		 System.out.println(kakao_id=request.getParameter("kakao_id"));
		 System.out.println(zipcode=request.getParameter("zipcode"));
		 System.out.println(address1=request.getParameter("address1"));
		 System.out.println(address2=request.getParameter("address2"));
		 //System.out.println(state=Integer.parseInt(request.getParameter("userid")));
		 //System.out.println(role=Integer.parseInt(request.getParameter("role")));
		 salt = "13579";
		 state=1;
		 role=1;
		 
		 MemberVO memverVO = new MemberVO();
		 memverVO.setUserid(userid);
		 memverVO.setPassword(password);
		 memverVO.setSalt(salt);
		 memverVO.setName(name);
		 //memverVO.setRegdate(regdate);
		 memverVO.setEmail(email);
		 memverVO.setPhone(phone);
		 memverVO.setKakao_open(kakao_open);
		 memverVO.setKakao_id(kakao_id);
		 memverVO.setZipcode(zipcode);
		 memverVO.setAddress1(address1);
		 memverVO.setAddress2(address2);
		 memverVO.setState(state);
		 memverVO.setRole(role);
		 
		 RegisterDAO registerDAO = RegisterDAO.getInstance();
		if(registerDAO.registing(memverVO)) {
			System.out.println(userid+" sign up succesefully");
		}else {
			System.out.println(userid+" false sign up");
		}
		
		return new ActionForward("main.jsp", true);
	}

}
