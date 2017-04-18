package courseman2.controller;

import courseman2.NotPossibleException;
import courseman2.model.ElectiveModules;
import courseman2.model.Modules;
import courseman2.model.Student;
import sun.security.pkcs11.Secmod;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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

    private JComboBox moduleBox;
    private JLabel nameLable;
    private JLabel semesterLable;
    private JLabel creditsLable;
    private JLabel moduleTypelLable;
    private JTextField nameField;
    private JTextField semesterField;
    private JTextField creditsField;
    private JTextField deptNameField;
    private JLabel deptName;
    protected HashMap<String,Modules> ModuleMap;
    public ModuleManager(String title, String titleText, int width, int height, int x, int y) {
        super(title, titleText, width, height, x, y);
    }

    @Override
    protected void createMiddlePanel() {
    	//moduleType
    	moduleTypelLable = new JLabel("Module Type:");
    	moduleBox = new JComboBox<Object>(new String[]{"Compulsory","Elective"});
        moduleBox.setSelectedIndex(0);
        //name
        nameLable = new JLabel("Name");
        nameField = new JTextField();
        //semester
        semesterLable = new JLabel("Semester");
        semesterField = new JTextField();
        //credits
        creditsLable = new JLabel("Credit");
        creditsField = new JTextField();
        //deptName
        deptName = new JLabel("deptName");
        deptNameField = new JTextField();
        
       
        //jpanel
        JPanel jPanelForMiddle = new JPanel();
        jPanelForMiddle.setLayout(new GridLayout(5,2));
        //add module Box
        jPanelForMiddle.add(moduleTypelLable);
        jPanelForMiddle.add(moduleBox);
        //add name
        jPanelForMiddle.add(nameLable);
        jPanelForMiddle.add(nameField);
        //add semester
        jPanelForMiddle.add(semesterLable);
        jPanelForMiddle.add(semesterField);
        //add credit
        jPanelForMiddle.add(creditsLable);
        jPanelForMiddle.add(creditsField);
        //deptName
        deptName.setVisible(false);
        deptNameField.setVisible(false);
        jPanelForMiddle.add(deptName);
	    jPanelForMiddle.add(deptNameField);
       
        moduleBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				moduleBox = (JComboBox)e.getSource();
				String choose = (String) moduleBox.getSelectedItem();
				if(choose == "Elective"){	
//					 jPanelForMiddle.add(deptName);
//				     jPanelForMiddle.add(deptNameField);
					  deptName.setVisible(true);
					  deptNameField.setVisible(true);
			
				}
				if(choose == "Compulsory"){
//					 jPanelForMiddle.remove(deptName);
//				     jPanelForMiddle.remove(deptNameField);			 
					 deptName.setVisible(false);
				     deptNameField.setVisible(false);			 
				}
				gui.add(jPanelForMiddle,BorderLayout.CENTER);
			}
		});
        
        gui.add(jPanelForMiddle,BorderLayout.CENTER);
     
        
        
        
        
    }

    @Override
    public void clearGUI() {
    	clearGUI((JPanel) gui.getContentPane());
    }

    @Override
    public Object createObject() throws NotPossibleException {
    	Modules modules = null;
    	
        String messeage = "";
        
        if(nameField.getText().trim().length()==0){
            messeage+="\n Name field can not be blank";
        }else
        if (semesterField.getText().trim().length()==0){
            messeage+="\n Semester field can not be blank";
        }else
        if (creditsField.getText().trim().length()==0){
            messeage+="\n Credit field can not be blank";
        } else if(moduleBox.getSelectedItem().equals("Elective")){
        	 if (deptNameField.getText().trim().length()==0){
                 messeage+="\n DeptName field can not be blank";
             }
       
        } else if(moduleBox.getSelectedItem().equals("Compulsory")){
        	modules = new Modules(nameField.getText().trim(),Integer.parseInt(semesterField.getText()),Integer.parseInt(creditsField.getText()));
        	ModuleMap.put(modules.getCode(),modules);
        }
        
			  
        if(messeage != ""){
        	throw new NotPossibleException(messeage);        	
        }
         
        displayMessage("Create a " + modules.toString(),"Create a Module");
        
        
        
        return modules;
    }

    @Override
    public void save() {

    }

    @Override
    public void startUp() {

    }
}
