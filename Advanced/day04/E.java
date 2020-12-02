import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

class E extends JFrame implements ActionListener {
	Container cp;
	JButton b;
	ImageIcon ii;
	ImageIcon iiGirl;

	void init(){
		cp = getContentPane();
		loadImg();
		b = new JButton(ii);
		b.addActionListener(this);
		cp.add(b);
		
		setUI();
	}
	void loadImg(){
		try{
			File f = new File("imgs/puppy.gif");
			BufferedImage bi = ImageIO.read(f);
			ii = new ImageIcon(bi);
			
			//ii = new ImageIcon(getClass().getResource("imgs/puppy.gif"));
			
			//ii = new ImageIcon(ImageIO.read(new File("puppy.gif")));
			//iiGirl = new ImageIcon(ImageIO.read(new File("imgs/p_girl.PNG")));
			iiGirl = new ImageIcon(getClass().getResource("imgs/p_girl.PNG"));
		}catch(IOException ie){
			pln("이미지 경로 틀림 " + ie );
		}
	}
	void setUI(){
		setTitle("Image Test");
		pack();
		setVisible(true);
		setLocation(200, 100);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e){
		int answer = JOptionPane.showConfirmDialog(null, "졸립나요?", "질문", 
			JOptionPane.OK_CANCEL_OPTION, 
			JOptionPane.QUESTION_MESSAGE, 
			iiGirl);
		if(answer == JOptionPane.YES_OPTION){
			JOptionPane.showMessageDialog(null, "아.. 힘드시군요.");
		}else{
			JOptionPane.showMessageDialog(null, "맑은 정신 축하!");
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String args[]){
		E e = new E();
		e.init();
	}
}
