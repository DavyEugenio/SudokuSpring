package br.com.dl.sudoku.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.dl.sudoku.model.Board;
import br.com.dl.sudoku.model.Cell;
import br.com.dl.sudoku.model.Result;

@Service
public class SudokuService {

	public Board initBoard(List<Integer> values) {
		Board board = new Board();
		for (int i = 0; i < 81; i++) {
            if (values.get(i) != 0) {
                board.defCell(i, values.get(i));
            }
        }
		return board;
	}
	
    public Result solveGame(Board board, int attempts) {
        List<Cell> notDef = new ArrayList<>();
        notDef.addAll(board.getCells().stream().filter(c-> !c.isDef()).toList());
        int cont = 0;
        while (!notDef.isEmpty() && cont < attempts) {
        	List<Cell> defs = new ArrayList<>();
            for(Cell c : notDef) {
                int index = board.getCells().indexOf(c);
                int lineIndex = board.getLineIndex(index);
                int colIndex = board.getColIndex(index);
                int sqrIndex = board.getSquareIndex(index);
                
                List<Cell> line = board.getLine(lineIndex);
                List<Cell> col = board.getColumn(colIndex);
                List<Cell> sqr = board.getSquare(sqrIndex);
                
                Set<Integer> notIncludes = new HashSet<>();
                getNotIncludes(line, c, notIncludes);
                getNotIncludes(col, c, notIncludes);
                getNotIncludes(sqr, c, notIncludes);
                c.setNumbers(new ArrayList<Integer>(notIncludes));
                removeDefsInList(line, c);
                removeDefsInList(col, c);
                removeDefsInList(sqr, c);
                
                
                if(c.isDef()){
                    defs.add(c);
                }
             }
            cont++;
            notDef.removeAll(defs);
            defs.clear();
        }
        return new Result(board.getCells(), notDef.isEmpty(), cont);
    }
    
    private void getNotIncludes(List<Cell> list, Cell c, Set<Integer> notIncludes){
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for(Cell cell: list){
                    for(Integer n: nums)
                        if(!cell.getNumbers().contains(n))
                            notIncludes.add(n);
                }
    }
    
   private void removeDefsInList(List<Cell> list, Cell c){
       list.remove(c);
       list.stream().filter(ce->ce.isDef()).map(ce -> ce.getNumbers().get(0)).forEach(n -> c.removeNumber(n));
   }

}
