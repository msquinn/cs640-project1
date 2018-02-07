package p1;

public class Iperfer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO call client or server depending on which command line shit is entered
		if (args[0].equals("-c")) {
		Client client = new	Client(args[3], Integer.parseInt(args[5]));
		}else if (args[0].equals("-s")) {
			Server server = new Server(Integer.parseInt(args[2]));
		}
	}

}
