import java.io.*;

public class Teacher extends Member {

	// constructors
	//
	/**
	 * Instantiate the object of Teacher with a given id, password and name.
	 * 
	 * @param id       The id of the Teacher.
	 * @param password The password of the Teacher.
	 * @param name     The name of the Teacher.
	 */
	public Teacher(String id, String password, String name) {
		super(id, password, name);
	}

	/**
	 * An empty constructor.
	 */
	public Teacher() {
	}

	// methods
	//
	/**
	 * Input a Student with a given id, password and name into the ArrayList named
	 * "members" and write the new Student into the file named "students_info.txt".
	 * 
	 * @param id       The id of the new Student.
	 * @param password The password of the new Student.
	 * @param name     The name of the new Student.
	 * @throws IOException
	 */
	public void input(String id, String password, String name) throws IOException {
		try {
			FileWriter fw = new FileWriter("students_info.txt", true);
			fw.write(id + " " + password + " " + name + "\n");
			fw.close();
			MemberList.members.add(new Student(id, password, name));
		} catch (IOException e) {
			System.out.println("檔案寫入錯誤。");
			System.exit(0);
		}
	}

	/**
	 * Inherit the method "changePassword()" in the superclass named "Member".
	 * Rewrite the teacher list with new password into the file named
	 * "teachers_info.txt".
	 * 
	 * @param id          The id of the teacher who is changing the password.
	 * @param oldPassword The old password of the Teacher.
	 * @param newPassword The new password of the Teacher.
	 */
	public void changePassword(String id, String oldPassword, String newPassword) {
		try {
			super.changePassword(id, oldPassword, newPassword);
			FileWriter fw = new FileWriter("teachers_info.txt", false);
			BufferedWriter tWriter = new BufferedWriter(fw);
			for (Member m : MemberList.members) {
				if (m instanceof Teacher) {
					tWriter.write(m.id + " " + m.password + " " + m.name + "\n");
				}
			}
			tWriter.flush();
			tWriter.close();
			fw.close();
			System.out.println("更改完成! 請重新登入");
		} catch (IOException e) {
			System.out.println("檔案寫入錯誤。");
		}
		System.exit(0);
	}

}
