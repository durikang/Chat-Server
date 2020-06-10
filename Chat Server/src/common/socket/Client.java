package common.socket;

import java.net.Socket;

// 한 명의 클라이언트와 통신하도록 해주는 클라이언트 클래스
public class Client {

	Socket socket;
	
	public Client(Socket socket) {
		this.socket = socket;
		receive();
	}
	// 반복적으로 클라이언트로부터 메세지를 받는 메소드
	private void receive() {
		
	}
	
	//해당 클라이언트엑 메세지를 전송하는 메소드
	public void send(String msg) {
		
	}
	
}
