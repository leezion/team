package action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.ActionForward;
import vo.BoardVO;

public class ContentAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardVO boardVO = boardDAO.select(Integer.parseInt(request.getParameter("articleid")));
		
		String pageNum = request.getParameter("num");

		int articleid = boardVO.getArticleid();
		String title = boardVO.getTitle();
		String content = boardVO.getContent();
		int readcount= boardVO.getReadcount();
		Timestamp regdate = boardVO.getRegdate();
		String ip = boardVO.getIp();
		String userid = boardVO.getUserid();
		String userid_off= boardVO.getUserid_off();
		String password_off = boardVO.getPassword_off();
		
		request.setAttribute("articleid", articleid);
		request.setAttribute("title",title);
		request.setAttribute("content",content);
		request.setAttribute("readcount",readcount);
		request.setAttribute("regdate", regdate);
		request.setAttribute("ip",ip);
		request.setAttribute("userid",userid);
		request.setAttribute("userid_off",userid_off);
		request.setAttribute("password_off",password_off);
		
		return new ActionForward("/board/content.jsp",false);
	}
}
