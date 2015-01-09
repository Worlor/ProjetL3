package gestionVehicule;

import javax.swing.table.AbstractTableModel;
import enums.VehiculesChamps;
import vehicule.ModelAbstractVehicule;

public class ModelTableGestionVehicule extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ControllerGestionVehicule controller;

    private final VehiculesChamps[] entetes = enums.VehiculesChamps.values();
 
    public ModelTableGestionVehicule(ControllerGestionVehicule controller) {
    	super();
    	this.controller = controller;
    }
 
    public int getRowCount() {
        return controller.getModel().size();
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex].toString();
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
    	return controller.getModel().get(rowIndex).getMap().get(entetes[columnIndex]);
    }
    
    public ModelAbstractVehicule getVehiculeRow(int rowIndex)
    {
    	return controller.getModel().get(rowIndex);

    }
    
    
    
}
