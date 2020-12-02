import java.io.*;
import java.net.*;

class Client extends Thread {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Socket s;
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	String chatId;

	Client(){
		connect();
	}
	void connect(){ //서버와 접속
		try{
			p("서버IP(기본:127.0.0.1): ");
			String ip = br.readLine();
			if(ip != null) ip = ip.trim();
			if(ip.length() == 0) ip = "127.0.0.1";
			
			p("PORT(기본:3000): ");
			String portStr = br.readLine();
			if(portStr != null) portStr = portStr.trim();
			if(portStr.length() == 0) portStr = "3000";
			int port = Integer.parseInt(portStr);
			if(port<0 || port>65535){
				pln("범위가 유효하지 않은 포트임");
				connect();
				return;
			}

			s = new Socket(ip, port);
			pln("서버와 연결 성공");
			is = s.getInputStream();
			os = s.getOutputStream();
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
			start();
             
            inputChatId();
		}catch(IOException ie){}
	}
	public void run(){ //listen ( socket -> monitor )
		try{
			while(true){
				String msg = dis.readUTF();
				pln(msg);
			}
		}catch(IOException ie){
			pln("서버가 다운됨.. 2초 후에 종료됩니다.");
			try{
				Thread.sleep(2000);
				System.exit(0);
			}catch(InterruptedException iee){}
		}finally{
			closeAll();
		}
	}
    void inputChatId(){ //채팅ID를 입력
		p("채팅ID(기본:GUEST): ");
		try{
			chatId = br.readLine();
			if(chatId  != null) chatId = chatId.trim();
			if(chatId.length() == 0) chatId = "GUEST";
			dos.writeUTF(chatId);
			dos.flush();

			inputMsg();
		}catch(IOException ie){
		}
	}
    void inputMsg(){ //speak ( key -> socket )
		String msg = "";
		try{
			while(true){
				msg = br.readLine();
				dos.writeUTF(chatId + ">> " + msg);
				dos.flush();
			}
		}catch(IOException ie){
		}finally{
			closeAll();
		}
	}
	void closeAll(){ //연결객체들 닫기
		try{
			if(dis != null) dis.close();
			if(dos != null) dos.close();
			if(is != null) is.close();
			if(os != null) os.close();
			if(s != null) s.close();
		}catch(IOException ie){
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	public static void main(String[] args) {
		new Client();
	}
}
