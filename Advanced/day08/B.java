package day08;

import java.sql.*;

//CallableStatement 
class B {
	Connection con;
	CallableStatement cstmt;
	PreparedStatement pstmt;
	
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String usr = "scott";
	String pwd = "tiger";
	
	String sql = "call incre(?, ?)";
	String sqlS = "select EMPNO, SAL from EMP where EMPNO=?";
    B(){
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, usr, pwd);
			cstmt = con.prepareCall(sql);
			pstmt = con.prepareStatement(sqlS);
		}catch(ClassNotFoundException cnfe) {
			pln("드라이버가 클래스패스 걸리지 않았음");
		}catch(SQLException se) {
		}
    }
    void exe(int empno, float rate) {
    	try {
    		cstmt.setInt(1, empno);
    		cstmt.setFloat(2, rate);
    		cstmt.execute();
    		pln("프로시져 호출 완료");
    	}catch(SQLException se) {
    	}
    	
    	showResult(empno);
    }
    void showResult(int empno) { //pstmt를 이용하여 구현하시오
    	ResultSet rs = null;
    	try {
    		pstmt.setInt(1, empno);
    		rs = pstmt.executeQuery();
    		double sal = 0.0;
    		pln("EMPNO \t SAL");
    		pln("-----------------");
    		if(rs.next()) {
    			//empno = rs.getInt(1);
    			sal = rs.getDouble(2);
    			pln(empno +"\t"+ sal);
    		}
    	}catch(SQLException se) { 
    	}finally {
    		try {
				rs.close();
			}catch(SQLException se) {}
    	}
    }
    void pln(String str) {
		System.out.println(str);
	}
	void p(String str) {
		System.out.print(str);
	}
	public static void main(String[] args) {
         B b = new B();
         b.exe(7902, 0.1f);
	}
}
//select EMPNO, SAL from EMP where EMPNO=7902;
