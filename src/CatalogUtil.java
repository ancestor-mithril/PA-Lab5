import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CatalogUtil {


    public static void save(Catalog catalog)
            throws IOException {
        try (var oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
             oos.writeObject(catalog);
        }
    }

    /**
     * citim de la path-ul respectiv un obiect binar
     * @param path
     * @return obiectul citit
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Catalog load(String path)
    throws ClassNotFoundException, IOException {
        //return new Catalog(path.substring(path.lastIndexOf('/')+1), path);
        try(var oos = new ObjectInputStream(
                new FileInputStream(path))){
            Catalog catalog = (Catalog)oos.readObject();
            return catalog;
        }
    }

    /**
     * dupa tipul documentului il deschidem ca File sau c
     * @param doc
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void view(Document1 doc) throws IOException, URISyntaxException {


        //… browse or open, depending of the location type
        //sau:
        Desktop desktop = Desktop.getDesktop();
        File file = new File("c:\\cygwin\\cygwin.bat");
        if (!file.isDirectory())
            file = file.getParentFile();
        if (file.exists()){
            desktop.open(new File(doc.getLocation()));
        }
        else
            desktop.browse(new URI(doc.getLocation()));

    }

    public static class InvalidCatalogException extends Exception {
        public InvalidCatalogException(Exception ex) {
            super("Invalid catalog file.", ex);
        }
    }
}