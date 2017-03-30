import javax.swing.*;
import java.awt.*;

/**
 * Created by Korybut on 28.03.2017.
 */
public class CountSeries extends JLabel {

    public CountSeries(){
        super("", SwingConstants.CENTER);
        setFont (new Font("Arial", Font.BOLD, 40));
        setOpaque(false);
    }

    public void setValue(int val){
        setText(String.valueOf(val));
    }

}
