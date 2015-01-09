package vehicule;

import java.util.EnumMap;
import java.util.EventObject;

import javax.swing.Icon;
import enums.VehiculesChamps;

public class EventVehiculeChanged extends EventObject{
	
	private EnumMap<VehiculesChamps, String> map;
	private Icon image;

	private static final long serialVersionUID = -7308346688312136530L;

	public EventVehiculeChanged(Object source, EnumMap<VehiculesChamps, String> map, Icon image) {
		super(source);
		this.map = map;
		this.image = image;
		
	}
	
	public EnumMap<VehiculesChamps, String> getMap() {
		return map;
	}
	
	public Icon getImage()
	{
		return image;
	}

}
