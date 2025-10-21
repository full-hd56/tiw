import java.io.*;
import java.net.*;

public class client_base {
    //String sent_out;
    client_base(){}
    public void send(String ip, int port,String send_string){
        try {
            // Connect to Server
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
    /*public static void main(String[] args) throws IOException {
        client_base client = new client_base();
        client.send("192.168.1.39", 40001, "abcdefg555");
    }*/
}
