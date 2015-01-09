package main;

import facturation.ModelListFacturation;
import gestionVehicule.ModelGestionVehicule;

import java.io.Serializable;

import stock.ModelListStock;

public class SerializableModel implements Serializable {

	private static final long serialVersionUID = 4630339312737245960L;
	private ModelGestionVehicule modelVehicule;
	private ModelListStock modelStock;
	private ModelListFacturation modelFacturation;
	
	public SerializableModel()
	{
		this.modelVehicule = null;
		this.modelStock = null;
		this.setModelFacturation(null);
	}
	
	public SerializableModel(final ModelGestionVehicule modelVehicule,final ModelListStock modelStock, final ModelListFacturation modelFacturation)
	{
		this.modelStock = modelStock;
		this.modelVehicule = modelVehicule;
		this.setModelFacturation(modelFacturation);
	}
	
	public ModelListStock getModelStock()
	{
		return modelStock;
	}
	
	public void setModelStock(ModelListStock model)
	{
		this.modelStock = model;
	}
	
	public ModelGestionVehicule getModelVehicule()
	{
		return modelVehicule;
	}
	
	public void setModelVehicule(ModelGestionVehicule model)
	{
		this.modelVehicule = model;
	}

	public ModelListFacturation getModelFacturation() {
		return modelFacturation;
	}

	public void setModelFacturation(ModelListFacturation modelFacturation) {
		this.modelFacturation = modelFacturation;
	}

}
