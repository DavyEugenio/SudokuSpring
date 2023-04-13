package br.com.dl.sudoku.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dl.sudoku.model.Board;
import br.com.dl.sudoku.model.Result;
import br.com.dl.sudoku.service.SudokuService;

@Service
public class Sudoku {
	@Autowired
	private SudokuService service;
	
	public Result initSudoku(List<Integer> cells, int attempts) {
		Board board = service.initBoard(cells);
		return service.solveGame(board, attempts);
	}
}
