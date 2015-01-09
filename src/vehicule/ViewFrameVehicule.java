package vehicule;

import javax.swing.JFrame;

import enums.VehiculesChamps;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.text.JTextComponent;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;

import lib.FileNameFilter;

public class ViewFrameVehicule extends ViewAbstractVehicule implements ActionListener{

	private JFrame frame;

	private ArrayList<JTextField> lChamp = null;
	
	
	private static final String tAnnuler = "Annuler";
	private static final String tAppliquer = "Appliquer";
	private static final String tOk = "Ok";
	
	private JComboBox<String> cbType;
	
	private final int imageWidth = 256;
	
	private JLabel imageVehicule;
	
	
	public ViewFrameVehicule(ControllerVehicule controller, EnumMap<VehiculesChamps, String> map, Icon image) {
		super(controller);
		lChamp = new ArrayList<>();
		initialize(map,image);
	}
	
	private void initialize(EnumMap<VehiculesChamps, String> map, Icon image) {
		int colonnes = 2;
		int lignes = 0;
		
		//Frame
		frame = new JFrame();
		frame.setTitle("Détails");
		frame.setResizable(false);
		frame.setBounds(600, 100, 700, 328);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Panels
		JPanel pFormulaire = new JPanel();
		JPanel pOptions = new JPanel();
		JPanel pImage = new JPanel();
		JPanel pImageButtons = new JPanel();
		frame.getContentPane().add(pFormulaire, BorderLayout.CENTER);
		frame.getContentPane().add(pOptions, BorderLayout.SOUTH);
		frame.getContentPane().add(pImage, BorderLayout.EAST);
		pImage.setLayout(new BorderLayout(0, 0));
		pImage.add(pImageButtons,BorderLayout.SOUTH);
		
		//Image
		imageVehicule = new JLabel(image);
		pImage.add(imageVehicule,BorderLayout.CENTER);
		
		//Buttons
		JButton btnAjoutImage = new JButton(new AjouterImage());
		btnAjoutImage.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAjoutImage.addActionListener(this);
		pImageButtons.add(btnAjoutImage);
		
		JButton btnSupprimerImage = new JButton(new SupprimerImage());
		btnSupprimerImage.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSupprimerImage.addActionListener(this);
		pImageButtons.add(btnSupprimerImage);
		
		JButton btnOk = new JButton(tOk);
		btnOk.setHorizontalAlignment(SwingConstants.RIGHT);
		btnOk.addActionListener(this);
		pOptions.add(btnOk);
		
		JButton btnAppliquer = new JButton(tAppliquer);
		btnAppliquer.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAppliquer.addActionListener(this);
		pOptions.add(btnAppliquer);
		
		JButton btnAnnuler = new JButton(tAnnuler);
		btnAnnuler.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAnnuler.addActionListener(this);
		pOptions.add(btnAnnuler);
		
		//Formulaire
		SpringLayout sl_panel = new SpringLayout();
		pFormulaire.setLayout(sl_panel);
				
		for(Entry<VehiculesChamps, String> entry : map.entrySet())
		{
			JLabel label = new JLabel(entry.getKey().toString());
			pFormulaire.add(label);
			if(entry.getKey().toString() == enums.VehiculesChamps.TYPE.toString()) //Création de la combobox
			{
				String[] items = {enums.VehiculesTypes.BUS.toString(),
						enums.VehiculesTypes.CAMION.toString(),
						enums.VehiculesTypes.VOITURE.toString(),
						enums.VehiculesTypes.MOTO.toString()};
				cbType = new JComboBox<String>(items);
				cbType.setSelectedItem(entry.getValue().toString());
				pFormulaire.add(cbType);
				
			}
			else
				lChamp.add((JTextField) pFormulaire.add(new JTextField(entry.getValue())));
		    lignes++;
		}
		
		lib.SpringUtilities.makeCompactGrid(pFormulaire, lignes, colonnes, 3, 3, 3, 3);
		

	}
	
	public void actionPerformed(ActionEvent evt) {
		boolean flag = true;
		switch(evt.getActionCommand())
		{
		case tAppliquer : //On enregistre sans fermer
			flag=false;
		case tOk :
			EnumMap<VehiculesChamps, String> map = new EnumMap<>(VehiculesChamps.class);

			int i=0;
			for(VehiculesChamps champ : VehiculesChamps.values() )
			{
				if(i==0)
				{
					 //Enregistrement de la valeur du Combobox
					map.put(champ, cbType.getSelectedItem().toString());
				}
				else
					map.put(champ, ((JTextComponent) lChamp.get(i-1)).getText());
				i++;
			}
			
			getController().notifyMapChanged(map);
		case tAnnuler: //On ferme la fenetre sans enregistrer
			if(flag)
			{					
				close();
			}
			break;			
		}
	}
	
	private class AjouterImage extends AbstractAction
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2333977715790136671L;

		public AjouterImage() {
			super("Ajouter image");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser dialogue = new JFileChooser(new File("."));
            dialogue.addChoosableFileFilter(new FileNameFilter(FileNameFilter.IMAGES_TYPE));
            dialogue.setAcceptAllFileFilterUsed(false);
			
			if (dialogue.showOpenDialog(null)== 
			    JFileChooser.APPROVE_OPTION) {
				Image g = new ImageIcon((dialogue.getSelectedFile().getPath())).getImage();
				//Traitement afin de ne pas deformer l'image
				double ratio = (double) g.getWidth(null)/(double) g.getHeight(null);				
				ImageIcon imageCharge = new ImageIcon(g.getScaledInstance(
						imageWidth,
						(int) (imageWidth/ratio),
						Image.SCALE_SMOOTH));
						
			    getController().notifyImageChanged(imageCharge);
			    imageVehicule.setIcon(imageCharge);
			}	
		}
		
	}
	
	private class SupprimerImage extends AbstractAction
	{
		private static final long serialVersionUID = 4388122709672569812L;
		public SupprimerImage()
		{
			super("Supprimer Image");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			getController().notifyImageChanged(null);		
		}
		
	}

	@Override
	public void vehiculeChanged(EventVehiculeChanged event) {
		
		int i = 0;
		for(Entry<VehiculesChamps, String> champ : event.getMap().entrySet())
		{
			if(i==0) //Modification de la combobox
			{ 
				cbType.setSelectedItem(champ.getValue());
			}
			else
				((JTextComponent) lChamp.get(i-1)).setText(champ.getValue());
			i++;
		}
		
		imageVehicule.setIcon(event.getImage());	
	}

	@Override
	public void display() {
		frame.setVisible(true);
	}

	@Override
	public void close() {
		frame.dispose();
		
	}
}
