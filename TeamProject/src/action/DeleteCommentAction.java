package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class DeleteCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		int articleid = Integer.parseInt(request.getParameter("articleid"));
		String pageNum = request.getParameter("pageNum");
		int commentid = Integer.parseInt(request.getParameter("commentid"));
		
		request.setAttribute("commentid", commentid);
		request.setAttribute("articleid", articleid);
		request.setAttribute("pageNum", pageNum);
		
		return new ActionForward("/board/deleteCommentForm.jsp",false);
	}

}
