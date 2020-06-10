package application;
	
import java.net.ServerSocket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;

import common.socket.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	public static ExecutorService threadPool;
	public static Vector<Client> clients = new Vector<>();
	
	ServerSocket serversocekt;
	
	//������ �������� Ŭ���̾�Ʈ�� ������ ��ٸ��� �޼ҵ�
	public void startServer(String IP, int port) {
		
	}
	// ������ �۵��� ������Ű�� �޼ҵ�
	public void stopServer() {
		
	}
	
	//UI�� �����ϰ� ���������� ���α׷��� �۵���Ű�� �޼ҵ�
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//���α׷��� ������
	public static void main(String[] args) {
		launch(args);
	}
	
}
