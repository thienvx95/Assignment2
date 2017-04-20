package courseman2.controller;

import courseman2.NotPossibleException;
import courseman2.model.CompulsoryModules;
import courseman2.model.ElectiveModules;

import courseman2.model.Modules;
import courseman2.model.Student;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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

    protected HashMap<String,Modules> ModuleMap = new HashMap<String,Modules>();
    public ModuleManager(String title, String titleText, int width, int height, int x, int y) {
        super(title, titleText, width, height, x, y);
    }
   
    public Modules getModuleByCode(String code) {
		Modules modules = null;
		System.out.println(objects.toString());
		for (Object obj : objects) {
			Modules t = (Modules) obj;
			if (t.getCode().equalsIgnoreCase(code)) {
				modules = t;
			}
		}
		return modules;
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
    	boolean check =true;
        StringBuilder messeage = new StringBuilder();
        if(nameField.getText().trim().length()==0){
            messeage.append("\n Name field can not be blank");
            check =false;
        }
        if (semesterField.getText().trim().length()==0){
            messeage.append("\n Semester field can not be blank");
            check =false;
        }
        if (creditsField.getText().trim().length()==0){
            messeage.append("\n Credit field can not be blank");
            check =false;
        }
        if(moduleBox.getSelectedItem().equals("Elective")){
        	 if (deptNameField.getText().trim().length()==0){
                 messeage.append("\n DeptName field can not be blank");
                 check =false;
             }

        	 
        	modules = new ElectiveModules(nameField.getText().trim(), Integer.parseInt(semesterField.getText()), Integer.parseInt(creditsField.getText()), deptNameField.getText().trim());
        } else if(moduleBox.getSelectedItem().equals("Compulsory")){
        	modules = new CompulsoryModules(nameField.getText().trim(),Integer.parseInt(semesterField.getText()),Integer.parseInt(creditsField.getText()));
        	objects.add(modules);

        } else if(moduleBox.getSelectedItem().equals("Compulsory")){
        	modules = new Modules(nameField.getText().trim(),Integer.parseInt(semesterField.getText()),Integer.parseInt(creditsField.getText()));
        	ModuleMap.put(modules.getCode(),modules);
        	objects.add(modules);
        }
        
        if(messeage.length()>0){
        	throw new NotPossibleException(messeage.toString());
        }

        displayMessage("Create a " + modules.toString(),"Create a Module");



        return modules;
    }

    @Override
    public void save() {
    	if(this.objects!=null){
    		System.out.println(this.objects.size());
			try {
				FileOutputStream fout = new FileOutputStream("Modules.dat");
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				
				Vector<Modules> obj = (Vector<Modules>) this.objects;
				for(Modules o : obj){
					oout.writeObject(o);
				}
				oout.close();
				
				System.out.println("Saved");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error");

			}
			
		}
    }

    @Override
    public void startUp() {
    	if(this.objects!=null){
			
			try {
				System.out.println("Start Up Modules");
				FileInputStream fin = new FileInputStream("Modules.dat");
				ObjectInputStream oin = new ObjectInputStream(fin);
		            ArrayList<Modules> LstModules = null;
		            LstModules = new ArrayList<Modules>();		
						int count = 0;
			             while(fin.available()>0){		            	 
			            	 Modules modules = (Modules) oin.readObject();
			            	 LstModules.add(count,modules);
			            	 count++;	        
			             }	      
			             for(Modules modules : LstModules){
			            	 objects.add(modules);
			             }
		            
			} 
	
			 catch (Exception e) {
				// TODO Auto-generated catch block
				 e.printStackTrace();
				 System.err.println("Can't open file or not found");
			}
		}
    }
}
