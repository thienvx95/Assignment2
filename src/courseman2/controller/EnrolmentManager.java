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
				JFrame jFrame = new JFrame("List of the assessed enrollments");
				jFrame.setLayout(new BorderLayout());
				jFrame.getContentPane().add(table.getTableHeader(),BorderLayout.PAGE_START);
				jFrame.getContentPane().add(table,BorderLayout.CENTER);
				jFrame.setLocationRelativeTo(null);
				jFrame.setSize(600,400);
				jFrame.setVisible(true);
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
	JFrame jFrame = new JFrame();
	jFrame.setLayout(new GridLayout(3,1));
	JPanel jPanelforTitle = new JPanel();
	JPanel jPanelforMiddle = new JPanel();
	JPanel jPanelforButton = new JPanel();
    title = new JLabel();
    title.setText("Enter enrollment details");

    studentLable = new JLabel();
    studentLable.setText("Student");
	studentField = new JTextField();

    moduleLable = new JLabel();
    moduleLable.setText("Module");
    moduleField = new JTextField();


    internalMark = new JLabel();
    internalMark.setText("Internal Mark");
    internalField = new JTextField();

    examMark = new JLabel();
    examMark.setText("Exam Mark");
    examField = new JTextField();

    okbutton = new JButton();
    okbutton.setText("Ok");
    okbutton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		}

	});
    cancleButton = new JButton();
    cancleButton.setText("Cancle");
    cancleButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			btnCancleClick(e);
		}

	});
    jPanelforTitle.add(title);
    jPanelforMiddle.setLayout(new GridLayout(4,2));
    jPanelforMiddle.add(studentLable);
    jPanelforMiddle.add(studentField);
    jPanelforMiddle.add(moduleLable);
    jPanelforMiddle.add(moduleField);
    jPanelforMiddle.add(internalMark);
    jPanelforMiddle.add(internalField);
    jPanelforMiddle.add(examMark);
    jPanelforMiddle.add(examField);
	jPanelforButton.add(okbutton);
	jPanelforButton.add(cancleButton);
	jFrame.add(jPanelforTitle);
	jFrame.add(jPanelforMiddle);
	jFrame.add(jPanelforButton);


	}
	private void btnOkClicked(ActionEvent event){
				boolean ExamIsNum = true;
				boolean InternalMarkisNum = true;
				StringBuilder stb = new StringBuilder();
				Modules modules = null;
				Student student =null;
				if(examField.getText().trim().length()==0){
					String tmp = "\nWrong input value: Exam mark can not be blank";
					stb.append(tmp);
				}else {
					for (int i=0;i<examField.getText().trim().length();i++){
						if (Character.isLetter(examField.getText().charAt(i))){
							ExamIsNum =false;
							break;
						}
					}
				}
		        if (internalField.getText().trim().length()==0){
					String tmp = "\nWrong input value:Internal mark can not be blank";
					stb.append(tmp);
				}else {
		        	for (int i=0;i<internalField.getText().trim().length();i++){
		        		if (Character.isLetter(internalField.getText().trim().charAt(i))){
		        			InternalMarkisNum= false;
		        			break;
						}
					}
				}
                if (stb.length()>0){
		        	super.displayErrorMessage(stb.toString(),"Create an enrollment");
				}else{
				}
	}
    private void btnCancleClick(ActionEvent event){
				this.exit();
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