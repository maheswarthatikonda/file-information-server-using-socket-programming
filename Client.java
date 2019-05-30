package clientTest;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 
  
public class Client 
{ 
    public static void main(String args[]) throws IOException 
    { 
        Scanner sc = new Scanner(System.in); 
  
        // Step 1:Create the socket object for 
        // carrying the data. 
        DatagramSocket ds = new DatagramSocket(); 
  
      // InetAddress ip = InetAddress.getLocalHost(); 
        InetAddress ip = InetAddress.getByName("192.168.100.4"); 
     /*   InetAddress  ip = InetAddress.getByAddress(new byte[] {
                (byte)192, (byte)168, (byte)100, (byte)4}
        );
*/     
        byte buf[] = null; 
        List<String> results = new ArrayList<String>();
        buf="send".getBytes();
        DatagramPacket DpSend1 = 
                   new DatagramPacket(buf, buf.length, ip, 1234); 
           ds.send(DpSend1); 

		 File file = new File("F:\\mtech\\sem-2\\DS\\test");
	        File[] files = file.listFiles();
	        for(File f: files){
	           System.out.println(f.getName());
	           String fname=f.getName();
	           buf = fname.getBytes(); 
	           DatagramPacket DpSend = 
	                   new DatagramPacket(buf, buf.length, ip, 1234); 
	           ds.send(DpSend); 
	        }
	        buf="bye".getBytes();
	        DatagramPacket DpSend = 
	                   new DatagramPacket(buf, buf.length, ip, 1234); 
	           ds.send(DpSend); 
        // loop while user not enters "bye" 
        /*while (true) 
        { 
            String inp = sc.nextLine(); 
  
            // convert the String input into the byte array. 
            buf = inp.getBytes(); 
  
            // Step 2 : Create the datagramPacket for sending 
            // the data. 
            DatagramPacket DpSend = 
                  new DatagramPacket(buf, buf.length, ip, 1234); 
  
            // Step 3 : invoke the send call to actually send 
            // the data. 
            ds.send(DpSend); 
  
            // break the loop if user enters "bye" 
            if (inp.equals("bye")) 
                break; 
        } */
    } 
} 