package courseman2.controller;

import courseman2.NotPossibleException;

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
        titlePanle = new JLabel();
        titlePanle.setText("Enter module details");

        moduleBox = new JComboBox();
        moduleBox.setModel(new DefaultComboBoxModel(new String[]{"Elective","Compulsory"}));

        nameLable = new JLabel();
        nameLable.setText("Name");

        nameField = new JTextField();


        semesterLable = new JLabel();
        semesterLable.setText("Semester");

        creditsLable = new JLabel();
        creditsLable.setText("Credit");

        moduleTypelLable = new JLabel();
        moduleTypelLable.setText("Module Type:");

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
