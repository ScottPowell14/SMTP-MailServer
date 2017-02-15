import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MySMTPServer {
	
	public static ArrayList<Thread> threads = new ArrayList<>();
	
	public static void main(String[] args) {
		try {
			// port number will be specified in the arguments: args[0]
			int serverPort = Integer.parseInt(args[0]); // 7854;
			ServerSocket socket = new ServerSocket(serverPort);
			FileMaker fileMaker = new FileMaker();
			fileMaker.sequenceNumber = 0;
			
			
			while (true) {
				System.out.println("Current Threads: " + threads.toString()); // way to test the different simulated threads
				System.out.println("Waiting...");
				
				Socket client = socket.accept();
				
				Thread clientThread = new Thread(new ConnectionThread(client, fileMaker));
				clientThread.start();
				threads.add(clientThread);
			}
		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
