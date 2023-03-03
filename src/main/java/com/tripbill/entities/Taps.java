package com.tripbill.entities;

import java.util.List;

/**
 * @author nsoliveau
 *
 * Class that represents all customers' acces at any station
 */
public class Taps {

	private List<Tap> taps;

	public Taps() {
		super();
	}
	
	public Taps(List<Tap> taps) {
		this.taps = taps;
	}

	public List<Tap> getTaps() {
		return taps;
	}

	public void setTaps(List<Tap> theTaps) {
		this.taps = theTaps;
	}
}
