package courseman2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by minhthuc on 4/4/2017.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        JFrame jFrame = new JFrame();
        jFrame.setSize(100, 200);

        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        jFrame.setVisible(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        jPanel.setBackground(Color.BLUE);
                        Thread.sleep(1000);
                        jPanel.setBackground(Color.RED);
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        break;
                    }
                }
            }
        }).start();

    }
}
