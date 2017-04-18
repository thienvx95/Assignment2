package courseman2.controller;

import courseman2.NotPossibleException;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class ModuleManager extends Manager{

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
    private JButton cancleButton;
    private JButton okButton;
    private JComboBox moduleBox;
    private JLabel titlePanle;
    private JLabel nameLable;
    private JLabel semesterLable;
    private JLabel creditsLable;
    private JLabel moduleTypelLable;
    private JTextField nameField;
    private JTextField semesterField;
    private JTextField creditsField;
    private JTextField deptNameField;
    private JLabel deptName;
    public ModuleManager(String title, String titleText, int width, int height, int x, int y) {
        super(title, titleText, width, height, x, y);
    }

    @Override
    protected void createMiddlePanel() {
       
    	moduleBox = new JComboBox();
        moduleBox.setModel(new DefaultComboBoxModel(new String[]{"Elective","Compulsory"}));
        
        nameLable = new JLabel();
        nameLable.setText("Name");
        nameField = new JTextField();


        semesterLable = new JLabel();
        semesterLable.setText("Semester");
        semesterField = new JTextField();
        
        creditsLable = new JLabel();
        creditsLable.setText("Credit");

        moduleTypelLable = new JLabel();
        moduleTypelLable.setText("Module Type:");
        
        
        JPanel jPanelForMiddle = new JPanel();
        jPanelForMiddle.setLayout(new GridLayout(4,2));
        jPanelForMiddle.add(moduleTypelLable);
        jPanelForMiddle.add(moduleBox);
        
        jPanelForMiddle.add(nameLable);
        jPanelForMiddle.add(nameField);
        
        jPanelForMiddle.add(semesterLable);
        jPanelForMiddle.add(semesterField);
        
        

        gui.add(jPanelForMiddle,BorderLayout.CENTER);
        
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
