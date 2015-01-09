package facturation;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.event.EventListenerList;

import stock.ModelStock;
import vehicule.ModelAbstractVehicule;

public class ModelFacturation implements Serializable {
	
	private static final long serialVersionUID = 604669619809322277L;

	private transient EventListenerList listeners = null;
	
	private Date dateIntervention;
	private ModelAbstractVehicule vehicule;
	private String description;
	private HashMap<ModelStock, Integer> stockUtilise;
	private double prix;
	
		
	public ModelFacturation()
	{
		dateIntervention = new Date();
		vehicule = null;
		description = "";
		setPrix(0.0);
		initialize();
	}
	
	public ModelFacturation(Date dateIntervention, ModelAbstractVehicule vehicule, String description, double prix)
	{
		this.dateIntervention = dateIntervention;
		this.vehicule = vehicule;
		this.description = description;
		this.prix = prix;
		initialize();
	}
	
	public ModelFacturation(Date dateIntervention, ModelAbstractVehicule vehicule, String description, double prix, HashMap<ModelStock, Integer> stockUtilise)
	{
		this.dateIntervention = dateIntervention;
		this.vehicule = vehicule;
		this.description = description;
		this.stockUtilise = stockUtilise;
		this.prix = prix;
		listeners = new EventListenerList();
	}
	
	private void initialize()
	{
		stockUtilise = new HashMap<ModelStock, Integer>();
		listeners = new EventListenerList();
	}
	
	public void addFacturationListener(EventListenerFacturation listener){
		if(listeners == null)
		{
			initialize(); // Après deserialization il faut recreer les listeners
		}

		listeners.add(EventListenerFacturation.class, listener);
	}
 
	public void removeFacturationListener(EventListenerFacturation l){
		 listeners.remove(EventListenerFacturation.class, l);
	}
	
	public void fireStockChanged()
	{
			EventListenerFacturation[] listenerList = (EventListenerFacturation[])listeners.getListeners(EventListenerFacturation.class);
			 
			for(EventListenerFacturation listener : listenerList){
				listener.FacturationChanged(new EventFacturationChanged(this, dateIntervention, vehicule, description, prix, stockUtilise ));
		}
	}

	public Date getDateIntervention() {
		return dateIntervention;
	}

	public void setDateIntervention(Date dateIntervention) {
		this.dateIntervention = dateIntervention;
	}

	public ModelAbstractVehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(ModelAbstractVehicule vehicule) {
		this.vehicule = vehicule;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HashMap<ModelStock, Integer> getStockUtilise() {
		return stockUtilise;
	}

	public void setStockUtilise(HashMap<ModelStock, Integer> stockUtilise) {
		this.stockUtilise = stockUtilise;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public String genererFacture()
	{	
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateIntervention);
		String s = "Facture du " + cal.get(Calendar.DAY_OF_MONTH) + "/" + 
									cal.get(Calendar.MONTH) + "/" +
									cal.get(Calendar.YEAR) + "\r\n";
		s += "Voiture : " + vehicule.toString() + "\r\n";
		s += "Description de l'intervention : " + description + "\r\n";
		s += "Prix de l'intervention : " + prix;
		
		return s;
		
	}

	
}
