package gestionVehicule;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTable;

import vehicule.ModelBus;
import vehicule.ModelCamion;
import vehicule.ModelMoto;
import vehicule.ModelVoiture;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewPanelGestionVehicule extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5358508476034857519L;
	private JTable table;
	private JFrame frame;
	
	private ModelTableGestionVehicule model;
	private ControllerGestionVehicule controller;


	public ViewPanelGestionVehicule(ControllerGestionVehicule controller, ModelTableGestionVehicule model, JFrame frame) {
		super();
		this.frame = frame;
		this.model = model;
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		this.setLayout(new BorderLayout());

		this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	table.validate();
            }
        });		
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() >= 2)
				{
					int i = table.getSelectedRow();
					if(i>-1)
					{
						controller.modifierVehicule(table.convertRowIndexToModel(table.getSelectedRow()));
					}
				}
					
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);
 
        JPanel boutons = new JPanel();
 
        boutons.add(new JButton(new AjouterVehicule()));
        boutons.add(new JButton(new SupprimerVehicule()));
 
        this.add(boutons, BorderLayout.SOUTH);

	}
	
	
    private class AjouterVehicule extends AbstractAction {

		private static final long serialVersionUID = -1288268400352274548L;

		private AjouterVehicule() {
            super("Ajouter");
        }
 
        public void actionPerformed(ActionEvent e) {
        	enums.VehiculesTypes s = (enums.VehiculesTypes)JOptionPane.showInputDialog(
        	                    frame,
        	                    "Choissisez le type de véhicule:\n",
        	                    "Choix du vehicule",
        	                    JOptionPane.PLAIN_MESSAGE,
        	                    null,
        	                    enums.VehiculesTypes.values(),
        	                    enums.VehiculesTypes.VOITURE.toString());

        	if (s != null) {
        		switch(s)
        		{
				case BUS:
					controller.ajouterVehicule(new ModelBus());
					break;
				case CAMION:
					controller.ajouterVehicule(new ModelCamion());
					break;
				case MOTO:
					controller.ajouterVehicule(new ModelMoto());
					break;
				case VOITURE:
					controller.ajouterVehicule(new ModelVoiture());
					break;
				default:
					break;
        			
        		}	
        	}
        }
    }
 
    private class SupprimerVehicule extends AbstractAction {

		private static final long serialVersionUID = 1014334072922569339L;

		private SupprimerVehicule() {
            super("Supprimer");
        }
 
        public void actionPerformed(ActionEvent e) {
            int[] selection = table.getSelectedRows();
 
            for(int i = selection.length - 1; i >= 0; i--){
                controller.supprimerVehicule(selection[i]);
            }
        }
    }

}
