import java.util.ArrayList;

public class data {
    ArrayList<Integer> bullet_x = new ArrayList<Integer>();
    ArrayList<Integer> bullet_y = new ArrayList<Integer>();
    ArrayList<Integer> a_x = new ArrayList<Integer>();
    ArrayList<Integer> a_y = new ArrayList<Integer>();
    String ip[];
    client_base client_send = new  client_base();
    public data () {}
    public data(String ip[]){
        this.ip = ip;
    }
    public void send(){
        String msg="";
        for(int i=0;i<bullet_x.size();i=i+1){
            msg = msg+bullet_x.get(i)+" ";
        }
        msg = msg+"\n";
        for(int i=0;i<bullet_y.size();i=i+1){
            msg = msg+bullet_y.get(i)+" ";
        }
        for(int i=0;i<ip.length;i=i+1){
            client_send.send(ip[i], 40002, msg);
        }
    }
    public void  setBullet_x(int index,int bullet_x){
        this.bullet_x.set(index,bullet_x);
    }
    public void  setBullet_y(int index,int bullet_y){
        this.bullet_y.set(index,bullet_y);
    }
    public void  addBullet_x(int bullet_x){
        this.bullet_x.add(bullet_x);
    }
    public void  addBullet_y(int bullet_y){
        this.bullet_y.add(bullet_y);
    }
    public void  addA_x(int a_x){
        this.a_x.add(a_x);
    }
    public void  addA_y(int a_y){
        this.a_y.add(a_y);
    }
}