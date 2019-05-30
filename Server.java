import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class Server {
	public static void main(String[] args) throws IOException {
		// Step 1 : Create a socket to listen at port 1234
		DatagramSocket ds = new DatagramSocket(1234);
		byte[] receive = new byte[65535];

		DatagramPacket DpReceive = null;
		ArrayList<String> ip = new ArrayList<String>();
		ArrayList<String> file_name = new ArrayList<String>();
		while (true) {
			receive = new byte[65535];
			DpReceive = new DatagramPacket(receive, receive.length);

			// Step 3 : revieve the data in byte buffer.
			ds.receive(DpReceive);

			if (data(receive).toString().equals("send")) {
				while (true) {
					receive = new byte[65535];
					// Step 2 : create a DatgramPacket to receive the data.
					DpReceive = new DatagramPacket(receive, receive.length);

					// Step 3 : revieve the data in byte buffer.
					ds.receive(DpReceive);
					String addr = "" + DpReceive.getAddress();
					String ipaddr = addr.substring(1);
					if (data(receive).toString().equals("bye")) {
						// System.out.println("Client sent bye.....EXITING");
						System.out.println("ip address and file list");
						for (int i = 0; i < ip.size(); i++) {
							System.out.println(ip.get(i) + ":" + file_name.get(i));
						}
						break;
					}
					ip.add(ipaddr);
					file_name.add("" + data(receive));
					// System.out.println("Client:"+ipaddr);
					// System.out.println("Client:-" + data(receive));
					// Exit the server if the client sends "bye"

					// Clear the buffer after every message.
				}
				receive = new byte[65535];
			}
			if (data(receive).toString().equals("receive")) {
				String addr = "" + DpReceive.getAddress();
				String ipaddr = addr.substring(1);
				System.out.println(ipaddr + ":receive");
				byte buf[] = null; 
				 InetAddress ip2 = InetAddress.getByName(ipaddr); 
				 DatagramSocket ds2 = new DatagramSocket(); 
				    
				for (int i = 0; i < ip.size(); i++) {
				   
				      // InetAddress ip = InetAddress.getLocalHost(); 
				       
					// System.out.println(ip.get(i) + ":" + file_name.get(i));
				    
				        buf = ip.get(i) .getBytes(); 
				           DatagramPacket DpSend = 
				                   new DatagramPacket(buf, buf.length, ip2, 1235); 
				           ds2.send(DpSend); 
					        buf = file_name.get(i) .getBytes(); 
					            DpSend = 
					                   new DatagramPacket(buf, buf.length, ip2, 1235); 
					           ds2.send(DpSend); 
				}
				 buf="bye".getBytes();
			        DatagramPacket DpSend = 
			                   new DatagramPacket(buf, buf.length, ip2, 1235); 
			           ds2.send(DpSend); 
			}
		}

	}

	// A utility method to convert the byte array
	// data into a string representation.
	public static StringBuilder data(byte[] a) {
		if (a == null)
			return null;
		StringBuilder ret = new StringBuilder();
		int i = 0;
		while (a[i] != 0) {
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}
}