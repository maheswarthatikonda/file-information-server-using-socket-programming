package clientTest;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client_file_receive {
	public static void main(String args[]) throws IOException {
		DatagramSocket ds = new DatagramSocket();

		// InetAddress ip = InetAddress.getLocalHost();
		InetAddress ip = InetAddress.getByName("192.168.100.4");
		byte buf[] = null;
		List<String> results = new ArrayList<String>();
		buf = "receive".getBytes();
		DatagramPacket DpSend1 = new DatagramPacket(buf, buf.length, ip, 1234);
		ds.send(DpSend1);
		DatagramSocket ds1 = new DatagramSocket(1235);
		byte[] receive = new byte[65535];
		DatagramPacket DpReceive = null;
		ArrayList<String> ip1 = new ArrayList<String>();
		ArrayList<String> file_name = new ArrayList<String>();
		int a = 0;
		while (true) {
			receive = new byte[65535];
			DpReceive = new DatagramPacket(receive, receive.length);
			ds1.receive(DpReceive);
			// System.out.println(""+data(receive));
			if (data(receive).toString().equals("bye"))
				break;
			if (a == 0)
				ip1.add("" + data(receive));
			else
				file_name.add("" + data(receive));
			a = 1 - a;
		}
		for (int i = 0; i < ip1.size(); i++) {
			System.out.println(i + "--->" + ip1.get(i) + "  " + file_name.get(i));
		}
		System.out.println("Enter file number you want  to download");
		Scanner sc = new Scanner(System.in);
		int no = sc.nextInt();
		Socket soc = new Socket(ip1.get(no), 9082);
		System.out.println("Client: Connection Estb");
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())), true);
		out.println(file_name.get(no));
		String FILE_TO_RECEIVED = "F:\\mtech\\sem-2\\DS\\RECEIVED\\"+file_name.get(no);
		  int bytesRead;
		    int current = 0;
		    FileOutputStream fos = null;
		    BufferedOutputStream bos = null;
		    Socket sock = null;
		    try {
		        sock = new Socket(ip1.get(no),9083);
		        System.out.println("Connecting...");
		        int FILE_SIZE = 6022386;
		        // receive file
		        byte [] mybytearray  = new byte [FILE_SIZE];
		        InputStream is = sock.getInputStream();
		        fos = new FileOutputStream(FILE_TO_RECEIVED);
		        bos = new BufferedOutputStream(fos);
		        bytesRead = is.read(mybytearray,0,mybytearray.length);
		        current = bytesRead;

		        do {
		           bytesRead =
		              is.read(mybytearray, current, (mybytearray.length-current));
		           if(bytesRead >= 0) current += bytesRead;
		        } while(bytesRead > -1);

		        bos.write(mybytearray, 0 , current);
		        bos.flush();
		        System.out.println("File " + FILE_TO_RECEIVED
		            + " downloaded (" + current + " bytes read)");
		      }
		      finally {
		        if (fos != null) fos.close();
		        if (bos != null) bos.close();
		        if (sock != null) sock.close();
		      }
	}

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
