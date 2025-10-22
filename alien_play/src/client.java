import java.io.*;
import java.net.*;

public class client {
    //String sent_out;
    client(){}
    public void send(String ip, int port,String send_string){
        try {
            // Connect to Server
            //server ip
            Socket socket = new Socket(ip, port);
            PrintStream dataOut = new PrintStream(socket.getOutputStream());
            // Prepare data format
            InetAddress address = InetAddress.getLocalHost();
            //String msg = address.getHostName() + "#" + "message";
            // Send data out
            dataOut.println(send_string);
            dataOut.close();
        } catch (Exception e1) {
        }
    }
}


