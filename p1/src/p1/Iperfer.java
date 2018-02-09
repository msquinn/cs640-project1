package p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Iperfer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO call client or server depending on which command line shit is entered
		if (args[0].equals("-c")) {
		client(args[3], Integer.parseInt(args[5]));
		}else if (args[0].equals("-s")) {
		server(Integer.parseInt(args[2]));
		}
	}
	
	
	private static void client(String hostName, int portNumber) {

		try(
				Socket echoSocket = new Socket(hostName, portNumber);//creates new socket and names it echoSocket
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),true);//opens a print writer on the output stream, necessary to send data over pipe
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); //opens a buffer reader on the input stream
				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));//used to get server's response
				
				){
			String userInput;//TODO change this to send 100 bytes
			while((userInput = stdin.readLine())!= null) {
				out.println(userInput);
				System.out.println("echo: " + in.readLine());
			}
		}
		/****EXCEPTION HANDLING***/
			catch (UnknownHostException e) {
				 System.err.println("Don't know about host " + hostName);
		            System.exit(1);
		        } catch (IOException e) {
		            System.err.println("Couldn't get I/O for the connection to " +
		                hostName);
		            System.exit(1);
		        } 
	}
	
	
	private static void server(int portNumber) {
		
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
