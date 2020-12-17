package day07;

import java.sql.*;

class A {
	Connection con;
	Statement stmt;

    String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String usr = "JDBC";
	String pwd = "JAVA";
	A(){
		//(1) 드라이버 로딩 
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("(1) 드라이버 로딩 성공");
		}catch(ClassNotFoundException cnfe){
			System.out.println("드라이버 로딩 실패");
		}
		//(2) DB와 연결 
		try{
			con = DriverManager.getConnection(url, usr, pwd);
			System.out.println("(2) DB와 연결 성공");
		}catch(SQLException se){
			System.out.println("DB와 연결 실패");
		}
		//(3) Statement 생성 
		try {
			stmt = con.createStatement();
			System.out.println("(3) stmt와 생성 성공");
		}catch(SQLException se) {
			System.out.println("(3) stmt와 생성 실패");
		}
		
		//createT();
		//dropT();
		createTnoExist();
		
		//insertD(10, "홍길동", "01012341234");
		//insertD(20, "강감찬", "01012341235");
		//insertD(30, "이순신", "01012341236");
		
		//updateD(20, "박감찬", "11122223333");
		
		deleteD(30);
	}
	
	String tname = "JDBCT";
	void createT() {
		String sql = "create table "+tname
				+"(NO number(2) primary key, NAME varchar2(10), PHONE varchar2(11), RDATE date)";
		//(4) Statement 실행
		try {
			stmt.execute(sql);
			System.out.println("(4) "+tname+"테이블 생성 성공");
		}catch(SQLException se) {
			System.out.println("(4) "+tname+"테이블 생성 실패 se:"+se);
		}
	}
    void dropT() {
    	String sql = "drop table "+tname;
    	//(4) Statement 실행
		try {
			stmt.execute(sql);
			System.out.println("(4) "+tname+"테이블 삭제 성공");
		}catch(SQLException se) {
			System.out.println("(4) "+tname+"테이블 삭제 실패");
		}
    }
    void createTnoExist() {
    	ResultSet rs = null;
    	String sql = "select TABLE_NAME from user_tables where TABLE_NAME='"+tname+"'";
    	//(4) Statement 실행
    	try {
    		rs = stmt.executeQuery(sql);
    		boolean flag = rs.next();
    		if(!flag) createT();
    	}catch(SQLException se) {
    	}finally {
    		try {
    			rs.close();
    		}catch(SQLException se) {}
    	}
    }
    void insertD(int no, String name, String phone) {
    	String sql = "insert into "+tname+" values("+no+", '"+name+"', '"+phone+"', SYSDATE)";
    	//(4) Statement 실행
    	try {
    		int i = stmt.executeUpdate(sql);
    		if(i>0) System.out.println(i+"개의 행이 입력 완료");
    		else System.out.println("입력 실패");
    	}catch(SQLException se) {
    		System.out.println("입력 실패 se:"+ se);
    	}
    }
    void updateD(int no, String name, String phone) {
    	String sql = "update "+tname+" set name='"+name+"', phone='"+phone+"' where NO="+no;
    	//(4) Statement 실행
    	try {
    		int i = stmt.executeUpdate(sql);
    		if(i>0) System.out.println(i+"개의 행이 수정 완료");
    		else System.out.println("수정 실패");
    	}catch(SQLException se) {
    		System.out.println("수정 실패 se:"+ se);
    	}
    }
    void deleteD(int no) {
    	String sql = "delete from "+tname+" where NO="+no;
    	//(4) Statement 실행
    	try {
    		int i = stmt.executeUpdate(sql);
    		if(i>0) System.out.println(i+"개의 행이 삭제 완료");
    		else System.out.println("삭제 실패");
    	}catch(SQLException se) {
    		System.out.println("삭제 실패 se:"+ se);
    	}
    }
    
	public static void main(String[] args) {
		A a = new A();
	}
}
