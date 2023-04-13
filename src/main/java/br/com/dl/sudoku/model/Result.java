package br.com.dl.sudoku.model;

import java.util.List;

public class Result {
	private List<Cell> cells;
	private boolean solved;
	private int attempts;
	
	public Result(List<Cell> cells, boolean solved, int attempts) {
		this.cells = cells;
		this.solved = solved;
		this.attempts = attempts;
	}

	public List<Cell> getCells() {
		return cells;
	}
	
	public boolean isSolved() {
		return solved;
	}
	
	public int getAttempts() {
		return attempts;
	}
}
