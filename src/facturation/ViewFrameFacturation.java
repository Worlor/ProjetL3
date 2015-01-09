package facturation;

import gestionVehicule.ControllerGestionVehicule;

import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;

import vehicule.ModelAbstractVehicule;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ViewFrameFacturation extends JFrame implements EventListenerFacturation,ActionListener{

	private static final long serialVersionUID = 243616606739807018L;

	private JPanel contentPane;
	
	private ControllerFacturation controller;
	private ModelFacturation model;
	
	private ControllerGestionVehicule controllerGestionVehicule;
	
	private JLabel lDate;
	private JLabel lVehicule;
	private JLabel lDescription;
	//private JLabel lStock;
	private JLabel lPrix;
	
	private JDatePickerImpl datePicker;
	private UtilDateModel dateModel;
	private JDatePanelImpl datePanel;
	
	private JComboBox<ModelAbstractVehicule> cbVehicule;
	private JTextField tfDescription;
	private JSpinner sPrix;
	
	
	private static final String tAnnuler = "Annuler";
	private static final String tOk = "Ok";
	private static final String tAppliquer = "Appliquer";
	
	private static final String tVehicule = "Vehicule";

	public ViewFrameFacturation(ControllerFacturation controller, ModelFacturation model, ControllerGestionVehicule controllerGestionVehicule) {
		this.controller = controller;
		this.model = model;
		this.controllerGestionVehicule = controllerGestionVehicule;
		initialize();
	}
	
	private void initialize()
	{
		setTitle("Details");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(600, 500, 500, 200);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnOk = new JButton(tOk);
		btnOk.addActionListener(this);
		panel.add(btnOk);
		
		JButton btnAppliquer = new JButton(tAppliquer);
		btnAppliquer.addActionListener(this);
		panel.add(btnAppliquer);
		
		JButton btnAnnuler = new JButton(tAnnuler);
		btnAnnuler.addActionListener(this);
		panel.add(btnAnnuler);
		
		JButton btnExporter = new JButton(new ExporterFacture());
		panel.add(btnExporter);
		
		JPanel pFormulaire = new JPanel();
		contentPane.add(pFormulaire, BorderLayout.CENTER);
		
		SpringLayout sl_panel = new SpringLayout();
		pFormulaire.setLayout(sl_panel);
		
		lDate = new JLabel(enums.FacturationChamps.DATE.toString());
		pFormulaire.add(lDate);
		
		dateModel = new UtilDateModel();
		dateModel.setValue(model.getDateIntervention());
		dateModel.setSelected(true);
		datePanel = new JDatePanelImpl(dateModel);
		datePicker = new JDatePickerImpl(datePanel); 
		pFormulaire.add(datePicker);
		
		lVehicule = new JLabel(tVehicule);
		pFormulaire.add(lVehicule);
		
		cbVehicule = new JComboBox<ModelAbstractVehicule>(
				new DefaultComboBoxModel<ModelAbstractVehicule>(
						controllerGestionVehicule.getModel().getList().toArray(new ModelAbstractVehicule[0])));
		cbVehicule.setSelectedItem(model.getVehicule());
		pFormulaire.add(cbVehicule);
		
		lDescription = new JLabel(enums.FacturationChamps.DESCRIPTION.toString());
		pFormulaire.add(lDescription);
		
		tfDescription = new JTextField(model.getDescription());
		pFormulaire.add(tfDescription);
		
		lPrix = new JLabel(enums.FacturationChamps.PRIX.toString());
		pFormulaire.add(lPrix);

		sPrix = new JSpinner(new SpinnerNumberModel(model.getPrix(),0.00,null,0.01));
		pFormulaire.add(sPrix);
		
		
		lib.SpringUtilities.makeCompactGrid(pFormulaire, 4, 2, 3, 3, 3, 3);
		
	}

	@Override
	public void FacturationChanged(EventFacturationChanged event) {
		dateModel.setValue(event.getDateIntervention());
		cbVehicule.setSelectedItem(event.getVehicule());
		tfDescription.setText(event.getDescription());
		sPrix.setModel(new SpinnerNumberModel(event.getPrix(),0.00,null,0.01));
	}

	public void display() {
		this.setVisible(true);
		
	}

	public void close() {
		this.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		boolean flag = true;
		switch(actionEvent.getActionCommand())
		{
		case tAppliquer:
			flag = false;
		case tOk:
			if(sPrix.getValue() instanceof Double)
			{
				controller.notifyAllChange(model, (Date) datePicker.getModel().getValue(),
						(ModelAbstractVehicule) cbVehicule.getSelectedItem(), tfDescription.getText(), (double) sPrix.getValue());
			}
			
		case tAnnuler:
			if(flag)
			{
				close();
			}
			break;
			
		}
		
	}
	
	private class ExporterFacture extends AbstractAction 
	{

		private static final long serialVersionUID = 6333224134780773220L;

		private ExporterFacture()
		{
			super("Exporter facture en texte");
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			JFileChooser dialogue = new JFileChooser(new File("."));
            dialogue.setAcceptAllFileFilterUsed(true);
        	if (dialogue.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
        	{
        		Calendar cal = Calendar.getInstance();
        		cal.setTime((Date) datePicker.getModel().getValue());
        		
        		File f = dialogue.getSelectedFile();
    			try
    			{
            		FileWriter fw = new FileWriter(f, false);
            		BufferedWriter output = new BufferedWriter(fw);
    				output.write("Facture du " + cal.get(Calendar.DAY_OF_MONTH) + "/" + 
    								cal.get(Calendar.MONTH) + "/" +
    								cal.get(Calendar.YEAR) + "\r\n");
    				output.write("Voiture : " + ((ModelAbstractVehicule) cbVehicule.getSelectedItem()).toString() + "\r\n");
    				output.write("Description de l'intervention : " + tfDescription.getText() + "\r\n");
    				output.write("Prix de l'intervention : " + (double) sPrix.getValue());
    				
    				output.flush();
    				output.close();
    			}
    			catch(IOException ioe){
    				System.out.print("Erreur : ");
    				ioe.printStackTrace();
    			}
        	}

		}
		
	}

}
