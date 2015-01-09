package main;

import facturation.ControllerFacturation;
import facturation.ModelListFacturation;
import gestionVehicule.ControllerGestionVehicule;
import gestionVehicule.ModelGestionVehicule;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.border.TitledBorder;
import stock.ControllerStock;
import stock.ModelListStock;

import lib.FileNameFilter;

public class Main {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenuItem mFichierOuvrir;
	private JMenuItem mFichierEnregistrer;
	private JMenuItem mFichierEnregistrerSous;
	private JMenuItem mFichierQuitter;
	
	private JPanel panelContent;
	
	private JPanel panelGestionVehicule;
	private ControllerGestionVehicule controllerGestionVehicule;
	private ModelGestionVehicule modelGestionVehicule;
	
	private JPanel panelGestionStock;
	private ControllerStock controllerStock;
	private ModelListStock modelStock;
	
	private JPanel panelGestionFacturation;
	private ControllerFacturation controllerFacturation;
	private ModelListFacturation modelFacturation;
	
	private File fileOpen = null;
	private SerializableModel serialModel = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Garage sans rage");
		frame.setBounds(50, 50, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		modelGestionVehicule = new ModelGestionVehicule();
		controllerGestionVehicule = new ControllerGestionVehicule(modelGestionVehicule,frame);
		panelGestionVehicule = controllerGestionVehicule.getPanel();
		
		modelStock = new ModelListStock();
		controllerStock = new ControllerStock(modelStock, frame);
		panelGestionStock = controllerStock.getPanel();
		
		modelFacturation = new ModelListFacturation();
		controllerFacturation = new ControllerFacturation(modelFacturation, frame, controllerGestionVehicule);
		panelGestionFacturation = controllerFacturation.getPanel();
		
		
		menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mFichierOuvrir = new JMenuItem("Ouvrir");
		mFichierOuvrir.addActionListener(new ActionOuvrir());
		mnFichier.add(mFichierOuvrir);
		
		mFichierEnregistrer = new JMenuItem("Enregistrer");
		mFichierEnregistrer.addActionListener(new ActionEnregistrer(false));
		mnFichier.add(mFichierEnregistrer);
		
		mFichierEnregistrerSous = new JMenuItem("Enregistrer sous");
		mFichierEnregistrerSous.addActionListener(new ActionEnregistrer(true));
		mnFichier.add(mFichierEnregistrerSous);
		
		mnFichier.addSeparator();
		
		mFichierQuitter = new JMenuItem("Quitter");
		mFichierQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		mnFichier.add(mFichierQuitter);
		
		panelContent = new JPanel();
		frame.getContentPane().add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new GridLayout(3, 1, 0, 0));
		
		panelGestionVehicule.setBorder(new TitledBorder(BorderFactory.createTitledBorder("Gestion des vehicules")));
		panelContent.add(panelGestionVehicule);
		
		panelGestionStock.setBorder(new TitledBorder(BorderFactory.createTitledBorder("Gestion du stock")));
		panelContent.add(panelGestionStock);
		
		panelGestionFacturation.setBorder(new TitledBorder(BorderFactory.createTitledBorder("Gestion des interventions et facturations")));
		panelContent.add(panelGestionFacturation);
			
	}
	private class ActionEnregistrer extends AbstractAction
	{
		private static final long serialVersionUID = 6649476674268016833L;
		private boolean enregistrerSous;
		public ActionEnregistrer(boolean enregistrerSous)
		{
			this.enregistrerSous = enregistrerSous;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(fileOpen == null)
			{
				enregistrerSous = true;
			}

            if(enregistrerSous)
            {
            	JFileChooser dialogue = new JFileChooser(new File("."));
                dialogue.setAcceptAllFileFilterUsed(false);
                dialogue.addChoosableFileFilter(new FileNameFilter(FileNameFilter.GARAGE_TYPE));
            	if (dialogue.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
            	{

            		String s = dialogue.getSelectedFile().getAbsoluteFile().getName();
                    int i = s.lastIndexOf('.');

                    if (i > 0 &&  i < s.length() - 1 && s.substring(i+1).toLowerCase().contains("gar"))
                    {
                		fileOpen = dialogue.getSelectedFile().getAbsoluteFile();
                    }
                    else
                    {
                    	fileOpen = new File(dialogue.getSelectedFile().getAbsoluteFile() + ".gar");
                    }
            	}
            	else
            	{
            		return ;
            	}
            }
            
			if (fileOpen != null)
			{			
				ObjectOutputStream oos = null;
				serialModel = new SerializableModel(controllerGestionVehicule.getModel(),controllerStock.getModel(), controllerFacturation.getModel());
				try {
					final FileOutputStream fichier = new FileOutputStream(fileOpen);
					oos = new ObjectOutputStream(fichier);
					oos.writeObject(serialModel);
				} catch (final java.io.IOException exception) {
					 exception.printStackTrace();
				} finally {
					try {
						if (oos != null) {
						  oos.flush();
						  oos.close();
						}
					} catch (final IOException exception) {
						exception.printStackTrace();
					}
				}
            }
		}
	}
	
	private class ActionOuvrir extends AbstractAction
	{
		private static final long serialVersionUID = 7882817473535069839L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser dialogue = new JFileChooser(new File("."));
			dialogue.setFileSelectionMode(JFileChooser.FILES_ONLY);
			dialogue.setAcceptAllFileFilterUsed(false);
            dialogue.addChoosableFileFilter(new FileNameFilter(FileNameFilter.GARAGE_TYPE));
            
			
			if (dialogue.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
			{	
				fileOpen = dialogue.getSelectedFile().getAbsoluteFile();
				ObjectInputStream ois = null;
			    try {
					final FileInputStream fichier = new FileInputStream(fileOpen);
					ois = new ObjectInputStream(fichier);
					final SerializableModel obj = (SerializableModel) ois.readObject();
					controllerGestionVehicule.setModel(obj.getModelVehicule());
					controllerStock.setModel(obj.getModelStock());
					controllerFacturation.setModel(obj.getModelFacturation());
			    }catch (final java.io.IOException ex) {
			    	ex.printStackTrace();
			    } catch (final ClassNotFoundException ex) {
			    	ex.printStackTrace();
			    } finally {
			    	try {
			    		if (ois != null) {
			    			ois.close();
			    		}
			    	} catch (final IOException ex) {
			    		ex.printStackTrace();
			    	}
			    }
			}

		}
	}
}
