package Machine2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Client.Store;

public class StoreManager2 extends UnicastRemoteObject implements Store {
	private String storeName;

	public StoreManager2(String storeName) throws RemoteException {
		this.storeName = storeName;
	}

	@Override
	public float getPrice(String ingredient) throws RemoteException {
		String NomFichier = "C:\\Users\\mahad\\OneDrive\\Desktop\\workspacejava\\ProjetInfoRapartieRmi\\src\\Machine2\\ingredientMagasin2.txt";

		try {
			BufferedReader lecture = new BufferedReader(new FileReader(NomFichier));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1.0f; // Ingrédient non trouvé
	}

	public static void main(String[] args) {
		try {
			Store store2 = new StoreManager2("Mag2");
			Registry registry = LocateRegistry.createRegistry(1098);
			registry.rebind("Mag2", store2);
			System.out.println("Server du Magasin 2 est disponible en ligne.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
