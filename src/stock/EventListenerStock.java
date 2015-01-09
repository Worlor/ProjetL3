package stock;

import java.util.EventListener;

public interface EventListenerStock extends EventListener {
	
	public void StockChanged(EventStockChanged event);

}
