import java.io.File;
import java.util.ArrayList;
import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;

public class ExcelOperator {
	private String path;
	private Workbook workbook;
	private boolean error = false;
	
	private String[] reversePair(String[] origin) {
		if (origin.length != 2) return null;
		return new String[] { origin[1], origin[0] };
	}
	
	public ExcelOperator(String path) {
		this.path = path;
		
		try {
			this.workbook = Workbook.getWorkbook(new File(this.path));
		}
		catch (Exception e) {
			this.error = true;
		}
	}
	
	public ArrayList<Cell[]> getColumns(int sheetIndex, int[] columnIndexs) {
		if (this.error) return null;
		
		ArrayList<Cell[]> columns = new ArrayList<Cell[]>();
		Sheet sheet = this.workbook.getSheet(sheetIndex);
		for (int columnNo : columnIndexs) {
			Cell[] column = sheet.getColumn(columnNo);
			columns.add(column);
		}
		
		return columns;
	}
	
	public ArrayList<String[]> getPairs(int sheetNo, int firstColumnIndex, int beginRowIndex) {
		if (this.error) return null;
		
		ArrayList<String[]> Pairs = new ArrayList<String[]>();
		ArrayList<Cell[]> Columns = this.getColumns(sheetNo, new int[] { firstColumnIndex, firstColumnIndex + 1 });
		Cell[] column0 = Columns.get(0), column1 = Columns.get(1);
		if (column0.length != column1.length) return null;
		
		while (beginRowIndex < column0.length) {
			String[] Pair = new String[] { column0[beginRowIndex].getContents(), column1[beginRowIndex].getContents() };
			Pairs.add(Pair);
			Pairs.add(this.reversePair(Pair));
			beginRowIndex++;
		}
		
		return Pairs;
	}
	
	public void close() {
		this.workbook.close();
	}
}
