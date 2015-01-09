package stock;

import java.awt.BorderLayout;
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

import javax.swing.JButton;

public class ViewFrameStock extends JFrame implements EventListenerStock,ActionListener{

	private static final long serialVersionUID = -8997745723021503551L;

	private JPanel contentPane;
	
	private ControllerStock controller;
	private ModelStock model;
	
	private JLabel labelType;
	private JLabel labelNombre;
	private JLabel labelPrix;
	
	private JTextField tfType;
	private JSpinner sNombre;
	private JSpinner sPrix;
	
	private static final String tAnnuler = "Annuler";
	private static final String tOk = "Ok";
	private static final String tAppliquer = "Appliquer";

	public ViewFrameStock(ControllerStock controller, ModelStock model) {
		this.controller = controller;
		this.model = model;
		initialize();
	}
	
	private void initialize()
	{
		setTitle("Details");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(600, 300, 500, 180);
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
		
		JPanel pFormulaire = new JPanel();
		contentPane.add(pFormulaire, BorderLayout.CENTER);
		
		SpringLayout sl_panel = new SpringLayout();
		pFormulaire.setLayout(sl_panel);
				
		labelType = new JLabel(enums.StockChamps.TYPE.toString());
		pFormulaire.add(labelType);
		
		tfType = new JTextField(model.getType());
		pFormulaire.add(tfType);
		
		labelNombre = new JLabel(enums.StockChamps.NOMBRE.toString());
		pFormulaire.add(labelNombre);
		
		sNombre = new JSpinner(new SpinnerNumberModel(model.getNombre(),0,null,1));
		pFormulaire.add(sNombre);
		
		labelPrix = new JLabel(enums.StockChamps.PRIX.toString());
		pFormulaire.add(labelPrix);
		
		sPrix = new JSpinner(new SpinnerNumberModel(model.getPrix(),0.00,null,0.01));
		pFormulaire.add(sPrix);
		
		
		lib.SpringUtilities.makeCompactGrid(pFormulaire, 3, 2, 3, 3, 3, 3);
		
	}

	@Override
	public void StockChanged(EventStockChanged event) {
		tfType.setText(event.getType());
		sNombre.setModel(new SpinnerNumberModel(event.getNombre(),0,null,1));
		sPrix.setModel(new SpinnerNumberModel(model.getPrix(),0.00,null,0.01));
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
			if(sNombre.getValue() instanceof Integer && sPrix.getValue() instanceof Double)
			controller.notifyAllChange(model,(Integer)sNombre.getValue(),tfType.getText(),(double)sPrix.getValue());
		case tAnnuler:
			if(flag)
			{
				close();
			}
			break;
			
		}
		
	}

}
