import java.io.*;
import java.util.*;

public class MemberList {

	// variables
	//
	protected static ArrayList<Member> members = new ArrayList<>();

	// methods
	//
	/**
	 * Loading the information of students and teachers in the file named as
	 * "students_info.txt" and "teachers_info.txt" into the system.
	 * 
	 * @throws IOException
	 */
	public void load() throws IOException {
		try {
			Scanner studentReader = new Scanner(new File("students_info.txt"));
			while (studentReader.hasNext()) {
				members.add(new Student(studentReader.next(), studentReader.next(), studentReader.next()));
			}
			studentReader.close();
			Scanner teacherReader = new Scanner(new File("teachers_info.txt"));
			while (teacherReader.hasNext()) {
				members.add(new Teacher(teacherReader.next(), teacherReader.next(), teacherReader.next()));
			}
			teacherReader.close();
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("檔案載入錯誤，系統自動結束");
			System.exit(0);
		}
	}

	/**
	 * Getter of the Member's name.
	 * 
	 * @param id The id to find the name.
	 * @return Return the name of the Member with the id inputed.
	 */
	public String getName(String id) {
		for (Member m : members) {
			if (m.id.equals(id)) {
				return m.name;
			}
		}
		return null;
	}

}
