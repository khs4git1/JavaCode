package day08;

import java.sql.*;

//PreparedStatement : �غ�� ������Ʈ��Ʈ 
class A {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String usr = "JDBC";
	String pwd = "JAVA";
	
	String tname = "JDBCT";
	Connection con;
	PreparedStatement pstmt1, pstmt2, pstmt3;
	String sql1 = "select * from "+tname+ " order by NO desc";
	String sql2 = "select * from "+tname+ " where NAME like ?";
	String sql3 = "insert into "+tname+" values(?, ?, ?, SYSDATE)";
	A(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, usr, pwd);
			pstmt1 = con.prepareStatement(sql1);
			pstmt2 = con.prepareStatement(sql2);
			pstmt3 = con.prepareStatement(sql3);
		}catch(ClassNotFoundException cnfe) {
			pln("����̹��� Ŭ�����н� �ɸ��� �ʾ���");
		}catch(SQLException se) {
		}
	}
	void selectD() {
		ResultSet rs = null;
		try {
			rs = pstmt1.executeQuery();
			int count = 0;
    	    System.out.println("NO\tNAME\tPHONE\t\tRDATE");
    	    System.out.println("---------- ---------- ----------- --------");
    		while(rs.next()) {
	    		int no = rs.getInt(1);
	    		String name = rs.getString(2);
	    		String phone = rs.getString(3);
	    		Date rdate = rs.getDate(4);
	    		System.out.println(no+"\t"+name+"\t"+phone+"\t"+rdate);
	    		count++;
    		}
    		System.out.println("---------- ---------- ----------- --------");
    		System.out.println("�� "+count+"�� ������ �˻� �Ϸ�");
		}catch(SQLException se) {
		}finally {
			try {
				rs.close();
			}catch(SQLException se) {}
		}
	}
	void selectD(String na) { //�˻���: �ӵ� ����
		ResultSet rs = null;
		try {
			pstmt2.setString(1, "%"+na+"%");
			rs = pstmt2.executeQuery();
			int count = 0;
    	    System.out.println("NO\tNAME\tPHONE\t\tRDATE");
    	    System.out.println("---------- ---------- ----------- --------");
    		while(rs.next()) {
	    		int no = rs.getInt(1);
	    		String name = rs.getString(2);
	    		String phone = rs.getString(3);
	    		Date rdate = rs.getDate(4);
	    		System.out.println(no+"\t"+name+"\t"+phone+"\t"+rdate);
	    		count++;
    		}
    		System.out.println("---------- ---------- ----------- --------");
    		System.out.println("�� "+count+"�� ������ �˻� �Ϸ�");
		}catch(SQLException se) {
		}finally {
			try {
				rs.close();
			}catch(SQLException se) {}
		}
	}
	void insertD(int no, String name, String phone) { //������ 
		try {
			pstmt3.setInt(1, no);
			pstmt3.setString(2, name);
			pstmt3.setString(3, phone);
			int i = pstmt3.executeUpdate();
			pln(i+"���� ���� �Էµ�");
		}catch(SQLException se) {}
	}
	void pln(String str) {
		System.out.println(str);
	}
	void p(String str) {
		System.out.print(str);
	}
	public static void main(String[] args) {
		A a = new A();
		a.selectD();
		//a.selectD("�浿");
		//a.insertD(60, "�ӱ浿", "12312341235");
	}
}
