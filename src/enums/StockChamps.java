package enums;

public enum StockChamps {
	
	TYPE("Type"),
	NOMBRE("Nombre"),
	PRIX("Prix"),
	ETAT("Etat");
	
	private String name ="";
	
	StockChamps(String name)
	{
		this.name= name;
	}

	public String toString()
	{
		return name;
	}
	

}
