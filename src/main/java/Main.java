import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import javax.swing.text.Document;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String args[])  {
        Main app = new Main();
/*
        app.testCreateSave();
        try {
            app.testLoadView();
        } catch (CatalogUtil.InvalidCatalogException | URISyntaxException e) {
            e.printStackTrace();
        }
*/
        try {
            ShellInterface shell=new Shell();
            shell.readInput();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CustomException e) {
            e.printStackTrace();
        }


    }

    /**
     * functie in care creeam un catalog si il salvam intr-un fisier
     */
    private void testCreateSave() {
        try{
            Catalog catalog =
                    new Catalog("Java Resources",
                            ".\\catalog.ser");
            Document1 doc = new Document1("java2", "Java Course 1",
                    "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");

            Document1 d=new Document1("da", "da", "da");
            doc.addTag("type", "Slides");
            catalog.add(doc);

            CatalogUtil.saveText(catalog);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * functie in care citim un catalog dintr-un fisier si deschidem ceea ce se afla in el
     * @throws CatalogUtil.InvalidCatalogException
     */
    private void testLoadView() throws CatalogUtil.InvalidCatalogException, URISyntaxException {
        try {
            Catalog catalog = CatalogUtil.loadText
             (".\\catalog.ser");
            //System.out.println(catalog);
            Document1 doc = catalog.findById("java2");
            if (doc==null)
                throw new CatalogUtil.InvalidCatalogException(new Exception("Nu exista documentul"));
            //System.out.println(doc);
            CatalogUtil.view(doc);
        } catch (/*CatalogUtil.InvalidCatalogException |*/ IOException e) {
            e.printStackTrace();
        }
    }
}