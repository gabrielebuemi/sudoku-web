package asssetdata.test.sudoku.generator;

import java.util.ArrayList;
import java.util.Date;

/**
 * Algoritmo di generazione da: https://www.go4expert.com/forums/sudoku-generation-algorithm-java-t27665/
 *
 */
public class SudokuGenerator {
	public final static int DIMENSIONE_GRIGLIA = 9;
	private ArrayList<Integer> cols[];
	private ArrayList<Integer> rows[];
	private ArrayList<Integer> boxes[];
	private int board[] = new int[1+(DIMENSIONE_GRIGLIA*DIMENSIONE_GRIGLIA)];
	
	private static SudokuGenerator instance;
	
	public static SudokuGenerator getInstance() {
		if (instance == null) {
			instance = new SudokuGenerator();
		}
		return instance;
	}

//	private static void main(String[] args) {
//		System.out.println("###### Sudoku generation basic algorithm by ManZzup ######");
//		resetBoard();
//		Date d1 = new Date();
//		generate(1);
//		Date d2 = new Date();
//		System.out.println("Time for generation : " + (d2.getTime() - d1.getTime()) + " milis");
//		System.out.println();
//		printAry(board);
//	}
	
	private SudokuGenerator() {
		super();
	}

	public int[] generaSudoku(){
		System.out.println("###### Sudoku generation basic algorithm by ManZzup ######");
		resetBoard();
		Date d1 = new Date();
		generate(1);
		Date d2 = new Date();
		System.out.println("Time for generation : " + (d2.getTime() - d1.getTime()) + " milis");
		printAry(board);
		System.out.println();
		return board;
	}

	private int getBox(int row,int col){
		if(row <=3){
			if(col <=3){
				return 1;
			}else if(col <=6){
				return 2;
			}else{
				return 3;
			}
		}else if(row <=6){
			if(col <=3){
				return 4;
			}else if(col <=6){
				return 5;
			}else{
				return 6;
			}
		}else if(row <= 9){
			if(col <=3){
				return 7;
			}else if(col <=6){
				return 8;
			}else{
				return 9;
			}
		}
		return 0;
	}

	private boolean generate(int n){
		ArrayList<Integer> pool = new ArrayList<Integer>();
		int col = n%DIMENSIONE_GRIGLIA;
		if(col==0) col=DIMENSIONE_GRIGLIA;
		int row = (int)( Math.ceil(n/(double)DIMENSIONE_GRIGLIA) );
		int box = getBox(row,col);
		//System.out.println(" cell " + n);
		//System.out.println(n + " " + row + " " + col);
		for(int i=1;i<=DIMENSIONE_GRIGLIA;i++){
			if( (rows[row].contains(i) && cols[col].contains(i)) && boxes[box].contains(i)){
				//System.out.print(i + " ");
				pool.add(i);
			}
		}
		//System.out.println();
		int ran;
		Integer sel=0;
		boolean ok = false;
		if(pool.size()==0) return false;
		while(!ok){
			//System.out.println("loop in cell : " + n);
			if(pool.size()==0){
				//System.out.println("wrong +" + n);
				return false;
			}else{
				ran = (int)( Math.ceil(Math.random()*(pool.size()-1)) );
				sel = pool.get(ran);
				//System.out.println(sel);
				board[n] = sel;

				rows[row].remove((Integer)sel);
				cols[col].remove((Integer)sel);
				boxes[box].remove((Integer)sel);
				pool.remove(sel);

				if(n==(DIMENSIONE_GRIGLIA*DIMENSIONE_GRIGLIA)){
					return true;
				}else{
					ok = generate(n+1);
					if(!ok){
						//System.out.println("not okay in " + n);
						rows[row].add(sel);
						cols[col].add(sel);
						boxes[box].add(sel);
					}
				}
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	private void resetBoard(){
		cols = new ArrayList[DIMENSIONE_GRIGLIA+1];
		rows = new ArrayList[DIMENSIONE_GRIGLIA+1];
		boxes = new ArrayList[DIMENSIONE_GRIGLIA+1];

		for(int i=1;i<=DIMENSIONE_GRIGLIA;i++){
			ArrayList<Integer> ary = new ArrayList<Integer>();
			ArrayList<Integer> ary2 = new ArrayList<Integer>();
			ArrayList<Integer> ary3 = new ArrayList<Integer>();
			for(int j=0;j<=DIMENSIONE_GRIGLIA;j++){
				ary.add(j);
				ary2.add(j);
				ary3.add(j);
			}
			cols[i] = ary;
			rows[i] = ary2;
			boxes[i] = ary3;
		}

	}
	private void printAry(int[] ary){
		int root = (int)Math.sqrt(DIMENSIONE_GRIGLIA);
		for(int i=1;i<ary.length;i++){
			System.out.printf("%2d",ary[i]);
			if(i%root==0) System.out.print("  ");
			if(i%DIMENSIONE_GRIGLIA==0) System.out.println();
			if(i%(DIMENSIONE_GRIGLIA*root)==0) System.out.println();
		}
	}
	@SuppressWarnings("unused")
	private void printAryList(ArrayList<Integer> ary){
		for(int i=1;i<=DIMENSIONE_GRIGLIA;i++){
			System.out.println(ary.get(i));
		}
	}
}