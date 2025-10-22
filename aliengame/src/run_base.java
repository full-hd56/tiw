import java.util.ArrayList;
import java.util.TreeMap;

public class run_base extends Thread {
    ArrayList<bullet> bullet = new ArrayList<bullet>();
    data data;
    server_base s_b;
    int n=25;
    boolean flag=true;
    String[] all_ip;
    ArrayList<enermy> enermy = new ArrayList<enermy>();
    public run_base (String[] ip) {
        this.all_ip=ip;
        data = new data(ip);
        s_b = new server_base(this);
        //left
        for(int i=0;i<n/4;i=i+1){
            int x1 = -80;
            int y1 = (int)(Math.random()*600);
            int x2 = 1067/2;
            int y2 = 600/2;
            enermy alien = new enermy(x1,y1,this,x2,y2);
            data.addA_x(x1);
            data.addA_y(y1);
            enermy.add(alien);
        }
        //top
        for(int i=0;i<n/4;i=i+1){
            int x1 = (int)(Math.random()*1067);
            int y1 = -80;
            int x2 = 1067/2;
            int y2 = 600/2;
            enermy alien = new enermy(x1,y1,this,x2,y2);
            data.addA_x(x1);
            data.addA_y(y1);
            enermy.add(alien);
        }
        //right
        for(int i=0;i<n/4;i=i+1){
            int x1 = 1067+80;
            int y1 = (int)(Math.random()*600);
            int x2 = 1067/2;
            int y2 = 600/2;
            enermy alien = new enermy(x1,y1,this,x2,y2);
            data.addA_x(x1);
            data.addA_y(y1);
            enermy.add(alien);
        }
        //below
        for(int i=0;i<n/4;i=i+1){
            int x1 = (int)(Math.random()*1067);
            int y1 = 600+80;
            int x2 = 1067/2;
            int y2 = 600/2;
            enermy alien = new enermy(x1,y1,this,x2,y2);
            data.addA_x(x1);
            data.addA_y(y1);
            enermy.add(alien);
        }
        Thread t = new Thread(this);
        t.start();
    }
    public void bullet_create(int x1, int y1, int x2, int y2){
        bullet bullet_c = new bullet(x1,y1,this,x2,y2);
        data.addBullet_x(x1);
        data.addBullet_y(y1);
        bullet.add(bullet_c);
    }
    public void check_bullet(){
        //System.out.println("s1");
        for(int i=0;i<this.bullet.size();i=i+1){
            data.bullet_x.set(i,(int)this.bullet.get(i).x);
            data.bullet_y.set(i,(int)this.bullet.get(i).y);
        }
        for(int i=0;i<this.enermy.size();i=i+1){
            data.a_x.set(i,(int)this.enermy.get(i).x);
            data.a_y.set(i,(int)this.enermy.get(i).y);
        }
        data.send();
        if(this.enermy.size() == 0){
            this.flag=false;
            client_base c = new client_base();
            for(int i=0;i<all_ip.length;i=i+1){
                c.send(this.all_ip[i],40002,"end");
            }
        }
        //System.out.println("send");
    }
    @Override
    public void run() {
        while (flag) {
            for(int i=0;i<bullet.size();i=i+1) {
                for(int j=0;j<enermy.size();j=j+1) {
                    int dx = (int)(bullet.get(i).x - enermy.get(j).x);
                    int dy = (int)(bullet.get(i).y - enermy.get(j).y);
                    double dist = Math.sqrt(dx*dx + dy*dy);
                    if (dist < 60) {
                        bullet.get(i).active = false;
                        enermy.get(j).active = false;
                    }
                    //int dx = panel.x[i] - panel.x[j];
                    //int dy = panel.y[i] - panel.y[j];
                    //double dist = Math.sqrt(dx*dx + dy*dy);
                }
            }
            for (int i = 0; i < bullet.size(); i = i + 1) {
                //bullet.get(i).move();
                bullet.get(i).check_end();
            }
            for (int i = 0; i < bullet.size(); i = i + 1) {
                if (this.bullet.get(i).active == false) {
                    this.bullet.remove(i);
                    data.bullet_x.remove(i);
                    data.bullet_y.remove(i);
                    i = 0;
                }
            }
            for (int i=0;i<enermy.size();i=i+1) {
                //enermy.get(i).move();
                enermy.get(i).check_end();
            }
            for (int i=0;i<enermy.size();i=i+1) {
                if (this.enermy.get(i).active == false) {
                    this.enermy.remove(i);
                    data.a_x.remove(i);
                    data.a_y.remove(i);
                    i = 0;
                }
            }
            /*for(int i=0;i<bullet.size();i=i+1) {
                for(int j=0;j<enermy.size();j=j+1) {
                    int dx = (int)(bullet.get(i).x - enermy.get(j).x);
                    int dy = (int)(bullet.get(i).y - enermy.get(j).y);
                    double dist = Math.sqrt(dx*dx + dy*dy);
                    if (dist < 60) {
                        bullet.get(i).active = false;
                        enermy.get(j).active = false;
                    }
                    //int dx = panel.x[i] - panel.x[j];
                    //int dy = panel.y[i] - panel.y[j];
                    //double dist = Math.sqrt(dx*dx + dy*dy);
                }
            }*/

            check_bullet();
            try {
                Thread.sleep(1);
            } catch (Exception e) {}
        }
    }
}

