package day09.back.user;

import java.sql.SQLException;

import day09.bank.Transfer;
import day09.bank.TransferImpl;

public class TsUser { //Client : Web or App 
	public static void main(String args[]) {
		Transfer t = new TransferImpl();
		
		try {
			boolean flag = t.transfer("one@daum.net", "two@naver.com", 50000L);
			if(flag) {
				System.out.println("#최종결과: 이체 성공");
			}else {
				System.out.println("#최종결과: 이체 실패");
			}
		}catch(SQLException se) {	
			System.out.println("#최종결과: 이체 실패 se: " + se);
		}
		
		t.showResult("one@daum.net", "two@naver.com");
		t.closeAll();
	}
}
