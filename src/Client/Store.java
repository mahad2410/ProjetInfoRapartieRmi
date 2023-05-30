package Client;

public interface Store extends java.rmi.Remote {
	
	float getPrice(String ingredient) throws java.rmi.RemoteException;
}