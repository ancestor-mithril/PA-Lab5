import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class ViewCommand implements ViewInterface{

    /**
     * in metoda view implementeaza aceasta comanda.
     * -exista o comunicare intre utilizator si program, utilizatorul putand vedea un document dintr-un catalog doar daca apasa "da"
     * -daca apasa da si apoi introduce un id, documentul respectiv este cautat.
     * -daca  nu este gasit utilizatorul este instiintat, dar executia se continua.
     * -daca este gasit se apeleaza metoda CatalogUtil.view(), implementata la Compulsory.
     * @param catalog
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void view(Catalog catalog) throws IOException, URISyntaxException {
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        String str;
        while(true){
            System.out.println("Daca doriti sa continuati cu vizionarea vreunui document, tastati da, altfel tastati orice");
            str=reader.readLine();
            if (str.compareTo("da")==0){
                System.out.println("Introduceti id-ul documentului ");
                str=reader.readLine();
                Document1 doc = catalog.findById(str);
                if (doc==null){
                    System.out.println("Documentul cautat nu a fost gasit!");
                    continue;
                }
                System.out.println("Documentul a fost gasit. Se incearca vizionarea lui");
                CatalogUtil.view(doc);
            }
            else {
                System.out.println("Vizionarea documentelor din catalog s-a incheiat.");
                return;
            }
        }
    }
}
