/*
 * ads-labs
 *
 * Copyright (c) 2019, Milten Plescott. All rights reserved.
 *
 * SPDX-License-Identifier: MIT
 */
package adslabs.lab1;

import adslabs.Launchable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Milten Plescott
 */
public class AssignmentA implements Launchable {
	// pick a single number from each row of the matrix so that they all sum to zero (or as close to zero as you can)

	private final Matrix mat;
	private Random rnd = new Random();
	private List<Integer> chosenElements;

	public AssignmentA() {
		mat = new Matrix("/lab1_assignmentA.txt");
		System.out.println("\nAssignment A\n------------");
	}

	public AssignmentA(String fileName, boolean includeNegatives) {
		mat = new Matrix(fileName);
		if (includeNegatives) {
			mat.addNegativeCols();
		}
	}

	@Override
	public void launch() {
		int result;
		result = alg_1();
		printResult("Partial sum closest to zero", result);
		result = alg_1_shuffle();
		printResult("Shuffle rows, then partial sum closest to zero", result);
		int iterations = 100000;
		result = alg_1_shuffleBestOfN(iterations);
		printResult("Shuffle rows, then partial sum closest to zero, best of " + iterations, result);

		// synchronize alg_2_once()and alg_2_repeat() RNG to get comparable results
		rnd.setSeed(1);
		result = alg_2_once();
		printResult("Start all at random, then for each row find best improvement", result);
		iterations = 10;
		rnd.setSeed(1);
		result = alg_2_repeat(iterations);
		printResult("Start all at random, then for each row find best improvement, repeat " + iterations + " times", result);
	}

	private void printResult(String algName, int resultSum) {
		System.out.println(algName + ", sum = " + resultSum);
	}

	// 1. choose a random element from first row
	// 2. from each next row choose an element, so that the partial sum is closest to zero
	private int alg_1() {
		chosenElements = new ArrayList<>();
		int partialSum = getRandomElementFromRow(0);
		chosenElements.add(partialSum);
		for (int r = 1; r < mat.getRows(); r++) {
			List<Integer> list = addNumberToEveryElement(mat.getRow(r), partialSum);
			int winnerIndex = absValueMin(list);
			int winnerElement = mat.getRow(r).get(winnerIndex);
			partialSum += winnerElement;
			chosenElements.add(winnerElement);
		}
		return partialSum;
	}

	private int getRandomElementFromRow(int row) {
		return mat.getRow(row).get(rnd.nextInt(mat.getCols()));
	}

	private List<Integer> addNumberToEveryElement(List<Integer> list, int n) {
		List<Integer> newList = new ArrayList<>();
		for (int i : list) {
			newList.add(i + n);
		}
		return newList;
	}

	// return the index of the minimum absolute value of the elements of list
	private int absValueMin(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			list.set(i, Math.abs(list.get(i)));
		}
		return list.indexOf(Collections.min(list));
	}

	// shuffle, then run alg_1()
	private int alg_1_shuffle() {
		mat.shuffleRows();
		return alg_1();
	}

	private int alg_1_shuffleBestOfN(int n) {
		int best = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int current = alg_1_shuffle();
			if (Math.abs(current) < Math.abs(best)) {
				best = current;
			}
		}
		return best;
	}

	// choose single element from each row at random
	// then in each row see if you can improve the overall sum
	private int alg_2() {
		int sum = sumList(chosenElements);
		for (int r = 0; r < mat.getRows(); r++) {
			int correctedSum = sum - chosenElements.get(r); // partial sum of the 0 to (r-1)-th row
			List<Integer> list = addNumberToEveryElement(mat.getRow(r), correctedSum);
			int winnerIndex = absValueMin(list);
			int winnerElement = mat.getRow(r).get(winnerIndex);
			sum = correctedSum + winnerElement;
			chosenElements.set(r, winnerElement);
		}
		if (sum != sumList(chosenElements)) {
			System.out.println("sum: " + sum);
			System.out.println("sumList(): " + sumList(chosenElements));
			throw new AssertionError("Sum not equal summed elements.");
		}
		else {
			return sum;
		}
	}

	private int alg_2_once() {
		chooseAllRandom();
		return alg_2();
	}

	private int alg_2_repeat(int n) {
		int sum = Integer.MAX_VALUE;
		chooseAllRandom();
		for (int i = 0; i < n; i++) {
			sum = alg_2();
		}
		return sum;
	}

	// choose a single element from each row at random
	private void chooseAllRandom() {
		chosenElements = new ArrayList<>();
		for (int r = 0; r < mat.getRows(); r++) {
			chosenElements.add(getRandomElementFromRow(r));
		}
	}

	private int sumList(List<Integer> list) {
		int sum = 0;
		for (int i : list) {
			sum += i;
		}
		return sum;
	}

}
