import java.io.*;
import java.util.*;

public class Find {

	// variables
	//
	private ArrayList<String> score;
	private String subject;

	// methods
	//
	/**
	 * Setter of the subject and load the test result of the subject into the
	 * ArrayList named "score" with the method "load()."
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		score = new ArrayList<>();
		this.subject = subject;
		this.load();
	}

	/**
	 * Loading the test result of given subject into the ArrayList named "score"
	 */
	public void load() {
		try {
			Scanner scanner = new Scanner(new File(this.subject + "_result.txt"));
			while (scanner.hasNext()) {
				scanner.next();
				score.add(scanner.next());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("查無該該科目");
			score.clear();
		}
	}

	/**
	 * Find the student with the id. Print the information in the subject result
	 * file.
	 * 
	 * @param id The students's id.
	 */
	public void find(String id) {
		String result = score.indexOf(id) == -1 ? "查無該資料"
				: "學生編號: " + id + " 學生姓名: " + score.get(score.indexOf(id) + 1) + "\n答對/答錯: "
						+ score.get(score.indexOf(id) + 2) + "/" + score.get(score.indexOf(id) + 3) + " 總分: "
						+ score.get(score.indexOf(id) + 4) + "\n" + score.get(score.indexOf(id) + 5);
		System.out.println(result);
	}
}
