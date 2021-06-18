public class CapstoneProject1 {

	public static void main(String[] args) {

		/*
		 * Name:Marjia Ismail Date: 18 May 2021 Level:2 Task:24
		 */

		// This Java program will be built onto to create a project management system
		// for a company to keep track of different projects

		try {
			new UserInterface();
		} catch (Exception e) {
			// Not best practice
			// But catches all exceptions and lets the user know that an error occurred
			// Will be improved on in final iteration of project
			System.out.println("Error occured. Restarting program.\n");
			new UserInterface();

		}
	}

}