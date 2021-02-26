package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardcommentDAO;
import vo.ActionForward;

public class DeleteCommentProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardcommentDAO commentDAO = BoardcommentDAO.getInstance();
		int articleid = Integer.parseInt(request.getParameter("articleid"));
		String pageNum = request.getParameter("pageNum");
		int commentid = Integer.parseInt(request.getParameter("commentid"));
		String password = request.getParameter("password");
		int check = -1;
		
		check = commentDAO.deleteComment(articleid, commentid, password);
		System.out.println(check);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("articleid", new Integer(articleid));
		request.setAttribute("check", new Integer(check));
		
		return new ActionForward("/board/deleteCommnetProc.jsp",false);
	}
}
