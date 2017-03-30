import java.awt.*;

/**
 * Created by Korybut on 26.03.2017.
 */
public class Main {
    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SecManager();
            }
        });
    }
}
