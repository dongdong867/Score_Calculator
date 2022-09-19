import java.util.*;

public class Project {

	public static void main(String args[]) throws Exception {

		MemberList memberList = new MemberList();
		memberList.load();
		Member m = new Member();
		//
		// login
		Scanner scan = new Scanner(System.in);
		byte x = 0;
		String id = null, password = null;
		while (true) {
			System.out.print("請輸入帳號: ");
			id = scan.nextLine();
			System.out.print("請輸入密碼: ");
			password = scan.nextLine();
			String y = id.length() == 0 ? (password.length() == 0 ? "帳號密碼不得為空\n" : "帳號不得為空\n")
					: password.length() == 0 ? "密碼不得為空\n" : "";
			System.out.print(y);
			if (y.equals("")) {
				x = m.login(id, password);
				if (x != 0) {
					String loginResult = x == 1 ? memberList.getName(id) + " 老師 成功登入"
							: x == 2 ? memberList.getName(id) + " 成功登入" : "";
					System.out.println(loginResult);
					break;
				} else {
					System.out.println("帳號密碼錯誤");
				}
			}
		}
		//
		// login as teacher
		if (x == 1) {
			while (true) {
				System.out.println("請輸入欲執行功能：建檔、批改、查詢、更改密碼 或輸入 \"end\" 以結束程式");
				String work = scan.next();
				switch (work) {
				// create profile of new Student
				case "建檔":
					Teacher t = new Teacher();
					System.out.println("請輸入學生編號與姓名，輸入 \"end\" 以返回主選單");
					do {
						String ID = scan.next();
						if (ID.equals("end")) {
							System.out.println("返回主選單");
							break;
						} else {
							String name = scan.next();
							System.out.println("請輸入學生預設密碼(至少五個位元)");
							String newPassword = scan.next();
							while (newPassword.length() < 5) {
								System.out.println("密碼需至少五個位元，請重新輸入");
								newPassword = scan.next();
							}
							t.input(ID, newPassword, name);
							System.out.println("建檔成功\n請輸入學生編號與姓名，輸入 \"end\" 以返回主選單");
						}
					} while (scan.hasNext());
					break;
				// check answers
				case "批改":
					Answer a = new Answer();
					System.out.println("請輸入科目：");
					a.setSubject(scan.next());
					System.out.println("請輸入學生編號與學生答案，輸入 \"end\" 以返回主選單");
					do {
						String ID = scan.next();
						if (ID.equals("end")) {
							System.out.println("返回主選單");
							break;
						} else {
							a.checkAnswer(ID, scan.nextLine());
							System.out.println("新增成功\n請輸入學生編號與學生答案，輸入 \"end\" 以返回主選單");
						}
					} while (scan.hasNext());
					break;
				// find the score and the result
				case "查詢":
					Find f = new Find();
					System.out.println("請輸入欲查詢科目");
					f.setSubject(scan.next());
					System.out.println("請輸入欲查詢學生編號，輸入 \"end\" 以返回主選單");
					while (scan.hasNext()) {
						String ID = scan.next();
						if (ID.equals("end")) {
							System.out.println("返回主選單");
							break;
						} else {
							f.find(ID);
							System.out.println("請輸入欲查詢學生編號，輸入 \"end\" 以返回主選單");
						}
					}
					break;
				// change the password
				case "更改密碼":
					Teacher teacher = new Teacher();
					String oldPassword, newPassword;
					do {
						System.out.println("請輸入舊密碼");
						oldPassword = scan.next();
						System.out.println("請輸入新密碼(至少五個位元 且包含英文字母)");
						newPassword = scan.next();
					} while (!m.checkPassword(id, oldPassword, newPassword));
					teacher.changePassword(id, oldPassword, newPassword);
					// exit the system
				case "end":
					System.out.println("系統關閉");
					System.exit(0);
					// wrong input
				default:
					System.out.println("輸入錯誤 請重新輸入");
				}
			}
		}
		//
		// to do for students
		if (x == 2) {
			System.out.println("請選擇欲執行功能：查詢、更改密碼 或輸入 \"end\" 以結束程式");
			String work = scan.next();
			switch (work) {
			// find the score and the answer result
			case "查詢":
				Find f = new Find();
				while (true) {
					System.out.println("請輸入欲查詢科目");
					String subject = scan.next();
					if (subject.equals("end")) {
						System.out.println("系統結束");
						System.exit(0);
					} else {
						f.setSubject(subject);
						f.find(id);
					}
				}
				// change the password
			case "更改密碼":
				Student s = new Student();
				String oldPassword, newPassword;
				do {
					System.out.println("請輸入舊密碼");
					oldPassword = scan.next();
					System.out.println("請輸入新密碼(至少五個位元 且包含英文字母)");
					newPassword = scan.next();
				} while (!m.checkPassword(id, oldPassword, newPassword));
				s.changePassword(id, oldPassword, newPassword);
				// exit the system
			case "end":
				System.out.println("系統結束");
				System.exit(0);
				// wrong input
			default:
				System.out.println("輸入錯誤 請重新輸入");
			}
		}
		scan.close();
	}
}
