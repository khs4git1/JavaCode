package day08;

import java.sql.*;

class C {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String usr = "JDBC";
	String pwd = "JAVA";
	
	String tname = "JDBCT";
	String sql = "select * from "+tname+" order by NO";
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	C(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, usr, pwd);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			/*
			 ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE, or ResultSet.TYPE_SCROLL_SENSITIVE
			 ResultSet.CONCUR_READ_ONLY or ResultSet.CONCUR_UPDATABLE
			 * */
		}catch(ClassNotFoundException cnfe) {
			pln("드라이버가 클래스패스 걸리지 않았음");
		}catch(SQLException se) {
			pln("se: " + se);
		}
	}
	void createRs() {
		try {
			rs = stmt.executeQuery(sql);
			
			forward(rs);
			rs.beforeFirst(); //BOF
			forward(rs);
		}catch(SQLException se) {}
	}
	void forward(ResultSet rs) {
		try {
			pln("< 순방향 >");
			pln("NO \t NAME \t PHONE \t RDATE");
			while(rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				Date rdate = rs.getDate(4);
				pln(no+"\t"+name+"\t"+phone+"\t"+rdate);
			}
			
			back(rs);
			rs.afterLast(); //EOF
			back(rs);
		}catch(SQLException se) {}
	}
	void back(ResultSet rs) { //구현하시오
		try {
			pln("< 역방향 >");
			pln("NO \t NAME \t PHONE \t RDATE");
			while(rs.previous()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				Date rdate = rs.getDate(4);
				pln(no+"\t"+name+"\t"+phone+"\t"+rdate);
			}
		}catch(SQLException se) {}
	}
	void pln(String str) {
		System.out.println(str);
	}
	void p(String str) {
		System.out.print(str);
	}
	public static void main(String[] args) {
		C c =new C();
		c.createRs();
	}
}

//rs.next();
//rs.previous();
//rs.beforeFirst(); //to BOF
//rs.afterLast(); //to EOF


