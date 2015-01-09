package gestionVehicule;

import javax.swing.JFrame;
import javax.swing.JPanel;

import vehicule.ControllerVehicule;
import vehicule.EventListenerVehicule;
import vehicule.EventVehiculeChanged;
import vehicule.ModelAbstractVehicule;

public class ControllerGestionVehicule implements EventListenerVehicule  {

	//Classe en lecture de données seulement, pas de listeners
	
	private ViewPanelGestionVehicule view = null;
	private ModelTableGestionVehicule table = null;
	private ModelGestionVehicule model = null;
	private JFrame frame = null;
	
	public ControllerGestionVehicule(JFrame frame)
	{
		this.model = new ModelGestionVehicule();
		this.frame = frame;
		this.table = new ModelTableGestionVehicule(this);
		this.view = new ViewPanelGestionVehicule(this, this.table, this.frame);
	}

	public ControllerGestionVehicule(ModelGestionVehicule model, JFrame frame)
	{
		this.model = model;
		this.frame = frame;
		this.table = new ModelTableGestionVehicule(this);
		this.view = new ViewPanelGestionVehicule(this, this.table, this.frame);
	}
	
    public void ajouterVehicule(ModelAbstractVehicule vehicule) {
        model.ajouterVehicule(vehicule);
        table.fireTableRowsInserted(model.size()-2, model.size()-1);
        details(table.getVehiculeRow(model.size()-1));
    }    
    
    public void modifierVehicule(int rowIndex)
    {
    	this.details(model.get(rowIndex));
    	table.fireTableDataChanged();
    }
 
    public void  supprimerVehicule(int rowIndex) {
    	model.supprimerVehicule(rowIndex);
    	table.fireTableDataChanged();
    }
	
	
	private void details(ModelAbstractVehicule vehicule)
	{
		vehicule.addVehiculeListener(this);
        ControllerVehicule control = new ControllerVehicule(vehicule);
        control.displayView();
	}
	
	public JPanel getPanel()
	{
		return view;
	}
	
	public ModelGestionVehicule getModel()
	{
		return model;
	}
	
	public void setModel(ModelGestionVehicule model)
	{
		this.model = model;
		table.fireTableDataChanged();
	}

	@Override
	public void vehiculeChanged(EventVehiculeChanged event) {
		table.fireTableDataChanged();
		
	}
	
	
}
