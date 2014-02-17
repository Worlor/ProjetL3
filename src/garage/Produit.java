package garage;

import java.util.ArrayList;

public class Produit {
	
	private ArrayList<Parametre> parametres;
	private String nom;
	private double quantite;
	private double ok;
	
	public Produit()
	{
		parametres = new ArrayList<Parametre>();
	}
	public Produit(String nom)
	{
		this.nom = nom;
	}
	
	public Produit(String nom, double quantite)
	{
		this.nom = nom;
		this.quantite = quantite;
	}
	
	public void addParam(Parametre p)
	{
		parametres.add(p);
	}
	
	public void deleteParam(Parametre p)
	{
		parametres.remove(p);
	}
	

}
