package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.ActionForward;
import vo.BoardVO;

public class UpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("UpdateProAction class");
		String pageNum = request.getParameter("pageNum");
		BoardVO boardVO = new BoardVO();
		String loginId = (String) request.getSession().getAttribute("loginID");
		
		boardVO.setArticleid(Integer.parseInt(request.getParameter("articleid")));
		boardVO.setTitle(request.getParameter("title"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setUserid(loginId);
		boardVO.setUserid_off(request.getParameter("userid"));
		boardVO.setPassword_off(request.getParameter("password"));
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		int check = boardDAO.updateSetArticle(boardVO);
		System.out.printf("%s%n",check==1?"update succeseful":"update false password error");
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		
		return new ActionForward("/board/updatePro.jsp",false);
	}

}
