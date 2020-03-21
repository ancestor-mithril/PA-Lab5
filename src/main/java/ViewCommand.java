import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class ViewCommand implements ViewInterface{
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
