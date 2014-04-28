package vehicule;

import java.util.EnumMap;

import javax.swing.event.EventListenerList;

import enums.VehiculesChamps;

public abstract class ModelAbstractVehicule {
	private EventListenerList listeners; 
	
	protected EnumMap<VehiculesChamps, String> map;
	

	public ModelAbstractVehicule(String type, String marque, String modele, String nomProprio, 
			String emailProprio, String immatriculation, String couleur, 
			int kilometreTotal, int anneeFabrication)
	{
		super();
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
			listener.vehiculeChanged(new EventVehiculeChanged(this, getMap()));
		}
	}
	
	public EnumMap<VehiculesChamps, String> getMap() {
		return map;
	}
	public void setMap(EnumMap<VehiculesChamps, String> e) {
		this.map = e;
		fireVehiculeChanged();
	}
	

	
	
	
	
	
}
