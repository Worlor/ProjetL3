package vehicule;

import java.io.Serializable;
import java.util.EnumMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.EventListenerList;

import lib.MissingIcon;
import enums.VehiculesChamps;

public abstract class ModelAbstractVehicule implements Serializable {

	private static final long serialVersionUID = 11L;

	private EventListenerList listeners; 
	
	protected ImageIcon image;
	
	protected EnumMap<VehiculesChamps, String> map;
	

	public ModelAbstractVehicule(String type, String marque, String modele, String nomProprio, 
			String emailProprio, String immatriculation, String couleur, 
			int kilometreTotal, int anneeFabrication)
	{
		String[] l = {type, marque, modele, nomProprio, emailProprio,
				immatriculation, couleur, String.valueOf(kilometreTotal), String.valueOf(anneeFabrication)};
		map = new EnumMap<>(VehiculesChamps.class);
		
		int i=0;
		for(VehiculesChamps champ : VehiculesChamps.values() )
		{
			map.put(champ, l[i]);
			i++;
		}
		
		listeners = new EventListenerList();
		image = null;

	}
	
	
	public void addVehiculeListener(EventListenerVehicule listener){
		listeners.add(EventListenerVehicule.class, listener);
	}
 
	public void removeVehiculeListener(EventListenerVehicule l){
		 listeners.remove(EventListenerVehicule.class, l);
	}
	
	public void fireVehiculeChanged()
	{
			EventListenerVehicule[] listenerList = (EventListenerVehicule[])listeners.getListeners(EventListenerVehicule.class);
			 
			for(EventListenerVehicule listener : listenerList){
			listener.vehiculeChanged(new EventVehiculeChanged(this, getMap(), getImage()));
		}
	}
	public Icon getImage() 
	{
		if(image != null)
			return this.image;
		else
			return new MissingIcon();
	}
	
	public void setImage(ImageIcon image)
	{
		this.image = image;
	}
	
	public EnumMap<VehiculesChamps, String> getMap() {
		return this.map;
	}
	public void setMap(EnumMap<VehiculesChamps, String> e) {
		this.map = e;
		fireVehiculeChanged();
	}
	
	public String toString()
	{
		return map.get(enums.VehiculesChamps.MARQUE).toString() + " " +
				map.get(enums.VehiculesChamps.MODELE).toString() + " " +
				"de " + map.get(enums.VehiculesChamps.NOMPROPRIO).toString();
	}
	

	
	
	
	
	
}
