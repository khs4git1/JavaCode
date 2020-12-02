import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

class A extends JFrame implements ActionListener {
	Container cp;
	JButton bOpen;
	JButton bSave;
	JButton bClear;
	JTextPane tp;
	JScrollPane sp;
	JPanel p;
	String fPath = "C:/SOO/Java/자료실";
	final static int FC_OPEN = 0;
	final static int FC_SAVE = 1;

	A(){
		tp = new JTextPane();
		sp = new JScrollPane(tp);
		p = new JPanel();
		bOpen = new JButton("Open");
		bSave = new JButton("Save");
		bClear = new JButton("Clear");
		bOpen.addActionListener(this);
		bSave.addActionListener(this);
		bClear.addActionListener(this);

		init();
	}
	void init(){
		cp = getContentPane();

        p.setLayout(new GridLayout(1, 2));
		p.add(bOpen);
		p.add(bSave);
        cp.add(p, BorderLayout.NORTH);
		cp.add(sp);
		cp.add(bClear, BorderLayout.SOUTH);

		setUI();
	}
 	void setUI(){
		setTitle("JFileChooser Test");
		setSize(400, 300);
		setVisible(true);
		setLocation(300, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void showFC(int mode){
		JFileChooser jfc = new JFileChooser(fPath);
		FileNameExtensionFilter filter 
			= new FileNameExtensionFilter("파일열기(txt/java)", "txt", "java");
		jfc.addChoosableFileFilter(filter);

        int option = -1;
		if(mode == FC_OPEN){
			option = jfc.showOpenDialog(this);
		}else{ //FC_SAVE
			option = jfc.showSaveDialog(this);	
		}
		//pln("option: " + option);

		if(option == JFileChooser.APPROVE_OPTION){
			File f = jfc.getSelectedFile();	
			//pln("f: " + f);
			if(mode == FC_OPEN){
				readF(f);
			}else{
				writeF(f);
			}
		}
	}
	void readF(File f){ //file -> tp
		FileReader fr = null;
		BufferedReader br = null;
		try{
			String line = "";
			StringBuffer sb = new StringBuffer();
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				sb.append(line + "\n");	
			}
			String result = sb.toString();
			tp.setText(result);
		}catch(IOException ie){
		}finally{
			try{
				if(br != null) br.close();
				if(fr != null) fr.close();
			}catch(IOException ie){}
		}
	}
	void writeF(File f){ //tp -> file
		String content = tp.getText();
	    
		FileWriter fw = null;
		PrintWriter pw = null;
		try{
			fw = new FileWriter(f);
			pw = new PrintWriter(fw, true);
			pw.println(content);
		}catch(IOException ie){
		}finally{
			try{
				if(pw != null) pw.close();
				if(fw != null) fw.close();
			}catch(IOException ie){}
		}
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == bOpen){
			showFC(FC_OPEN);
		}else if(obj == bSave){
			showFC(FC_SAVE);
		}else{
			tp.setText("");
			tp.requestFocus();
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new A();
	}
}
