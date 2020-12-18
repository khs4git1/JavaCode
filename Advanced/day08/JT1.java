package day08;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JT1 extends JFrame {
	JTable t;
	Object[] columnNames = {"번호", "이름", "연락처", "날짜"};
	Object rowData[][] = { 
		{"10", "홍길동", "01012341234", "20/12/18"}, 
		{"20", "이순신", "01012341235", "20/12/18"},
		{"30", "강감찬", "01012341236", "20/12/18"}
	};
    void init() {
    	Container cp = getContentPane();
    	t = new JTable(rowData, columnNames); // JTable(Object rowData[][],  Object columnNames[])
    	JScrollPane sp = new JScrollPane(t);
    	cp.add(sp);
    	
    	test();
    	setUI();
    }
    void test() {
        //(1) 임의의 데이터 ( get/set )
    	t.setValueAt("신촌", 1, 2);
    	Object data = t.getValueAt(1, 2); //1행 2열 
    	pln("data: " + data);
    	
    	//(2) 갯수 
        int rc = t.getRowCount();
        int cc = t.getColumnCount();
        pln("rc: " + rc + ", cc: " + cc);
        
        //(3) 컬럼 이름
        String cn = t.getColumnName(1);
        pln("cn: " + cn);
    }
    void setUI() {
    	setTitle("JTable Test1");
    	setSize(300, 200); 
    	setVisible(true);
    	setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void pln(String str) {
    	System.out.println(str);
    }
	public static void main(String[] args) {
		JT1 j = new JT1();
		j.init();
	}

}
