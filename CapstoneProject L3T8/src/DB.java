import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {

	Connection connection;
	Statement statement;

	public DB() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root",
					"password123");
			// Create a direct line to the database for running our queries
			statement = connection.createStatement();

			// Creates a database called PoisePMS
			statement.execute("CREATE DATABASE IF NOT EXISTS PoisePMS");

			// Open the database
			statement.execute("USE PoisePMS");

			// Type 1 == Architect
			// Type 2 == Contractor
			// Type 3 == Customer
			// Type 4 == Project Manager

			// Create a table called books with the columns
			statement.execute(
					"CREATE TABLE IF NOT EXISTS People (projectNumber int, name varchar(150),email varchar(150),"
					+ "address varchar(500), mobile varchar(25), type int, FOREIGN KEY (projectNumber) REFERENCES Projects(projectNumber))");

			//Create a table of projects 
			statement.execute(
					"CREATE TABLE IF NOT EXISTS Projects (projectNumber int NOT NULL,projectName varchar(150),erfNumber int,address varchar(500),buildingType varchar(150),"
							+ "projectFee double,amountPaid double,deadline varchar(25), completionDate varchar(25),finalise bool, PRIMARY KEY(projectNumber))");
			
		} catch (SQLException e) {
			// We only want to catch a SQLException - anything else is off-limits for now.
			e.printStackTrace();
		}

	} // DB (constructor)

	//Method for saving a new project
	public void saveNewProject(Project p){
		if (p != null) {
			try {
			//Variables 
			int projectNumber = p.projectNumber;
			String projectName = p.projectName;
			int erfNumber = p.erfNumber;
			String address = p.physicalAddress;
			String buildingType = p.buildingType;
			double projectFee = p.projectFee;
			double amountPaid = p.amountPaid;
			String deadline = p.deadline;
			String completionDate = p.completionDate;
			boolean finalise = p.finalise;

			Person customer = p.customer;
			Person architect = p.architect;
			Person contractor = p.customer;
			Person projectManager = p.projectManager;
			
			//Stores the information into the projects table
			String query5 = "INSERT INTO Projects VALUES(" + projectNumber + ", '" + projectName + "', " + erfNumber
					+ ", '" + address + "', '" + buildingType + "', " + projectFee + ", " + amountPaid + ", '"
					+ deadline + "', '" + completionDate + "', " + finalise + ")";
			statement.execute(query5);
			
			//Stores the information into the people table for the customer information
			String query1 = "INSERT INTO People VALUES(" + projectNumber + ", '" + customer.name + "', '"
					+ customer.email + "', '" + customer.physicalAddr + "', '" + customer.mobile + "', " + 3 + ")";
			statement.addBatch(query1);

			//Stores the information into the people table for the architect information
			String query2 = "INSERT INTO People VALUES(" + projectNumber + ", '" + architect.name + "', '"
					+ architect.email + "', '" + architect.physicalAddr + "', '" + architect.mobile + "', " + 1 + ")";			
			statement.addBatch(query2);

			//Stores the information into the people table for the contractor information
			String query3 = "INSERT INTO People VALUES(" + projectNumber + ", '" + contractor.name + "', '"
					+ contractor.email + "', '" + contractor.physicalAddr + "', '" + contractor.mobile + "', " + 2
					+ ")";
			statement.addBatch(query3);

			//Stores the information into the people table for the project Manager information
			String query4 = "INSERT INTO People VALUES(" + projectNumber + ", '" + projectManager.name + "', '"
					+ projectManager.email + "', '" + projectManager.physicalAddr + "', '" + projectManager.mobile
					+ "', " + 4 + ")";
			
			//Adding the information into table
			statement.addBatch(query4);
			statement.executeBatch();
			statement.clearBatch();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	} // saveNewProject()

	//Finding information from database
	public Project readProject(int projectNumber) throws SQLException {

		Project p = new Project();

		ResultSet project = statement.executeQuery("SELECT * FROM projects WHERE projectNumber=" + projectNumber);
		
		if (project.next()) {
		setProject(project, p);
		}
		
		//
		ResultSet customer = statement
				.executeQuery("SELECT * FROM people WHERE projectNumber=" + projectNumber + " AND type=" + 3);
		
		if (customer.next()) {
		setPerson(customer, p.customer);
		}

		ResultSet contractor = statement
				.executeQuery("SELECT * FROM people WHERE projectNumber=" + projectNumber + " AND type=" + 2);
		
		if (contractor.next()) {
		setPerson(contractor, p.contractor);
		}

		ResultSet architect = statement
				.executeQuery("SELECT * FROM people WHERE projectNumber=" + projectNumber + " AND type=" + 1);
		
		if (architect.next()) {
		setPerson(architect, p.architect);
		}

		ResultSet projectManager = statement
				.executeQuery("SELECT * FROM people WHERE projectNumber=" + projectNumber + " AND type=" + 4);
		
		if (projectManager.next()) {
		setPerson(projectManager, p.projectManager);
		}

		return p;
	} // readProject()

	public ArrayList<Project> readAllProjects() throws SQLException {

		ResultSet count = statement.executeQuery("SELECT COUNT(*) AS total FROM projects");
		count.next();
		int i = count.getInt("total");

		if (i > 0) {

			ArrayList<Project> list = new ArrayList<Project>();
			
			count = statement.executeQuery("SELECT projectNumber FROM projects ORDER BY projectNumber ASC");
			count.next();


			
			int j = count.getInt("projectNumber");;
			while (i > 0) {
				Project p = readProject(j);
				list.add(p);
				i--;
				j++;
			}
			return list;
		} else {
			return null;
		}
	} // readAllProjects

	public Boolean deleteProject(int pNum) {
		try {

			String query2 = "DELETE FROM People WHERE projectNumber=" + pNum;
			statement.execute(query2);
			
			String query1 = "DELETE FROM Projects WHERE projectNumber=" + pNum;
			statement.execute(query1);
			

			return true;
		} catch (Exception e) {
			return false;
		}
	} // deleteProject()

	public Boolean updateProject(Project p) {
		try {
			int projectNumber = p.projectNumber;
			String projectName = p.projectName;
			int erfNumber = p.erfNumber;
			String address = p.physicalAddress;
			String buildingType = p.buildingType;
			double projectFee = p.projectFee;
			double amountPaid = p.amountPaid;
			String deadline = p.deadline;
			String completionDate = p.completionDate;
			boolean finalise = p.finalise;

			Person customer = p.customer;
			Person architect = p.architect;
			Person contractor = p.customer;
			Person projectManager = p.projectManager;

			String query1 = "UPDATE People SET name='" + customer.name + "', email= '"
					+ customer.email + "', address='" + customer.physicalAddr + "', mobile='"
					+ customer.mobile + "' WHERE projectNumber=" + projectNumber + " AND type=3" ;

			statement.addBatch(query1);
			
			String query2 = "UPDATE People SET name='" + architect.name + "', email= '"
					+ architect.email + "', address='" + architect.physicalAddr + "', mobile='"
					+ architect.mobile + "' WHERE projectNumber=" + projectNumber + " AND type=1" ;
			statement.addBatch(query2);


			String query3 = "UPDATE People SET name='" + contractor.name + "', email= '"
					+ contractor.email + "', address='" + contractor.physicalAddr + "', mobile='"
					+ contractor.mobile + "' WHERE projectNumber=" + projectNumber + " AND type=2" ;
			statement.addBatch(query3);

			String query4 = "UPDATE People SET name='" + projectManager.name + "', email= '"
					+ projectManager.email + "', address='" + projectManager.physicalAddr + "', mobile='"
					+ projectManager.mobile + "' WHERE projectNumber=" + projectNumber + " AND type=4" ;
			statement.addBatch(query4);
			
			String query5 = "UPDATE Projects SET projectName='" + projectName + "', address='" + address + "', buildingType='"
			+ buildingType + "', deadline='" + deadline + "', completionDate='" + completionDate + "', erfNumber="
					+ erfNumber + ", projectFee=" + projectFee + ", amountPaid=" + amountPaid + "WHERE projectNumber=" + projectNumber ;
			if (finalise) {
				statement.executeUpdate("UPDATE Projects SET finalise=" + (finalise == true ? 1 : 0) + " WHERE projectNumber=" + projectNumber);
			}
			
			statement.addBatch(query5);

			statement.executeBatch();
			statement.clearBatch();

			return true; // no exceptions probably means DB updated successfully
		} catch (Exception e) {
			return false; // failed to update
		}
	} // updateProject()
	
	private void setProject(ResultSet row, Project p) throws SQLException {
		p.projectNumber = row.getInt("projectNumber");
		p.erfNumber = row.getInt("erfNumber");
		p.projectFee = row.getDouble("projectFee");
		p.amountPaid = row.getDouble("amountPaid");
		p.finalise = row.getBoolean("finalise");
		p.projectName = row.getString("projectName");
		p.physicalAddress = row.getString("address");
		p.buildingType = row.getString("buildingType");
		p.deadline = row.getString("deadline");
		p.completionDate = row.getString("completionDate");
	} // setProject()

	private void setPerson(ResultSet row, Person p) throws SQLException {
		p.name = row.getString("name");
		p.mobile = row.getString("mobile");
		p.email = row.getString("email");
		p.physicalAddr = row.getString("address");
//		}
	} // setPerson

}
