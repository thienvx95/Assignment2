package courseman2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by minhthuc on 4/4/2017.
 */
public class Test {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        JMenuItem item = new JMenuItem("exit");
        item.setMnemonic(KeyEvent.VK_E);
        item.setToolTipText("Exit the application");
        item.addActionListener((ActionEvent event)->System.exit(0));
        file.add(item);
        menuBar.add(file);
        jf.setJMenuBar(menuBar);
    }
}
