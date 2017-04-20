package courseman2.controller;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
			String[] header = {"No","Student","Student name","Module code","Module name"};
			List<List> list_return = new ArrayList();
			int i=1;
			for(Enrolment enrolment : list) {
				List listtmp = new ArrayList();
				listtmp.add(i);
				i++;
				listtmp.add(enrolment.getStudent().getId());
				listtmp.add(enrolment.getStudent().getName());
				listtmp.add(enrolment.getModules().getCode());
				listtmp.add(enrolment.getModules().getName());
				list_return.add(listtmp);
			}
			EasyTable table = new EasyTable(list_return,header);

			JFrame jFrame = new JFrame("List of the initial Enrollments");
			jFrame.getContentPane().add(table.getTableHeader(), BorderLayout.PAGE_START);
			jFrame.setLayout(new BorderLayout());
			jFrame.getContentPane().add(table,BorderLayout.CENTER);
			jFrame.setSize(600,400);
			jFrame.setLocationRelativeTo(null);
			jFrame.setVisible(true);
			gui.add(jFrame);
//			JFrame jFrame = new JFrame("List of the initial Enrollments");
//			jFrame.getContentPane().add(table.getTableHeader(), BorderLayout.PAGE_START);
//			jFrame.setLayout(new BorderLayout());
//			jFrame.getContentPane().add(table,BorderLayout.CENTER);
//			jFrame.setSize(600,400);
//			jFrame.setLocationRelativeTo(null);
//			jFrame.setVisible(true);

		}
		/**
		 * @effect
		 * display full report with mark
		 */
			public void reportAssessment() {
				sort();
				String[] header = {"No","Student ID","Module Code","Internal Mark","Exam Mark","Final Grade"};
				List<List> list_return = new ArrayList();
				int i=1;
				for(Enrolment enrolment : list) {
					List tmpList = new ArrayList();
					tmpList.add(i);
					i++;
					tmpList.add(enrolment.getStudent().getId());
					tmpList.add(enrolment.getModules().getCode());
					tmpList.add(enrolment.getInternalMark());
					tmpList.add(enrolment.getExaminationMark());
					tmpList.add(enrolment.getFinalGrade());
					list_return.add(tmpList);
				}
				EasyTable table = new EasyTable(list_return,header);
//				JFrame jFrame = new JFrame("List of the assessed enrollments");
//				jFrame.setLayout(new BorderLayout());
//				jFrame.getContentPane().add(table.getTableHeader(),BorderLayout.PAGE_START);
//				jFrame.getContentPane().add(table,BorderLayout.CENTER);
//				jFrame.setLocationRelativeTo(null);
//				jFrame.setSize(600,400);
//				jFrame.setVisible(true);
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
		if (studentField.getText().trim().length()==0){
			messeage+="\n Student ID can not be blank";
		}
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
		}else {
			enrolment = new Enrolment(
					studentManager.StudentMap.get(studentField.getText().trim()),
							moduleManager.ModuleMap.get(moduleField.getText().trim()));
			displayMessage("Created an enrollment","Enrollment Create");
		}
		return enrolment;
	}

	@Override
	public void save() {

	}

	@Override
	public void startUp() {

	}
}