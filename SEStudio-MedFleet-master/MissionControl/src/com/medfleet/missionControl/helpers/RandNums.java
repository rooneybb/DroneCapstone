package com.medfleet.missionControl.helpers;

import java.util.Random;

public class RandNums {
	/**
	 * Rand int.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}

	/**
	 * Rand double.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the double
	 */
	public static double randDouble(double min, double max) {
	    Random rand = new Random();
	    return min = (max - min) * rand.nextDouble();
	}
}
