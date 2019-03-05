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
import adslabs.lab2.Foo;
import adslabs.lab3.Bar;
import adslabs.lab4.Knapsack;

/**
 *
 * @author Milten Plescott
 */
public class AdsLabs {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// Launchable lab1 = new AssignmentA();
		// lab1.launch();
		// lab1 = new AssignmentB();
		// lab1.launch();
		// Launchable lab2 = new Foo();
		// lab2.launch();
		// Launchable lab3 = new Bar();
		// lab3.launch();

		Launchable lab4 = new Knapsack();
		lab4.launch();

	}

}
