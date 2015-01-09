package stock;

import java.io.Serializable;

import javax.swing.event.EventListenerList;

public class ModelStock implements Serializable {

	private static final long serialVersionUID = -78262474471818140L;
	
	private transient EventListenerList listeners = null;
	
	private final static int seuilAvertissement = 10;
	
	protected int nombre;
	protected double prix;
	protected String type;
		
	public ModelStock()
	{
		this.nombre = 0;
		this.type = "";
		this.prix = 0;
		initialize();
	}
	
	public ModelStock(int nombre, String type, double prix)
	{
		this.nombre = nombre;
		this.type = type;
		this.prix = prix;
		initialize();
		
	}
	
	private void initialize()
	{
		listeners = new EventListenerList();
	}
	
	public void addStockListener(EventListenerStock listener){
		if(listeners == null)
		{
			initialize(); // Après deserialization il faut recreer les listeners
		}

		listeners.add(EventListenerStock.class, listener);
	}
 
	public void removeStockListener(EventListenerStock l){
		 listeners.remove(EventListenerStock.class, l);
	}
	
	public void fireStockChanged()
	{
			EventListenerStock[] listenerList = (EventListenerStock[])listeners.getListeners(EventListenerStock.class);
			 
			for(EventListenerStock listener : listenerList){
			listener.StockChanged(new EventStockChanged(this, getType(), getNombre()));
		}
	}
	
	public String getType()
	{
		return type;
	}
	
	public int getNombre()
	{
		return nombre;
	}

	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setNombre(int nombre)
	{
		this.nombre = nombre;
	}
	
	public String toString()
	{
		return this.type + " : " + this.nombre;
	}

	public String getEtat() {
		if(nombre > seuilAvertissement)
		{
			return enums.StockEtat.ENSTOCK.toString();
		}
		else if(nombre > 0)
		{
			return enums.StockEtat.AVERTISSEMENT.toString();
		}
		else if(nombre == 0)
		{
			return enums.StockEtat.RUPTURE.toString();
		}
		return null;
	}
	
	public double getPrix()
	{
		return prix;
	}
	
	public void setPrix(double prix)
	{
		this.prix = prix;
	}
	
	
}
