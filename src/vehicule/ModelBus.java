package vehicule;

public class ModelBus extends ModelAbstractVehicule{
	
	private static final long serialVersionUID = 11L;

	public ModelBus(String marque, String modele, String nomProprio, 
			String emailProprio, String immatriculation, String couleur, 
			int kilometreTotal, int anneeFabrication)
	{
		super(enums.VehiculesTypes.BUS.toString(), marque, modele, nomProprio, emailProprio, immatriculation, couleur, kilometreTotal, anneeFabrication);
	}
	
	public ModelBus()
	{
		super(enums.VehiculesTypes.BUS.toString(), "","","","","","",0,0);
	}

}
