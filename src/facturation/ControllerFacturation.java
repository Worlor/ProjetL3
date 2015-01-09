package facturation;

import java.util.Date;

import gestionVehicule.ControllerGestionVehicule;

import javax.swing.JFrame;
import javax.swing.JPanel;

import vehicule.ModelAbstractVehicule;

public class ControllerFacturation {
	
	private ModelListFacturation model = null;
	private ViewFrameFacturation view = null;
	private ModelTableFacturation table = null;
	private ViewPanelFacturation panelStock;
	private ControllerGestionVehicule controllerGestionVehicule;
	
	public ControllerFacturation(ModelListFacturation model,JFrame frame,ControllerGestionVehicule controllerGestionVehicule)
	{
		this.model = model;
		table = new ModelTableFacturation(this);
		this.controllerGestionVehicule = controllerGestionVehicule;
		panelStock = new ViewPanelFacturation(this, table, frame);
	}
	
	public ControllerFacturation(ModelListFacturation model, ViewFrameFacturation view, JFrame frame, ControllerGestionVehicule controllerGestionVehicule)
	{
		this.model = model;
		this.controllerGestionVehicule = controllerGestionVehicule;
		table = new ModelTableFacturation(this);
		panelStock = new ViewPanelFacturation(this, table, frame);
	}
	
	public ModelListFacturation getModel()
	{
		return model;
	}
	
	public void notifyDateChange(ModelFacturation modelChanged, Date date)
	{
		model.get(model.lastIndexOf(modelChanged)).setDateIntervention(date);
		table.fireTableDataChanged();
	}
	
	public void notifyVehiculeChange(ModelFacturation modelChanged, ModelAbstractVehicule vehicule)
	{
		model.get(model.lastIndexOf(modelChanged)).setVehicule(vehicule);
		table.fireTableDataChanged();
	}
	public void notifyDescriptionChange(ModelFacturation modelChanged, String description)
	{
		model.get(model.lastIndexOf(modelChanged)).setDescription(description);
		table.fireTableDataChanged();
	}
	
	public void notifyPrixChange(ModelFacturation modelChanged, double prix)
	{
		model.get(model.lastIndexOf(modelChanged)).setPrix(prix);
		table.fireTableDataChanged();
	}
	
	public void notifyAllChange(ModelFacturation modelChanged, Date date, ModelAbstractVehicule vehicule, String description, double prix)
	{
		model.get(model.lastIndexOf(modelChanged)).setDateIntervention(date);
		model.get(model.lastIndexOf(modelChanged)).setVehicule(vehicule);
		model.get(model.lastIndexOf(modelChanged)).setDescription(description);
		model.get(model.lastIndexOf(modelChanged)).setPrix(prix);
		table.fireTableDataChanged();
	}
	
	
	public void displayView(int index)
	{
		this.details(index);
	}
	
	public JPanel getPanel()
	{
		return panelStock;
	}
	
	private void details(int index){
		view = new ViewFrameFacturation(this, model.get(index), controllerGestionVehicule);
		model.get(index).addFacturationListener(view);
		view.display();
	}
		
	public ModelListFacturation getList()
	{
		return model;
	}
	
	public void closeView()
	{
		view.close();
	}

	public void setModel(ModelListFacturation model) {
		this.model = model;
		table.fireTableDataChanged();
		
	}

	public void ajouterFacture() {
		model.add(new ModelFacturation());
		table.fireTableRowsInserted(model.size()-2, model.size()-1);
		displayView(model.size()-1);
		
	}

	public void supprimerFacture(int index) {
		model.remove(index);
		table.fireTableDataChanged();		
	}
	
}
