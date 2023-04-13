package br.com.dl.sudoku.dto;

import java.util.List;

import br.com.dl.sudoku.model.Result;

public class ResultDTO {

	private List<Integer> cells;
	private boolean solved;
	private int attempts;
	
	public ResultDTO() {}
	
	public ResultDTO(Result result) {
		this.cells = result.getCells().stream().map(x->x.getNumbers().size() != 1? 0:x.getNumbers().get(0)).toList();
		this.solved = result.isSolved();
		this.attempts = result.getAttempts();
	}

	public List<Integer> getCells() {
		return cells;
	}

	public boolean isSolved() {
		return solved;
	}

	public int getAttempts() {
		return attempts;
	}
}
