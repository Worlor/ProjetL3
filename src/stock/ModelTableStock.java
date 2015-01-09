package stock;

import enums.StockChamps;
import javax.swing.table.AbstractTableModel;

public class ModelTableStock extends AbstractTableModel implements EventListenerStock{
	
	private static final long serialVersionUID = -5882052783085828844L;

	private ControllerStock controller;
	private final StockChamps[] entetes = enums.StockChamps.values();
	
	public ModelTableStock(ControllerStock controller)
	{
		super();
		this.controller = controller;
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return controller.getModel().size();
	}

	 public String getColumnName(int columnIndex) {
	    return entetes[columnIndex].toString();
	 }
	 
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0)
			return controller.getModel().get(rowIndex).getType();
		else if(columnIndex == 1)
			return controller.getModel().get(rowIndex).getNombre();
		else if(columnIndex == 2)
			return controller.getModel().get(rowIndex).getPrix();
		else if(columnIndex == 3)
			return controller.getModel().get(rowIndex).getEtat();
		return null;
	}

	@Override
	public void StockChanged(EventStockChanged event) {
		this.fireTableDataChanged();
		
	}

}
