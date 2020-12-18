package day08;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JT1 extends JFrame {
	JTable t;
	Object[] columnNames = {"��ȣ", "�̸�", "����ó", "��¥"};
	Object rowData[][] = { 
		{"10", "ȫ�浿", "01012341234", "20/12/18"}, 
		{"20", "�̼���", "01012341235", "20/12/18"},
		{"30", "������", "01012341236", "20/12/18"}
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
        //(1) ������ ������ ( get/set )
    	t.setValueAt("����", 1, 2);
    	Object data = t.getValueAt(1, 2); //1�� 2�� 
    	pln("data: " + data);
    	
    	//(2) ���� 
        int rc = t.getRowCount();
        int cc = t.getColumnCount();
        pln("rc: " + rc + ", cc: " + cc);
        
        //(3) �÷� �̸�
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
