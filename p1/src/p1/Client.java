package p1;
import java.io.*;
import java.net.*;

public class Client {

	public Client(String hostName, int portNumber) {
		// TODO Auto-generated constructor stub

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

}
