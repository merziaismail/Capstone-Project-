import java.util.Scanner;

public class CapstoneProject1 {

	public static void main(String[] args) {
		
		/* 
		 * Name:Marjia Ismail
		 * Date: 24 April 2021
		 * Level:2
		 * Task:7 
		 * */
		
		
		//This Java program will be built onto to create a project management system for a company to keep track of different projects 
		
		
		Project project=new Project();
		
		//Create scanner to read input
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Welcome to the Capstone project creater!");
		
		//While loop to continue to as the user to enter options
		while (true){
			System.out.println("\n1- Capture new project details.\n2-Edit due date.\n3-Edit the total amount of the fee paid to date.\n4-Edit Architect information.\n"
					+"5-Edit Contractor information.\n"+ "6-Edit Customer information.\n" +"7-Finalise project.\n0-Exit\n");
			
			System.out.print(": ");
			
			//Reads and stores the value the user entered
			int option=Integer.parseInt(scan.nextLine());
			System.out.println();
			
			//If the user enters option 1 the following questions with be asked
			if(option==1) {

				//Set of questions asked for user input when needed to capture new project details
				
				System.out.println("Enter the project name: ");
				project.projectName=scan.nextLine();							//This stores the input from the user into the projectName variable from the project Class 
						
				System.out.println("Enter the project building type: ");
				project.buildingType=scan.nextLine();							//This stores the input from the user into the buildingType variable from the project Class 
				
				System.out.println("Enter the ERF number: ");
				project.erfNumber=Integer.parseInt(scan.nextLine());			//This stores the input from the user into the erfNumber variable from the project Class 
			
				System.out.println("Enter the physical address: ");				//This stores the input from the user into the physicalAddress variable from the project Class 
				project.physicalAddress=scan.nextLine();
				
				System.out.println("Enter the project fee: ");
				project.projectFee=Double.parseDouble(scan.nextLine());			//This stores the input from the user into the projectFee variable from the project Class 
				
				System.out.println("Enter the project amount paid to date: ");
				project.amountPaid=Double.parseDouble(scan.nextLine());			//This stores the input from the user into the amountPaid variable from the project Class 
				
				System.out.println("Enter the project deadline: "); 			 
				project.deadline=scan.nextLine();								//This stores the input from the user into the deadline variable from the project Class
				
				System.out.println("Project details has been captured.");
			}
			
			
			//If the user selects option 2 the user is required to enter the new date 
			else if (option==2) {
				newProjectDueDate(project, scan);		//Calls the newProjectDueDate method taking information for the project class and the users input
			}
			
			//If the user selects option 3 the user is required to enter the new fee amount
			else if (option==3) {
				newFeeAmount(project, scan);			//Calls the newFeeAmount method taking information for the project class and the users input
			}
			
			//If option 4 is selected the user will be able to edit or add the architects information
			else if (option==4) {
				int input;

				//This while loop is used to continuously display the sub menu 
				while(true) {
					System.out.println("\n1-New Architect 2- Edit full name 3-Edit contact number 4-Edit physical address  5-Edit email 6-Return to main menu");
					input=Integer.parseInt(scan.nextLine());	//Stores and reads the input entered by the user
					
					//If selection is to add to architect details
					if (input==1) {
						
						Person architect=new Person();			//From the empty constructor created in the person class there is not argument information yet since the user needs to still input the information 
						
						System.out.println("Enter name: ");
						architect.name=scan.nextLine();				//This then assigns the information into the object architect from the person constructor 

						
						System.out.println("Enter contact number: ");
						architect.mobile=scan.nextLine();				//This then assigns  the information into the object architect from the person constructor 
						
						System.out.println("Enter email: ");
						architect.email=scan.nextLine();				//This then assigns  the information into the object architect from the person constructor 

						System.out.println("Enter address: ");
						architect.physicalAddr=scan.nextLine();			//This then assigns  the information into the object architect from the person constructor 
						
						System.out.println("New Architect has been added. ");		
						project.architect=architect;					//Assigns the new architect information	
					}
					
					//If the user selects return to main menu the while loop is exited 
					else if (input==6) {
						break;
					}
					
					//if the user chooses to change the name of the architect
					else if(input==2) {
						System.out.println("Enter new name: ");							//Requests user to enter new name
						project.architect.name=scan.nextLine();							//From the project class the architects name, from the person object, is updated to the users input
						System.out.println("Changes has been successfully made!");
					}
					
					//if the user chooses to change the contact number of the architect
					else if(input==3) {
						System.out.println("Enter new contact number: ");				//Requests user to enter new contact number
						project.architect.mobile=scan.nextLine();						//From the project class the architects name, from the person object, is updated to the users input
						System.out.println("Changes has been successfully made!");
					}
					
					////if the user chooses to change the address of the architect
					else if(input==4) {
						System.out.println("Enter new address: ");
						project.architect.physicalAddr=scan.nextLine();					//From the project class the architects name, from the person object, is updated to the users input
						System.out.println("Changes has been successfully made!");
					}
		
					//if the user chooses to change the email address of the architect
					else if(input==5) {
						System.out.println("Enter new email address: ");
						project.architect.email=scan.nextLine();
						System.out.println("Changes has been successfully made!");		//From the project class the architects name, from the person object, is updated to the users input
					}
					
					//If a user enters a number that is not shown in the option list 
					else {
						System.out.println("Enter a valid option!");
					}
					
				}
			}
			
			//If option 5 is selected the user will be able to edit or add the Contractor information
			else if (option==5) {
				int input;
				
				//This while loop is used to continuously display the sub menu 
				while(true) {
					System.out.println("\n1-New Contractor 2- Edit full name 3-Edit contact number 4-Edit physical address  5-Edit email 6-Return to main menu");
					input=Integer.parseInt(scan.nextLine());
					
					//If selection is to add to contractors details
					if (input==1) {
						Person contractor=new Person();			//From the empty constructor created in the person class there is not argument information yet since the user needs to still input the information 
						
						System.out.println("Enter name: ");				//Requests user to enter the name
						contractor.name=scan.nextLine();				//Reads and assigns the input from the user then stores that into the name variable
						
						System.out.println("Enter contact number: ");
						contractor.mobile=(scan.nextLine());
						
						System.out.println("Enter email: ");
						contractor.email=(scan.nextLine());

						System.out.println("Enter address: ");
						contractor.physicalAddr=(scan.nextLine());
						
						System.out.println("New Contractor has been added. ");
						
						project.contractor=(contractor);				//Assigns the new contractor information	
					}
					
					//If the user selects return to main menu the while loop is exited 
					else if (input==6) {
						break;
					}
					
					//if the user chooses to change the name of the contractor
					else if(input==2) {
						System.out.println("Enter new name: ");							//Requests user to enter new name
						project.contractor.name=scan.nextLine();						//From the project class the contractors name, from the person object, is updated to the users input
						System.out.println("Changes has been successfully made!");		


					}
					
					//if the user chooses to change the contact number of the contractor
					else if(input==3) {
						System.out.println("Enter new contact number: ");
						project.contractor.mobile=scan.nextLine();						//From the project class the contractors contact number, from the person object, is updated to the users input
						System.out.println("Changes has been successfully made!");


					}
					
					//if the user chooses to change the address of the contractor
					else if(input==4) {
						System.out.println("Enter new address: ");
						project.contractor.physicalAddr=scan.nextLine();
						System.out.println("Changes has been successfully made!");		//From the project class the contractors address, from the person object, is updated to the users input


					}
				
					//if the user chooses to change the contact email address of the contractor
					else if(input==5) {
						System.out.println("Enter new email address: ");
						project.contractor.email=scan.nextLine();						//From the project class the contractors email address, from the person object, is updated to the users input
						System.out.println("Changes has been successfully made!");
					}
					
					//If a user enters a number that is not shown in the option list 
					else {
						System.out.println("Enter a valid option!");
					}
				}
			}

			//If option 6 is selected the user will be able to edit or add the Customer information
			else if (option==6) {
				int input;
				
				//This while loop is used to continuously display the sub menu 
				while(true) {
					System.out.println("\n1-New Customer 2- Edit full name 3-Edit contact number 4-Edit physical address  5-Edit email 6-Return to main menu");
					input=Integer.parseInt(scan.nextLine());
					
					//If selection is to add to contractors details
					if (input==1) {
						Person customer=new Person();				//From the empty constructor created in the person class there is not argument information yet since the user needs to still input the information 
						System.out.println("Enter name: ");
						customer.name=(scan.nextLine());				//Reads and assigns the input from the user then stores that into the name variable
						
						System.out.println("Enter contact number: ");
						customer.mobile=(scan.nextLine());
						
						System.out.println("Enter email: ");
						customer.email=(scan.nextLine());

						System.out.println("Enter address: ");
						customer.physicalAddr=(scan.nextLine());
						
						System.out.println("\nNew Customer has been added. ");
						
						project.customer=(customer);					//Assigns the new contractor information
					}
					
					//If the user selects return to main menu the while loop is exited 
					else if (input==6) {
						break;
					}
					
					//if the user chooses to change the name of the customer
					else if(input==2) {
						System.out.println("Enter new name: ");								//Requests user to enter new name
						project.customer.name=scan.nextLine();
						System.out.println("Changes has been successfully made!");			//From the project class the customers name, from the person object, is updated to the users input


					}
					
					//if the user chooses to change the contact number of the customer
					else if(input==3) {
						System.out.println("Enter new contact number: ");
						project.customer.mobile=scan.nextLine();							//From the project class the customers contact number, from the person object, is updated to the users input
						System.out.println("Changes has been successfully made!");
					}
					
					//if the user chooses to change the address of the customer
					else if(input==4) {
						System.out.println("Enter new address: ");
						project.customer.physicalAddr=scan.nextLine();						//From the project class the customers address, from the person object, is updated to the users input
						System.out.println("Changes has been successfully made!");

					}
					
					//if the user chooses to change the email address of the customer
					else if(input==5) {
						System.out.println("Enter new email address: ");
						project.customer.email=scan.nextLine();								//From the project class the customers email, from the person object, is updated to the users input
						System.out.println("Changes has been successfully made!");
					}
					
					//If a user enters a number that is not shown in the option list 
					else {
						System.out.println("Enter a valid option!");
					}
				}
			}

			
			//If the user selects option 7 the user is required to finalize the project by entering the completion date
			else if (option==7) {
				System.out.println("Please enter completion date: ");
				project.completionDate=(scan.nextLine());							//assigns the completion date to the users input into the project class in from the completion date
				project.finalise=true;												//Sets the finalise project variable to true
				double amountOwed=project.projectFee-project.amountPaid;			//calculates amount owed
				
				//If owed amount is greater than zero 
				if (amountOwed>0) {
					Invoice invoice=new Invoice(project);							//Invoice object is created
					System.out.println("Here is the outstanding invoice:");
					invoice.generateInvoice();										//Invoice is then generated
				}
				
				//If the owed amount is not greater than zero no invoice is generated
				else if (amountOwed<=0){
					System.out.println("Project fee paid in full.");
				}
				
				
			}
			
			//Exits the while loop it the user selects option to exit 
			else if (option==0) {
				exitProgram(project);
				break;
			}
			
			//If a user enters a number that is not shown in the option list 
			else {
				System.out.println("Enter a valid option!");
			}
			}
		scan.close();	//Close scanner
	}


	private static void exitProgram(Project project) {
		System.out.println("Goodbye.");
		System.out.println(project.toString());				//Displays all the information stored by the user
	}

	
	//Methods
	
	//Methods to request user to enter amount paid to date
	private static void newFeeAmount(Project project, Scanner scan) {
		System.out.println("Enter the new total amount of the fee paid to date: "); 
		project.amountPaid=(Double.parseDouble(scan.nextLine())); 			//The new fee amount is then stored into the project class in the projectFee object
		System.out.println("Fee amount updated.");
	}

	//Method to edit the project Due date
	private static void newProjectDueDate(Project project, Scanner scan) {
		System.out.println("Enter the new due date: ");		//Requests user to enter the new due date
		project.deadline=(scan.nextLine());					//The new date is then stored into the project class in the deadline object
		System.out.println("Due date updated. ");
	}

}
