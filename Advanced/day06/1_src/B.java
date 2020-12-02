package soo.ui.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.border.*;

class B extends JFrame {
	Container cp;
	ImageIcon ii;
	JLabel laImg;
	JLabel laFont;

	B(){
		laImg = new JLabel();
		laFont = new JLabel();
	}
	void init(){
		loadImg();
		setLaImg();

		cp = getContentPane();
		cp.add(laImg);
 
        laFont.setText("춤 추는 아이들");
		cp.add(laFont, BorderLayout.NORTH);

	    setUI();
	}
	void setLaImg(){
		//(1) Icon 셋팅
		laImg.setIcon(ii);

		//(2) Font 셋팅 
		laFont.setFont(new Font("궁서체", Font.BOLD, 30));
		//laFont.setHorizontalAlignment(JLabel.RIGHT); 
		laFont.setHorizontalAlignment(JLabel.CENTER); //LEFT(default), CENTER, RIGHT
		//laFont.setVerticalAlignment(JLabel.BOTTOM); //TOP, CENTER(default), BOTTOM

        //(3) Border 셋팅 
		Border oborder = laFont.getBorder();
		Border eBorder = new EmptyBorder(20, 10, 20, 10);
		CompoundBorder cBorder = new CompoundBorder(oborder, eBorder);
		laFont.setBorder(cBorder);
	}
	void loadImg(){
		//방법1
		/*try{
			File f = new File("imgs/dance.gif");
			BufferedImage bi = ImageIO.read(f);
			ii = new ImageIcon(bi);
		}catch(IOException ie){
			pln("이미지를 못 찾음");
		}*/

        //방법2
		ii = new ImageIcon(getClass().getResource("imgs/dances.gif")); 
	}
	void setUI(){
		setTitle("움직이는 gif");
		pack();
		setVisible(true);
		//setLocation(300, 100);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new B().init();
	}
}
