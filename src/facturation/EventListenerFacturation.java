package facturation;

import java.util.EventListener;

public interface EventListenerFacturation extends EventListener {
	
	public void FacturationChanged(EventFacturationChanged event);

}
