import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Workout implements Runnable{

    private int seria = 0;
    private int seriaTime = 0;
    private int interval = 0;
    private int value;
    private Thread thread;
    private String soundName = "pick.wav";
    CountSeries count = new CountSeries();

    public void soundWhistle() throws LineUnavailableException {
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = AudioSystem.getClip();
        try {
            clip.open(audioInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();
    }

    public void setSeria(int s){
        seria = s;
    }

    public void setSeriaTime(int t){
        seriaTime = t;
    }

    public void setInterval(int i){
        interval = i;
    }

    public void start(){
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void pause(){
        thread = null;
    }

    public void resume() {
        thread.start();
        value--;
    }

    public void stop(){
        thread = null;
        value = 0;
    }

    @Override
    public void run() {
        value = 0;
        System.out.println("START!");
        try {
            soundWhistle();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        while (thread == Thread.currentThread() && value<seria) {
            count.setValue(value+1);
            if(thread!=null){
                try {
                    soundWhistle();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }

            try {
                TimeUnit.SECONDS.sleep(seriaTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value++;
            if(value<seria){

                if(thread!=null){
                    try {
                        soundWhistle();
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    TimeUnit.SECONDS.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i=0; i<3; i++){
            try {
                soundWhistle();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        System.out.println("KONIEC!");
    }
}
