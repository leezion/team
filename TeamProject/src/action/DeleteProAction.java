package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.ActionForward;

public class DeleteProAction implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	int articleid = Integer.parseInt(request.getParameter("articleid"));
	String pageNum = request.getParameter("pageNum");
	String password = request.getParameter("password");
	int check = -1;
	BoardDAO boardDAO = BoardDAO.getInstance();
	//if(boardDAO.getMemberPassword(articleid).equals("")) {}
	check = boardDAO.deleteArticle(articleid, password);
	System.out.println(check);
	request.setAttribute("pageNum", pageNum);
	request.setAttribute("check", new Integer(check));
			
	return new ActionForward("/board/deletePro.jsp",false);
}
}
