package asssetdata.test.sudoku.generator.manager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import asssetdata.test.sudoku.generator.SudokuGenerator;

public class SudokuUtility {

	public Griglia griglia = new Griglia();
	private final static int NUM_CELLE_PIENE = 34;

	public static void main(String[] args) {
		SudokuUtility sudokuUtility = new SudokuUtility();
		sudokuUtility.generaGriglia();
	}
	
	/**
	 * metodo utilizzato per la generazione degli indici da rimuove dal'array monodimensionale
	 * contenente tutti i valori della griglia generata
	 * 
	 * @param dimensioneGriglia
	 * @param numCelleDaRimuovere
	 * @return
	 */
	private List<Integer> getIndiciCelleDaRimuovere(int dimensioneGriglia, int numCelleDaRimuovere){
		List<Integer> indiciCelleDaRimuovere = new ArrayList<Integer>(numCelleDaRimuovere);
		Random random = new Random();
		for (int i = 0; i < numCelleDaRimuovere; i++) {
			int indiceDaRimuovere = random.nextInt(dimensioneGriglia);
			// System.out.println(indiceDaRimuovere);
			if ( indiciCelleDaRimuovere.contains(indiceDaRimuovere)) {
				i--;
				continue;
			}
			indiciCelleDaRimuovere.add(indiceDaRimuovere);				
		}
		return indiciCelleDaRimuovere;
	}

	/**
	 * generazione griglia da completare, inserendo per ciascuna cella la soluzione
	 * 
	 * @return
	 */
	public Griglia generaGriglia() {
		try {
			int dimensioneGriglia = SudokuGenerator.DIMENSIONE_GRIGLIA*SudokuGenerator.DIMENSIONE_GRIGLIA;
			int numCelleDaRimuovere = dimensioneGriglia-NUM_CELLE_PIENE;
			int colonna = 0;
			int riga = 0;
			
			// generazione griglia completa			
			int[] valori = SudokuGenerator.getInstance().generaSudoku();
			
			// generazione indici da rimuovere dalla griglia completa
			List<Integer> indiciCelleDaRimuovere = getIndiciCelleDaRimuovere(dimensioneGriglia, numCelleDaRimuovere);

			// generazione griglia da completare
			for (int i = 0; i < dimensioneGriglia; i++) {
				colonna = i%SudokuGenerator.DIMENSIONE_GRIGLIA;
				riga = (int) (i/(double)SudokuGenerator.DIMENSIONE_GRIGLIA);
				int valore = 0;
				boolean daSettare = true;
				if ( !indiciCelleDaRimuovere.contains(i) ) {
					valore = valori[i+1];
					daSettare = false;
				}
				Cella cella = new Cella(riga, colonna, valore);
				cella.setValoreCorretto(valori[i+1]);
				cella.setDaSettare(daSettare);
				griglia.addCella(cella, riga, colonna);

			}

			// stampa griglia
			griglia.stampaGriglia();

		} catch (Exception e) { 
			e.printStackTrace();
		}
		return griglia;
	}

	/**
	 * al primo valore non corretto viene interrotto il ciclo e viene marcato il flag 
	 * isValoreCorretto della cella errato
	 * 
	 * @param griglia
	 * @return
	 */
	public Griglia verificaGriglia() {
		Cella[][] celle = griglia.getCelle();
		try {
			for (int riga = 0; riga < 9; riga++) {
				for (int colonna = 0; colonna < 9; colonna++) {
					Cella cella = celle[riga][colonna];
					if ( cella.getValore() != cella.getValoreCorretto() && cella.isDaSettare() && (cella.getValore() == 0 || !isCellaValida(riga, colonna, cella.getValore())) ) {	
						cella.setCorretto(false);

					} else {
						cella.setCorretto(true);
					}
				}
			}
			
			return griglia;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return griglia;
	}

	/**
	 * meotodo che verifica le regole del sudoku
	 * 
	 * @param riga
	 * @param colonna
	 * @param valore
	 * @return
	 */
	private boolean isCellaValida(int riga, int colonna, int valore) {
		boolean isValid = true;

		// controllo valori ripetuti nella riga
		for (int i = 0; i < 9; i++) {
			if (i == colonna){
				continue;
			}
			if (griglia.getCella(riga, i) != null && griglia.getValoreCella(riga, i) == valore) {
				return false;
			}
		}

		// controllo valori ripetuti nella colonna
		for (int i = 0; i < 9; i++) {	
			if (i == riga){
				continue;
			}
			if (griglia.getCella(i, colonna) != null && griglia.getValoreCella(i, colonna) == valore) {
				return false;
			}
		}
		
		// controllo nelle sottogriglie
		int sottoRiga = riga - (riga % 3);
		int sottoColonna = colonna - (colonna % 3);
		for (int sRiga = sottoRiga; sRiga < sottoRiga + 3; sRiga++) {
			for (int sColonna = sottoColonna; sColonna < sottoColonna + 3; sColonna++) {
				if (sRiga==riga && sColonna==colonna){
					continue;
				}
				if (griglia.getCella(sRiga, sColonna) != null && griglia.getValoreCella(sRiga, sColonna) == valore) {
					return false;
				}
			}
		}
		return isValid;
	}
	
	public Griglia getGriglia() {
		return griglia;
	}

	public void setGriglia(Griglia griglia) {
		this.griglia = griglia;
	}
}