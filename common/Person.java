package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Person extends Remote {
	
	public void setUsername(String string) throws RemoteException;
	
	public void setPassword(String string) throws RemoteException;
	
	public String getUsername() throws RemoteException;
	
	public String getPassword() throws RemoteException;
	
	public boolean login(String username, String password) throws RemoteException;
	
	public boolean register(String username, String password) throws RemoteException;
} 
