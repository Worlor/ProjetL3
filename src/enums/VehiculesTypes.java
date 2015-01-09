package enums;

public enum VehiculesTypes {
	VOITURE("Voiture"),
	BUS("Bus"),
	MOTO("Moto"),
	CAMION("Camion");
	
	private String name ="";
	
	VehiculesTypes(String name)
	{
		this.name= name;
	}

	public String toString()
	{
		return name;
	}
}
