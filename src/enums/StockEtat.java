package enums;

public enum StockEtat {
	
	ENSTOCK("En stock"),
	AVERTISSEMENT("Stocks bas !"),
	RUPTURE("Rupture de stock !");
	
	
	private String name ="";
	
	StockEtat(String name)
	{
		this.name= name;
	}

	public String toString()
	{
		return name;
	}

}
