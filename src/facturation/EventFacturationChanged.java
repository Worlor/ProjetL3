package facturation;

import java.io.Serializable;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;

import stock.ModelStock;
import vehicule.ModelAbstractVehicule;

public class EventFacturationChanged extends EventObject implements Serializable{
	
	private static final long serialVersionUID = 5828281919749148585L;
	private Date dateIntervention;
	private ModelAbstractVehicule vehicule;
	private String description;
	private HashMap<ModelStock, Integer> stockUtilise;
	private double prix;
	
	public EventFacturationChanged(Object source, Date dateIntervention, ModelAbstractVehicule vehicule, String description, double prix,HashMap<ModelStock, Integer> stockUtilise) 
	{
		super(source);
		this.dateIntervention = dateIntervention;
		this.vehicule = vehicule;
		this.description = description;
		this.stockUtilise = stockUtilise;
		this.prix = prix;
	
	}

	public Date getDateIntervention() {
		return dateIntervention;
	}

	public ModelAbstractVehicule getVehicule() {
		return vehicule;
	}

	public String getDescription() {
		return description;
	}

	public HashMap<ModelStock, Integer> getStockUtilise() {
		return stockUtilise;
	}

	public double getPrix() {
		return prix;
	}


	

}
