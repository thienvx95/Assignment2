package courseman2.model;

import java.io.Serializable;

/**
 * @overview Modules 
 * @object A typical compulsoryModules is c=<c--n--s--c>, where code(c), name(n), semester(s), credits(c)
 */	
public class CompulsoryModules extends Modules implements Serializable{


	/**
	 * @effect
	 * <pre>
	 * inherit attributes from module class
	 * </pre>
	 * @param name
	 * @param semester
	 * @param credit 
	 */	
	public CompulsoryModules(String name, int semester, int credits){
		super(name,semester,credits);	
	}
	@Override
	public String toString() {
		return "Compulsory Modules :<" +getCode() + "--" + getName() + "--" + getSemester() + "--" + getCredits() + ">";
	}
}
