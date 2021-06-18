import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.mysql.cj.protocol.Resultset;

public class UserInterface {

    DB db = new DB();

    public UserInterface() {
        mainMenu();
    }

    final String FILE_PATH = "./ProjectList.txt"; // this directory
    final String CP_PATH = "./Completed projects.txt"; // file to store projects marked as finalised

    /*
     * Name:Marjia Ismail Date: 19 May 2021 Level:2 Task:24
     */

    // This Java program is used as a project management system for a company to
    // keep track of different projects

    // The first menu options
    public void mainMenu() {
        try {
            // Allows information to be read from the project class
            // Create scanner to read input
            Scanner scan = new Scanner(System.in);
            System.out.println("Welcome to the Capstone project creater!");

            while (true) {
                System.out.println(
                        "\n1-List existing projects.\n2-Add new project.\n3-Edit Existing Project.\n4-List incomplete projects.\n5-List past due projects. \n6 Delete Project.\n7-List Completed Projects.\n8-Finalise Project.\n0-Exit.");

                // Stores and reads the input entered by the user
                int selection = Integer.parseInt(scan.nextLine());

                // Exits the while loop
                if (selection == 0) {
                    System.out.println("Goodbye.");
                    break;
                }

                // Displays the existing projects by printing all the items in the toStrings
                // from the project class
                else if (selection == 1) {
                    // ArrayList<Project> list = getProjectList();
                    ArrayList<Project> list = db.readAllProjects();
                    if (list != null) {
                        for (Project p : list) {
                            System.out.println(p.toString());
                            System.out.println("<-------------------------------------------------------------------------------->");
                        }
                    }

                    // If there are not existing projects
                    else {
                        System.out.println("No existing projects!");
                    }
                }

                // If user wants to add new projects the method below is called
                else if (selection == 2) {
                    addProjectMenu();
                }

                // If user wants to edit an existing project the method below is called
                else if (selection == 3) {
                    editProjectMenu();
                }

                // If user wants a list of incomplete projects the method below is called
                else if (selection == 4) {
                    // Displays the items in the array that stores all incomplete projects
                    for (Project p : getIncompleteProjects()) {
                        System.out.println(p.toString());
                        System.out.println("<-------------------------------------------------------------------------------->");
                    }
                }

                // If user wants a list of due projects the method below is called
                else if (selection == 5) {
                    // Displays the items in the array that stores all due projects
                    for (Project p : getDueProjects()) {
                        System.out.println(p.toString());
                        System.out.println("<-------------------------------------------------------------------------------->");
                    }

                    // If user wants to delete projects the method below is called
                } else if (selection == 6) {

                    // Gets the list of the project names from method
                    listProjectsByName();

                    // Asks user which project to delete
                    System.out.println("\nPlease enter the index of the project you would like to delete: ");


                    int pNumber = Integer.parseInt(scan.nextLine());
                    if (db.deleteProject(pNumber)) {
                        System.out.println("Project removed");
                    } else {
                        System.out.println("Project does not exist");
                    }
                }
                // If the user selects option 7 the user is required to finalize the project by
                // entering the completion date
                else if (selection == 7) {
                    ArrayList<Project> list = getCompletedProjects();
                    if (list != null) {
                        for (Project p : list) {
                            System.out.println();
                            System.out.println(p.toString());
                            System.out.println("*******************************************************************");
                        }
                    } else {
                        System.out.println("No Completed projects to list.");
                    }
                }

                else if (selection == 8) {
                	ArrayList<Project> list = db.readAllProjects();
                	
                	if (list != null) {
                        // Gets the list of the project names from method
                        listFinalisedProjects();

                        // Asks user which project to delete
                        System.out.println("\nPlease enter the index of the project you would like to finalise: ");


                        int pNumber = Integer.parseInt(scan.nextLine());

                        Project project = db.readProject(pNumber);
                        if (project != null) {
                            finaliseProject(project, scan);
                            System.out.println("Project finalised");
                        } else {
                            System.out.println("Project does not exist");
                        }
                	} else {
                        System.out.println("No existing projects to finalise");

                	}

                }

            }
            scan.close(); // Close scanner

        } catch (Exception e) {
        }

    } // mainMenu ()

    public void addProjectMenu() {
        try {
            Project project = null;

            while (true) {
                System.out.println(
                        "\n1-Capture new project details\n4-Display Project  \n0-Back to main menu\n\n(NB! Project is automatically stored.)");
                System.out.print(": ");
                Scanner scan = new Scanner(System.in);

                // Creates a person object

                // Reads and stores the value the user entered
                int option = Integer.parseInt(scan.nextLine());
                System.out.println();

                // If the user enters option 1 the following questions with be asked
                if (option == 1) {
                    // Set of questions asked for user input when needed to capture new project
                    // details
                    project = new Project();

                    // Stores the project details into a array list
                    ResultSet id = db.statement.executeQuery("SELECT projectNumber FROM projects ORDER BY projectNumber DESC");

//                     To count project number
                    if (id.next()) {
                        project.projectNumber = id.getInt("projectNumber") + 1;
                    }

                    System.out.println("Enter customer full name (eg. Name Surname): ");
                    project.customer.name = scan.nextLine(); // This stores the input from the user into the projectName
                                                             // variable from
                    // the project Class

                    // Splits the name to check that the user entered full name
                    String[] fullname = project.customer.name.split(" ");
                    if (fullname.length < 2) {
                        while (true) {
                            System.out.println("Please enter your name and surname!");
                            System.out.println("Enter customer full name (eg. Name Surname): ");
                            project.customer.name = scan.nextLine(); // This stores the input from the user into the
                                                                     // projectName
                            String[] fullname2 = project.customer.name.split(" ");

                            // If full name is added then exit
                            if (fullname2.length > 1) {
                                break;
                            }
                        }
                    }

                    System.out.println("Enter customer contact number (eg. 0730831111): ");
                    project.customer.mobile = scan.nextLine(); // This stores the input from the user into the
                                                               // projectName
                                                               // variable
                    // from the project Class

                    System.out.println("Enter customer email address (eg. name@email.com): ");
                    project.customer.email = scan.nextLine(); // This stores the input from the user into the
                                                              // projectName
                                                              // variable
                    // from the project Class

                    System.out.println("Enter customer address (eg. 80 Street Name, Suburb, City, 7387): ");
                    project.customer.physicalAddr = scan.nextLine(); // This stores the input from the user into the
                                                                     // projectName
                    // variable from the project Class

                    // Contractor details
                    System.out.println("Enter contractor full name (eg. Name Surname): ");
                    project.architect.name = scan.nextLine(); // This stores the input from the user into the
                                                              // projectName
                                                              // variable from
                    System.out.println("Enter contractor contact number (eg. 0730831111): ");
                    project.contractor.mobile = scan.nextLine(); // This stores the input from the user into the
                                                                 // projectName
                                                                 // variable
                    // from the project Class

                    System.out.println("Enter contractor email address (eg. name@email.com): ");
                    project.contractor.email = scan.nextLine(); // This stores the input from the user into the
                                                                // projectName
                                                                // variable
                    // from the project Class

                    System.out.println("Enter contractor address (eg. 80 Street Name, Suburb, City, 7387): ");
                    project.contractor.physicalAddr = scan.nextLine(); // This stores the input from the user into the
                                                                       // projectName
                    // variable from the project Class

                    // Architect details
                    System.out.println("Enter architect full name (eg. Name Surname): ");
                    project.architect.name = scan.nextLine(); // This stores the input from the user into the
                                                              // projectName
                                                              // variable from
                    System.out.println("Enter architect contact number (eg. 0730831111): ");
                    project.architect.mobile = scan.nextLine(); // This stores the input from the user into the
                                                                // projectName
                                                                // variable
                    // from the project Class

                    System.out.println("Enter architect email address (eg. name@email.com): ");
                    project.architect.email = scan.nextLine(); // This stores the input from the user into the
                                                               // projectName
                                                               // variable
                    // from the project Class

                    System.out.println("Enter architect address (eg. 80 Street Name, Suburb, City, 7387): ");
                    project.architect.physicalAddr = scan.nextLine(); // This stores the input from the user into the
                                                                      // projectName
                    // variable from the project Class
                    
                    
                    
                    
                    // Project Manager details
                    System.out.println("Enter project manager full name (eg. Name Surname): ");
                    project.projectManager.name = scan.nextLine(); // This stores the input from the user into the
                                                              // projectName
                                                              // variable from
                    System.out.println("Enter project manager contact number (eg. 0730831111): ");
                    project.projectManager.mobile = scan.nextLine(); // This stores the input from the user into the
                                                                // projectName
                                                                // variable
                    // from the project Class

                    System.out.println("Enter project manager email address (eg. name@email.com): ");
                    project.projectManager.email = scan.nextLine(); // This stores the input from the user into the
                                                               // projectName
                                                               // variable
                    // from the project Class

                    System.out.println("Enter project manager address (eg. 80 Street Name, Suburb, City, 7387): ");
                    project.projectManager.physicalAddr = scan.nextLine(); // This stores the input from the user into the
                                                                      // projectName
                    // variable from the project Class
                    

                    System.out.println("Enter the project name (eg. New Dawn): ");
                    project.projectName = scan.nextLine(); // This stores the input from the user into the projectName
                                                           // variable from the project Class

                    while (true) {
                        ResultSet duplicate = db.statement.executeQuery("SELECT projectName FROM projects WHERE projectName='" + project.projectName + "'");

//                  To count project number
                    if (duplicate.next()) {
                        System.out.println("\nProject with this name already exists, please try again.\n");

                        System.out.println("Enter the project name (eg. New Dawn): ");
                        
                        project.projectName = scan.nextLine(); // This stores the input from the user into the projectName

                    } else {
                    	break;
                    }
                    }


                    System.out.println("Enter the project building type (eg. House, Tower, Skyscraper, Garage etc): ");
                    project.buildingType = scan.nextLine(); // This stores the input from the user into the buildingType
                                                            // variable from the project Class

                    System.out.println("Enter the ERF number (eg. 8000): ");
                    project.erfNumber = Integer.parseInt(scan.nextLine()); // This stores the input from the user into the erfNumber variable from the project Class

                    System.out.println("Enter the physical address (eg. 80 Street Name, Suburb, City, 7387): ");
                    project.physicalAddress = scan.nextLine();// This stores the input from the user into the physical address variable

                    System.out.println("Enter the project fee (eg. 8000000.58): ");
                    project.projectFee = Double.parseDouble(scan.nextLine()); // This stores the input from the user into the projectFee variable from the project Class
                                                                              
                    System.out.println("Enter the project amount paid to date (eg. 8000000.58): ");
                    //Stores the input from the user into   the amountPaid variable from the project Class
                    project.amountPaid = Double.parseDouble(scan.nextLine()); 
                    
                    System.out.println("Enter the project deadline(dd/MM/yyyy eg.18/05/2021): ");
                    project.deadline = scan.nextLine(); // This stores the input from the user into the deadline variable from the project Class
                                                    
                    System.out.println("Project details has been captured.");
                    
                    // if the project name is left blank the building type and customer name is stored into its place
                    if (project.projectName.length() < 1) {
                        project.projectName = project.buildingType + " " + project.customer.name.split(" ")[1];
                    }
                    
                    db.saveNewProject(project);

                }

                // Type 1 == Architect
                // Type 2 == Contractor
                // Type 3 == Customer
                // Type 4 == Project Manager

                // Displays the project that was just created
                else if (option == 4) {
                    System.out.println(project.toString());
                }

                // Exits the while loop it the user selects option to exit and saves project
                // automatically
                else if (option == 0) {
                    break;
                }

                // If a user enters a number that is not shown in the option list
                else {
                    System.out.println("Enter a valid option!");
                }
            }
        } catch (Exception e) {
            // just catch exceptions and do nothing
        }
    } // addProjectMenu ()

    //
    public void editPersonMenu(int type, Scanner scan, Project project) {
        // Type 1 == Architect
        // Type 2 == Contractor
        // Type 3 == Customer
    	// Type 4 == Project Manager

        int input;
        
        // This while loop is used to continuously display the sub menu
        while (true) {
            System.out.println(
                    "\n1-Edit full name\n2-Edit contact number\n3-Edit physical address\n4-Edit email\n0-Return to main menu");
            System.out.print(": ");

            input = Integer.parseInt(scan.nextLine()); // Stores and reads the input entered by the user
            
            // If the user selects return to main menu the while loop is exited
            if (input == 0) {
                break;
            }

            // if the user chooses to change the name of the architect
            else if (input == 1) {
                updateName(type, project, scan);
            }

            // if the user chooses to change the contact number of the architect
            else if (input == 2) {
                updateNumber(type, project, scan);
            }

            //// if the user chooses to change the address of the architect
            else if (input == 3) {
                updateAddress(type, project, scan);
            }

            // if the user chooses to change the email address of the architect
            else if (input == 4) {
                updateEmail(type, project, scan);
            }
            // If a user enters a number that is not shown in the option list
            else {
                System.out.println("Enter a valid option!");
            }
        }
    } // editPersonMenu ()

    public void editProjectMenu() {
        try {
            Scanner scan = new Scanner(System.in);
            listProjectsByName();
            System.out.println("\nPlease enter the index(number) of the project you would like to edit: ");
            
            int pNumber = Integer.parseInt(scan.nextLine());
            Project project = db.readProject(pNumber);

            if (project != null) {
                while (true) {
                    System.out.println("\n1-Edit Architect information\n" + "2-Edit Contractor information\n"
                            + "3-Edit Customer information\n8-Update Project Manager information \n4-Update amount paid \n5-Edit Deadline \n6-Edit project fee \n7-Display project \n0-Back to main menu\n");
                    System.out.print(": ");
                    // Reads and stores the value the user entered
                    int option = Integer.parseInt(scan.nextLine());
                    System.out.println();
                    // If the user enters option 1 the following questions with be asked
                    if (option == 4) {
                        newFeeAmount(project, scan);
                        if (db.updateProject(project)) {
                            System.out.println("Changes saved");
                        } else {
                            System.out.println("Failed to save changes");
                        }
                    }
                    
                    //If the user wants to edit a project
                    else if (option == 8) {
                        editPersonMenu(4, scan, project);
                        if (db.updateProject(project)) {
                            System.out.println("Changes saved");
                        } else {
                            System.out.println("Failed to save changes");
                        }
                    }
                    
                    //If the user selects option 5 to edit the project due date
                    else if (option == 5) {
                        newProjectDueDate(project, scan);
                        
                        if (db.updateProject(project)) {
                            System.out.println("Changes saved");
                        } else {
                            System.out.println("Failed to save changes");
                        }

                    }
                    
                    //If the user selects option 6 the user is required to enter the new fee amount
                    else if (option == 6) {
                        System.out.println("Enter new project fee: ");
                        project.projectFee = Double.parseDouble(scan.nextLine());

                        if (db.updateProject(project)) {
                            System.out.println("Changes saved");
                        } else {
                            System.out.println("Failed to save changes");
                        }
                    }
                    
                    // If option 1 is selected the user will be able to edit or add the architects information
                    else if (option == 1) {
                        editPersonMenu(1, scan, project);

                        if (db.updateProject(project)) {
                            System.out.println("Changes saved");
                        } else {
                            System.out.println("Failed to save changes");
                        }
                    }
                    // If option 2 is selected the user will be able to edit or add the Contractor information
                    else if (option == 2) {
                        editPersonMenu(2, scan, project);
                        if (db.updateProject(project)) {
                            System.out.println("Changes saved");
                        } else {
                            System.out.println("Failed to save changes");
                        }
                    }
                    
                    // If option 3 is selected the user will be able to edit or add the Customer information
                    else if (option == 3) {
                        editPersonMenu(3, scan, project);
                     
                        if (db.updateProject(project)) {
                            System.out.println("Changes saved");
                        } else {
                            System.out.println("Failed to save changes");
                        }
                    } else if (option == 7) {
                        System.out.println(project.toString());
                    }
                    
                    // Exits the while loop it the user selects option to exit
                    else if (option == 0) {
                       
                        if (db.updateProject(project)) {
                            System.out.println("Changes saved");
                        } else {
                            System.out.println("Failed to save changes");
                        }
                        break;
                    }
                    // If a user enters a number that is not shown in the option list
                    else {
                        System.out.println("Enter a valid option!");
                    }
                }
            } else {
                System.out.println("Project not found! Please enter the correct index from the list");
                editProjectMenu();
            }
        } catch (Exception e) {

        }
    } // editProjectMenu ()

    // Methods

    // To exit the program and display the project information

    public boolean finaliseProject(Project project, Scanner scan) {
        System.out.println("Please enter completion date(dd/MM/yyyy eg.18/05/2021): ");
        project.completionDate = (scan.nextLine()); // assigns the completion date to the users input into
                                                    // the project class in from the completion date
        project.finalise = true; // Sets the finalise project variable to true
        double amountOwed = project.projectFee - project.amountPaid; // calculates amount owed

        // If owed amount is greater than zero
        if (amountOwed > 0) {
            System.out.println("Here is the outstanding invoice:");
            generateInvoice(project);
        }

        // If the owed amount is not greater than zero no invoice is generated
        else if (amountOwed <= 0) {
            System.out.println("Project fee paid in full.");
        }
        
        try {
            db.updateProject(project);
            return true;
        } catch (Exception e) {
            System.out.println("Project could not be finalised");
            return false;
        }
    } // finaliseProject ()

    // Method to edit the amount paid
    public void newFeeAmount(Project project, Scanner scan) {
        System.out.println("Enter the new total amount of the fee paid to date: ");
        project.amountPaid = (Double.parseDouble(scan.nextLine())); // The new fee amount is then stored into the project class in the projectFee object
                                                                  
        System.out.println("Fee amount updated.");
    }

    // Method to edit the project due date
    public void newProjectDueDate(Project project, Scanner scan) {
        System.out.println("Enter the new due date(dd/MM/yyyy eg.18/05/2021): ");
        project.deadline = (scan.nextLine()); // The new date is then stored into the project class in the deadline
                                              // object
        System.out.println("Due date updated. ");
    }

    // Method for the subMenu

    // This method assigns the different persons depending on which persons information the user wants to edit
    public void setPerson(int type, Project project, Person person) {
        if (type == 1) {
            project.architect = (person);
        } else if (type == 2) {
            project.contractor = (person);
        } else if (type == 3) {
            project.customer = (person);
        } else if (type == 4)  {
        	project.projectManager = (person);
        }
    }

    // This method reads the person object or the type of person depending on the input
    public Person getPerson(int type, Project project) {
        if (type == 1) {
            return project.architect;
        } else if (type == 2) {
            return project.contractor;
        } else if (type == 3){
            return project.customer;
        } else {
        	return project.projectManager;
        	}
    }

    // This Method allows user to edit the name of the person
    public void updateName(int type, Project project, Scanner scan) {
        Person person = getPerson(type, project);
        System.out.println("Enter new name: ");
        person.name = scan.nextLine();
        setPerson(type, project, person);
        System.out.println("Changes has been successfully made!");
    }

    // This Method allows user to edit the number of the person
    public void updateNumber(int type, Project project, Scanner scan) {
        Person person = getPerson(type, project);
        System.out.println("Enter new contact number: "); // Requests user to enter new contact number
        person.mobile = scan.nextLine();
        setPerson(type, project, person);
        System.out.println("Changes has been successfully made!");
    }

    // This Method allows user to edit the address of the person
    public void updateAddress(int type, Project project, Scanner scan) {
        Person person = getPerson(type, project);
        System.out.println("Enter new address: "); // Requests user to enter new contact number
        person.physicalAddr = scan.nextLine();
        setPerson(type, project, person);
        System.out.println("Changes has been successfully made!");
    }

    // This Method allows user to edit the email of the person
    public void updateEmail(int type, Project project, Scanner scan) {
        Person person = getPerson(type, project);
        System.out.println("Enter new email address: "); // Requests user to enter new contact number
        person.mobile = scan.nextLine(); // Stores or assigns the new number to the mobile variable to the person class
        setPerson(type, project, person); // Calls the method to assign the type of person
        System.out.println("Changes has been successfully made!");
    }

    // This Method allows user to generate an invoice
    public void generateInvoice(Project project) {
        double amountOwed = project.projectFee - project.amountPaid; // Computes the amount owed
        String output = "\nCustomer: " + project.customer.toString(); // Displays the customers details from the
                                                                      // toString from persons class
        output += "\nAmount Owed:R" + amountOwed; // Displays the amount owed
        System.out.println(output);
    }

    // Saves the projects into a list
    public boolean saveProject(Project project) {
        ArrayList<Project> projectList = getProjectList();
        try {
            if (projectList != null) {
                for (Project p : projectList) {
                    // If the project name already exists
                    if (p.projectName.equals(project.projectName)) {
                        System.out.println("Project with this name already exists, please create a new one.");
                        return false;
                    }
                }
                projectList.add(project);
                writeToFile(projectList);
                System.out.println("Project Saved!");
                return true; // return true if project was saved successfully

                // Otherwise add the new project into the project list
            } else {
                projectList = new ArrayList<Project>();
                projectList.add(project);
                writeToFile(projectList);
                System.out.println("Project Saved!");
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    // Writes all the information into the files
    public boolean writeToFile(ArrayList<Project> list) {
        try {
            File f = new File(FILE_PATH);
            f.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(f);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(list);
            objectOut.close();
            fileOut.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // Reads the list of projects
    public ArrayList<Project> getProjectList() {
        try {
            FileInputStream fileIn = new FileInputStream(FILE_PATH);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            objectIn.close();
            fileIn.close();
            if (obj != null)
                return (ArrayList<Project>) obj;
            else
                return null;
        } catch (Exception ex) {
            return null;
        }
    }

    // Checks if the the dates subtracted is a negative value if it is the store it
    // in array list with due projects
    public ArrayList<Project> getDueProjects() {
        ArrayList<Project> listDue = new ArrayList<Project>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String now = dtf.format(LocalDateTime.now()).toString();

        for (Project p : getIncompleteProjects()) {
            if (dateDiff(p.deadline, now) < 0) {
                listDue.add(p);
            }
        }
        return listDue;
    }

    // Subtracts the due date with the current date
    public int dateDiff(String d1, String d2) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date date1 = format.parse(d1);
            java.util.Date date2 = format.parse(d2);
            return (int) (TimeUnit.DAYS.convert((date1.getTime() - date2.getTime()), TimeUnit.MILLISECONDS));

        } catch (Exception e) {
            return 0; // not appropriate value in context
        }
    }

    // Stores the incomplete projects into the array list and checks which projects are finalised
    public ArrayList<Project> getIncompleteProjects() {
        try {
            ArrayList<Project> list = new ArrayList<Project>();
            for (Project p : db.readAllProjects() /* getProjectList() */) {
                if (p.finalise == false) {
                    list.add(p);
                }
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    // reads the project names
    public Project getProject(String projectName) {
        for (Project p : getProjectList()) {
            if (p.projectName.equals(projectName)) {
                return p;
            }
        }
        return null;
    }

    // Loop through the projects and then displays the project names
    public void listProjectsByName() {
        try {
            for (Project p : db.readAllProjects() /* getProjectList() */) {
                System.out.println(p.projectNumber + ": " + p.projectName);
            }
        } catch (Exception e) {
        }
    }
    
    public void listFinalisedProjects() {
        try {
            for (Project p : db.readAllProjects() /* getProjectList() */) {
            	if (!p.finalise)
            		System.out.println(p.projectNumber + ": " + p.projectName);
            }
        } catch (Exception e) {
        }
    }

    // Stores the projects into array list if the name of the project is called to be removed this method will be called
    public boolean removeProject(String name) {
        ArrayList<Project> list = getProjectList();
        for (Project p : list) {
            if (p.projectName.equals(name)) {
                list.remove(p);
                if (updateProjectList(list, null))
                    return true;
            }
        }
        return false;
    }

    //Completed projects are stored in array 
    public ArrayList<Project> getCompletedProjects() throws SQLException  {
        ArrayList<Project> list = db.readAllProjects();

        if (list != null) {
            ArrayList<Project> completed = new ArrayList<Project>();
            for (Project p : list) {
                if (p.finalise == true)
                    completed.add(p);
            }
            return completed;
        }
        return null;
    }

    // If any information is updated the the project information is removed and then overwritten
    public boolean updateProjectList(ArrayList<Project> list, Project project) {
        list.remove(project);
        File f = new File(FILE_PATH);
        f.delete();
        if (project != null)
            list.add(project);
        if (writeToFile(list))
            return true;
        return false;
    }
}
