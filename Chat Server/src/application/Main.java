package application;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.socket.Client;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	public static ExecutorService threadPool;
	public static Vector<Client> clients = new Vector<>();

	ServerSocket serverSocket;

	// ������ �������� Ŭ���̾�Ʈ�� ������ ��ٸ��� �޼ҵ�
	public void startServer(String IP, int port) {

		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(IP, port));
		} catch (IOException e) {
			e.printStackTrace();
			if (!serverSocket.isClosed())
				stopServer();
			return;
		}

		// Ŭ���̾�Ʈ�� ������ ������ ��� ��ٸ��� ������.
		Runnable thread = new Runnable() {

			@Override
			public void run() {

				while (true) {
					Socket socket;
					try {
						socket = serverSocket.accept();
						clients.add(new Client(socket));
						System.out.println("[Ŭ���̾�Ʈ ����!] " + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName());
					} catch (IOException e) {
						if(!serverSocket.isClosed())
							stopServer();
						break;
					}
				}
			}
		};
		threadPool = Executors.newCachedThreadPool();
		threadPool.submit(thread);
	}

	// ������ �۵��� ������Ű�� �޼ҵ�
	public void stopServer() {
		// ���� �۵� ���� ��� ���� �ݱ�
		Iterator<Client> itr = clients.iterator();
		try {
		while(itr.hasNext()) {
			Client client = itr.next();
			client.socket.close();
			itr.remove();
		}
		//���� ���� ��ü �ݱ�
		if(serverSocket != null && !serverSocket.isClosed())
			serverSocket.close();
		//������ Ǯ ����
		if(threadPool != null && !threadPool.isShutdown())
			threadPool.shutdown();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	// UI�� �����ϰ� ���������� ���α׷��� �۵���Ű�� �޼ҵ�
	@Override
	public void start(Stage primaryStage) {
			
			System.out.println("������ �����մϴ�.");
		
			BorderPane root = new BorderPane();
			root.setPadding(new Insets(5));
			
			//ä��â UI
			TextArea textArea = new TextArea();
			textArea.setEditable(false);
			textArea.setFont(new Font("�������",15));
			root.setCenter(textArea);
			
			
			//���۹�ư 
			Button toggleButton = new Button("�����ϱ�");
			toggleButton.setMaxHeight(Double.MAX_VALUE);
			BorderPane.setMargin(toggleButton, new Insets(1,0,0,0));
			root.setBottom(toggleButton);
			
			
			
			String IP = "127.0.0.1";
			int port = 9876;
			
			toggleButton.setOnAction(event ->{
				if(toggleButton.getText().equals("�����ϱ�")) {
					startServer(IP,port);
					Platform.runLater(() ->{;
						String msg = String.format("[���� ����]\n", IP,port);
						textArea.appendText(msg);
						toggleButton.setText("�����ϱ�");
					});
					
				}else {
					stopServer();
					Platform.runLater(() ->{
						String msg = String.format("[t���� ����]\n", IP,port);
						textArea.appendText(msg);
						toggleButton.setText("�����ϱ�");
					});
				}
			});
			
			
			Scene scene = new Scene(root, 400, 400);
			primaryStage.setTitle("[ ä�� ����]");
			primaryStage.setOnCloseRequest(event->stopServer());
			primaryStage.setScene(scene);
			primaryStage.show();
	}

	// ���α׷��� ������
	public static void main(String[] args) {
		launch(args);
	}

}
