package courseman2.controller;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import courseman2.NotPossibleException;
import courseman2.model.Modules;
import courseman2.model.Student;
import courseman2.model.Enrolment;
import courseman2.view.EasyTable;

import javax.swing.*;

public class EnrolmentManager extends Manager{
	private JLabel title,studentLable,moduleLable,internalMark,examMark;
	private JTextField studentField,moduleField, internalField,examField;
	private JButton okbutton,cancleButton;
	private ModuleManager moduleManager;
	private StudentManager studentManager;
	public List<Enrolment> list = new ArrayList<Enrolment>();

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
	public EnrolmentManager(String title, String titleText, int width, int height, int x, int y) {
		super(title, titleText, width, height, x, y);
	}
	public EnrolmentManager(String title, String titleText, int width, int height, int x, int y,ModuleManager modules,StudentManager student) {
		super(title, titleText, width, height, x, y);
		this.moduleManager = modules;
		this.studentManager = student;	
	}
	/**
	 * @effec
	 * add Enrolment
	 * @param enrolment
	 */
		public void addEnrolment(Enrolment enrolment) {
			list.add(enrolment);
		}
		/**
		 * @effect
		 * is used to call attribute of class enrolment
		 * @param student
		 * @param modules
		 * @return 
		 */
		public Enrolment getEnrolment(Student student,Modules modules){
			for(Enrolment enrolment : list){
				if(enrolment.getStudent() == student && enrolment.getModules() == modules){
					return enrolment;
				}
			}
			return null;
		}
		/**
		 * @effect
		 * add marks for each enrolment
		 * @param student
		 * @param modules
		 * @param internalMark
		 * @param examinationMark 
		 */
		public void setMarks(Student student, Modules modules, double internalMark, double examinationMark) {
			Enrolment enrolment = getEnrolment(student, modules);
			enrolment.setInternalMark((float) internalMark);
			enrolment.setExaminationMark((float) examinationMark);
		}
		/**
		 * @effect
		 * display report in console screen
		 */
		public void report(){
			sort();
			String[] header = {"No","Student ID","Student name","Module code","Module name"};
			EasyTable table = new EasyTable(header);
		
		        try {
           
		        	for(Object obj: objects){
		        		int newRowIndex = table.addRow();
		        		Enrolment e = (Enrolment) obj;
		        		int a=1; 
		        		 table.setValueAt(a++, newRowIndex, 0);
		                   table.setValueAt(e.getStudent().getId(), newRowIndex, 1);
		                   table.setValueAt(e.getStudent().getName(), newRowIndex, 2);
		                   table.setValueAt(e.getModules().getCode(), newRowIndex, 3);
		                   table.setValueAt(e.getModules().getName(), newRowIndex, 4);
		        	}
		           
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        JScrollPane scroll = new JScrollPane(table);
			

			JFrame jFrame = new JFrame("List of the initial Enrollments");
			jFrame.setSize(600,400);
			jFrame.setLocation(300, 300);
			jFrame.setVisible(true);
			jFrame.add(scroll);
			
			


		}
		/**
		 * @effect
		 * display full report with mark
		 */
			public void reportAssessment() {
				sort();
				
				String[] header = {"No","Student ID","Module code","Internal mark","Exam mark","Final Grade"};
				EasyTable table = new EasyTable(header);
			
			        try {
			        	for(Object obj: objects){
			        		 int newRowIndex = table.addRow();
			        		Enrolment e = (Enrolment) obj;
			        		int a=1; 
			        		 table.setValueAt(a++, newRowIndex, 0);
			                   table.setValueAt(e.getStudent().getId(), newRowIndex, 1);
			                   table.setValueAt(e.getModules().getCode(), newRowIndex, 2);
			                   table.setValueAt(e.getInternalMark(), newRowIndex, 3);
			                   table.setValueAt(e.getExaminationMark(), newRowIndex, 4);
			                   table.setValueAt(e.getFinalGrade(), newRowIndex, 5);
			        	}
			        	
			        	
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        JScrollPane scroll = new JScrollPane(table);
				

				JFrame jFrame = new JFrame("List of the assessed Enrollments");
				jFrame.setSize(600,400);
				jFrame.setLocation(300, 300);
				jFrame.setVisible(true);
				jFrame.add(scroll);
			}
		/**
		 * @effect
		 * sort id student by descending order
		 */
			public void sort() {
				Collections.sort(list);				
			}


	@Override
	protected void createMiddlePanel() {
		//student
	    studentLable = new JLabel("Student");
		studentField = new JTextField();
		//module
	    moduleLable = new JLabel("Module");
	    moduleField = new JTextField();
	    //internalMark
	    internalMark = new JLabel("Internal Mark");
	    internalField = new JTextField();
	    //examMark
	    examMark = new JLabel("Exam Mark");
	    examField = new JTextField();
		
		//jpanel
        JPanel jPanelForMiddle = new JPanel();
        jPanelForMiddle.setLayout(new GridLayout(4,2));
        //add student
        jPanelForMiddle.add(studentLable);
        jPanelForMiddle.add(studentField);
        //add module
        jPanelForMiddle.add(moduleLable);
        jPanelForMiddle.add(moduleField);
        //add internalMark
        jPanelForMiddle.add(internalMark);
        jPanelForMiddle.add(internalField);
        //add examMark
        jPanelForMiddle.add(examMark);
        jPanelForMiddle.add(examField);
        
        gui.add(jPanelForMiddle);
	}
	
	@Override
	public void clearGUI() {
		clearGUI((JPanel) gui.getContentPane());
	}

	@Override
	public Object createObject() throws NotPossibleException {
		Enrolment enrolment =null;
		String messeage = "";
		if (moduleField.getText().trim().length()==0){
			messeage+="\n Module id can not be blank";
		}
		if (internalField.getText().trim().length()==0){
			messeage+="\n Internal Mark can not be blank";
		}
		if (examField.getText().trim().length()==0){
			messeage+="\n Exam Mark can not be blank";
		}
		if (messeage.length()!=0){
			throw  new NotPossibleException(messeage);
		}
		if (studentField.getText().trim().length()==0){
		messeage+="\n Student ID can not be blank";
		} else{		
			Student student = studentManager.getStudentByID(studentField.getText());
			Modules modules = moduleManager.getModuleByCode(moduleField.getText());
				enrolment = new Enrolment(student,modules);
				enrolment.setInternalMark(Float.parseFloat(internalField.getText()));
				enrolment.setExaminationMark(Float.parseFloat(examField.getText()));
				objects.add(enrolment);
		}

		displayMessage("Created an enrollment","Enrollment Create");
		
		return enrolment;
	}

	@Override
	public void save() {
		if(this.objects!=null){
			try {
				FileOutputStream fout = new FileOutputStream("Enrolment.dat");
				ObjectOutputStream oout = new ObjectOutputStream(fout);				
				Vector<Enrolment> obj = (Vector<Enrolment>) this.objects;
				for(Enrolment o : obj){		
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
				System.out.println("Start Up Enrolment");
				FileInputStream fin = new FileInputStream("Enrolment.dat");
				ObjectInputStream oin = new ObjectInputStream(fin);
		            ArrayList<Enrolment> LstEnrol = null;
		            LstEnrol = new ArrayList<Enrolment>();		
						int count = 0;
			             while(fin.available()>0){		            	 
			            	 Enrolment enrol = (Enrolment) oin.readObject();
			            	 LstEnrol.add(count,enrol);
			            	 count++;	        
			             }	      
			             for(Enrolment enrol : LstEnrol){
			            	 objects.add(enrol);
			             }
			}
	
			 catch (Exception e) {
				// TODO Auto-generated catch block
				 System.err.println("Can't open file or not found");
				 e.printStackTrace();
			}
		}
	}
}