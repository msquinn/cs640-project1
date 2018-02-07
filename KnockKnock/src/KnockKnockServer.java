import java.net.*;
import java.io.*;

public class KnockKnockServer {
	public static void main(String[]args) throws IOException {
		if(args.length!=1) {
			 System.err.println("Usage: java KnockKnockServer <port number>");
	         System.exit(1);
		}
		int portNumber = Integer.parseInt(args[0]);
		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);//opens server socket
			Socket clientSocket = serverSocket.accept();//listens for connections to the socket
			/**how data is sent through the pipe**/
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				){
			String inputLine, outputLine; 
			//initiate conversation with client
			KnockKnockProtocol kkp = new KnockKnockProtocol();
			outputLine = kkp.processInput(null);
			out.println(outputLine);
			while((inputLine = in.readLine()) != null) {
				outputLine = kkp.processInput(inputLine);//gets the first message the server sends to the client
				out.println(outputLine);//write to the PrintWriter, thereby sending the info to the client via the socket
				if(outputLine.equals("Bye.")){//as long as the client has something to say the server keeps fetching messages 
					break;
				}
			} 
			
		}catch (IOException e) {
				 System.out.println("Exception caught when trying to listen on port "
			                + portNumber + " or listening for a connection");
			            System.out.println(e.getMessage());
			}
		}
	} 
