import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//BorderLayout
class B extends JFrame implements ActionListener {
	JButton bN, bS, bW, bE, bC;
	JButton bPC, bPE, bPW; 
	Container cp;
	JPanel p;

	void init(){
		p = new JPanel();
		p.setLayout(new BorderLayout());
		bPC = new JButton("패널 센터");
		bPE = new JButton("패널 동쪽");
		bPW = new JButton("패널 서쪽");
        p.add(bPC, BorderLayout.CENTER);
		p.add(bPE, BorderLayout.EAST);
		p.add(bPW, BorderLayout.WEST);
		bPC.addActionListener(this);
		bPE.addActionListener(this);
		bPW.addActionListener(this);

		bN = new JButton("북");
		bS = new JButton("남");
		bW = new JButton("서");
		bE = new JButton("동");
		bC = new JButton("가운데");
		cp = getContentPane();	
		cp.add(bN, BorderLayout.NORTH);
		cp.add(bS, BorderLayout.SOUTH);
		cp.add(bW, BorderLayout.WEST);
		cp.add(bE, BorderLayout.EAST);
		//cp.add(bC, BorderLayout.CENTER);
		cp.add(p);

		setUI();
	}
	void setUI(){
		setTitle("GUI Test Ver 1.0");
		setSize(500, 200);
		setVisible(true);
		setLocation(200, 100);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == bPC){
			pln("패널 센터 클릭");
		}else if(obj == bPE){
			pln("패널 동쪽 클릭");
		}else{
			pln("패널 서쪽 클릭");
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		B b = new B();
		b.init();
	}
}
