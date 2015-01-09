package gestionVehicule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import vehicule.ModelAbstractVehicule;

public class ModelGestionVehicule implements Serializable
{

	private static final long serialVersionUID = 1032917513535608084L;
	private List<ModelAbstractVehicule> models;
	
	public ModelGestionVehicule()
	{
		models = new ArrayList<ModelAbstractVehicule>();
	}
	
	public List<ModelAbstractVehicule> getList()
	{
		return models;
	}
	
	public void setList(List<ModelAbstractVehicule> models)
	{
		this.models = models;
	}
	
    public void ajouterVehicule(ModelAbstractVehicule vehicule) {
        models.add(vehicule);
    }
    
 
    public void supprimerVehicule(int index) {
        models.remove(index);
    }
    
    public int size()
    {
    	return models.size();
    }

    public ModelAbstractVehicule get(int index)
    {
    	return models.get(index);
    }
}
