	package courseman2.model;

import java.io.Serializable;

import courseman2.NotPossibleException;

/** @overview The purpose of this program is to allow the students  of a faculty of a
 *  university to enrol into pre-defined course modules each semester, and to allow the 
 *  administrative staff of the faculty to enter the module marks for each enrolment
 *   and to compute the final grade from these marks
 * @attributes 
 *   id    String
 *   name  String   
 *   dob String
 *   address String
 *   email String
 * @object A typical Student is c=<i--n--d--a--e>, where id(d), name(n), dob(d), address(a),
 * 	email(e)
  * @abstract_properties
 * mutable(id)=false/\optional(id)=false
 * mutable(name)=true/\optional(name)=false
 * mutable(address)=true/\optional(address)=false
 * mutable(email)=true/\optional(email)=false
 */

public class Student implements Serializable{
	
	private String id;
	private String name;
	private String dob;
	private String address;
	private String email;
	private static int year = 2016;
	
	 /**
     * @param id
     * @param name
     * @param dob
     * @param address
     * @param email
   * @effects <pre>
   *            if ID, name ,dob, address, email are valid
   *              initialise this as <Id,name,dob,address,email>
   *            else
   *               throws NotPossibleException </pre>
   */
	public Student(String name, String dob, String address, String email){
		if(repOk(name,dob,address,email)){
			year= year +1;
			this.id = "S" + (year);
			this.name = name;
			this.dob = dob;
			this.address = address;
			this.email = email;
	
		}
		else 
			throw new NotPossibleException("student");  
	}
	
    /**
   * @return 
 * @effects return <tt>id</tt>
 */
	public String getId(){
		return id;
	}
    /**
   * @return 
 * @effects return <tt>name</tt>
 */
	public String getName(){
		return name;
	}
    /**
   * @param name
 * @effects <pre>
 *            if name is valid
 *              set this.name=name
 *            else
 *               throw NotPossibleException name</pre>
 */
	public void setName(String name){
		if(validateName(name)){
			this.name = name;
		}
		else
			throw new NotPossibleException("name");
	}
    /**
   * @return 
 * @effects return <tt>dob</tt>
 */
	public String getDob(){
		return dob;
	}
    /**
   * @param dob
 * @effects <pre>
 *            if dob is valid
 *              set this.dob= dob
 *            else
 *               throw NotPossibleException name</pre>
 */
	public void setDob(String dob){
		if(validateDob(dob)){
				this.dob=dob;
		}
		else
			throw new NotPossibleException("dob");
	}
    /**
   * @return 
 * @effects return <tt>address</tt>
 */
	public String getAddress(){
		return address;
	}
    /**
   * @param dob
 * @effects <pre>
 *            if address is valid
 *              set this.address= address
 *            else
 *               throw NotPossibleException name</pre>
 */
	public void setAddress(String address){
		if(validateAddress(address)){
			this.address=address;
		}
		else
			throw new NotPossibleException("address");
	}
    /**
   * @return 
 * @effects return <tt>email</tt>
 */
	public String getEmail(){
		return email;
	}
    /**
   * @param email
 * @effects <pre>
 *            if email is valid
 *              set this.email = email
 *            else
 *               throw NotPossibleException name</pre>
 */
	public void setEmail(String email){
		if(validateEmail(email)){
			this.email=email;
		}
		else
			throw new NotPossibleException("email");
	}
	public boolean validateName(String name){
		return name != null;
	}
	public boolean validateDob(String dob){
		return dob != null;
	}
	public boolean validateAddress(String address){
		return address != null;
	}
	public boolean validateEmail(String email){
		return email != null;
	}
	public boolean repOk(String name, String dob, String address, String email){
		if(validateName(name) && validateDob(dob) && validateAddress(address) && validateEmail(email)){
			return true;
		}
		else 
			return false;
	}
	public String toString() {
		return "Student (" +id + "," + name + "," + dob + "," + address + "," + email+ ")";
	}
	
	
	
}
