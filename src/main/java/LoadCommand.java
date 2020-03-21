import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class LoadCommand implements LoadInterface{

    /**
     * -metoda load cere utilizatorului sa introduca o cale catre locul unde se afla catalogul, care apoi va fi incarcat in memorie
     * -prin CatalogUtil.load
     * -in caz de eroare se anunta utilizatorul de esec si se termina executia metodei, revenind in execute()
     * -daca incarcarea catalogului a avut loc cu succes, utilizatorul poate sa opteze sa parareasca aceasta optiune, sau sa continue prin
     * -vizionarea fie a reportului html, fie prin comanda list, fie prin view
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws URISyntaxException
     */
    public static void load() throws IOException, ClassNotFoundException, URISyntaxException {
        Catalog catalog;
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduceti calea catre catalogul pe care doriti sa il incarcati in memorie!");
        String cale=reader.readLine();
        try {
            catalog = CatalogUtil.load(cale);
        } catch (ClassNotFoundException e) {
            System.out.println("Ne pare rau. Incarcarea catalogului a esuat! ");
            throw e;
        }
        System.out.println("Incarcarea a reusit");
        System.out.println("Daca doritu sa vizionati continutul unui catalog intr-un report html, apasati \"view\". " +
                "Daca doriti sa listati continutul catalogului, apasati \"list\""
                + "Pentru a iesi apasati quit, iar pentru a viziona un document, apasati orice altceva"
        );
        cale=reader.readLine();
        if (cale.compareTo("view")==0)
            HTMLReportCommand.reportHTML(catalog);
        else
        if (cale.compareTo("list")==0)
            ListCommand.list(catalog);
        else if (cale.compareTo("quit")==0)
            return;
        ViewCommand.view(catalog);

    }
}
