import javax.print.Doc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Shell implements  ShellInterface{
    /**
     * metoda citeste inputul utilizatorului de la tastatura, redirectandu-l pentru a fi analizat de metoda execute(), care intoarce
     * valoarea doar in momentul in care utilizatorul a specificat intreruperea comunicarii, pentru a putea oprii
     * "Shell-ul"
     * Daca se intampina erori, acestea sunt afisate, dar executia continua. Programul se opreste doar daca da eroare citirea din stdin
     *
     * @throws IOException
     * @throws CustomException
     */
    public void readInput()
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

    /**
     *  analizeaza comanda introdusa de utilizator, si daca o recunoaste redirecteaza catre o noua metoda care va executa cererea,
     *  altfel va returna o eroare custom
     *  pentru comenzile list, view, report, va fi nevoie mai intai sa se apeleze load, deoarece altfel nu avem cum sa stim la ce
     *  catalog ne referim
     *  Pentru a fi independente comenzile, ar fi trebuit sa cream niste variabile de environment in care sa retinem cataloagele
     *  create, sau la care s-a dat deja load, insa nu am facut asa ceva (tot trebuie sa precizezi calea fisier/catalog,
     *  deci am decis sa le fac continue)
     *
     * @param command
     * @return false doar daca utilizatorul introduce "quit"
     * @throws CustomException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws URISyntaxException
     */
    public boolean execute(String command) throws CustomException, IOException, ClassNotFoundException, URISyntaxException {
        if (command.compareTo("quit")==0)
            return false;
        if (command.compareTo("help")==0)
            help();
        else if (command.compareTo("save")==0)
            SaveCommand.save();
        else if (command.compareTo("load")==0)
            LoadCommand.load();
        else if (command.compareTo("report")==0) {
            System.out.println("Mai intai precizati locatia catalogului la care doriti reportul HTML");
            LoadCommand.load();
        }
        else if (command.compareTo("list")==0){
            System.out.println("Mai intai precizati locatia catalogului pe care doriti sa il listati");
            LoadCommand.load();
        }
        else if (command.compareTo("view")==0){
            System.out.println("Mai intai precizati locatia catalogului pe care doriti sa il listati");
            LoadCommand.load();
        }
        else
            throw new CustomException("Comanda nerecunoscuta. Pentru mai multe informatii tastati \"help\"");
        return true;
    }

    /**
     * Enumera elementele specifice protocolului de comunicare cu Shell-ul
     */
    public void help(){
        System.out.println("Comenzile posibile sunt quit, help, save, load si view");
        System.out.println("quit va incheia executia programului");
        System.out.println("help este aceasta comanda");
        System.out.println("O comanda necunoscuta va arunca o eroare, dar executia programului va continua");
        System.out.println("Apasand save, load, view, report sau list se va incepe executia acestora, cu un protocol propriu ce va fi " +
                "afisat imediat dupa apelare");
    }


}
