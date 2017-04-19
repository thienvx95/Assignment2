package courseman2.model;
import java.io.Serializable;
import java.util.HashMap;

import courseman2.NotPossibleException;

/** @overview Modules 
 * @attributes 
 *   code    String
 *   name  String   
 *   semester integral
 *   credits integral
 * @object A typical Modules is c=<c--n--s--c>, where code(c), name(n), semester(s), credits(c)
 * @abstract_properties
 * mutable(code)=false/\optional(code)=false
 * mutable(name)=true/\optional(name)=false
 * mutable(semester)=true/\optional(semester)=false
 * mutable(credit)=true/\optional)credit)=false
 */
public class Modules implements Serializable{
	private String code;
	private String name;
	private int semester;
	private int credits;
	private static HashMap<Integer, Integer> map = new HashMap<>();

	/**
	 * @param code
	 * @param name
	 * @param semester
	 * @param credits
	 * @effects <pre>
	 *            if code, name ,semester, credits are validate
	 *              initialise this as <code,name,semester,credits>
	 *            else
	 *               throws NotPossibleException </pre>
	 */
	public Modules(String name, int semester, int credits) {
		if (repOk(name,semester,credits)){
			if (!map.containsKey(semester)) {
				map.put(semester, 1);
				this.code = "M" + (semester * 100 + 1);
			} else {
				int semesterCount = map.get(semester);
				map.put(semester, semesterCount + 1);
				this.code = "M" + (semester * 100 + semesterCount + 1);
			}

			this.name = name;
			this.semester = semester;
			this.credits = credits;
		} else {
			throw new NotPossibleException("Modules");
		}
	}
	
    /**
   * @return 
 * @effects return <tt>code</tt>
 */
	public String getCode(){
		return code;
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
 *            if name is validate
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
 * @effects return <tt>semester</tt>
 */
	public int getSemester(){
		return semester;
	}
    /**
   * @param semester
 * @effects <pre>
 *            if semester is validate
 *              set this.semester= semester
 *            else
 *               throw NotPossibleException semester</pre>
 */
	public void setSemester(int semester){
		if(validateSemester(semester)){
				this.semester=semester;
		}
		else
			throw new NotPossibleException("semester");
	}
    /**
   * @return 
 * @effects return <tt>credits</tt>
 */
	public int getCredits(){
		return credits;
	}
    /**
   * @param credits
 * @effects <pre>
 *            if credits is validate
 *              set this.credits= credits
 *            else
 *               throw NotPossibleException credits</pre>
 */
	public void setCredits(int credits){
		if(validateCredits(credits)){
			this.credits=credits;
		}
		else
			throw new NotPossibleException("credits");
	}
 
	public boolean validateName(String name){
		return name != null;
	}
	public boolean validateSemester(int semester){
		return semester > 0 ;
	}
	public boolean validateCredits(int credits){
		return credits > 0;
	}
	public boolean repOk(String name, int semester, int credits){
		if(validateName(name) && validateSemester(semester) && validateCredits(credits) ){
			return true;
		}
		else 
			return false;
	}
	@Override
	public String toString() {
		return "Modules :<" + code + "--" + name + "--" + semester + "--" + credits +">";
	}
	
	
	
}
