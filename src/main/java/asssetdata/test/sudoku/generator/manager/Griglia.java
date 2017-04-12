package asssetdata.test.sudoku.generator.manager;

import java.io.Serializable;

public class Griglia implements Serializable {

	private static final long serialVersionUID = 913898407474630231L;	
	public Cella[][] celle = new Cella[9][9];

	public Griglia() {
		for (int riga = 0; riga < 9; riga++) {
			for (int colonna = 0; colonna < 9; colonna++) {
				Cella cella = new Cella(riga, colonna, 0);
				this.celle[riga][colonna] = cella;
			}
		}
	}
	public Cella[][] getCelle() {
		return celle;
	}
	public void setCelle(Cella[][] celle) {
		this.celle = celle;
	}
	public int getValoreCella(int row, int col) {
		return celle[row][col].getValore();
	}
	public Cella getCella(int row, int col) {
		return celle[row][col];
	}
	public Cella addCella(Cella cella, int row, int col) {
		return celle[row][col] = cella;
	}
	public Cella removeCella(int row, int col) {
		return celle[row][col] = null;
	}
	public void stampaGriglia() {
		for (int i = 0; i < 9; i++) {
			if (i%3 == 0) { 
				System.out.println("-------------------------------------------");
			}
			for (int j = 0; j < 9; j++) {
				String val = "";
				if (j%3 == 0) {
					val += "| ";
				}
				
				String numero = celle[i][j] != null ? String.valueOf(celle[i][j].getValore()) : "-";
				if ( celle[i][j] != null && !celle[i][j].isDaSettare() ) {
					val += "["+numero+"]";
				} else {
					val += " "+numero+" ";
				}
				
				System.out.print(val +" ");
			}
			System.out.println("|");
		}
		System.out.println("-------------------------------------------");
	}
	public Cella[] getRigaMatrice(int row) {
		return celle[row];
	}
	public void setRigaMatrice(Cella[] rigaMatrice, int row) {
		celle[row] = rigaMatrice;
	}
	public void cleanGriglia() {
		celle = new Cella[9][9];
	}
	public void pulisciCella(int riga, int colonna) {
		this.celle[riga][colonna] = new Cella(riga, colonna, 0);
	}	
}
