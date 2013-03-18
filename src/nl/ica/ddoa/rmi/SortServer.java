package nl.ica.ddoa.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class SortServer {
	private static final String HOST = "localhost";
	
	private SortServer(){
		
	}

	public static void main(String[] args) throws RemoteException {
		SortFactory sf = new SortFactory();
		String rmiObjectName = "rmi://" + HOST + "/SortFactory";
		
		try {
			System.out.println("SortServer: Binding SortFactory...");
			Naming.rebind(rmiObjectName,sf);
			System.out.println("SortServer: SortFactory bound.");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
