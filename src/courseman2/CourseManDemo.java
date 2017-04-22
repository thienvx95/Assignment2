package courseman2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import courseman2.controller.EnrolmentManager;
import courseman2.controller.LogoHelper;
import courseman2.controller.ModuleManager;
import courseman2.controller.StudentManager;

import static java.awt.Font.CENTER_BASELINE;
import static java.awt.Font.SERIF;
import static java.awt.SystemColor.text;

/**
 * @overview Represents the main class of the CourseMan program. 
 *   
 * @attributes
 *  initial       String
 *  sman          StudentManager
 *  mman          ModuleManager
 *  emain         EnrolmentManager
 *  lhelper       CourseManDemo.LogoHelper
 *  mainGUI       JFrame
 *    
 * @abstract_properties
 * <pre>
 *   optional(initial) = false /\ 
 *   optional(sman) = false /\
 *   optional(mman) = false /\
 *   optional(eman) = false /\
 *   optional(lhelper) = false /\  
 *   optional(mainGUI) = false /\ 
 * </pre>  
 * 
 * @author dmle
 */
public class CourseManDemo implements ActionListener {
  /** the initial used as logo */
  @DomainConstraint(type=DomainConstraint.Type.String,optional=false)
  private String initial;
  
  /**the student manager*/
  @DomainConstraint(type=DomainConstraint.Type.UserDefined,optional=false)
  private StudentManager sman;
  
  /**the module manager*/
  @DomainConstraint(type=DomainConstraint.Type.UserDefined,optional=false)
  private ModuleManager mman;
  
  /**the enrolment manager*/
  @DomainConstraint(type=DomainConstraint.Type.UserDefined,optional=false)
  private EnrolmentManager eman;
  
  /**the logo helper used for creating a visual effect on the logo */
  @DomainConstraint(type=DomainConstraint.Type.UserDefined,optional=false)
  private LogoHelper lhelper;
  
  /**the main GUI window*/
  @DomainConstraint(type=DomainConstraint.Type.Object,optional=false)
  private JFrame mainGUI;

  // constructor method
  /**
   * @effects 
   *  initialise this with initial
   *  {@link #createGUI()}: create mainGUI
   *  initialise sman, mman, eman such that their (x,y) locations are each 50 pixels higher
   *    those of mainGUI
   *  initialise lhelper  
   */
  public CourseManDemo(String initial) throws InterruptedException {
		this.initial = initial;
	    createGUI();
	    sman = new StudentManager("Manage Student", "Enter Student Details", 500, 300, 300, 200);
	    mman = new ModuleManager("Manage Modules", "Enter Modules Details",500, 300, 300, 200);
	    eman = new EnrolmentManager("Manage Enrolment", "Enter Enrolment Details",500, 300, 300, 200,mman,sman);
	  
  }

  /**
   * @modifies this
   * @effects create mainGUI that has a menu bar with:
   *   
   *   File menu has two items: Save and Exit
   *   Tools has three menu items for the three data management functions
   *   Reports: has the two reporting functions
   *   {@link #createLogoPanel(JMenuBar, String)}: a logo panel containing a 
   *          logo label at the far end of the menu bar
   *   
   *     
   *   The action listener of the menu items is this.
   */
  protected void createGUI() throws InterruptedException {
	  mainGUI = new JFrame("Program CourseManDemo");
	  mainGUI.setSize(500, 100);
	  mainGUI.setLocation(300, 200);
	  mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	  
	  JMenuBar MenuBar = new JMenuBar();
	
	  //FILE
	  JMenu File = new JMenu("File");
	  JMenuItem Save = new JMenuItem("Save");
	  Save.addActionListener(this);
	  JMenuItem Exit = new JMenuItem("Exit");
	  Exit.addActionListener(this);
	  File.add(Save);
	  File.add(Exit);
	  //Tools
	  JMenu Tools = new JMenu("Tools");
	  JMenuItem Students = new JMenuItem("Manage Students");
	  Students.addActionListener(this);
	  
	  JMenuItem Modules = new JMenuItem("Manage Modules");
	  Modules.addActionListener(this);
	  
	  JMenuItem Enrolments = new JMenuItem("Manage Enrolments");
	  Enrolments.addActionListener(this);
	  
	  Tools.add(Students);
	  Tools.add(Modules);
	  Tools.add(Enrolments);
	  //Report
	  JMenu Reports = new JMenu("Report");
	  JMenuItem iniEnrol = new JMenuItem("Initial enrolment report");
	  JMenuItem assEnrol = new JMenuItem("Assessed report");
	  iniEnrol.addActionListener(this);
	  assEnrol.addActionListener(this);
	  Reports.add(iniEnrol);
	  Reports.add(assEnrol);
	  
	  MenuBar.add(File);
	  MenuBar.add(Tools);
	  MenuBar.add(Reports);
	  MenuBar.add(Box.createHorizontalGlue());

	  //LogoHelper
	  createLogoPanel(MenuBar,initial);
	  
	  mainGUI.setJMenuBar(MenuBar);


  }

  /**
   * @effects 
   *  create a label panel containing a decorated JLabel whose text is 
   *  initial. The decoration must use the following settings:
   *  
   *    background colour: orange
   *    foreground colour: blue
   *    font: Serif, bold, 18 points
   *    size: height=20, wide enough to fit the text
   *    alignment: center
   *    focusable: false 
   *  
   *  
   *  add the label panel to the menu bar mb so that it appears at the far end.
   *
   *  The logo text must have the "appearing" effect.
   */
  private void createLogoPanel(JMenuBar mb, String initial) throws InterruptedException {
	  JLabel text = new JLabel(initial,JLabel.CENTER);

	  text.setOpaque(true);
	  new Thread(new Runnable() {
		  @Override
		  public void run() {
			  while (true){

				  try {
					  Thread.sleep(1000);
					  text.setBackground(Color.orange);
					  text.setForeground(Color.BLUE);


					  Thread.sleep(1000);
					  text.setBackground(Color.white);
					  text.setForeground(Color.black);
				  } catch (InterruptedException e) {
					  e.printStackTrace();
					  break;
				  }

			  }
		  }
	  }).start();

	  text.setFont(new Font("Serif",1, 18));
	  text.setSize(10, 20);
	  text.setAlignmentX(1);
	  text.setFocusable(false);
	  mb.add(text);

  }
  
  /**
   * @effects save data objects managed by sman, mman and eman to files 
   */
  public void save(){
	  sman.save();
	  mman.save();
	  eman.save();
  }

  /**
   * @effects 
   *  start up sman, mman, eman 
   *  start lhelper
   */
  public void startUp(){
	  sman.startUp();
	  mman.startUp();
	  eman.startUp();
  }

  /**
   * @effects shut down sman, mman, eman 
   *          dispose mainGUI and end the program. 
   */
  public void shutDown(){
	  mainGUI.dispose();
  }

  /**
   * @effects show mainGUI 
   */
  public void display(){
	  mainGUI.setVisible(true);
  }
  /**
   * @effects handles user actions on the menu items
   *          <pre>
   *          if menu item is Tools/Manage students
   *            {@link #sman}.display()}
   *          else if menu item is Tools/Manage modules  
   *            {@link #mman}.display()
   *          else if menu item is Tools/Manage enrolments
   *            {@link #eman}.display()
   *          else if menu item is Reports/Initial enrolment report
   *            {@link #eman}.report()
   *          else if menu item is Reports/Assessment report
   *            {@link #eman}.reportAssessment()
   *          else if menu item is File/Save
   *            {@link #save()}
   *          else if menu item is File/Exit 
   *            {@link #shutDown()}
   *          </pre>
   */
  public void actionPerformed(ActionEvent e) {
	  String actions = e.getActionCommand();
	  if(actions.equals("Manage Students")){
		  sman.display();
	  }else if(actions.equals("Manage Modules")){
		  mman.display();
	  }else if (actions.equals("Manage Enrolments")){
		  eman.display();
	  }else if (actions.equals("Initial enrolment report")){
		  eman.report();
	  }else if (actions.equals("Assessed report")){
		  eman.reportAssessment();
	  }else if (actions.equals("Save")){
		  save();
		  JOptionPane.showMessageDialog(mainGUI, "Saved", "Program CourseManDemo", JOptionPane.INFORMATION_MESSAGE);
	  }else if (actions.equals("Exit")){
		  shutDown();
	  }

  }
  /**
   * The run method
   * @effects 
   *  initialise an initial 
   *  create an instance of CourseManDemo from the initial 
   *  {@link #startUp()}: start up the CourseManDemo instance
   *  {@link #display()}: display the main gui of CourseManDemo instance 
   */
  public static void main(String[] args) throws InterruptedException {
    final String initial = "VXT - TMT";
    CourseManDemo app = new CourseManDemo(initial);

    app.startUp();
    app.display();
  }
}
