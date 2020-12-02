import java.net.*;
import java.io.*;

class UClient {
	DatagramSocket ds; //메세지함(우체통)
	DatagramPacket dp; //메세지틀(편지봉투)
	int port = 5000;
	String ip;
	String ipHeader = "192.168.0.";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	void init(){
		inputIp();

		try{
			ds = new DatagramSocket();
			
			while(true){
				p("전달할 메세지: ");
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
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	public static void main(String[] args) 
	{
		new UClient().init();
	}
}
