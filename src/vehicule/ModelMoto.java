package vehicule;

public class ModelMoto extends ModelAbstractVehicule{


	private static final long serialVersionUID = 11L;

	public ModelMoto(String marque, String modele, String nomProprio, 
			String emailProprio, String immatriculation, String couleur, 
			int kilometreTotal, int anneeFabrication)
	{
		super(enums.VehiculesTypes.MOTO.toString(), marque, modele, nomProprio, emailProprio, immatriculation, couleur, kilometreTotal, anneeFabrication);
	}
	
	public ModelMoto()
	{
		super(enums.VehiculesTypes.MOTO.toString(), "","","","","","",0,0);
	}
}
