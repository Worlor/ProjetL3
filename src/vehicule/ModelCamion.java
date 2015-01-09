package vehicule;


public class ModelCamion extends ModelAbstractVehicule{

	private static final long serialVersionUID = 11L;

	public ModelCamion(String marque, String modele, String nomProprio, 
			String emailProprio, String immatriculation, String couleur, 
			int kilometreTotal, int anneeFabrication)
	{
		super(enums.VehiculesTypes.CAMION.toString(), marque, modele, nomProprio, emailProprio, immatriculation, couleur, kilometreTotal, anneeFabrication);
	}
	
	public ModelCamion()
	{
		super(enums.VehiculesTypes.CAMION.toString(), "","","","","","",0,0);
	}
}
