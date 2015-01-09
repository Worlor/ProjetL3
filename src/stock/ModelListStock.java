package stock;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelListStock implements Serializable {
	
	private static final long serialVersionUID = -3382637005110139254L;
	private ArrayList<ModelStock> models;
	
	public ModelListStock()
	{
		models = new ArrayList<ModelStock>();
	}
	
	public void add(ModelStock model)
	{
		models.add(model);
	}
	
	public void remove(int index)
	{
		models.remove(index);
	}
	
	public void remove(ModelStock model)
	{
		models.remove(model);
	}
	
	public int size()
	{
		return models.size();
	}
	
	public ModelStock get(int index)
	{
		return models.get(index);
	}
	
	public int lastIndexOf(ModelStock model)
	{
		return models.lastIndexOf(model);
	}
	

}
