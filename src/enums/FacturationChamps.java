package enums;

public enum FacturationChamps {
	
	DATE("Date"),
	DESCRIPTION("Description"),
	TYPEVEHICULE("Type du vehicule"),
	MARQUEVEHICULE("Marque du vehicule"),
	MODELEVEHICULE("Modele du vehicule"),
	PROPRIETAIRE("Propriétaire du vehicule"),
	PRIX("Prix");
	
	private String name ="";
	
	FacturationChamps(String name)
	{
		this.name= name;
	}

	public String toString()
	{
		return name;
	}
	

}
