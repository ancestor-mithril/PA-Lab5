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
    public static Catalog load(String path)
    throws ClassNotFoundException, IOException {
        //return new Catalog(path.substring(path.lastIndexOf('/')+1), path);
        try(var oos = new ObjectInputStream(
                new FileInputStream(path))){
            Catalog catalog = (Catalog)oos.readObject();
            return catalog;
        }
    }
    public static void view(Document1 doc) throws IOException, URISyntaxException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI(doc.getLocation()));
        //… browse or open, depending of the location type
    }

    public class InvalidCatalogException extends Exception {
        public InvalidCatalogException(Exception ex) {
            super("Invalid catalog file.", ex);
        }
    }
}