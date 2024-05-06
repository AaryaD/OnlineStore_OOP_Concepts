package client;

import java.rmi.RemoteException;
import java.util.Scanner;

public interface UserActions {
	void executeAction(MenuOptions option, Scanner sc)throws RemoteException;
	MenuOptions showMenu(Scanner sc);
}
