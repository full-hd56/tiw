import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

public class game_panel extends JPanel  implements MouseListener {
    Image space_1 = Toolkit.getDefaultToolkit()
            .createImage(System.getProperty("user.dir") +
                    File.separator + "space_1.png");
    Image space_ship_1 = Toolkit.getDefaultToolkit()
            .createImage(System.getProperty("user.dir") +
                    File.separator + "space ship_1.png");
    Image space_ship_2 = Toolkit.getDefaultToolkit()
            .createImage(System.getProperty("user.dir") +
                    File.separator + "space ship_2.png");
    Image space_ship_3 = Toolkit.getDefaultToolkit()
            .createImage(System.getProperty("user.dir") +
                    File.separator + "space ship_3.png");
    Image space_ship_4 = Toolkit.getDefaultToolkit()
            .createImage(System.getProperty("user.dir") +
                    File.separator + "space ship_4.png");
    Image ammo = Toolkit.getDefaultToolkit()
            .createImage(System.getProperty("user.dir") +
                    File.separator + "bullet.png");

    int my_position;
    String ip_server;
    //data data;
    client client  = new client();
    //ArrayList<bullet> bullet = new ArrayList<bullet>();
    server server;
    public game_panel(String ip){
        this.ip_server = ip;
        //data =  new data(ip);
        server = new server(this);
        setLayout(null);
        setSize(1067,600);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(space_1, 0, 0, 1067, 600, 0, 0, 1067, 600, this);
        g.setColor(Color.CYAN);
        g.drawRect(getWidth() / 2 - 125,getHeight() / 2 - 125,250,250);

        g.setColor(Color.CYAN);
        g.drawRect(getWidth() / 2 - 150,getHeight() / 2 - 150,300,300);

        g.drawImage(space_ship_1,getWidth() / 2 - 25,getHeight() / 2,this);
        g.drawImage(space_ship_2,getWidth() / 2 + 50,getHeight() / 2,60,60,this);
        g.drawImage(space_ship_3,getWidth() / 2 - 100,getHeight() / 2,60,60,this);
        g.drawImage(space_ship_4,getWidth() / 2 - 25,getHeight() / 2 - 100,60,60,this);

        String server_info_b = server.get_info();
        if(server_info_b!=""){
            String[] bxy =  server_info_b.split("\n");
            // System.out.println(bxy.length);
            if(bxy.length==2){
                String[] bxs = bxy[0].split(" ");
                String[] bys = bxy[1].split(" ");
                int[] bx = new int[bxs.length];
                int[] by = new int[bys.length];
                for(int i=0;i<bxs.length;i=i+1){
                    bx[i]=Integer.parseInt(bxs[i]);
                    by[i]=Integer.parseInt(bys[i]);
                }
                for(int i=0;i<bx.length;i=i+1){
                    g.drawImage(ammo, bx[i], by[i],30,30,this);
                }
            }
        }

    }
    //setter getter
    public  void setPosition(int position){
        this.my_position = position;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(my_position == 1){
            String str = 531+" "+213+" "+e.getX()+" "+e.getY();
            client.send(ip_server, 40001,str);
        }
        else if(my_position == 2){
            String str = 455+" "+311+" "+e.getX()+" "+e.getY();
            client.send(ip_server, 40001,str);
        }
        if(my_position == 3){
            String str = 531+" "+317+" "+e.getX()+" "+e.getY();
            client.send(ip_server, 40001,str);
        }
        else if(my_position == 4){
            String str = 605+" "+310+" "+e.getX()+" "+e.getY();
            client.send(ip_server, 40001,str);
        }
        else {}

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }




}