package model;

import garage.Vehicule;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;

public abstract class AbstractAdminModel implements Observable{
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();   
	
	private ArrayList<Vehicule> vehicules = new ArrayList<Vehicule>();
	
	public abstract void addVehicule(Vehicule vehicule);
	public abstract void removeVehicule(Vehicule vehicule);
	
	
	 //Implémentation du pattern observer
	  public void addObserver(Observer obs) {
	    this.listObserver.add(obs);
	  }

	  public void notifyObserver(String str) {
		  

	    for(Observer obs : listObserver)
	      obs.update(str);
	  }

	  public void removeObserver() {
	    listObserver = new ArrayList<Observer>();
	  }  
}
