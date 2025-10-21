import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int player = 1;
        Scanner scanf = new Scanner(System.in);
        ArrayList<String> ip_list=new ArrayList<String>();
        while(true){
            System.out.print("ip : ");
            String str = scanf.next();
            if(str.equals("n")){
                for(int i=0;i<ip_list.size();i=i+1){
                    System.out.println(ip_list.get(i));

                }
                break;
            }
            else {
                ip_list.add(str);
                player=player+1;
            }
        }

        String[] ip = new String[ip_list.size()];
        for(int i=0;i<ip_list.size();i=i+1){
            ip[i]=ip_list.get(i);
        }
        run_base base = new run_base(ip);
    }
}