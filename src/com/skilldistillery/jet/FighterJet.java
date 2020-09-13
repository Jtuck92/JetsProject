package com.skilldistillery.jet;

public class FighterJet extends Jet implements CombatReady {
	public FighterJet(String type, String model, double speed, int range, long price) {
		super(type, model, speed, range, price);
	}

	@Override
	public void dogFight() {
		System.out.println(getModel() + " is armed for war. Give the word to engage.");
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
