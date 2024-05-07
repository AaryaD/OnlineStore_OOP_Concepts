package common;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface PersonFactory extends Remote{
	public Person factorPerson(String type) throws RemoteException;
}
