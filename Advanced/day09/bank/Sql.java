package day09.bank;

public class Sql {
	public static final String sql1 = "select * from ACC where EMAIL=?";
	public static final String sql2 = "select BALANCE from ACC where EMAIL=?";
	public static final String sql3 = 
	"update ACC set BALANCE=((select BALANCE from ACC where EMAIL=?)-?) where EMAIL=?";
	public static final String sql4 = 
	"update ACC set BALANCE=((select BALANCE from ACC where EMAIL=?)+?) where EMAIL=?";
	public static final String seql5 =
	"insert into TRAN_LOG values(TRAN_LOG_SEQ.nextval, ?,?,?, CURRENT_TIMESTAMP)";
	public static final String seql6 =
    "select * from ACC where EMAIL=? or EMAIL=?";
}
