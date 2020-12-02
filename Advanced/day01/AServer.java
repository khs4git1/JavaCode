import java.io.*;
import java.net.*;

class AServer extends Thread {
	ServerSocket ss;
	Socket s;
	int port = 2000; //0 ~ 65535 // Well-Known Port : 0~1023
	InputStream is; //Node 
	DataInputStream dis;//Filter 
	PrintStream ps = System.out; //Node : Monitor 

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Keyboard
	OutputStream os; //Node 
	DataOutputStream dos; //Filter 

    AServer(){
		try{
			ss = new ServerSocket(port);
			pln("서버가 "+port+"번 포트에서 대기중...");
	
			s = ss.accept();
			pln("Client("+s.getInetAddress().getHostAddress()+") 연결 성공");

            readyIO();
			start();
			listen();
        }catch(IOException ie){
			pln("ie: " + ie);
		}
	}
	void readyIO(){
		try{
			is = s.getInputStream();
			dis = new DataInputStream(is);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
		}catch(IOException ie){}
	}
	void listen(){ //Socket -> Moniter
		try{
			String line = null;
			while((line=dis.readUTF()) != null){
				ps.println("Client>> " + line);
			}
		}catch(IOException ie){
			try{
				ps.println("클라이언트 퇴장! 2초 후에 종료할께요!");
				Thread.sleep(2000);
				System.exit(-1);
			}catch(InterruptedException iie){}
		}finally{
			try{
				if(dis != null) dis.close();
				if(is != null) is.close();
				if(ps != null) ps.close();
				if(s != null) s.close();
				if(ss != null) ss.close();
			}catch(IOException ie){}
		}
	}
	public void run(){
		speak();
	}
	void speak(){ //Keyboard -> Socket 
		try{
			String line = null;
			while((line=br.readLine()) != null){
				dos.writeUTF(line);
				dos.flush();
			}
		}catch(IOException ie){
		}finally{
			try{
				if(dos != null) dos.close();
				if(os != null) os.close();
				if(br != null) br.close();
				if(s != null) s.close();
				if(ss != null) ss.close();
			}catch(IOException ie){}
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new AServer();
	}
}
