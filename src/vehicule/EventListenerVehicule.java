package vehicule;

import java.util.EventListener;

public interface EventListenerVehicule extends EventListener{
	
	public void vehiculeChanged(EventVehiculeChanged event);

}
