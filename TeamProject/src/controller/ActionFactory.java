package controller;

import action.Action;
import action.BoardWriteAction;
import action.LoginAction;
import action.LogoutAction;
import action.MainAction;
import action.RegisterAction;
import action.TestAction;

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
