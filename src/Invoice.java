
public class Invoice {
	
	//Attributes
	Project project;
	double amountOwed;
	
	//Constructors to the different objects to generate invoice 
	public Invoice() {
	}

	public Invoice(Project project) {
		this.project=project;
	}
	
	//This displays the customers invoice
	public String toString () {
		String output = "\nCustomer: " + project.customer.toString();	//Displays the customers details from the toString from persons class
		output += "\nAmount Owed:R" + amountOwed;						//Displays the amount owed
		return output;
		}
	
	//This method will calculate amount owed and then display the information in the toString
	public void generateInvoice() {
		amountOwed=project.projectFee-project.amountPaid;			//Computes the amount owed
		System.out.println(toString());								//Displays the above toString with customer details and amount owed details
	}
	
}
