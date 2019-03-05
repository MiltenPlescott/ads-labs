/*
 * ads-labs: knapsack
 *
 * Copyright (c) 2019, Milten Plescott. All rights reserved.
 *
 * SPDX-License-Identifier: BSD-3
 */
package adslabs.lab4;

import adslabs.Launchable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Milten Plescott
 */
public class Knapsack implements Launchable {

	/*
	DATA:
		- 1000 rows, in each row 4 numbers (int, <1;9>), comma delimiters
		- 2 items in each row:
			- 1st number: value of the first item
			- 2nd number: weight of the first item
			- 3rd number: value of the second item
			- 4th number: weight of the second item
	PROBLEM:
		- try to fill a knapsack to maximize its value (sum of values of items in the knapsack)
		- the sum of weights of items in the knapsack cannot exceed 2000
		- from each row you can choose only 0 or 1 items to put into the knapsack (not both)
	 */

	private final List<String> lines;

	public Knapsack() {
		this.lines = readLines("/lab4.txt");
	}

	private List<String> readLines(String fileName) {
		List<String> lines = new ArrayList<>();
		InputStream is = getClass().getResourceAsStream(fileName);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return lines;
	}

	@Override
	public void launch() {
		List<Integer> values_1 = new ArrayList<>();
		List<Integer> weights_1 = new ArrayList<>();
		List<Integer> values_2 = new ArrayList<>();
		List<Integer> weights_2 = new ArrayList<>();
		for (String line : this.lines) {
			String[] str = line.split(",");
			try {
				values_1.add(Integer.parseInt(str[0]));
				weights_1.add(Integer.parseInt(str[1]));
				values_2.add(Integer.parseInt(str[2]));
				weights_2.add(Integer.parseInt(str[3]));
			}
			catch (NumberFormatException ex) {
				System.out.println("Line '" + line + "' could not be parsed into ints.");
			}
		}

		int weightLimit = 2000;
		int itemPairs = values_1.size();
		List<Integer> maxValues = new ArrayList<>(weightLimit + 1);

		for (int i = 0; i < weightLimit + 1; i++) {
			maxValues.add(0);
		}
		for (int i = 0; i < itemPairs; i++) {
			for (int j = weightLimit; j >= Math.min(weights_1.get(i), weights_2.get(i)); j--) {
				if (j >= weights_1.get(i) && j >= weights_2.get(i)) {
					int max = Math.max(maxValues.get(j - weights_1.get(i)) + values_1.get(i), maxValues.get(j - weights_2.get(i)) + values_2.get(i));
					if (max > maxValues.get(j)) {
						maxValues.set(j, max);
					}
				}
				else if (j >= weights_1.get(i)) {
					if (maxValues.get(j - weights_1.get(i)) + values_1.get(i) > maxValues.get(j)) {
						maxValues.set(j, maxValues.get(j - weights_1.get(i)) + values_1.get(i));
					}
				}
				else if (j >= weights_2.get(i)) {
					if (maxValues.get(j - weights_2.get(i)) + values_2.get(i) > maxValues.get(j)) {
						maxValues.set(j, maxValues.get(j - weights_2.get(i)) + values_2.get(i));
					}
				}
			}
		}

		System.out.println("" + Collections.max(maxValues));
		System.out.println("" + maxValues.get(weightLimit));
		// 4557
	}

}
