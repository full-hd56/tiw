
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //192.168.1.39
    //int player_position;
    public static void main(String[] args){

        Scanner scanf = new Scanner(System.in);
        String server_ip;
        System.out.print("server ip = ");server_ip = scanf.next();
        System.out.print("position = ");int player_position = scanf.nextInt();



        //int player_position=1;
        GameOnline frame = new GameOnline();
        game_panel background = new game_panel(server_ip);

        background.setPosition(player_position);
        System.out.println("your_position = "+background.my_position);
        //System.out.println("your_position = "+player_position);
        frame.add(background);
        frame.setVisible(true);

       /* GameOnline frame = new GameOnline();
        game_panel background = new game_panel();
        frame.add(background);
        frame.setVisible(true);*/
    }
}