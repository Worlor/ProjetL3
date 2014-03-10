package garage;

public abstract class Vehicule {
	protected String type;
	protected String marque;
	protected String modele;
	protected String nomProprietaire;
	protected String emailProprietaire;
	protected String immatriculation;
	protected String couleur;
	protected int kilometresTotal;
	protected int anneeFabrication;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getNomProprietaire() {
		return nomProprietaire;
	}
	public void setNomProprietaire(String nomProprietaire) {
		this.nomProprietaire = nomProprietaire;
	}
	public String getEmailProprietaire() {
		return emailProprietaire;
	}
	public void setEmailProprietaire(String emailProprietaire) {
		this.emailProprietaire = emailProprietaire;
	}
	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public int getKilometresTotal() {
		return kilometresTotal;
	}
	public void setKilometresTotal(int kilometresTotal) {
		this.kilometresTotal = kilometresTotal;
	}
	public int getAnneeFabrication() {
		return anneeFabrication;
	}
	public void setAnneeFabrication(int anneeFabrication) {
		this.anneeFabrication = anneeFabrication;
	}
	
	
	
	
}
