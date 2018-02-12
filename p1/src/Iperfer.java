import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Iperfer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO call client or server depending on which command line shit is entered
	if(((args[0].equals("-s")&&args.length!=3) && (args[0].equals("-c") && args.length!=7))) { //TODO change
		
			System.out.println("length:"+args.length);
			System.out.println("Error: missing or additional arguments");
			System.exit(1);
		}
		if (args[0].equals("-c")) {
		client(args[2], Integer.parseInt(args[4]), Integer.parseInt(args[6]));
		}else if (args[0].equals("-s")) {
		server(Integer.parseInt(args[2]));
		}
	}
	
	
	private static void client(String hostName, int portNumber, int time) {
		byte [] b = new byte [1000];
		try(
				Socket socketInput = new Socket(hostName, portNumber);//creates new socket and names it echoSocket
				DataOutputStream dout = new DataOutputStream(socketInput.getOutputStream());					
				) {
			int count =0;
			long convertedTime = time * 1000;
			long startTime = System.currentTimeMillis();
			while(false||(System.currentTimeMillis()-startTime)<convertedTime) {
			dout.write(b, 0, b.length);
			count++;
			}
			double rate = count/time;
			System.out.println("sent="+(count/1000)+" KB rate="+rate+ "Mbps");//TODO print stats
		}
		/****EXCEPTION HANDLING***/
			catch (UnknownHostException e) {
				 System.err.println("Don't know about host " + hostName);//TODO change message
		            System.exit(1);
		        } catch (IOException e) {
		            System.err.println("Couldn't get I/O for the connection to " + //TODO change message
		                hostName);
		            System.exit(1);
		        } 
	}
	
	
	private static void server(int portNumber) {
		
		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);//opens server socket
			Socket clientSocket = serverSocket.accept();//listens for connections to the socket
			/**how data is sent through the pipe**/
				DataInputStream din = new DataInputStream(clientSocket.getInputStream());
				//TODO recieve data and print stats
				){
			int count = 0;
			int value = 0;
			long startTime;
			long finishTime;
			startTime = System.currentTimeMillis();
			 while(true){
				 value = din.read();
				 if(value != -1) {
					 count++;
				 }
				 else {
				  break;
				 }
			 }
			 System.out.println("Count"+count);
			 double finalData = count/1000;
			 finishTime = System.currentTimeMillis();
			 double finalRate = count/(finishTime-startTime);//TODO throws divide by zero exception 
			 System.out.println("recieved="+finalData/1000+" KB rate="+finalRate+" Mbps");
			
		}catch (IOException e) {
				 System.out.println("Exception caught when trying to listen on port "
			                + portNumber + " or listening for a connection");//TODO change message
			            System.out.println(e.getMessage());
			}
	}

}
