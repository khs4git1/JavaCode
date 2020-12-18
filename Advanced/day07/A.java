package day07;

import java.sql.*;

class A {
	Connection con;
	Statement stmt;

    String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String usr = "JDBC";
	String pwd = "JAVA";
	A(){
		//(1) ����̹� �ε� 
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("(1) ����̹� �ε� ����");
		}catch(ClassNotFoundException cnfe){
			System.out.println("����̹� �ε� ����");
		}
		//(2) DB�� ���� 
		try{
			con = DriverManager.getConnection(url, usr, pwd);
			System.out.println("(2) DB�� ���� ����");
		}catch(SQLException se){
			System.out.println("DB�� ���� ����");
		}
		//(3) Statement ���� 
		try {
			stmt = con.createStatement();
			System.out.println("(3) stmt�� ���� ����");
		}catch(SQLException se) {
			System.out.println("(3) stmt�� ���� ����");
		}
		
		//createT();
		//dropT();
		createTnoExist();
		
		//insertD(10, "ȫ�浿", "01012341234");
		//insertD(20, "������", "01012341235");
		//insertD(30, "�̼���", "01012341236");
		
		//updateD(20, "�ڰ���", "11122223333");
		
		//deleteD(30);
		
		//selectD();
		
		//insertD(40, "�̱浿", "01012341236");
		
		selectD("�浿");
		
		closeAll();
	}
	
	String tname = "JDBCT";
	void createT() {
		String sql = "create table "+tname
				+"(NO number(2) primary key, NAME varchar2(10), PHONE varchar2(11), RDATE date)";
		//(4) Statement ����
		try {
			stmt.execute(sql);
			System.out.println("(4) "+tname+"���̺� ���� ����");
		}catch(SQLException se) {
			System.out.println("(4) "+tname+"���̺� ���� ���� se:"+se);
		}
	}
    void dropT() {
    	String sql = "drop table "+tname;
    	//(4) Statement ����
		try {
			stmt.execute(sql);
			System.out.println("(4) "+tname+"���̺� ���� ����");
		}catch(SQLException se) {
			System.out.println("(4) "+tname+"���̺� ���� ����");
		}
    }
    void createTnoExist() {
    	ResultSet rs = null;
    	String sql = "select TABLE_NAME from user_tables where TABLE_NAME='"+tname+"'";
    	//(4) Statement ����
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
    	//(4) Statement ����
    	try {
    		int i = stmt.executeUpdate(sql);
    		if(i>0) System.out.println("(4) "+ i+"���� ���� �Է� �Ϸ�");
    		else System.out.println("�Է� ����");
    	}catch(SQLException se) {
    		System.out.println("�Է� ���� se:"+ se);
    	}
    }
    void updateD(int no, String name, String phone) {
    	String sql = "update "+tname+" set name='"+name+"', phone='"+phone+"' where NO="+no;
    	//(4) Statement ����
    	try {
    		int i = stmt.executeUpdate(sql);
    		if(i>0) System.out.println("(4) "+i+"���� ���� ���� �Ϸ�");
    		else System.out.println("���� ����");
    	}catch(SQLException se) {
    		System.out.println("���� ���� se:"+ se);
    	}
    }
    void deleteD(int no) {
    	String sql = "delete from "+tname+" where NO="+no;
    	//(4) Statement ����
    	try {
    		int i = stmt.executeUpdate(sql);
    		if(i>0) System.out.println("(4) "+ i+"���� ���� ���� �Ϸ�");
    		else System.out.println("���� ����");
    	}catch(SQLException se) {
    		System.out.println("���� ���� se:"+ se);
    	}
    }
    void selectD() {
    	ResultSet rs = null;
    	String sql = "select * from "+tname+" order by NO";
    	//(4) Statement ����
    	try {
    		rs = stmt.executeQuery(sql);
    		int count = 0;
    	    System.out.println("NO\tNAME\tPHONE\t\tRDATE");
    	    System.out.println("---------- ---------- ----------- --------");
    	    //(5) ������ ����
    		while(rs.next()) {
	    		int no = rs.getInt(1);
	    		String name = rs.getString(2);
	    		String phone = rs.getString(3);
	    		Date rdate = rs.getDate(4);
	    		System.out.println(no+"\t"+name+"\t"+phone+"\t"+rdate);
	    		count++;
    		}
    		System.out.println("---------- ---------- ----------- --------");
    		System.out.println("(4)(5) �� "+count+"�� ������ �˻� �Ϸ�");
    	}catch(SQLException se) {
    	}finally {
    		try {
    			rs.close();
    		}catch(SQLException se) {}
    	}
    }
    void selectD(String name) {
    	ResultSet rs = null;
    	//String sql = "select * from "+tname+" order by NO";
    	//String sql = "select * from "+tname+" where NAME='"+name+"'";
    	String sql = "select * from "+tname+" where NAME like '%"+name+"%'";
    	//(4) Statement ����
    	try {
    		rs = stmt.executeQuery(sql);
    		int count = 0;
    	    System.out.println("NO\tNAME\tPHONE\t\tRDATE");
    	    System.out.println("---------- ---------- ----------- --------");
    	    //(5) ������ ����  
    		while(rs.next()) {
	    		int no = rs.getInt("NO");
	    		//String name = rs.getString("NAME");
	    		String phone = rs.getString("PHONE");
	    		Date rdate = rs.getDate("RDATE");
	    		System.out.println(no+"\t"+name+"\t"+phone+"\t"+rdate);
	    		count++;
    		}
    		System.out.println("---------- ---------- ----------- --------");
    		System.out.println("(4)(5) �� "+count+"�� ������ �˻� �Ϸ�");
    	}catch(SQLException se) {
    	}finally {
    		try {
    			rs.close();
    		}catch(SQLException se) {}
    	}
    }
    void closeAll() {
    	//(6) ���� ��ü�� �ݱ� 
        try {
        	stmt.close();
        	con.close();
        	System.out.println("(6) ���� ��ü�� �ݱ� �Ϸ�"); 
        }catch(SQLException se) {}
    }
	public static void main(String[] args) {
		A a = new A();
	}
}








