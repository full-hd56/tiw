import java.util.ArrayList;
import java.util.TreeMap;


public class run_base extends Thread {
    ArrayList<bullet> bullet = new ArrayList<bullet>();
    data data;
    server_base s_b;
    public run_base (String[] ip) {
        data = new data(ip);
        s_b = new server_base(this);
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
            //System.out.println("s2");

            /*if(this.bullet.get(i).active==false){
                this.bullet.remove(i);
                data.bullet_x.remove(i);
                data.bullet_y.remove(i);
                i=0;
                System.out.println("s3");
            }
            else if(this.bullet.get(i).active==true){
                System.out.println("size = "+this.bullet.size());
                System.out.println("data  size = " + data.bullet_x.size());
                System.out.println("before s4   i = " + i);
                //-----

                data.bullet_x.set(i,(int)this.bullet.get(i).x);
                data.bullet_y.set(i,(int)this.bullet.get(i).y);
                //System.out.println("s4");
            }*/
            //null active
            data.bullet_x.set(i,(int)this.bullet.get(i).x);
            data.bullet_y.set(i,(int)this.bullet.get(i).y);
            //System.out.println("s end");
        }
        data.send();
        //System.out.println("send");
    }
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < bullet.size(); i = i + 1) {
                bullet.get(i).move();
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
            check_bullet();
            try {
                Thread.sleep(5);
            } catch (Exception e) {}
        }
    }
}
