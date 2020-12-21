package day09;

import java.sql.*;

class A {
	Connection con;
	PreparedStatement pstmt1, pstmt2;
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String usr = "JDBC";
	String pwd = "JAVA";
	String tname = "JDBCT";
	String sql1 = "update "+ tname + " set NAME=? where NO=?";
	String sql2 = "insert into "+ tname + " values(?,?,?,SYSDATE)";
	A(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, usr, pwd);
			con.setAutoCommit(false); //�߿�!!
			pstmt1 = con.prepareStatement(sql1);
			pstmt2 = con.prepareStatement(sql2);
			
			up(); 
		}catch(ClassNotFoundException cnfe) {
			pln("����̹��� Ŭ�����н� �ɸ��� �ʾ���");
		}catch(SQLException se) {
		}
	}
	
	Savepoint sp1;
	void up() { //DML1 
		try {
			pstmt1.setString(1, "����");
			pstmt1.setInt(2, 10);
			int i = pstmt1.executeUpdate();
			if(i>0) {
				pln("update ����");
				sp1 = con.setSavepoint("sp1name");
				in();
			}else {
				con.rollback();
				pln("�ѹ� up()-1");
			}
		}catch(SQLException se) {
			try {
				con.rollback();
				pln("�ѹ� up()-2");
			}catch(SQLException se2) {}
		}
	}
	void in() { //DML2
		try {
			pstmt2.setInt(1, 70);
			pstmt2.setString(2, "��");
			pstmt2.setString(3, "11111111111");
			int i = pstmt2.executeUpdate();
			if(i>0) {
				pln("insert ����");
				
				con.commit();
			}else {
				con.rollback();
				pln("�ѹ� in()-1");
			}
		}catch(SQLException se) {
			try {
				con.rollback(sp1);
				//con.commit();
				pln("�ѹ� in()-2");
			}catch(SQLException se2) {
			}
			pln("�ѹ� in()-2 ����:" + se);
		}
	}
	void pln(String str) {
		System.out.println(str);
	}
	void p(String str) {
		System.out.print(str);
	}
	public static void main(String[] args) {
		A a = new A();
	}
}
