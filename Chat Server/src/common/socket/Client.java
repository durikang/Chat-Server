package common.socket;

import java.net.Socket;

// �� ���� Ŭ���̾�Ʈ�� ����ϵ��� ���ִ� Ŭ���̾�Ʈ Ŭ����
public class Client {

	Socket socket;
	
	public Client(Socket socket) {
		this.socket = socket;
		receive();
	}
	// �ݺ������� Ŭ���̾�Ʈ�κ��� �޼����� �޴� �޼ҵ�
	private void receive() {
		
	}
	
	//�ش� Ŭ���̾�Ʈ�� �޼����� �����ϴ� �޼ҵ�
	public void send(String msg) {
		
	}
	
}
