package action;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.ActionForward;
import vo.BoardVO;

public class BoardlistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ������ ��ȣ
				String pageNum =request.getParameter("pageNum");
				if(pageNum == null) {
					pageNum ="1";
				}
				
				
				int pageSize = 5;
				
				int currentPage = Integer.parseInt(pageNum);
				
				
				int startRow = (currentPage -1) * pageSize + 1;
				
				int endRow = currentPage * pageSize;
				
				int count= 0;
				int number = 0;
				
				String find = null;
				String find_box = null;
				
				find = request.getParameter("find");
				find_box = request.getParameter("find_box");
				
				if(find == null) {
					find = "no";
				}
				if(find_box == null) {
					find_box ="no";
				}
				
				
				
				List<BoardVO> articleList = null;
				
				// ��񿬵�
				BoardDAO dbPro = BoardDAO.getInstance();
				
				// ��ü ���� �� �����ٰ� count �� ����
				count = dbPro.getArticleCount(find, find_box);
				
				if(count > 0) {// ���� ������ �ִٸ�
					articleList = dbPro.getArticles(find, find_box, startRow, endRow);
					System.out.println("뭔가 있음");
				}else { // ���� ���ٸ�
					articleList = Collections.emptyList();		
					System.out.println("비어있음");
				}
				
				// �� ��Ͽ� ǥ���� �� ��ȣ
				number = count -(currentPage -1) * pageSize;
				
				
				// �ش�信�� ����� �Ӽ��� ����
				request.setAttribute("currentPage", new Integer(currentPage));
				request.setAttribute("startRow", new Integer(startRow));
				request.setAttribute("endRow", new Integer(endRow));
				request.setAttribute("count", new Integer(count));
				request.setAttribute("pageSize", new Integer(pageSize));
				request.setAttribute("number", new Integer(number));
				request.setAttribute("articleList", articleList);
				request.setAttribute("find", new String(find));
				request.setAttribute("find_box", new String(find_box));
//				return "/board/list.jsp";
		
		return new ActionForward("/board/boardlist.jsp",false);
	}

}
