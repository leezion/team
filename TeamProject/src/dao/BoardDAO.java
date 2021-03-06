package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.BoardVO;

public class BoardDAO {
	Connection con = JdbcUtil.getConnection();
	private static BoardDAO boardDAO;

	private BoardDAO() {
	}

	public static synchronized BoardDAO getInstance() {
		if (boardDAO == null) {
			synchronized (BoardDAO.class) {
				boardDAO = new BoardDAO();
			}
		}
		return boardDAO;
	}

	// 기본키로 검색 이지만 조회수가 1 늘어나는 코드 제가 바꿨어요!

	public BoardVO select(int articleid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVO = null;

		try {
			pstmt = con.prepareStatement("update board set readcount = readcount+1 where articleid=?");
			pstmt.setInt(1, articleid);
			pstmt.executeUpdate();

			pstmt = con.prepareStatement("select * from board where articleid = ?");

			pstmt.setInt(1, articleid);
			rs = pstmt.executeQuery();

			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardVO = new BoardVO();

				boardVO.setArticleid(rs.getInt("ARTICLEID"));
				boardVO.setTitle(rs.getString("TITLE"));
				boardVO.setContent(rs.getString("CONTENT"));
				boardVO.setReadcount(rs.getInt("READCOUNT"));
				boardVO.setRegdate(rs.getTimestamp("REGDATE"));
				boardVO.setIp(rs.getString("IP"));
				boardVO.setUserid(rs.getString("USERID"));
				boardVO.setUserid_off(rs.getString("USERID_OFF"));
				boardVO.setPassword_off(rs.getString("PASSWORD_OFF"));
			}

		} catch (SQLException expn) {
			expn.printStackTrace();
		} finally {

			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		return boardVO;

	} // END BoardVO select(int articleid)

	// 행번호 기준 검색
	public ArrayList<BoardVO> select(int startrow, int endrow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVO = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		try {
			pstmt = con.prepareStatement(
					"SELECT * FROM (SELECT ROWNUM RNUM, ARTICLEID, TITLE, CONTENT, READCOUNT, REGDATE, IP, USERID, USERID_OFF, PASSWORD_OFF FROM (SELECT * FROM BOARD ORDER BY REGDATE DESC)) WHERE RNUM BETWEEN "
							+ startrow + " AND " + endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				boardVO = new BoardVO();

				boardVO.setArticleid(rs.getInt("ARTICLEID"));
				boardVO.setTitle(rs.getString("TITLE"));
				boardVO.setContent(rs.getString("CONTENT"));
				boardVO.setReadcount(rs.getInt("READCOUNT"));
				boardVO.setRegdate(rs.getTimestamp("REGDATE"));
				boardVO.setIp(rs.getString("IP"));
				boardVO.setUserid(rs.getString("USERID"));
				boardVO.setUserid_off(rs.getString("USERID_OFF"));
				boardVO.setPassword_off(rs.getString("PASSWORD_OFF"));

				list.add(boardVO);
			}

		} catch (SQLException expn) {
			expn.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		return list;

	} // END ArrayList<BoardVO> select(int startrow, int endrow)

	// 시간기준 검색 : 형식 "YYYY-MM-DD HH24:MI:SS"
	public ArrayList<BoardVO> selectByTime(String fromTime, String toTime) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVO = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		try {
			pstmt = con.prepareStatement("SELECT * FROM BOARD WHERE REGDATE BETWEEN TO_DATE('" + fromTime
					+ "', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('" + toTime + "', 'YYYY-MM-DD HH24:MI:SS')");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				boardVO = new BoardVO();

				boardVO.setArticleid(rs.getInt("ARTICLEID"));
				boardVO.setTitle(rs.getString("TITLE"));
				boardVO.setContent(rs.getString("CONTENT"));
				boardVO.setReadcount(rs.getInt("READCOUNT"));
				boardVO.setRegdate(rs.getTimestamp("REGDATE"));
				boardVO.setIp(rs.getString("IP"));
				boardVO.setUserid(rs.getString("USERID"));
				boardVO.setUserid_off(rs.getString("USERID_OFF"));
				boardVO.setPassword_off(rs.getString("PASSWORD_OFF"));

				list.add(boardVO);
			}

		} catch (SQLException expn) {
			expn.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		return list;

	} // END ArrayList<BoardVO> selectByTime(String fromTime, String toTime)

	// 기본키로 삭제
	public int delete(int articleid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";

		try {
			sql = "DELETE FROM BOARD WHERE ARTICLEID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleid);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}

		return result;
	}// END int delete(int articleid)

	// 이거 제가 바꿔서 좀 다릅니다.
	public int insertBoard(BoardVO vo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";

		try {

			sql = "INSERT INTO BOARD(ARTICLEID, TITLE, CONTENT, READCOUNT, REGDATE, IP, USERID, USERID_OFF, PASSWORD_OFF) "
					+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, vo.getArticleid());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getReadcount());

			pstmt.setString(5, vo.getIp());
			pstmt.setString(6, vo.getUserid());
			pstmt.setString(7, vo.getUserid_off());
			pstmt.setString(8, vo.getPassword_off());

			result = pstmt.executeUpdate();
			JdbcUtil.commit(con);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error occurred insert method in BoardDAO class");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error occurred insert method in BoardDAO class");
		} finally {
			JdbcUtil.close(pstmt);

		}

		return result;
	}// END int insert(BoardVO vo)

	// 기본키와 DB테이블의 속성으로 업데이트
	public int update(int articleid, String attributeName, String inputString) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE BOARD SET " + attributeName + " = ? WHERE ARTICLEID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputString);
			pstmt.setInt(2, articleid);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int articleid, String attributeName, String inputString)

	public int update(int articleid, String attributeName, int inputInt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		try {
			sql = "UPDATE BOARD SET " + attributeName + " = ? WHERE ARTICLEID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inputInt);
			pstmt.setInt(2, articleid);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}// END int update(int articleid, String attributeName, int inputInt)

	// 필요하다면 이 밑에 DAO관련 메소드 추가하기
	// 바람---------------------------------------------------------------------------------------------------------

	// articleid를 구하는 메소드
	public int getNextArticleNumber() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number = 0;
		try {

			pstmt = con.prepareStatement("select max(articleid) from board");
			rs = pstmt.executeQuery();
			if (rs.next())
				number = rs.getInt(1);
			number = (int) (number / 10);
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Error ouccured in getNextArticleNumber method");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error ouccured in getNextArticleNumber method");
		} finally {

			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return number;
	}

	public int getArticleCount(String find, String find_box) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {

			// �˻� ������ �ۼ���
			if (find.equals("writer")) {
				pstmt = con.prepareStatement("select count(*) from board where userid=? or userid_off=?");
				pstmt.setString(1, find_box);
			}
			// �˻� ������ ����
			else if (find.equals("subject")) {
				pstmt = con.prepareStatement("select count(*) from board where title like '%" + find_box + "%'");
			}
			// �˻� ������ ����
			else if (find.equals("content")) {
				pstmt = con.prepareStatement("select count(*) from board where content like '%" + find_box + "%'");
			} else {
				// ��ü ���� ����
				pstmt = con.prepareStatement("select count(*) from board");
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException s) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException s) {
				}

		}
		return x;
	}

	public List<BoardVO> getArticles(String find, String find_box, int start, int end) {// 1

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> articleList = null;

		try {

			StringBuffer sql = new StringBuffer();
			sql.append("select * from ");
			sql.append(
					"(select rownum rnum, articleid, title, content, regdate ,readcount, ip, userid, userid_off, password_off from ");
			if (find.equals("writer")) {
				sql.append(
						"(select * from board where userid = ? or userid_off = ? order by articleid desc)) where rnum >= ? and rnum <= ?");
				System.out.println(sql);
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, find_box);
				pstmt.setString(2, find_box);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			} else if (find.equals("subject")) {
				sql.append("(select * from board where  title like '%" + find_box
						+ "%'  order by articleid desc)) where rnum >= ? and rnum <= ?");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			} else if (find.equals("content")) {
				sql.append("(select * from board where  content like '%" + find_box
						+ "%'  order by articleid desc)) where rnum >= ? and rnum <= ?");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			} else {
				sql.append("(select * from board order by articleid desc)) where rnum >= ? and rnum <= ?");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {

				articleList = new ArrayList<BoardVO>(end - start + 1);// 4

				do {
					BoardVO article = new BoardVO();

					article.setArticleid(rs.getInt("articleid"));
					article.setTitle(rs.getString("title"));
					article.setContent(rs.getString("content"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setIp(rs.getString("ip"));
					article.setUserid(rs.getString("userid"));
					article.setUserid_off(rs.getString("userid_off"));
					article.setPassword_off(rs.getString("password_off"));

					articleList.add(article);

				} while (rs.next());

			}
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("error occured in BoardDAO class getArticle method");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException s) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException s) {
				}

		}

		return articleList;
	}// end List (getArticles)

	public BoardVO updateGetArticle(int articleid) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;

		try {

			pstmt = con.prepareStatement("select * from board where articleid=?");

			pstmt.setInt(1, articleid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new BoardVO();
				article.setArticleid(rs.getInt("articleid"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setIp(rs.getString("ip"));
				article.setUserid(rs.getString("userid"));
				article.setUserid_off(rs.getString("userid_off"));
				article.setPassword_off(rs.getString("password_off"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException s) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException s) {
				}

		}
		return article;
	}

	public int updateSetArticle(BoardVO boardVO) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbpasswd = "";
		String dbUserid = "";
		String sql = "";
		int result = -1; // ｰ皺� ｾｽ , 1: ｼ､ｼｺｰ�, 0: ｼ､ ｽﾇﾆﾐ

		try {

			pstmt = con.prepareStatement("select password_off from board where articleid=?");
			pstmt.setInt(1, boardVO.getArticleid());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// ｺﾐｹ｣ ｺｳ

				if (boardVO.getUserid() == null) {// 널이라는건 오프라인 계정
					dbpasswd = rs.getString("password_off");

					if (dbpasswd.equals(boardVO.getPassword_off())) {
						// ｺﾐｹ｣ｰ｡ ｰｰﾀｸｸ� ｼ､ﾃｳｸｮ
						sql = "update board set userid_off=?, " + "title=?, content=? where articleid=?";

						pstmt = con.prepareStatement(sql);

						pstmt.setString(1, boardVO.getUserid_off());
						pstmt.setString(2, boardVO.getTitle());
						pstmt.setString(3, boardVO.getContent());
						pstmt.setInt(4, boardVO.getArticleid());
						pstmt.executeUpdate();
						JdbcUtil.commit(con);
						result = 1; // ｼ､ｼｺｰ�
					}
				} else {
					dbUserid = rs.getString("userid");

					if (dbpasswd.equals(boardVO.getUserid())) {
						// ｺﾐｹ｣ｰ｡ ｰｰﾀｸｸ� ｼ､ﾃｳｸｮ
						sql = "update board set " + "title=?, content=? where articleid=?";

						pstmt = con.prepareStatement(sql);

						pstmt.setString(1, boardVO.getTitle());
						pstmt.setString(2, boardVO.getContent());
						pstmt.setInt(3, boardVO.getArticleid());
						pstmt.executeUpdate();
						JdbcUtil.commit(con);
						result = 1; // ｼ､ｼｺｰ�
					}
				}

			} else {
				result = 0;// ｺﾐｹ｣ ｿﾀｷ�
			}

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("error occured updateSetArticle method");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error occured updateSetArticle method");
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException s) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException s) {
				}

		}
		return result;
	}

	public int deleteArticle(int articleid, String password) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memberPassword = BoardDAO.getInstance().getMemberPassword(articleid);
		String dbpasswd = "";
		String sql = "";
		int result = -1; // ｰ皺� ｾｽ , 1: ｼ､ｼｺｰ�, 0: ｼ､ ｽﾇﾆﾐ

		try {
			if (memberPassword == null || memberPassword.equals("")) {
				System.out.println("비회원 게시글 ");
				pstmt = con.prepareStatement("select password_off from board where articleid=?");
				pstmt.setInt(1, articleid);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					// ｺﾐｹ｣ ｺｳ
					dbpasswd = rs.getString("password_off");
					System.out.println("비회원 비밀번호" + dbpasswd);
					System.out.println("내가입력한 비밀번호" + password);
					
					if (dbpasswd.equals(password)) {
						System.out.println("비회원 게시글 삭제");
						// ｺﾐｹ｣ｰ｡ ｰｰﾀｸｸ� ｼ､ﾃｳｸｮ
						sql = "delete from board where articleid=?";

						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, articleid);

						pstmt.executeUpdate();

						result = 1; // ｻ霖ｦ ｼｺｰ�
						JdbcUtil.commit(con);
					} else {
						System.out.println("비회원 게시글 암호 오류");
						result = 0;// ｺﾐｹ｣ ｿﾀｷ�
					}
						
				}
			} else if (memberPassword.equals(password)) {
				System.out.println("회원게시글 삭제");
				System.out.println("회원의 비밀번호" + memberPassword);
				System.out.println("내가 입력한 비밀번호" + password);
				pstmt = con.prepareStatement("delete from board where articleid=?");
				pstmt.setInt(1, articleid);
				pstmt.executeUpdate();
				JdbcUtil.commit(con);
				result = 1;
			} else {
				System.out.println("회원게시글 암호 오류");
				result = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException s) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException s) {
				}

		}
		return result;

	}// end delete

	public String getMemberPassword(int articleid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userid = "";
		String password = "";
		try {
			pstmt = con.prepareStatement("select userid from board where articleid = ?");
			pstmt.setInt(1, articleid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userid = rs.getString("userid");
				System.out.println(articleid + "는 " + userid + "의 게시글입니다.");
			}

			if (userid != null) {
				System.out.println(articleid + "는 회원글입니다.");
				pstmt = con.prepareStatement("select password from memberlist where userid = ?");
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					password = rs.getString("password");
					System.out.println(articleid + "암호" + password);

				}
			} else {
				System.out.println(articleid + "는비회원글입니다.");
			}

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("error occured getMemberPassword method");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error occured getMemberPassword method");
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return password;
	}
}// END public class BoardDAO
