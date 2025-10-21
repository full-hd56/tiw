public class bullet/* extends Thread*/{
boolean active=true;
int time=5;
int index;
double x;
double y;
/*int dx =1;
int dy =0;*/
double dx;
double dy;
double angle;
run_base base;
public bullet(){

}
public bullet(int x1, int y1,run_base game,int x2, int y2){
    this.x = x1;
    this.y = y1;
    this.base = game;
    //index = data.bullet_x.size() - 1;
    double xdiff = x2-x1;
    double ydiff = y2-y1;
    //angle = (float)Math.atan2(ydiff,xdiff) * (float)(180/Math.PI) ;
    //angle = (int)Math.toDegrees(Math.atan2(ydiff,xdiff));
    angle = Math.toDegrees(Math.atan2(ydiff,xdiff));
    System.out.println("angle :  "+angle);
    dx = Math.cos(Math.toRadians(angle));
    dy = Math.sin(Math.toRadians(angle));
    //run in
    /*Thread t = new Thread(this);
    t.start();*/
}
void move(){
    x = x+dx;
    y = y+dy;
}
void check_end(){
    if(x>1067 || y>600 || x<0 || y<0){
        active=false;
        System.out.println("remove bullet");
        //base.check_bullet();
    }
    /*else {
        base.check_bullet();
    }*/
}
/*@Override
public void run() {
    while(active){
        move();
        check_end();
        //base.check_bullet();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        } catch (Exception e){
            System.out.println("exception");
        }

    }
}*/
}

