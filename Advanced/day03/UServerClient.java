import java.net.*;
import java.io.*;

class UServerClient extends Thread {
	
	int port = 5000;
	String ip;
	String ipHeader = "192.168.0.";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	void init(){
		start(); 
		try{
			Thread.sleep(50);
		}catch(Exception e){}

		//inputIp();

        DatagramSocket ds = null; 
		DatagramPacket dp = null; 
		try{
			ds = new DatagramSocket();
			
			while(true){
				inputIp();
				//p("전달할 메세지: ");
				String msg = br.readLine();
				if(msg != null) msg = msg.trim();
				byte[] buf = msg.getBytes();
				InetAddress ia = InetAddress.getByName(ip);
				dp = new DatagramPacket(buf, buf.length, ia, port);
				ds.send(dp);
				pln("전송 완료!!");
			}
		}catch(SocketException se){
			init();
		}catch(UnknownHostException ue){
			pln("네트웍상에 해당서버("+ip+")를 찾을 수 없음");
			init();
		}catch(IOException ie){
			init();
		}finally{
			ds.close();
		}
	}
	void inputIp(){
		try{
			p("IP(끝자리): ");
			String ipTail =  br.readLine();
			if(ipTail != null) ipTail = ipTail.trim();
			if(ipTail.length() != 0){
				ip = ipHeader + ipTail;
			}else{
				inputIp();
			}
		}catch(IOException ie){
			inputIp();
		}
	}
	public void run(){
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		try{
			ds = new DatagramSocket(port);
			pln(port+"번에서 UDP서버 대기중..");
			byte buf[] = new byte[2048];
			dp = new DatagramPacket(buf, buf.length);

            while(true){
				ds.receive(dp);
				String msg = new String(buf);
				msg = msg.trim();
				pln("Client>> " + msg);
				for(int i=0; i<buf.length; i++) buf[i]=0;
			}
		}catch(SocketException se){
		}catch(IOException ie){
		}finally{
			ds.close();
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	public static void main(String[] args) 
	{
		new UServerClient().init();
	}
}
