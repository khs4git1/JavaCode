import java.io.*;
import java.net.*;

class AClient extends Thread {
	Socket s;
	String ip = "192.168.0.136";
	int port = 2000;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Keyboard
	OutputStream os; //Node 
	DataOutputStream dos; //Filter 

	InputStream is; //Node 
	DataInputStream dis;//Filter 
	PrintStream ps = System.out; //Node : Monitor 

	AClient(){
		try{
			s = new Socket(ip, port);
			pln("서버와 연결 성공");

			readyIO();
			start();
			speak();
		}catch(UnknownHostException ne){
		}catch(IOException ie){
			pln("서버("+ip+")를 네트워크에서 찾을 수 없음");
		}
	}
	void readyIO(){
		try{
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			is = s.getInputStream();
			dis = new DataInputStream(is);
		}catch(IOException ie){
		}
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
			}catch(IOException ie){}
		}
	}
	public void run(){
		listen();
	}
	void listen(){ //Socket -> Moniter
		try{
			String line = null;
			while((line=dis.readUTF()) != null){
				ps.println("Server>> " + line);
			}
		}catch(IOException ie){
			try{
				ps.println("서버 퇴장! 2초 후에 종료할께요!");
				Thread.sleep(2000);
				System.exit(-1);
			}catch(InterruptedException iie){}
		}finally{
			try{
				if(dis != null) dis.close();
				if(is != null) is.close();
				if(ps != null) ps.close();
				if(s != null) s.close();
			}catch(IOException ie){}
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new AClient();
	}
}
