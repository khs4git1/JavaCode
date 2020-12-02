import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class A2 extends JFrame { //Container 
	JButton b; //UI Component
	void init(){
		b = new JButton("자바의 버튼");
		add(b);
        /*ActionListener listener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.setText("버튼 클릭됨!!( by 익(무)명내부클래스 )");
			}
		};
        b.addActionListener(listener);
		*/
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				b.setText("버튼 클릭됨!!( by 익(무)명내부클래스 )");
			}
		});

		setUI();
	}
	void setUI(){
		setTitle("GUI Test Ver 1.0");
		setSize(300, 200);
		setVisible(true);
		setLocation(200, 100);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]){
		A2 a2 = new A2();
		a2.init();
	}
}