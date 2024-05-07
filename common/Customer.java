package common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Customer extends UnicastRemoteObject implements Person {
	/**
	 * this Customer class implements the interface Person and implements login(), setUsername() and setPassword() methods
	 * common to customers and admins.
	 */
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	List<Person> persons;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() throws RemoteException{
		return password;
	}

	public void setPassword(String password) throws RemoteException{
		this.password = password;
	}
	
    public Customer(String username, String password)throws RemoteException {
		this.username=username;
		this.password=password;
	}

	public Customer() throws RemoteException {
        this.username=" ";
        this.password=" ";
        //add few default customers to the list of persons to check if login is successful or not; mirroring a database
        this.persons = new ArrayList<Person>();
        Person cust1 = new Customer("cust1","cust1@00");
		Person cust2 = new Customer("cust2", "cust2@00");
		Person cust3 = new Customer("cust3", "cust3@00");
		Person cust4 = new Customer("cust4", "cust4@00");
		this.persons.add(cust1);
		this.persons.add(cust2);
		this.persons.add(cust3);
		this.persons.add(cust4);
		Admin.autoLoad();
    }

	@Override
	public boolean login(String username, String password) throws RemoteException {
		for (Person person: Admin.customers) {
    		if(person.getUsername().equals(username) && person.getPassword().equals(password)) {
    			System.out.println("The customer is successfully logged in!");
    			return true;
    		}
    	}
        return false;
    }
	
	@Override
	public boolean register(String username, String password) throws RemoteException {
		for (Person person : Admin.customers) {
            if (person.getUsername().equals(username) && person.getPassword().equals(password)) {
                // Return false immediately if customer already exists
                return false;
            }
        }
        // If no existing customer is found, add a new one
        Admin.customers.add(new Customer(username, password));
        return true;
	}

}