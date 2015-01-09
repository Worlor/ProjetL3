package vehicule;

import java.util.EnumMap;

import javax.swing.ImageIcon;
import enums.VehiculesChamps;

public class ControllerVehicule {
	private ModelAbstractVehicule model = null;
	private ViewAbstractVehicule view = null;

	public ControllerVehicule(ModelAbstractVehicule model)
	{
		this.model = model;
		this.view = new ViewFrameVehicule(this, model.getMap(), model.getImage());
		model.addVehiculeListener(view);
	}
	
	public ControllerVehicule(ModelAbstractVehicule model, ViewAbstractVehicule view)
	{
		this.model = model;
		this.view = view;
		model.addVehiculeListener(view);
	}
	
	
	public void displayView(){
		view.display();
	}
	
	public void closeView()
	{
		view.close();
	}
	
	
	public void notifyMapChanged(EnumMap<VehiculesChamps, String> map)
	{
		model.setMap(map);
	}
	
	public void notifyImageChanged(ImageIcon image)
	{
		model.setImage(image);
	}
	
}
