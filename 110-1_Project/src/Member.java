
public class Member {

	// variables
	//
	protected String id, password, name;

	// constructors
	//
	/**
	 * Instantiate the object of Member with a given id, password and name.
	 * 
	 * @param id       The name of the member.
	 * @param password The password of the member.
	 * @param name     The name of the member.
	 */
	public Member(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}

	/**
	 * An empty constructor.
	 */
	public Member() {
	}

	// methods
	//
	/**
	 * Checking if the password inputed is the same as the data in the ArrayList
	 * named "members."
	 * 
	 * @param id       The id of the student.
	 * @param password The password inputed.
	 * @return Return "0" when login failed, "1" when login as teacher, "2" when
	 *         login as student.
	 */
	public byte login(String id, String password) {
		for (Member m : MemberList.members) {
			if (m.id.equals(id)) {
				if (m.password.equals(password)) {
					return (byte) (m instanceof Teacher ? 1 : (m instanceof Student) ? 2 : 0);
				}
			}
		}
		return 0;
	}

	/**
	 * Change the password of the student in the ArrayList named "members".
	 * 
	 * @param id          The id of the student.
	 * @param oldPassword The original password of the student.
	 * @param newPassword The new password that is going to replace the old one.
	 */
	public void changePassword(String id, String oldPassword, String newPassword) {
		if (login(id, oldPassword) != 0) {
			for (Member m : MemberList.members) {
				if (m.id.equals(id)) {
					m.password = newPassword;
				}
			}
		} else {
			System.out.println("舊密碼錯誤，系統將自動關閉");
			System.exit(0);
		}
	}

	/**
	 * Check the password if it conforms the situation below:
	 * 
	 * 1. longer than five characters 
	 * 
	 * 2. contains at least one English character 
	 * 
	 * 3. different from the old password 
	 * 
	 * 4. different from the id of the member
	 * 
	 * @param id          The id of the student.
	 * @param oldPassword The original password of the student.
	 * @param newPassword The new password that is going to replace the old one.
	 * @return Return "true" if the new password conforms all the situation, "false"
	 *         when not conform any of the above situation.
	 */
	public boolean checkPassword(String id, String oldPassword, String newPassword) {
		String checkResult = newPassword.length() < 5 ? "密碼長度需大於五個字元\n"
				: isNumber(newPassword) ? "密碼需包括英文字母與數字\n"
						: oldPassword.equals(newPassword) ? "新密碼與舊密碼不可相同\n"
								: newPassword.equals(id) ? "新密碼不可與編號相同\n" : "";
		System.out.print(checkResult);
		return checkResult.equals("") ? true : false;
	}

	/**
	 * Checking if the String is a number.
	 * 
	 * @param s The input String.
	 * @return Return "true" when input is a number, "false" when opposite.
	 */
	public boolean isNumber(String s) throws NumberFormatException {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
