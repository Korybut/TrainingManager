import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Stoper extends JLabel implements Runnable{

    private double a, b, time = 0;
    private Thread thread;

    public Stoper() {
        super("", SwingConstants.RIGHT);
        setFont (new Font("Arial", Font.BOLD, 28));
        setOpaque(true);
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void pause() {
        thread = null;
    }

    public void resume() {
        thread.start();
    }

    public void stop(){
        thread = null;
        time = -0.019;
    }

    @Override
    public void run() {
        while (thread == Thread.currentThread()) {
            a = System.currentTimeMillis();
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b = System.currentTimeMillis();
            time = time + ((b - a)/1000);
            setText(String.valueOf(new BigDecimal(time).setScale(2, BigDecimal.ROUND_HALF_UP))); //precision double
        }
    }
}
