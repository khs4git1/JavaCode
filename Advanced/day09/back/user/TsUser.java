package day09.back.user;

import day09.bank.Transfer;
import day09.bank.TransferImpl;

public class TsUser { //Client : Web or App 
	public static void main(String args[]) {
		Transfer t = new TransferImpl();
		boolean flag = t.transfer("one@daum.net", "two@naver.com", 50000L);
		if(flag) {
			System.out.println("��ü ����");
		}else {
			System.out.println("��ü ����");
		}
		
		t.showResult("one@daum.net", "two@naver.com");
	}
}
