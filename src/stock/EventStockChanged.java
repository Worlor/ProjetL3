package stock;

import java.io.Serializable;
import java.util.EventObject;

public class EventStockChanged extends EventObject implements Serializable{
	
	private static final long serialVersionUID = 5828281919749148585L;
	private int nombre;
	private String type;

	public EventStockChanged(Object source, String type, int nombre) {
		super(source);
		this.nombre = nombre;
		this.type = type;
	
	}
	
	public int getNombre()
	{
		return nombre;
	}
	
	public String getType()
	{
		return type;
	}
}
