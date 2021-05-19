//This is the project class of the main CapestoneProject1
public class Project {

	//Attributes
	static int projectNumber=0;			//Automatically number each project 
	int erfNumber;
	double projectFee, amountPaid;
	boolean finalise=false;				
	String physicalAddress, buildingType, projectName,deadline,completionDate=null;

	//Sub classes of persons
	Person contractor;		
	Person architect;
	Person customer;

	public Project() {
		projectNumber++;	//Increase project number by 1
	}

	//Constructor assigning the attributes 
	public Project(int erfNumber, double projectFee,double amountPaid, String physicalAddress,String buildingType,String projectName, String deadline) {
	this.erfNumber=erfNumber;
	this.projectFee=projectFee;
	this.amountPaid=amountPaid;
	this.physicalAddress=physicalAddress;
	this.buildingType=buildingType;
	this.projectName=projectName;
	this.deadline=deadline;
}
	
	
	//This is to display all the information 
	public String toString () {
		String output = "Project Number: " + projectNumber;
		output += "\nProject Name: " + projectName;
		output += "\nErf Number: " + erfNumber;
		output += "\nPhysical Address: " + physicalAddress;
		output += "\nBuilding Type: " + 	buildingType;
		output += "\nProject Fee: " + 	projectFee;
		output += "\nAmount Paid: " + 	amountPaid;
		output += "\nProject Fee: " + 	projectFee;
		output += "\nDeadline: " + 	deadline;
		output += "\nisFinalised: " + 	finalise;
		output += "\nCompletion date: " + 	completionDate;
		
		output += "\nContractor:" + contractor.toString();		
		output += "\nCustomer:" + customer.toString();
		output += "\nArchitect:" + architect.toString();
		
		return output;
		}
	
}
