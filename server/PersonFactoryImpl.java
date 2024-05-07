package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.Admin;
import common.Customer;
import common.Person;
import common.PersonFactory;

public class PersonFactoryImpl extends UnicastRemoteObject implements PersonFactory {
    /**
	 * This class implements the Factory class of Person to create a new person who could be either a customer or an admin.
	 */
	private static final long serialVersionUID = 1L;

	public PersonFactoryImpl() throws RemoteException {
        super();
    }

    public Person factorPerson(String type) throws RemoteException {
        if (type.equalsIgnoreCase("CUSTOMER")) {
            return new Customer();
        } else if (type.equalsIgnoreCase("ADMIN")) {
            return new Admin();
        } else {
            throw new IllegalArgumentException("The type"  + type + "does not exist!");
        }
    }

}