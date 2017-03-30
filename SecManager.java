import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

/**
 * Created by Korybut on 26.03.2017.
 */

public class SecManager extends JFrame implements ActionListener{


    JPanel contentPane = new JPanel(new BorderLayout());
    private JButton startB = new JButton("Start!");
    private JButton stopB = new JButton("Stop!");
    private JButton resumeB = new JButton("Wznów");
    private JButton resetB = new JButton("Resetuj");
    private JButton workoutB = new JButton("Ustaw trening");
    private Stoper stoper = new Stoper(); // my class Stoper

    private JTextField seriesNumberField = new JTextField("val");
    private JTextField seriesTimeField = new JTextField("sec");
    private JTextField intervalField = new JTextField("val");

    private JLabel seriesNumberLabel = new JLabel("ilość serii:", JLabel.RIGHT);
    private JLabel seriesTimeLabel = new JLabel("czas serii:", JLabel.RIGHT);
    private JLabel intervalLabel = new JLabel("przerwa:", JLabel.RIGHT);
    private JLabel countingLabel = new JLabel("seria", JLabel.CENTER);

    private Workout workout = new Workout();

    public SecManager(){
        super("SecManager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        Dimension scSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = scSize.width;
        int height = scSize.height;
        setBounds(width/2-200,height/2-130,400,260);
        setLayout(null);
        setVisible(true);

        add(stoper);
        stoper.setBounds(20,20,100,40);
        stoper.setText("0.00");

        add(workout.count);
        workout.count.setBounds(130,60,50,50);
        workout.count.setBounds(130,60,50,50);
        workout.count.setText("0");

        startB.setBounds(200,20,140,40);
        startB.setVisible(true);
        startB.setEnabled(false);
        startB.addActionListener(this);
        add(startB);

        resumeB.setBounds(200,20,140,40);
        resumeB.setVisible(false);
        resumeB.addActionListener(this);
        add(resumeB);

        stopB.setBounds(200,20,140,40);
        stopB.setVisible(false);
        stopB.addActionListener(this);
        add(stopB);

        resetB.setBounds(200,70,140,20);
        resetB.setVisible(true);
        resetB.setEnabled(false);
        resetB.addActionListener(this);
        add(resetB);

        workoutB.setBounds(120,170,160,30);
        workoutB.setVisible(true);
        workoutB.addActionListener(this);
        add(workoutB);

        seriesNumberField.setBounds(80,120,40,25);
        seriesNumberField.setVisible(true);
        seriesNumberField.addActionListener(this);
        add(seriesNumberField);

        seriesTimeField.setBounds(200,120,40,25);
        seriesTimeField.setVisible(true);
        seriesTimeField.addActionListener(this);
        add(seriesTimeField);

        intervalField.setBounds(320,120,40,25);
        intervalField.setVisible(true);
        intervalField.addActionListener(this);
        add(intervalField);

        seriesNumberLabel.setBounds(10,120,60,25);
        seriesNumberLabel.setVisible(true);
        add(seriesNumberLabel);

        seriesTimeLabel.setBounds(130,120,60,25);
        seriesTimeLabel.setVisible(true);
        add(seriesTimeLabel);

        intervalLabel.setBounds(250,120,60,25);
        intervalLabel.setVisible(true);
        add(intervalLabel);

        countingLabel.setBounds(130,46,50,30);
        countingLabel.setVisible(true);
        countingLabel.setFont( new Font("Arial", 10, 10));
        add(countingLabel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==startB){
            stoper.start();
            workout.start();
            startB.setVisible(false);
            stopB.setVisible(true);

        }
        else if(source==stopB){
            stoper.pause();
            workout.pause();
            resumeB.setVisible(true);
            stopB.setEnabled(true);
            resetB.setEnabled(true);
        }
        else if(source==resumeB){
            stoper.start();
            workout.start();
            startB.setVisible(false);
            resumeB.setVisible(false);
            stopB.setVisible(true);
            resetB.setEnabled(false);
        }
        else if(source==resetB){
            stoper.stop();
            workout.stop();
            startB.setVisible(true);
            startB.setEnabled(false);
            stopB.setVisible(false);
            resumeB.setVisible(false);
            resetB.setEnabled(false);
            stoper.setText("0.00");
            workoutB.setEnabled(true);
            intervalField.setText("0");
            seriesNumberField.setText("0");
            seriesTimeField.setText("0");
        }
        else if(source==workoutB){
            workout.setInterval(parseInt(intervalField.getText()));
            workout.setSeria(parseInt(seriesNumberField.getText()));
            workout.setSeriaTime(parseInt(seriesTimeField.getText()));
            startB.setEnabled(true);
            workoutB.setEnabled(false);
        }
    }

}