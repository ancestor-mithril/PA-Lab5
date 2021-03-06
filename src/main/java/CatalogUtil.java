import org.codehaus.jackson.map.ObjectMapper;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class CatalogUtil {
    private static int a=0;

    public static void save(Catalog catalog)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
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
        try(ObjectInputStream oos = new ObjectInputStream(
                new FileInputStream(path))){
            Catalog catalog = (Catalog)oos.readObject();
            return catalog;
        }
    }
    /**
     * https://kodejava.org/how-to-read-and-write-java-object-to-json-file/
     * JSON-ul este plain text.
     */
    public static void saveText(Catalog catalog) throws IOException {
        File file= new File(catalog.getPath()+".json");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(file, catalog);
    }
    public static Catalog loadText(String path) throws IOException {
        File file= new File(path+".json");
        //se presupune ca nu va fi nevoie de +.json, ci va face parte din path, dar pentru moment ramane ca sa poata fi apelat la fel ca load()
        ObjectMapper mapper=new ObjectMapper();
        Catalog catalog=mapper.readValue(file, Catalog.class);
        return catalog;
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

    /**
     * creaza un document html in care sunt scrise numele catalogului si continutul acestuia, adica
     * lista si metadata documentelor componente.
     * @param catalog
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void reportHTML(Catalog catalog) throws IOException, URISyntaxException {
        File file=new File("report"+a+".html");
        a++;
        BufferedWriter writer=new BufferedWriter(new FileWriter(file));
        writer.write("<h1>"+catalog.getName()+ "</h1>");
        //System.out.println(catalog.getName());
        for (Document1 d:catalog.getDocuments()){
            writer.write("<p>" + d.toString()+ "</p>");
            //System.out.println(d.toString());
        }
        writer.close();
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI(file.getPath()));
    }

    public static class InvalidCatalogException extends Exception {
        public InvalidCatalogException(Exception ex) {
            super("Invalid catalog file.", ex);
        }
    }
}