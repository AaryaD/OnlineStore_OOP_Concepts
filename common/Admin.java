package common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Admin extends UnicastRemoteObject implements Person {
	/**
	 * this Admin class implements the interface Person and implements login(), setUsername() and setPassword() methods
	 * common to customers and admins. It additionally has methods like addCustomer(), removeCustomer(), getCustomers(), addAdmin()
	 * which are specific to admins only.
	 */
	
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    public static List<Person> customers = new CopyOnWriteArrayList<>();;
    public static List<Person> admins = new CopyOnWriteArrayList<>();;

    public Admin() throws RemoteException {
        this.username = "";
        this.password = "";
        
        Person cust1 = new Customer("cust1","cust1@00");
		Person cust2 = new Customer("cust2", "cust2@00");
		Person cust3 = new Customer("cust3", "cust3@00");
		Person cust4 = new Customer("cust4", "cust4@00");
		customers.add(cust1);
		customers.add(cust2);
		customers.add(cust3);
		customers.add(cust4);
		
		Person admin1 = new Admin("admin1","admin1@00");
		Person admin2 = new Admin("admin2", "admin2@00");
		admins.add(admin1);
		admins.add(admin2);
        
    }
    
    public Admin(String username, String password) throws RemoteException {
    	this.username = username;
    	this.password = password;
    }

    public String getUsername() throws RemoteException {
        return username;
    }

    public void setUsername(String username) throws RemoteException {
        this.username = username;
    }

    public String getPassword() throws RemoteException {
        return password;
    }

    public void setPassword(String password) throws RemoteException {
        this.password = password;
    }
    
    public static void autoLoad() throws RemoteException {
        //add few default customers and admins to the list of persons to check if login is successful or not; mirroring a database
    	Person cust1 = new Customer("cust1","cust1@00");
		Person cust2 = new Customer("cust2", "cust2@00");
		Person cust3 = new Customer("cust3", "cust3@00");
		Person cust4 = new Customer("cust4", "cust4@00");
		customers.add(cust1);
		customers.add(cust2);
		customers.add(cust3);
		customers.add(cust4);
		
		Person admin1 = new Admin("admin1","admin1@00");
		Person admin2 = new Admin("admin2", "admin2@00");
		admins.add(admin1);
		admins.add(admin2);
    }

    @Override
    public boolean login(String username, String password) throws RemoteException {
    	for (Person person: admins) {
    		if(person.getUsername().equals(username) && person.getPassword().equals(password)) {
    			System.out.println("The admin is successfully logged in!");
    			return true;
    		}
    	}
        return false;
    }
    
    @Override
	public boolean register(String username, String password) throws RemoteException {
    	for (Person person : admins) {
            if (person.getUsername().equals(username) && person.getPassword().equals(password)) {
                // Return false immediately if admin already exists
                return false;
            }
        }
        // If no existing admin is found, add a new one
        Admin.admins.add(new Admin(username, password));
        return true;
    }


    public void addCustomer(Customer customer) throws RemoteException {
        customers.add(customer);
    }
    
    public List<Person> getCustomers() throws RemoteException {
        return customers;
    }

	public void removeCustomer(String customer) throws RemoteException {
        customers.remove(customer);
    }

    public void addAdmin(Admin admin) throws RemoteException {
        admins.add(admin);
    }	
}
