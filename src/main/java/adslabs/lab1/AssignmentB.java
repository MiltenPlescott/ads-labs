/*
 * ads-labs
 *
 * Copyright (c) 2019, Milten Plescott. All rights reserved.
 *
 * SPDX-License-Identifier: MIT
 */
package adslabs.lab1;

import adslabs.Launchable;

/**
 *
 * @author Milten Plescott
 */
public class AssignmentB implements Launchable {
	// same as the assignment A, but for each number you can choose +/-

	private final AssignmentA a;

	public AssignmentB() {
		a = new AssignmentA("/lab1_assignmentB.txt", true);
		System.out.println("\nAssignment B\n------------");
	}

	@Override
	public void launch() {
		a.launch();
	}

}
