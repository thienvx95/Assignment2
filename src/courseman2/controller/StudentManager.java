package courseman2.controller;

import courseman2.NotPossibleException;
import courseman2.model.Student;

import javax.swing.*;
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
import java.util.Vector;

import java.util.HashMap;
import java.util.Objects;


public class StudentManager extends Manager{
    private JLabel titleLable, nameLable,dobLable,addressLable,emailLable;
    private JTextField nameField,dobField,addressField,emailField;
    protected HashMap<String,Student> StudentMap = new HashMap<String,Student>();

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
        nameLable = new JLabel("name");
        dobLable = new JLabel("dob");
        addressLable = new JLabel("Address");
        emailLable = new JLabel("Email");

        nameField = new JTextField();
        dobField = new JTextField();
        addressField = new JTextField();
        emailField = new JTextField();

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
        gui.add(jPanelForMiddle,BorderLayout.CENTER);
        
    }

    @Override
    public void clearGUI() {

        clearGUI((JPanel) gui.getContentPane());
    }

    @Override
    public Object createObject() throws NotPossibleException {
        Student student = null;
        StringBuilder messeage = new StringBuilder();
        boolean check = true;

        if(nameField.getText().trim().length()==0){
            messeage.append("\n Name field can not be blank");
            check= false;
        }
        if (dobField.getText().trim().length()==0){
            messeage.append("\nDob can not be blank");
            check= false;
        }
        if (addressField.getText().trim().length()==0){
            messeage.append("\nAddress can not be blank");
            check= false;
        }
        if (emailField.getText().trim().length()==0){
            messeage.append("\nEmail can not be blank");
            check= false;
        } 
        System.out.println(check);
        if (check){
            student = new Student(nameField.getText().trim(),dobField.getText().trim(),addressField.getText().trim(),emailField.getText().trim());
               objects.add(student);
        }

        if(messeage.length()>0){
            throw new NotPossibleException(messeage.toString());
        }
        displayMessage("Create a " + student.toString(),"Create a Student");
        return student;
    }

    @Override
    public void save() {
    	if(this.objects!=null){
    		System.out.println(this.objects.size());
			try {
				FileOutputStream fout = new FileOutputStream("Student.dat");
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				
				Vector<Student> obj = (Vector<Student>) this.objects;
				for(Student o : obj)
				
					oout.writeObject(o);
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
				FileInputStream fin = new FileInputStream("Student.dat");
				ObjectInputStream oin = new ObjectInputStream(fin);
				ArrayList<Student> LstStudent = new ArrayList<Student>();
				 System.out.println("Student Start Up");
					 Object obj = null;

				 while((obj = oin.readObject()) != null){					
					 objects.add(obj);					
				 }
			} catch (EOFException ex) {  //This exception will be caught when EOF is reached
	            System.out.println("End of file reached.");
	        }
	
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
