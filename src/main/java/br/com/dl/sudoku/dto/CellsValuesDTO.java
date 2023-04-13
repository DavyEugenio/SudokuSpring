package br.com.dl.sudoku.dto;

import java.util.List;

public class CellsValuesDTO {
	private List<Integer> cells;
	private int attempts;
	
	public CellsValuesDTO() {
	}

	public List<Integer> getCells() {
		return cells;
	}

	public int getAttempts() {
		return attempts;
	}
	
}
