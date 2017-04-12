package it.assetdata.test.sudoku.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import asssetdata.test.sudoku.generator.manager.Cella;
import asssetdata.test.sudoku.generator.manager.Griglia;
import it.assetdata.test.sudoku.service.SudokuService;

@Path("/")
public class SudokuController {
	
	private SudokuService service = new SudokuService();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public Cella[][] getGrigliaSudoku() {
		Griglia griglia = service.generaGriglia();
		return griglia.getCelle();
	}
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public Cella[][] verificaGrigliaSudoku(Cella[][] celle) {
    	Griglia griglia = service.verificaGrigliaSudoku(celle);
    	return griglia.getCelle();
	}


}
