package vehicule;


public class ModelVoiture extends ModelAbstractVehicule{

	private static final long serialVersionUID = 11L;

	public ModelVoiture(String marque, String modele, String nomProprio, 
			String emailProprio, String immatriculation, String couleur, 
			int kilometreTotal, int anneeFabrication)
	{
		super(enums.VehiculesTypes.VOITURE.toString(), marque, modele, nomProprio, emailProprio, immatriculation, couleur, kilometreTotal, anneeFabrication);
	}
	
	public ModelVoiture()
	{
		super(enums.VehiculesTypes.VOITURE.toString(), "","","","","","",0,0);
	}
	

	
}
