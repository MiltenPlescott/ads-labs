/*
 * ads-labs
 *
 * Copyright (c) 2019, Milten Plescott. All rights reserved.
 *
 * SPDX-License-Identifier: MIT
 */
package adslabs.lab1;

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
public class Matrix {

	private int rows;
	private int cols;
	private List<List<Integer>> matrixElements;

	protected Matrix(String fileName) {
		List<String> lines = readLines(fileName);
		parseLines(lines);
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

	// file format:
	//     first line  -> number of rows
	//     second line -> number of cols
	//     other lines -> matrix rows with columns separated by a single space
	private void parseLines(List<String> lines) {
		matrixElements = new ArrayList<>();
		this.rows = Integer.parseInt(lines.get(0));
		this.cols = Integer.parseInt(lines.get(1));
		for (String line : lines.subList(2, lines.size())) {
			List<Integer> list = new ArrayList<>();
			for (String cell : line.split(" ")) {
				list.add(Integer.parseInt(cell));
			}
			matrixElements.add(list);
		}
	}

	protected void addNegativeCols() {
		for (int row = 0; row < rows; row++) {
			List<Integer> list = new ArrayList<>();
			for (int col = 0; col < cols; col++) {
				list.add(-1 * matrixElements.get(row).get(col));
			}
			matrixElements.get(row).addAll(list);
		}
	}

	protected void shuffleRows() {
		Collections.shuffle(matrixElements);
	}

	protected void printMatrix() {
		for (List<Integer> row : matrixElements) {
			System.out.println("" + row);
		}
	}

	protected List<Integer> getRow(int rowIndex) {
		return matrixElements.get(rowIndex);
	}

	protected int getRows() {
		return rows;
	}

	protected int getCols() {
		return cols;
	}

}
