package stock;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControllerStock {
	
	private ModelListStock model = null;
	private ViewFrameStock view = null;
	private ModelTableStock table = null;
	private ViewPanelStock panelStock;
	
	public ControllerStock(ModelListStock model,JFrame frame)
	{
		this.model = model;
		table = new ModelTableStock(this);
		panelStock = new ViewPanelStock(this, table, frame);
	}
	
	public ControllerStock(ModelListStock model, ViewFrameStock view, JFrame frame)
	{
		this.model = model;
		table = new ModelTableStock(this);
		panelStock = new ViewPanelStock(this, table, frame);
	}
	
	public ModelListStock getModel()
	{
		return model;
	}
	
	public void notifyStockChange(ModelStock modelChanged, int stock)
	{
		model.get(model.lastIndexOf(modelChanged)).setNombre(stock);
		table.fireTableDataChanged();
	}
	
	public void notifyPrixChange(ModelStock modelChanged, double prix)
	{
		model.get(model.lastIndexOf(modelChanged)).setPrix(prix);
		table.fireTableDataChanged();
	}
	public void notifyTypeChange(ModelStock modelChanged, String type)
	{
		model.get(model.lastIndexOf(modelChanged)).setType(type);
		table.fireTableDataChanged();
	}
	
	public void notifyAllChange(ModelStock modelChanged, int stock, String type, double prix)
	{
		model.get(model.lastIndexOf(modelChanged)).setNombre(stock);
		model.get(model.lastIndexOf(modelChanged)).setPrix(prix);
		model.get(model.lastIndexOf(modelChanged)).setType(type);
		table.fireTableDataChanged();
	}
	
	
	private void detailsStock(int index)
	{
		view = new ViewFrameStock(this, model.get(index));
		model.get(index).addStockListener(view);
		view.display();
	}
	
	public JPanel getPanel()
	{
		return panelStock;
	}
	
	public void displayView(int index){
		detailsStock(index);
	}
	
	public void ajouterStock()
	{
		model.add(new ModelStock());
		table.fireTableRowsInserted(model.size()-2, model.size()-1);
		displayView(model.size()-1);
	}
	
	public void supprimerStock(int index)
	{
		model.remove(index);
		table.fireTableDataChanged();
	}
	
	public ModelListStock getList()
	{
		return model;
	}
	
	public void closeView()
	{
		view.close();
	}

	public void setModel(ModelListStock model) {
		this.model = model;
		table.fireTableDataChanged();
	}
	
}
