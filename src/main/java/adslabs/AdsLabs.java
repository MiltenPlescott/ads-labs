/*
 * ads-labs
 *
 * Copyright (c) 2019, Milten Plescott. All rights reserved.
 *
 * SPDX-License-Identifier: MIT
 */
package adslabs;

import adslabs.lab1.AssignmentA;
import adslabs.lab1.AssignmentB;

/**
 *
 * @author Milten Plescott
 */
public class AdsLabs {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Launchable lab1 = new AssignmentA();
		lab1.launch();
		lab1 = new AssignmentB();
		lab1.launch();
	}

}
