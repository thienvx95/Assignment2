package courseman2.controller;

import courseman2.NotPossibleException;
import courseman2.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManager extends Manager{
    private JLabel titleLable, nameLable,dobLable,addressLable,emailLable;
    private JTextField nameField,dobField,addressField,emailField;
    private JButton okButton, cancleButton;

    /**
     * @param title
     * @param titleText
     * @param width
     * @param height
     * @param x
     * @param y
     * @effects initialise <tt>this</tt> with a <tt>gui</tt> using the specified settings
     * and an empty set of objects
     */
    public StudentManager(String title, String titleText, int width, int height, int x, int y) {
        super(title, titleText, width, height, x, y);
    }

    @Override
    protected void createMiddlePanel() {
    JFrame jFrame = new JFrame();
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("File");
        jMenuBar.add(jMenu);
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");
        jMenu.add(save);
        jMenu.add(exit);
    exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
    titleLable = new JLabel("Enter Student Detail");
    nameLable = new JLabel("name");
    dobLable = new JLabel("dob");
    addressLable = new JLabel("Address");
    emailLable = new JLabel("Email");

    nameField = new JTextField();
    dobField = new JTextField();
    addressField = new JTextField();
    emailField = new JTextField();
    okButton = new JButton("ok");
    cancleButton = new JButton("Cancle");
    jFrame.setLayout(new GridLayout(3,1));
    JPanel jPanelForTitle = new JPanel();
    jPanelForTitle.add(titleLable);
    jFrame.add(jPanelForTitle);

    JPanel jPanelForMiddle = new JPanel();
    jPanelForMiddle.setLayout(new GridLayout(4,2));
    jPanelForMiddle.add(nameLable);
    jPanelForMiddle.add(nameField);
    jPanelForMiddle.add(dobLable);
    jPanelForMiddle.add(dobField);
    jPanelForMiddle.add(addressLable);
    jPanelForMiddle.add(addressField);
    jPanelForMiddle.add(emailLable);
    jPanelForMiddle.add(emailField);
    jFrame.add(jPanelForMiddle);
    cancleButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i  =JOptionPane.showConfirmDialog(jFrame,"Do you want to cancle this?","Confirm",JOptionPane.YES_NO_OPTION);
            System.exit(i);
        }
    });
    JPanel jPanelForButton = new JPanel();
    jPanelForButton.add(okButton);
    jPanelForButton.add(cancleButton);
    jFrame.add(jPanelForButton);
    jFrame.setJMenuBar(jMenuBar);
    }

    private void OkButton(ActionListener actionListener){
        Student student = null;
        String messeage = "";
        if(nameField.getText().trim().length()==0){
            messeage+="\n Name field can not be blank";
        }else
        if (dobField.getText().trim().length()==0){
            messeage+="\nDob can not be blank";
        }else
        if (addressField.getText().trim().length()==0){
            messeage+="\nAddress can not be blank";
        }else
        if (emailField.getText().trim().length()==0){
            messeage+="\nEmail can not be blank";
        }

    }
    @Override
    public void clearGUI() {

    }

    @Override
    public Object createObject() throws NotPossibleException {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public void startUp() {

    }
}
