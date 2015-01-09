package lib;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileNameFilter extends FileFilter {

	public final static int IMAGES_TYPE = 0;
	public final static int GARAGE_TYPE = 1;
	
	private int type;
	
	public FileNameFilter(int type)
	{
		this.type = type;
	}
	
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null	&& type == IMAGES_TYPE) {
            if (extension.equals(Utils.tiff) ||
                extension.equals(Utils.tif) ||
                extension.equals(Utils.gif) ||
                extension.equals(Utils.jpeg) ||
                extension.equals(Utils.jpg) ||
                extension.equals(Utils.png) ||
            	extension.equals(Utils.TIFF) ||
                extension.equals(Utils.TIF) ||
                extension.equals(Utils.GIF) ||
                extension.equals(Utils.JPEG) ||
                extension.equals(Utils.JPG) ||
                extension.equals(Utils.PNG)) {
                    return true;
            }
            else
            {
            	return false;
            }
        }
        else if(extension != null && type == GARAGE_TYPE )
        {
        	if(extension.equals("GAR") ||
        	   extension.equals("gar"))
        	{
        		return true;
        	}
        	else
        	{
          	return false;
        	}
        }
        
        return false;
    }

    public String getDescription() {
    	if(type == IMAGES_TYPE)
    	{
    		return "Images";
    	}
    	else if(type == GARAGE_TYPE)
    	{
    		return "Fichier Garage (.gar)";
    	}
    	return "Tous les fichiers";
    }
    
    private static class Utils {
        public final static String jpeg = "jpeg";
        public final static String jpg = "jpg";
        public final static String gif = "gif";
        public final static String tiff = "tiff";
        public final static String tif = "tif";
        public final static String png = "png";
        public final static String JPEG = "JPEG";
        public final static String JPG = "JPG";
        public final static String GIF = "GIF";
        public final static String TIFF = "TIFF";
        public final static String TIF = "TIF";
        public final static String PNG = "PNG";
        
        public static String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 &&  i < s.length() - 1) {
                ext = s.substring(i+1).toLowerCase();
            }
            return ext;
        }
    }

}
