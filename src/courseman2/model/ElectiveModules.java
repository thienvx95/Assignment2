package courseman2.model;

import java.io.Serializable;

import courseman2.NotPossibleException;

/**
 * @overview create object inherit from module and add extra attribute department name
 * @attributes
 * departmentName String
 * @object A typical compulsoryModules is c=<c--n--s--c--d>, where code(c), name(n), semester(s), credits(c)
 * ,departmentName(d)
 * @abstract_properties
 * mutable(departmentName)=true/\optional(departmentName)=false
 */
public class ElectiveModules extends Modules implements Serializable{
	
	private String departmentName;
	
	/**
	 * @effect
	 * <pre>
	 * inherit all attributes from module
	 * add departmentName
	 * this.departmentName = departmentName
	 * </pre>
	 * @param name
	 * @param semester
	 * @param credit
	 * @param departmentName 
	 */
	
	public ElectiveModules(String name, int semester, int credit, String departmentName) {
		super(name, semester, credit);
		this.departmentName = departmentName;
	}
/**
 * @effect
 * <pre>
 * return "EModule [departmentName=" + departmentName + "]"
 * </pre>
 * @return 
 */
	
	public String getDepartmentName(){
		return departmentName;
	}
	public void setDepartmentName(String departmentName){
		if(validate(departmentName)){
			this.departmentName = departmentName;
		}
		else throw new NotPossibleException("department Name");
	}
	public boolean validate(String departmentName){
		return departmentName != null;
	}
	@Override
	public String toString() {
		return "Elective Modules :<" + getCode() + "--" + getName() + "--" + getSemester() + "--" + getCredits() +"--" +departmentName+ ">";
	}
}
