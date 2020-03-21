import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SaveCommand implements SaveInterface{
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
            if (catalog.findById(id)!=null){
                System.out.println("Exista deja un document cu id-ul respectiv");
                i--;
                continue;
            }

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
}
