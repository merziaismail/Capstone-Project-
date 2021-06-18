import java.io.Serializable;

//This is the person class of the main CapestoneProject1

public class Person implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Attributes 
	public String name,mobile,email,physicalAddr;
		
	
	//Assigning of the attributes into constructor 
	public Person(String name,String mobile,String email,String physicalAddr) {
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.physicalAddr = physicalAddr;
	}
	
	//When a new object is created in the main method for the specific person this constructor is empty at first since the user is required to input information once the user inputs information the 
	//information is then stored into the object 
	public Person() {
	}
	
	//Displays the details of the persons
	public String toString () {
		String output = "Name: " + name;
		output += "\nMobile:" + mobile;
		output += "\nEmail:" + email;
		output += "\nPhysical Address:" + physicalAddr;
		
		return output;
		}
		
	
	
}
