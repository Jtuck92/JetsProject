package com.skilldistillery.jet;

public class CargoPlane extends Jet implements CargoCarrier {
	public CargoPlane(String type, String model, double speed, int range, long price) {
		super(type, model, speed, range, price);
	}

	@Override
	public void loadCargo() {
		System.out.println("Understood. The " + getModel() + " will be loaded to specification.");
	}

	@Override
	public void fly(Jet j) {
		super.fly(j);
	}

	@Override
	public String toString() {
		return getModel() + "\n" + "Speed: " + getSpeed() + "\n" + "Range: " + getRange() + "\n" + "Cost: "
				+ getPrice();
	}
}
