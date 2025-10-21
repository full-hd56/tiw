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

public class server_base {
    String in_server="";
    //game_panel game_panel;
    server_base(run_base game) {
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
    server_base server;
    run_base game;
    public ServerThread(server_base server, run_base game) {
        this.server = server;
        this.game = game;
    }
    @Override
    public void run() {
        try {
            // Open port 50101 for receiving data
            ServerSocket servSocket = new ServerSocket(40001);
            // Loop for waiting data
            while (true) {
                try {
                    System.out.println("Waiting for connection...");
                    String line = "";
                    String str = "";
                    //String all_line = "";
                    // Waiting for data
                    Socket socket = servSocket.accept();
                    // Data in then convert to String
                    InputStream input = socket.getInputStream();
                    InputStreamReader inputStream = new InputStreamReader(input);
                    BufferedReader bufferIn = new BufferedReader(inputStream);
                    // Read all lines to TextArea
                    while ((line = bufferIn.readLine()) != null) {
                        //System.out.println(line);
                        //all_line=all_line + line + "\n";
                        str=str + line + " ";
                        /*server.content.insert(line + "\n",0);
                        server.content.setCaretPosition(0);*/
                    }
                    server.in_server = str;
                    System.out.println(str);
                    bufferIn.close();
                    if(str!="") {
                        String[] xy = str.split(" ");
                        if (xy.length == 4) {
                            int x1 = Integer.parseInt(xy[0]);
                            int y1 = Integer.parseInt(xy[1]);
                            int x2 = Integer.parseInt(xy[2]);
                            int y2 = Integer.parseInt(xy[3]);
                            game.bullet_create(x1, y1, x2, y2);
                            //game.check_bullet();
                        }
                    }
                    //game.repaint();
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
