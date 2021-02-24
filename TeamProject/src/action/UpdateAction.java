package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.ActionForward;
import vo.BoardVO;

public class UpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int articleid = Integer.parseInt(request.getParameter("articleid"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardVO boardVO = boardDAO.updateGetArticle(articleid);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", boardVO);
		
		return new ActionForward("/board/updateForm.jsp",false);
	}

}
