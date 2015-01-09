package vehicule;

public abstract class ViewAbstractVehicule implements EventListenerVehicule{
	
	private ControllerVehicule controller = null;
	
	public ViewAbstractVehicule(ControllerVehicule controller)
	{
		super();
		this.controller = controller;
	}
	
	public final ControllerVehicule getController()
	{
		return this.controller;
	}

	public abstract void display();
	public abstract void close();

}
