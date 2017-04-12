package it.assetdata.test.sudoku.service;

import asssetdata.test.sudoku.generator.manager.Cella;
import asssetdata.test.sudoku.generator.manager.Griglia;
import asssetdata.test.sudoku.generator.manager.SudokuUtility;

public class SudokuService {
	
	public Griglia generaGriglia() {
		SudokuUtility sudokuUtility = new SudokuUtility();
		return sudokuUtility.generaGriglia();
	}

	public Griglia verificaGrigliaSudoku(Cella[][] celle) {
		SudokuUtility sudokuUtility = new SudokuUtility();
		Griglia griglia = new Griglia();
		griglia.setCelle(celle);
		griglia.stampaGriglia();
		sudokuUtility.setGriglia(griglia);
		return sudokuUtility.verificaGriglia();
	}
	

}
