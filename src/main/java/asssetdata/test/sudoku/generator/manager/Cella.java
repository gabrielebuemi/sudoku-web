package asssetdata.test.sudoku.generator.manager;

import java.io.Serializable;

public class Cella implements Serializable {

	private static final long serialVersionUID = 1007253954787115389L;
	private int riga;
	private int colonna;
	private int valore;
	private int valoreCorretto;
	private boolean isDaSettare = true;
	private boolean isCorretto = true;

	public Cella() {
		super();
	}

	public Cella(int riga, int colonna, int valore) {
		this.riga = riga;
		this.colonna = colonna;
		this.valore = valore;
	}

	public Cella(int riga, int colonna, int valore, int valoreCorretto, boolean isDaSettare, boolean isCorretto) {
		this.riga = riga;
		this.colonna = colonna;
		this.valore = valore;
		this.valoreCorretto = valoreCorretto;
		this.isDaSettare = isDaSettare;
		this.isCorretto = isCorretto;
	}

	public int getRiga() {
		return riga;
	}

	public void setRiga(int riga) {
		this.riga = riga;
	}

	public int getColonna() {
		return colonna;
	}

	public void setColonna(int colonna) {
		this.colonna = colonna;
	}

	public int getValore() {
		return valore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}

	public int getValoreCorretto() {
		return valoreCorretto;
	}

	public void setValoreCorretto(int valoreCorretto) {
		this.valoreCorretto = valoreCorretto;
	}

	public boolean isDaSettare() {
		return isDaSettare;
	}

	public void setDaSettare(boolean isDaSettare) {
		this.isDaSettare = isDaSettare;
	}

	public boolean isCorretto() {
		return isCorretto;
	}

	public void setCorretto(boolean isCorretto) {
		this.isCorretto = isCorretto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colonna;
		result = prime * result + riga;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cella other = (Cella) obj;
		if (colonna == other.colonna && riga == other.riga) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cella [riga=");
		builder.append(riga);
		builder.append(", colonna=");
		builder.append(colonna);
		builder.append(", valore=");
		builder.append(valore);
		builder.append(", valoreCorretto=");
		builder.append(valoreCorretto);
		builder.append(", isDaSettare=");
		builder.append(isDaSettare);
		builder.append(", isCorretto=");
		builder.append(isCorretto);
		builder.append("]");
		return builder.toString();
	}


}
