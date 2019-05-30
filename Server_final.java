package socket_assign1;


import java.net.*;
import java.io.*;

public class Server_final
{
public static void main(String []args)throws Exception
{
System.out.println("Server signing on");
ServerSocket ss= new ServerSocket(9082);
Socket soc=ss.accept();
System.out.println("Server: Connection Estb");
InputStream a=soc.getInputStream();
BufferedReader in=new BufferedReader(new InputStreamReader(a));
String str=in.readLine();
System.out.println("filname: "+str);
soc.close();
FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    try {
      servsock = new ServerSocket(9083);
      while (true) {
        System.out.println("Waiting...");
        try {
          sock = servsock.accept();
          System.out.println("Accepted connection : " + sock);
          // send file
          String FILE_TO_SEND ="F:\\garbage\\assign_directory\\"+str;
          File myFile = new File (FILE_TO_SEND);
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = sock.getOutputStream();
          System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Done.");
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
    }
    finally {
      if (servsock != null) servsock.close();
    }
}
}
