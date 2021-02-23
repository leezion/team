package action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.ActionForward;
import vo.BoardVO;

public class BoardWriteAction implements Action {

	//아이피 주소를 얻는 코드
	public static String getClientIp(HttpServletRequest req) {
	    String ip = req.getHeader("X-Forwarded-For");
	    if (ip == null) ip = req.getRemoteAddr();
	    return ip;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardVO boardVO = new BoardVO();
		int articleid = boardDAO.getNextArticleNumber()+1;
		String title;// varchar2(120) not null 제목
		String content;// varchar2(4000) not null 내용
		int readcount;// number(8) not null 조회수
		Timestamp regdate;// timestamp default sysdate not null 작성시간
		String ip;// varchar2(39) not null 아이피
		String userid;// varchar2(20) 유저아이디
		String userid_off;// varchar2(20) 
		String password_off;// varchar2(20) 
		
		System.out.println(articleid = articleid*10 + 1);//자유게시판이기때문에 1의자리가 1
		System.out.println(title = request.getParameter("title"));
		System.out.println(content =request.getParameter("content"));
		System.out.println(readcount = 0);
		//System.out.println(regdate = request.getParameter(regdate));
		System.out.println(ip = request.getRemoteAddr());
		System.out.println(userid=request.getParameter("userid"));
		System.out.println(userid_off = request.getParameter("userid_off"));
		System.out.println(password_off = request.getParameter("password_off"));
		
		boardVO.setArticleid(articleid);
		boardVO.setTitle(title);
		boardVO.setContent(content);
		boardVO.setReadcount(readcount);
		//boardVO.setRegdate(regdate);
		boardVO.setIp(ip);
		boardVO.setUserid(userid);
		boardVO.setUserid_off(userid_off);
		boardVO.setPassword_off(password_off);
		
		boardDAO.insert(boardVO);
		return new ActionForward("boardlist.do", false);
	}

}
