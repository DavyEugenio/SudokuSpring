package br.com.dl.sudoku.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dl.sudoku.dto.CellsValuesDTO;
import br.com.dl.sudoku.dto.ResultDTO;
import br.com.dl.sudoku.facade.Sudoku;

@RestController
@RequestMapping(value = "/sudoku")
public class SudokuResorce {
	@Autowired
	private Sudoku sudoku;
	
	@PostMapping()
	public ResponseEntity<ResultDTO> solve(@RequestBody CellsValuesDTO cells) {
		return ResponseEntity.ok().body(new ResultDTO(sudoku.initSudoku(cells.getCells(), cells.getAttempts())));
	}
}
