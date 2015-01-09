package stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ViewPanelStock extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6022589230865298170L;
	private JTable table;
	private ModelTableStock model;
	private ControllerStock controller;

	
	public ViewPanelStock(ControllerStock controller,ModelTableStock model,JFrame frame) {
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
	table.setDefaultRenderer(Object.class, new rendererTable());
	
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
    
    boutons.add(new JButton(new AjouterStock()));
    boutons.add(new JButton(new SupprimerStock()));

    this.add(boutons, BorderLayout.SOUTH);
	}
    
	private class rendererTable extends DefaultTableCellRenderer
	{
		private static final long serialVersionUID = 1654407438943741069L;

		@Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        Object o = table.getValueAt(row, 3);
	        if (o != null && component instanceof JLabel) {
	            JLabel label = (JLabel) component;
	            if (label.getText().contains(enums.StockEtat.RUPTURE.toString())) {
	                component.setBackground(Color.RED);
	            }
	            else if (label.getText().contains(enums.StockEtat.AVERTISSEMENT.toString()))
	            {
	            	component.setBackground(Color.YELLOW);
	            }
	            else if(label.getText().contains(enums.StockEtat.ENSTOCK.toString()))
	            {
	            	component.setBackground(Color.GREEN);
	            }
	            else
	            {
	            	component.setBackground(Color.WHITE);
	            }
	        }
	        return component;
	    }
		
	}
	
	 private class AjouterStock extends AbstractAction {

		private static final long serialVersionUID = -6167493073295895418L;

			private AjouterStock() {
	            super("Ajouter");
	        }
	 
	        public void actionPerformed(ActionEvent e) {
	        	controller.ajouterStock();
	        }
	 }
	 
	private class SupprimerStock extends AbstractAction {

			/**
		 * 
		 */
		private static final long serialVersionUID = -4534626403575002120L;

			private SupprimerStock() {
	            super("Supprimer");
	        }
	 
	        public void actionPerformed(ActionEvent e) {
	            int[] selection = table.getSelectedRows();
	 
	            for(int i = selection.length - 1; i >= 0; i--){
	                controller.supprimerStock(selection[i]);
	            }
	        }
	    }

}
