import javax.print.Doc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Shell implements  ShellInterface{
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
    public boolean execute(String command) throws CustomException, IOException, ClassNotFoundException, URISyntaxException {
        if (command.compareTo("quit")==0)
            return false;
        if (command.compareTo("help")==0)
            help();
        else if (command.compareTo("save")==0)
            SaveCommand.save();
        else if (command.compareTo("load")==0)
            LoadCommand.load();
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
    public void help(){
        System.out.println("Comenzile posibile sunt quit, help, save, load si view");
        System.out.println("quit va incheia executia programului");
        System.out.println("help este aceasta comanda");
        System.out.println("O comanda necunoscuta va arunca o eroare, dar executia programului va continua");
        System.out.println("Apasand save, load, view sau listse va incepe executia acestora, cu un protocol propriu ce va fi " +
                "afisat imediat dupa apelare");
    }


}
