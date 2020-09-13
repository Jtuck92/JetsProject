package com.skilldistillery.jet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class JetsApp {
	private AirField airField;
	private static Scanner scanner;

	public JetsApp() {
		airField = new AirField();
	}

	public static void main(String[] args) {
		JetsApp app = new JetsApp();
		app.launch(scanner);
		app.displayUserMenu(scanner);
	}

	private void launch(Scanner scanner) {
		List<Jet> jets = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("jets"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] planeDetails = line.split(",");
				String subclass = planeDetails[0].trim();
				String model = planeDetails[1].trim();
				double speed = Double.parseDouble(planeDetails[2].trim());
				int range = Integer.parseInt(planeDetails[3].trim());
				long price = Long.parseLong(planeDetails[4].trim());
				if (subclass.equals("FighterJet")) {
					Jet fj = new FighterJet(model, model, speed, range, price);
					jets.add(fj);
				}
				if (subclass.equals("CargoPlane")) {
					Jet cp = new CargoPlane(model, model, speed, range, price);
					jets.add(cp);
				}
				if (subclass.equals("JetImpl")) {
					Jet ca = new JetImpl(model, model, speed, range, price);
					jets.add(ca);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		airField.setJets(jets);
	}

	private void displayUserMenu(Scanner scanner) {
		int selection = 0;
		while (selection != 9) {
			System.out.println();
			System.out.println(" ________________________________________________________________ ");
			System.out.println("|                          Fleet Command                         |");
			System.out.println("|----------------------------------------------------------------|");
			System.out.println("|               Greetings, commander. Your orders?               |");
			System.out.println("|                                                                |");
			System.out.println("| 1. List Fleet inventory                                        |");
			System.out.println("| 2. Scramble all craft!                                         |");
			System.out.println("| 3. View fastest craft                                          |");
			System.out.println("| 4. View craft with the furthest range                          |");
			System.out.println("| 5. Load all Transports                                         |");
			System.out.println("| 6. Engage!                                                     |");
			System.out.println("| 7. Commission craft to Fleet                                   |");
			System.out.println("| 8. Decommission craft from Fleet                               |");
			System.out.println("| 9. Resign                                                      |");
			System.out.println("|________________________________________________________________|");
			scanner = new Scanner(System.in);
			selection = scanner.nextInt();
			switch (selection) {
			case 1:
				displayAirField();
				break;
			case 2:
				scrambleAllCraft();
				break;
			case 3:
				fastestCraft();
				break;
			case 4:
				furthestRange();
				break;
			case 5:
				loadAllTransports();
				break;
			case 6:
				readyEngage();
				break;
			case 7:
				addNewCraft(scanner);
				break;
			case 8:
				removeCraft(scanner);
				break;
			case 9:
				System.out.println("Your service has been commendable. Enjoy your retirement, commander...");
				break;
			default:
				System.out.println("Your orders are illogical, please re-issue your command: ");
				selection = scanner.nextInt();
				break;
			}
		}
	}

	private void displayAirField() {
		List<Jet> allJets = airField.getJets();
		for (Jet jet : allJets) {
			System.out.println(jet.toString());
			System.out.println();
		}
	}

	private void scrambleAllCraft() {
		List<Jet> jets = airField.getJets();
		for (Jet jet : jets) {
			jet.fly(jet);
		}
	}

	private void fastestCraft() {
		List<Jet> allJets = airField.getJets();
		Comparator<Jet> comparator = Comparator.comparing(Jet::getSpeed);
		Jet fastest = allJets.stream().max(comparator).get();
		System.out.println("Your quickest vessel is: \n" + fastest);
		System.out.println("Its speed of " + fastest.getSpeed() + " MpH is unmatched!");
	}

	private void furthestRange() {
		List<Jet> allJets = airField.getJets();
		Comparator<Jet> comparator = Comparator.comparing(Jet::getRange);
		Jet longest = allJets.stream().max(comparator).get();
		System.out.println("Your longest range vessel is: \n" + longest);
		System.out.println("Its reach of " + longest.getRange() + " miles is peerless!");
	}

	private void loadAllTransports() {
		List<Jet> allJets = airField.getJets();
		for (Jet jet : allJets) {
			if (jet instanceof CargoPlane) {
				((CargoPlane) jet).loadCargo();
			}
		}
	}

	private void readyEngage() {
		List<Jet> allJets = airField.getJets();
		for (Jet jet : allJets) {
			if (jet instanceof FighterJet) {
				((FighterJet) jet).dogFight();
			}
		}
	}

	public void addNewCraft(Scanner scanner) {
		double speed;
		System.out.println("Enter your craft's type: \nCargoPlane\nFighterJet\nor\nJetImpl");
		String type = scanner.next();
		System.out.println("Enter the name of your new vessel. ");
		String model = scanner.next();
		System.out.println("How fast is this craft? ");
		speed = scanner.nextDouble();
		System.out.println("How far can it fly? ");
		int range = scanner.nextInt();
		System.out.println("What will it cost to build? ");
		long price = scanner.nextLong();
		Jet newCraft = new Jet(type, model, speed, range, price);
		airField.addNewJet(newCraft);
	}

	public void removeCraft(Scanner scanner) {
		ArrayList<Jet> getJets = (ArrayList<Jet>) airField.getJets();
		int decomm;
		int bay = 1;
		System.out.println("These are craft in your fleet.: ");
		System.out.println();
		for (Jet jet : getJets) {
			System.out.println("Bay - " + bay);
			System.out.println(jet);
			System.out.println();
			bay++;
		}
		System.out.println();
		System.out.println("Please enter the bay of the vessel you would like to decommission.");
		decomm = scanner.nextInt();
		switch (decomm) {
		case 1:
			getJets.remove(0);
			break;
		case 2:
			getJets.remove(1);
			break;
		case 3:
			getJets.remove(2);
			break;
		case 4:
			getJets.remove(3);
			break;
		case 5:
			getJets.remove(4);
			break;
		case 6:
			getJets.remove(5);
			break;
		case 7:
			getJets.remove(6);
			break;
		case 8:
			getJets.remove(7);
			break;
		case 9:
			getJets.remove(8);
			break;
		case 10:
			getJets.remove(9);
			break;
		}
		System.out.println("You have ordered the vessel in Bay - " + decomm + " to be decommissioned.");
	}
}
