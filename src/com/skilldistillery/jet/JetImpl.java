package com.skilldistillery.jet;

public class JetImpl extends Jet {
	public JetImpl(String type, String model, double speed, int range, long price) {
		super(type, model, speed, range, price);
	}

	@Override
	public void fly(Jet j) {
		// TODO Auto-generated method stub
		super.fly(j);
	}

	@Override
	public String toString() {
		return getModel() + "\n" + "Speed: " + getSpeed() + "\n" + "Range: " + getRange() + "\n" + "Cost: "
				+ getPrice();
	}
}
