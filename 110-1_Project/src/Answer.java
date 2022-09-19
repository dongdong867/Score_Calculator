import java.util.*;
import java.io.*;

public class Answer {

	// variables
	//
	private MemberList memberList = new MemberList();
	private ArrayList<Character> correctAnswer = new ArrayList<>();
	private ArrayList<String> result;
	private String subject;

	// methods
	//
	/**
	 * Setter of the subject and load the correct answer of the subject with the
	 * method named "load_correctAnswer()."
	 * 
	 * @param subject
	 * @throws IOException
	 */
	public void setSubject(String subject) throws IOException {
		this.subject = subject;
		this.load_correctAnswer();
	}

	/**
	 * Loading the correct answer by character into the ArrayList named
	 * "correctAnswer."
	 * 
	 * @throws IOException
	 */
	public void load_correctAnswer() throws IOException {
		int i;
		char c;
		try {
			InputStream reader = new FileInputStream(this.subject + "_answer.txt");
			while ((i = reader.read()) != -1) {
				c = (char) i;
				correctAnswer.add(c);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(0);
		}
	}

	/**
	 * Check the answers inputed with the ArrayList named "correctAnswer" and write
	 * the result into the file of the subject result.
	 * 
	 * The result contains the number of correct question, the number of the wrong
	 * question and the consequence which "-" with answer correct and the correct
	 * answer when wrong.
	 * 
	 * @param id      The id of the student who write the answer.
	 * @param answers The answer String with space between each answer.
	 * @throws IOException
	 */
	public void checkAnswer(String id, String answers) throws IOException {
		try {
			result = new ArrayList<>();
			Scanner reader = new Scanner(answers);
			FileWriter fw = new FileWriter(this.subject + "_result.txt", true);
			BufferedWriter writer = new BufferedWriter(fw);
			while (reader.hasNext()) {
				int correct = 0, wrong = 0;
				writer.write("學生座號: " + id + " 學生姓名: " + memberList.getName(id) + "\n");
				for (Character answer : correctAnswer) {
					char studentAnswer = reader.next().charAt(0);
					if (answer.equals(studentAnswer)) {
						result.add("-");
						correct++;
					} else {
						result.add(Character.toString(answer));
						wrong++;
					}
				}
				writer.write(String.format("答對題數: %d 答錯題數: %d 總分: %d\n作答狀況: ", correct, wrong,
						correct * 100 / (correct + wrong)));
				for (String answer : result) {
					writer.write(answer);
				}
				writer.write("\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(0);
		}
	}

}
