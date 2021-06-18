
import java.io.Serializable;

//This is the project class of the main CapestoneProject1
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Attributes
	public int projectNumber = 0; // Automatically number each project
	public int erfNumber;
	public double projectFee, amountPaid;
	public boolean finalise = false;
	public String physicalAddress, buildingType, projectName = null, deadline, completionDate = null;

	// Sub classes of persons
	public Person contractor = new Person();
	public Person architect = new Person();
	public Person customer = new Person();
	public Person projectManager=new Person();

	public Project() {
	}

	// Constructor assigning the attributes
	public Project(int erfNumber, double projectFee, double amountPaid, String physicalAddress, String buildingType,
			String projectName, String deadline) {
		this.erfNumber = erfNumber;
		this.projectFee = projectFee;
		this.amountPaid = amountPaid;
		this.physicalAddress = physicalAddress;
		this.buildingType = buildingType;
		this.projectName = projectName;
		this.deadline = deadline;


	}

	// This is to display all the information
	public String toString() {
		String output = "Project Number: " + projectNumber;
		output += "\nProject Name: " + projectName;
		output += "\nErf Number: " + erfNumber;
		output += "\nPhysical Address: " + physicalAddress;
		output += "\nBuilding Type: " + buildingType;
		output += "\nProject Fee: " + projectFee;
		output += "\nAmount Paid: " + amountPaid;
		output += "\nDeadline: " + deadline;
		output += "\nisFinalised: " + finalise;
		output += "\nCompletion date: " + completionDate;

		
		//If the persons information has not yet been entered
		if(customer==null) {
			output += "\n\nCustomer information not saved!";

		}
		//Otherwise print the persons information
		else {
			output += "\n\nCustomer:" + customer.toString();
		}
		
		//If contractor information has not been entered 
		if(contractor==null) {
			output += "\n\nContractor information not saved!";

		}
		//Otherwise display the contractors information
		else {
			output += "\n\nContractor:" + contractor.toString();
		}
		
		//If architect information has not been entered 
		if(architect==null) {
			output += "\n\nArchitect information not saved!";
		}
		
		//Otherwise display the architects  information
		else {
			output += "\n\nArchitect:" + architect.toString();
		}
		
		//If project manager information has not been entered 
		if(projectManager==null) {
			output += "\n\nProject Manager information not saved!";
		}
		
		//Otherwise display the project managers  information
		else {
			output += "\n\nProject Manager:" + projectManager.toString();
		}

		return output;
	}

}
