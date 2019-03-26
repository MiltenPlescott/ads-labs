/*
 * ads-labs: binary search tree
 *
 * Copyright (c) 2019, Milten Plescott. All rights reserved.
 *
 * SPDX-License-Identifier: MIT
 */
package adslabs.lab7;

import adslabs.Launchable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milten Plescott
 */
public class BinarySearchTree implements Launchable {

	/*
	DATA:
		- sequence of positive integers:
			- text file with 48058 rows
			- single number	in each row
	PROBLEM:
		- Let x be a key in BST.
		- Path to x in BST is a sequence of numbers that are compared agaisnt x when searching BST for x. (root and x inclusive)
		- Given a sequence of numbers (the first number in the sequence is root, the last number is x),
			is it possible to construct a BST that contains a path to x represented by this sequence ?
		- For the sequence in 'lab7.txt' it is NOT possible.
		- Change a single number in the given sequence, so that it is possible to construct a BST that contains
			path to x represented by the sequence.
	 */

	private final List<Integer> numbers;

	public BinarySearchTree() {
		numbers = readLines("/lab7.txt");
	}

	@Override
	public void launch() {
		int root = numbers.get(0);
		int x = numbers.get(numbers.size() - 1);

		if (root == x) {
			System.out.println("FOUND X !!!");
		}
		else {
			int i = 1;
			while (i != numbers.size()) {
				if (numbers.get(i) == x) {
					System.out.println("FOUND X !!!");
					break;
				}
				else if (numbers.get(i) < numbers.get(i - 1) && x < numbers.get(i - 1)) {
					i++;  // turn left
				}
				else if (numbers.get(i) > numbers.get(i - 1) && x > numbers.get(i - 1)) {
					i++;  // turn right
				}
				else {
					System.out.println("           x = " + x);
					System.out.format("ERROR  on  i = %6d%n", i);
					System.out.println("numbers[i-1] = " + numbers.get(i - 1));
					System.out.println("numbers[i]   = " + numbers.get(i));
					System.out.println("numbers[i+1] = " + numbers.get(i + 1));
					if (x < numbers.get(i - 1)) {
						System.out.println("Changing " + i + "-th number " + numbers.get(i) + " to " + (numbers.get(i - 1) - 1));
						numbers.set(i, numbers.get(i - 1) - 1);
					}
					else if (x > numbers.get(i - 1)) {
						System.out.println("Changing " + i + "-th number " + numbers.get(i) + " to " + (numbers.get(i - 1) + 1));
						numbers.set(i, numbers.get(i - 1) + 1);
					}
					System.out.println("numbers[i-1] = " + numbers.get(i - 1));
					System.out.println("numbers[i]   = " + numbers.get(i));
					System.out.println("numbers[i+1] = " + numbers.get(i + 1));
				}
			}
		}
	}

	private List<Integer> readLines(String fileName) {
		List<Integer> lines = new ArrayList<>();
		InputStream is = getClass().getResourceAsStream(fileName);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
			String line;
			while ((line = br.readLine()) != null) {
				try {
					lines.add(Integer.parseInt(line));
				}
				catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return lines;
	}

}
