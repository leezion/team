package action;

import db.JdbcUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ActivityLogDAO;
import dao.BannedEmailDAO;
import dao.BoardDAO;
import dao.CardDBDAO;
import dao.CategoryDAO;
import dao.ConditionDAO;
import dao.DealEventDAO;
import dao.GeneralDAO;
import dao.ItemDAO;
import dao.MemberDAO;
import dao.RarityDAO;
import dao.ReplyDAO;
import dao.ReportDAO;
import dao.ReviewDAO;
import db.JdbcUtil;
import vo.ActionForward;
import vo.ActivityLogVO;
import vo.BannedEmailVO;
import vo.BoardVO;
import vo.CardDBVO;
import vo.CategoryVO;
import vo.ConditionVO;
import vo.DealEventVO;
import vo.GeneralVO;
import vo.ItemVO;
import vo.MemberVO;
import vo.RarityVO;
import vo.ReplyVO;
import vo.ReportVO;
import vo.ReviewVO;

public class TestAction implements Action {
	
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//========================== DAO 사용 예시
		// DAO를 통한 DB접속 방법
		BannedEmailDAO dao = BannedEmailDAO.getInstance();
		Connection con = JdbcUtil.getConnection();
		dao.setConnection(con);
		
		// 검색
		dao.select("asdf@naver.com");
		
		
		
		
		// DB 커밋
		JdbcUtil.commit(con);
				
		// Connection 객체 종료
		JdbcUtil.close(con);
		
		
		//========================== 파라미터 전달 예시
		// test.jsp 확인
		System.out.println("hidden value transferred : " + request.getParameter("paramTest"));
		int i;
		if(request.getParameter("paramTest").equals("")) {
			i = 1;
		}
		else i = Integer.parseInt(request.getParameter("paramTest"));
		request.setAttribute("reflection", i * 2);
		
		return new ActionForward("/test.jsp", false);
	}

}
