package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class StoreClient {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		try {
			Store store1 = (Store) Naming.lookup("rmi://localhost:1099/Mag1");
			Store store2 = (Store) Naming.lookup("rmi://localhost:1098/Mag2");
			Store store3 = (Store) Naming.lookup("rmi://localhost:1097/Mag3");

			// Obtenez l'ingrédient sélectionné à partir de l'entrée utilisateur
			Scanner scanner = new Scanner(System.in);
			System.out.println("Selectionnez le numero de l'ingredient :");
			System.out.println("cornichons");
			System.out.println("safran");
			System.out.println("sel");
			System.out.println("poivre");

			String selectedIngredient = scanner.next();

			float prix1 = store1.getPrice(selectedIngredient);
			float prix2 = store2.getPrice(selectedIngredient);
			float prix3 = store3.getPrice(selectedIngredient);

			float lowestprix = Float.MAX_VALUE;
			String MagPrixBas = "";
			if (prix1 < prix2 && prix2 < prix3) {
				lowestprix = prix1;
				MagPrixBas = "Mag1";
			} else if (prix2 < prix1 && prix2 < prix3) {
				lowestprix = prix2;
				MagPrixBas = "Mag2";
			} else if (prix3 < prix1 && prix3 < prix2) {
				lowestprix = prix3;
				MagPrixBas = "Mag3";
			}
			System.out.println("Le prix le plus bas de l'ingrédient '" + selectedIngredient + "' est de '" + lowestprix
					+ "' et se trouve chez '" + MagPrixBas + "'.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
