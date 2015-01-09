package facturation;

import java.util.Calendar;

import enums.FacturationChamps;
import enums.VehiculesChamps;

import javax.swing.table.AbstractTableModel;

public class ModelTableFacturation extends AbstractTableModel implements EventListenerFacturation{
	
	private static final long serialVersionUID = -5882052783085828844L;

	private ControllerFacturation controller;
	private final FacturationChamps[] entetes = enums.FacturationChamps.values();
	
	public ModelTableFacturation(ControllerFacturation controller)
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
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(controller.getModel().get(rowIndex).getDateIntervention());
			return String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "/" +
					String.valueOf(cal.get(Calendar.MONTH) + 1) + "/" +
					String.valueOf(cal.get(Calendar.YEAR));
		}
		else if(columnIndex == 1)
		{
			return controller.getModel().get(rowIndex).getDescription();
		}
		else if(columnIndex == 2)
		{
			if(controller.getModel().get(rowIndex).getVehicule() != null)
				return controller.getModel().get(rowIndex).getVehicule().getMap().get(VehiculesChamps.TYPE).toString();
			else
				return "";
		}
		else if(columnIndex == 3)
		{
			if(controller.getModel().get(rowIndex).getVehicule() != null)
				return controller.getModel().get(rowIndex).getVehicule().getMap().get(VehiculesChamps.MARQUE).toString();
			else
				return "";
		}
		else if(columnIndex == 4)
		{
			if(controller.getModel().get(rowIndex).getVehicule() != null)
				return controller.getModel().get(rowIndex).getVehicule().getMap().get(VehiculesChamps.MODELE).toString();
			else
				return "";
		}
		else if(columnIndex == 5)
		{
			if(controller.getModel().get(rowIndex).getVehicule() != null)
				return controller.getModel().get(rowIndex).getVehicule().getMap().get(VehiculesChamps.NOMPROPRIO).toString();
			else
				return "";
		}
		else if(columnIndex == 6)
		{
			return controller.getModel().get(rowIndex).getPrix();
		}
		return null;
	}

	@Override
	public void FacturationChanged(EventFacturationChanged event) {
		this.fireTableDataChanged();
		
	}

}
