package sudukoPackage;

public class Cell {
	private int row;
	private int col;
	private int val;
	
	private boolean editable;
	
	

	public Cell(int row, int col, int val, boolean editable) {
		super();
		this.row = row;
		this.col = col;
		this.val = val;
		this.editable = editable;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	
	

}
