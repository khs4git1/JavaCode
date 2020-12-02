import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class UiTip 
{
	JFrame f;
	Container cp;
	JComboBox cbReceiver;
	JTextField tfSender;
	JTextPane tp;
	JButton b;
	Vector<String> vNames = new Vector<String>();
	Vector<String> vIps = new Vector<String>();
	int targetIdx;

	void init(){
		vNames.add("김나린");
		vIps.add("127.0.0.1");

		setFrame();
		setUI();
	}
	void setFrame(){
		try{
			UIManager.setLookAndFeel(
				UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception e){
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
	}
	void setUI(){
		//(1) f 셋팅 
		f = new JFrame();
		cp = f.getContentPane();
		cp.setLayout(new BorderLayout());

        //(2) pNorth 셋팅 
        Color c1 = new Color(184, 207, 229);
        Color c2 = new Color(99, 130, 191);
        JPanel pNorth = new JPanel(new GridLayout(1, 2));
		pNorth.setBorder(
			new TitledBorder(
				new BevelBorder(BevelBorder.RAISED, c1, c2), ""));

		JPanel pNorthReceiver = new JPanel();
        pNorthReceiver.setBorder(
			new TitledBorder(
				new BevelBorder(
					BevelBorder.RAISED, c1, c2), "받는 사람"));
		cbReceiver = new JComboBox(vNames);
		cbReceiver.setPreferredSize(new Dimension(110,30));
		pNorthReceiver.add(cbReceiver);
		ActionHandler h = new ActionHandler(this);
		cbReceiver.addActionListener(h);
		cbReceiver.setSelectedIndex(targetIdx);
		JPanel pNorthSender = new JPanel();
        pNorthSender.setBorder(
			new TitledBorder(
				new BevelBorder(
					BevelBorder.RAISED, c1, c2), "보내는 사람"));
		Font font = new Font("SansSerif", Font.BOLD, 12);
	    tfSender = new JTextField();
		tfSender.setFont(font);	
		tfSender.setPreferredSize(new Dimension(110,30));
		tfSender.addActionListener(h);
		pNorthSender.add(tfSender);

        pNorth.add(pNorthReceiver);
		pNorth.add(pNorthSender);

        //(3) pCenter 셋팅
		JPanel pCenter = new JPanel(new BorderLayout());
        pCenter.setBorder(
			new TitledBorder(
				new BevelBorder(
					BevelBorder.RAISED, c1, c2), "보낼 메세지"));
		tp = new JTextPane();
		JScrollPane sp = new JScrollPane(tp);
		pCenter.add(sp, BorderLayout.CENTER);
		b = new JButton("보내기");
		b.addActionListener(h);
		pCenter.add(b, BorderLayout.SOUTH);

		cp.add(pNorth, BorderLayout.NORTH);
		cp.add(pCenter, BorderLayout.CENTER);

		always();
	}
	void always(){
		f.setTitle("Soo's UdpChat - ver2018");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setSize(300, 300);
		f.setLocation(500, 50);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int answer = JOptionPane.showConfirmDialog(
					f, "종료할까요?", "선택", JOptionPane.OK_CANCEL_OPTION);
				if(answer == JOptionPane.YES_OPTION){
					System.exit(-1);
				}else{
					f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}
	public static void main(String[] args) {
		UiTip tip = new UiTip();
		tip.init();
	}
}

class ActionHandler implements ActionListener {
	UiTip chat;
	ActionHandler(UiTip chat){
		this.chat = chat;
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == chat.cbReceiver){
			int idx = chat.cbReceiver.getSelectedIndex();
			System.out.println("cbReceiver ip: " + chat.vIps.get(idx));
		}else if(obj == chat.tfSender){
			System.out.println("tfSender");
		}else{ // obj == b
			int idx = chat.cbReceiver.getSelectedIndex();
			System.out.println("b idx: " + idx);
		}
	}
}


