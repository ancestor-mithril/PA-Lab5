import javax.print.Doc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Shell {
    public static void readInput()
            throws IOException, CustomException {
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        String command;
        boolean flag=true;
        while(flag){
            command=reader.readLine();
            try{
                flag=execute(command);
            }
            catch(Exception e){
                e.printStackTrace();
                flag=true;
            }
        }
    }
    public static boolean execute(String command) throws CustomException, IOException, ClassNotFoundException, URISyntaxException {
        if (command.compareTo("quit")==0)
            return false;
        if (command.compareTo("help")==0)
            help();
        else if (command.compareTo("save")==0)
            save();
        else if (command.compareTo("load")==0)
            loadView();
        else if (command.compareTo("view")==0)
            loadView();
        else
            throw new CustomException("Comanda nerecunoscuta. Pentru mai multe informatii tastati \"help\"");
        return true;
    }
    public static void help(){
        System.out.println("Comenzile posibile sunt quit, help, save, load si view");
        System.out.println("quit va incheia executia programului");
        System.out.println("help este aceasta comanda");
        System.out.println("O comanda necunoscuta va arunca o eroare, dar executia programului va continua");
        System.out.println("Apasand save, load sau view se va incepe executia acestora, cu un protocol propriu ce va fi afisat imediat dupa apelare");
    }
    public static void save() throws IOException {
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduceti numele catalogului pe care doriti sa il creati:");
        String nume=reader.readLine();
        System.out.println("Introduceti calea catre catalogului pe care doriti sa il creati:");
        String cale=reader.readLine();
        Catalog catalog=new Catalog(nume, cale);
        boolean flag =true;
        int n=1;
        while(flag){
            flag=false;
            System.out.println("Introduceti numarul de documente pe care doriti sa le creati in catalog");
            try{
                n=Integer.parseInt(reader.readLine());
            }
            catch (NumberFormatException e){
                System.out.println("Va rugam introduceti un NUMAR");
                flag=true;
            }
        }
        String id;
        String locatie;
        for (int i=0; i<n; i++){
            System.out.println("Introduceti id-ul documentului " + i + " din " + n);
            id=reader.readLine();
            System.out.println("Introduceti numele documentului " + i + " din " + n);
            nume=reader.readLine();
            System.out.println("Introduceti locatia documentului " + i + " din " + n);
            locatie=reader.readLine();
            Document1 d=new Document1(id, nume, locatie);
            System.out.println("Introduceti tipul tagului pentru documentul " + i + " din " + n);
            id=reader.readLine();
            System.out.println("Introduceti tagul propriuzis pentru documentul " + i + " din " + n);
            nume=reader.readLine();
            d.addTag(id, nume);
            catalog.add(d);
        }
        CatalogUtil.save(catalog);
        System.out.println("Felicitari, documentul a fost salvat!");
    }
    public static void loadView() throws IOException, ClassNotFoundException, URISyntaxException {
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
        while(true){
            System.out.println("Daca doriti sa continuati cu vizionarea vreunui document, tastati da, altfel tastati orice");
            cale=reader.readLine();
            if (cale.compareTo("da")==0){
                System.out.println("Introduceti id-ul documentului ");
                cale=reader.readLine();
                Document1 doc = catalog.findById(cale);
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
