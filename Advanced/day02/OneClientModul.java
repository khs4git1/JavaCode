import java.io.*;
import java.net.*;

//하나의 클라이언트와 통신(특정클에서 들어서 나머지클들에게 뿌려줌)하는 서버 모듈 클래스
class OneClientModul extends Thread 
{
	Server server;
	Socket s;
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	String chatId = "GUEST";

	OneClientModul(Server server){
		this.server = server;
		this.s = server.s;
		try{
			is = s.getInputStream();
			os = s.getOutputStream();
			dis= new DataInputStream(is);
			dos = new DataOutputStream(os);
		}catch(IOException ie){}
	}
	public void run(){ // listen -> broadcasting 
		listen();
	}
	void listen(){
		String msg = "";
		try{
			chatId = dis.readUTF();
			String inMsg = chatId +"님 입장!! (총인원:" + server.v.size()+"명)";
			broadcast(inMsg);
			server.pln(inMsg);
			while(true){
				msg = dis.readUTF();
				broadcast(msg);
				server.pln(msg);
			}
		}catch(IOException ie){
			server.v.remove(this);
			String outMsg = chatId+"님 퇴장!!(총인원:" + server.v.size()+"명)";
			broadcast(outMsg);
			server.pln(outMsg);
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
	void broadcast(String msg){
		try{
			for(OneClientModul ocm: server.v){
				ocm.dos.writeUTF(msg);
				ocm.dos.flush();
			}
		}catch(IOException ie){}
	}
}
