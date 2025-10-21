import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class server {
    String in_server="";
    //game_panel game_panel;
    server(game_panel game) {
        //game_panel = game;
        try {
            InetAddress a = InetAddress.getLocalHost();
            System.out.println(a.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Thread serverThread = new ServerThread(this, game);
        serverThread.start();
    }
    public String get_info() {
        return in_server;
    }
    /*public static void main(String[] args) {
        server server = new server();
    }*/

}
class ServerThread extends Thread {
    server server;
    game_panel game;
    public ServerThread(server server, game_panel game) {
        this.server = server;
        this.game = game;
    }
    @Override
    public void run() {
        try {
            // Open port 50101 for receiving data
            ServerSocket servSocket = new ServerSocket(40002);
            // Loop for waiting data
            while (true) {
                try {
                    System.out.println("Waiting for connection...");
                    String line = "";
                    String all_line = "";
                    // Waiting for data
                    Socket socket = servSocket.accept();
                    // Data in then convert to String
                    InputStream input = socket.getInputStream();
                    InputStreamReader inputStream = new InputStreamReader(input);
                    BufferedReader bufferIn = new BufferedReader(inputStream);
                    // Read all lines to TextArea
                    while ((line = bufferIn.readLine()) != null) {
                        //System.out.println(line);
                        all_line=all_line + line + "\n";
                        /*server.content.insert(line + "\n",0);
                        server.content.setCaretPosition(0);*/
                    }
                    server.in_server = all_line;
                    System.out.println(all_line);
                    bufferIn.close();
                    game.repaint();
                    System.out.println("Connection closed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
