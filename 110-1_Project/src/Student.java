import java.io.*;

public class Student extends Member {

	// constructors
	//
	/**
	 * Instantiate the object of the Student with a given id, password and name.
	 * 
	 * @param id       The id of the student.
	 * @param password The password of the student.
	 * @param name     The name of the student.
	 */
	public Student(String id, String password, String name) {
		super(id, password, name);
	}

	/**
	 * An empty constructor.
	 */
	public Student() {
	}

	// methods
	//
	/**
	 * Inherit the method "changePassword()" in the superclass named "Member".
	 * Rewrite the student list with new password into the file named
	 * "students_info.txt".
	 * 
	 * @param id          The id of the student who is changing the password.
	 * @param oldPassword The old password of the student.
	 * @param newPassword The new password of the student.
	 */
	public void changePassword(String id, String oldPassword, String newPassword) {
		try {
			super.changePassword(id, oldPassword, newPassword);
			FileWriter fw = new FileWriter("students_info.txt", false);
			BufferedWriter studentWriter = new BufferedWriter(fw);
			for (Member m : MemberList.members) {
				if (m instanceof Student) {
					studentWriter.write(m.id + " " + m.password + " " + m.name + "\n");
				}
			}
			studentWriter.flush();
			studentWriter.close();
			fw.close();
			System.out.println("更改完成! 請重新登入");
		} catch (IOException e) {
			System.out.println("密碼更改失敗");
		}
		System.exit(0);
	}

}
