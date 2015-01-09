package facturation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewPanelFacturation extends JPanel{

	private static final long serialVersionUID = 74116186794260326L;
	private JTable table;
	private ModelTableFacturation model;
	private ControllerFacturation controller;

	
	public ViewPanelFacturation(ControllerFacturation controller,ModelTableFacturation model,JFrame frame) {
		super();
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
					controller.displayView(table.convertRowIndexToModel(table.getSelectedRow()));
				}
			}
				
		}
	});
	
	JScrollPane scrollPane = new JScrollPane(table);
	this.add(scrollPane, BorderLayout.CENTER);
	
    JPanel boutons = new JPanel();
    
    boutons.add(new JButton(new AjouterFacture()));
    boutons.add(new JButton(new SupprimerFacture()));

    this.add(boutons, BorderLayout.SOUTH);
	}
	
	 private class AjouterFacture extends AbstractAction {


		private static final long serialVersionUID = -2145455253965589329L;

			private AjouterFacture() {
	            super("Ajouter");
	        }
	 
	        public void actionPerformed(ActionEvent e) {
	        	controller.ajouterFacture();
	        }
	 }
	 
	private class SupprimerFacture extends AbstractAction {


		private static final long serialVersionUID = -4166871278864951076L;

			private SupprimerFacture() {
	            super("Supprimer");
	        }
	 
	        public void actionPerformed(ActionEvent e) {
	            int[] selection = table.getSelectedRows();
	 
	            for(int i = selection.length - 1; i >= 0; i--){
	                controller.supprimerFacture(selection[i]);
	            }
	        }
	    }
	


}
