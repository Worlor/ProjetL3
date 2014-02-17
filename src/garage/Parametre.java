package garage;

public class Parametre {
	private String nom;
	private Object variable;
	
	public Parametre()
	{
		
	}
	
	public Parametre(String nom)
	{
		this.nom = nom;
	}
	
	public Parametre(Object variable)
	{
		this.variable = variable;
	}
	
	public Parametre(String nom, Object variable)
	{
		this.variable = variable;
		this.nom = nom;
	}
		
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Object getVariable() {
		return variable;
	}

	public void setVariable(Object variable) {
		this.variable = variable;
	}


}
