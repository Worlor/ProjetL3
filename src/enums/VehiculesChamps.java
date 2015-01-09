package enums;

public enum VehiculesChamps {
	
	TYPE("Type"),
	MARQUE("Marque"),
	MODELE("Modele"),
	NOMPROPRIO("Nom propriétaire"),
	EMAILPROPRIO("Email propriétaire"),
	IMMAT("Immatriculation"),
	COULEUR("Couleur"),
	KMTOTAL("Kilomètres total"),
	ANNEEFAB("Année de fabrication");
	
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
