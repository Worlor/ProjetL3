package facturation;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelListFacturation implements Serializable {
	
	private static final long serialVersionUID = -3382637005110139254L;
	private ArrayList<ModelFacturation> models;
	
	public ModelListFacturation()
	{
		models = new ArrayList<ModelFacturation>();
	}
	
	public void add(ModelFacturation model)
	{
		models.add(model);
	}
	
	public void remove(int index)
	{
		models.remove(index);
	}
	
	public void remove(ModelFacturation model)
	{
		models.remove(model);
	}
	
	public int size()
	{
		return models.size();
	}
	
	public ModelFacturation get(int index)
	{
		return models.get(index);
	}
	
	public int lastIndexOf(ModelFacturation model)
	{
		return models.lastIndexOf(model);
	}
	

}
