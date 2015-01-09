package enums;

public enum VehiculesChamps {
	
	TYPE("Type"),
	MARQUE("Marque"),
	MODELE("Modele"),
	NOMPROPRIO("Nom propri�taire"),
	EMAILPROPRIO("Email propri�taire"),
	IMMAT("Immatriculation"),
	COULEUR("Couleur"),
	KMTOTAL("Kilom�tres total"),
	ANNEEFAB("Ann�e de fabrication");
	
	private String name ="";
	
	VehiculesChamps(String name)
	{
		this.name= name;
	}

	public String toString()
	{
		return name;
	}
	
}
