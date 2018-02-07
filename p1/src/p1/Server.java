package p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public Server(int portNumber) {
		// TODO check portnumber value
		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);//opens server socket
			Socket clientSocket = serverSocket.accept();//listens for connections to the socket
			/**how data is sent through the pipe**/
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				){
			 
			
		}catch (IOException e) {
				 System.out.println("Exception caught when trying to listen on port "
			                + portNumber + " or listening for a connection");
			            System.out.println(e.getMessage());
			}
	}

}
