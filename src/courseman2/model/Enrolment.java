package courseman2.model;
import courseman2.NotPossibleException;

/** @overview An enrolment records a fact that a student has registered interest 
 * to study a specific module in a given semester.
 * @attributes 
 *   student Student
 *   modules Modules
 *   internalMark float
 *   examinationMark float
 *   finalGrade String
 * @object A typical Enrollment is c=<s--m--i--e--f>, where Student(s), Modules(m), internalMark(i),
 * examinationMark(e),finalGrade(f)
 */
public class Enrolment implements Comparable<Enrolment> {
	private Student student;
	private Modules modules;
	private float internalMark;
	private float examinationMark;
	private String finalGrade;
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
	public Enrolment(Student student, Modules modules){
			this.student = student;
			this.modules = modules;
	}
	
    /**
   * @return 
 * @effects return <tt>student</tt>
 */
	public Student getStudent(){
		return student;
	}
    /**
   * @param student
 * @effects <pre>
 *            if code is validate
 *              set this.student = student
 *            else
 *              throw NotPossibleException student</pre>
 */
	public void setStudent(Student student){
		if(validateStudent(student)){
			this.student = student;
		}
		else 
			throw new NotPossibleException("student");
	}
    /**
   * @return 
 * @effects return <tt>Modules</tt>
 */
	public Modules getModules(){
		return modules;
	}
    /**
   * @param modules
 * @effects <pre>
 *            if name is validate
 *              set this.name=name
 *            else
 *               throw NotPossibleException modules</pre>
 */
	public void setModules(Modules modules){
		if(validateModules(modules)){
			this.modules = modules;
		}
		else
			throw new NotPossibleException("modules");
	}
    /**
   * @return 
 * @effects return <tt>internalMark</tt>
 */
	public float getInternalMark(){
		return internalMark;
	}
    /**
   * @param internalMark
 * @effects <pre>
 *            if intermalMark is validate
 *              set this.internalMark = internakMark
 *            else
 *               throw NotPossibleException internalMark</pre>
 */
	public void setInternalMark(float internalMark){
		if(validateInternalMark(internalMark)){
				this.internalMark = internalMark;
		}
		else
			throw new NotPossibleException("Internal Mark");
	}
    /**
   * @return 
 * @effects return <tt>examinationMark</tt>
 */
	public float getExaminationMark(){
		return examinationMark;
	}
    /**
   * @param examinationMark
 * @effects <pre>
 *            if examinationMark is validate
 *              set this.examinationMark= examinationMark
 *            else
 *               throw NotPossibleException examinationMark</pre>
 */
	public void setExaminationMark(float examinationMark){
		if(validateExaminationMark(examinationMark)){
			this.examinationMark = examinationMark;
		}
		else
			throw new NotPossibleException("examinationMark");
	}
	/**
	 * @effect
	 * <pre>
	 * mark = 0.4*iMark + 0.6*eMark
	 * if(mark < 5) 
	 * fMark = "F"
	 * else if (5 == mark)
	 * fMark = "P"
	 * else if (6 <= mark && mark <=7)
	 * fMark = "G"
	 * else if (8 <= mark && mark <= 10)
	 * fMark = "E"
	 * </pre>
	 * @param eMark
	 * @param iMark
	 * @return 
	 */
		public String getFinalGrade() {
			float examinationMark = getExaminationMark();
			float internalMark = getInternalMark();
			int AvrMark = (int)(0.4*internalMark + 0.6*examinationMark);
			
			if(AvrMark < 5) 
				finalGrade = "F";
			else if (5 == AvrMark)
				finalGrade = "P";
			else if (6 <= AvrMark && AvrMark <=7)
				finalGrade = "G";
			else if (8 <= AvrMark && AvrMark <= 10){
				finalGrade = "E";
			}
			return finalGrade;
		}

	public boolean validateStudent(Student student){
		return student != null;
	}
	public boolean validateModules(Modules modules){
		return modules != null;
	}
	public boolean validateInternalMark(float internalMark){
		return internalMark >= 0 && internalMark <= 10 ;
	}
	public boolean validateExaminationMark(float examinationMark){
		return examinationMark >=0 && examinationMark <= 10;
	}
	@Override
	public String toString() {
		return "Enrolment: <["+ student.toString() + "]--[" + modules.toString() + "]--" + internalMark + "--" + examinationMark
				+ "--" + getFinalGrade() + ">";
	}
	public String toStringNoMark() {
		return "Enrolment: <["+ student.toString() + "]--[" + modules.toString() + "]>";
	}
	
	
	/**
	 * @effect
	 * <pre>
	 * result = this.getStudent().getId().compareTo(o.getStudent().getId()
	 * if (result == -1) {
	 * result = 1
	 * } else if (result == 1) {
	 * result = -1
	 * </pre>
	 * @param o
	 * @return 
	 */
		@Override
		public int compareTo(Enrolment o) {
			int result = this.getStudent().getId().compareTo(o.getStudent().getId());
			if (result == -1) {
				result = 1;
			} else if (result == 1) {
				result = -1;
			}
			return result;
		}
}
