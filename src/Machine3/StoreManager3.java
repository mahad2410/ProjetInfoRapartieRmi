package Machine3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Client.Store;

public class StoreManager3 extends UnicastRemoteObject implements Store {
	private String storeName;

	public StoreManager3(String storeName) throws RemoteException {
		this.storeName = storeName;
	}

	@Override
	public float getPrice(String ingredient) throws RemoteException {
		String NonFichier = "C:\\Users\\mahad\\OneDrive\\Desktop\\workspacejava\\ProjetInfoRapartieRmi\\src\\Machine3\\ingredientMagasin3.txt";

		try {
			BufferedReader lecture = new BufferedReader(new FileReader(NonFichier));
			String ligne;
			while ((ligne = lecture.readLine()) != null) {
				String[] parts = ligne.split(", ");
				for (String part : parts) {
					String[] item = part.split(" ");
					if (item.length == 2 && item[0].equals(ingredient)) {
						return Float.parseFloat(item[1]);
					}
				}
				lecture.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return -1.0f; // Ingrédient non trouvé
	}

	public static void main(String[] args) {
		try {
			Store store3 = new StoreManager3("Mag3");
			Registry registry = LocateRegistry.createRegistry(1097);
			registry.rebind("Mag3", store3);
			System.out.println("Server du Magasin 3 est disponible en ligne.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
