package controller;

import action.Action;
import action.BoardWriteAction;
import action.BoardlistAction;
import action.ContentAction;
import action.DeleteFormAction;
import action.DeleteProAction;
import action.LoginAction;
import action.LogoutAction;
import action.MainAction;
import action.RegisterAction;
import action.TestAction;
import action.UpdateAction;
import action.UpdateProAction;

public class ActionFactory {
	private static ActionFactory factory;
	
	private ActionFactory() {
		
	}
	
	public static synchronized ActionFactory getInstance() {
		if(factory==null) {
			factory = new ActionFactory();
		}
		return factory;
	}
	
	public Action getAction(String command) {
		Action action = null;
		switch(command) {
		
//		액션 추가예시
		case "/deletePro.do":
		case "deletePro.do" :
			action = new DeleteProAction();
			break;
		case "/board/delete.do":
		case "/delete.do":
			action = new DeleteFormAction();
			break;
			
		case "/updateForm.do":
			action = new UpdateAction();
			break;
			
		case "/updatePro.do":
			action = new UpdateProAction();
			break;
		case "/content.do":
		case "/board/content.do":
			action = new ContentAction();
			break;
		case "/board/boardlist.do":
		case "/boardlist.do":
			action = new BoardlistAction();
			break;
			
		case "/board/boardWrite.do":
			action = new BoardWriteAction();
			break;
			
		case "/register/register.do" :
			action = new RegisterAction();
			break;
		case "/logout.do":
			action = new LogoutAction();
			break;	
			
		case "/login.do" :
			action = new LoginAction();
			break;
		
		case "/test.do" : // 파라미터 전달 예시 페이지
			action = new TestAction();
			break;
			
		case "/main.do" : 
		default :
			action = new MainAction();
			break;
		}
		
		
		return action;
	}
}
