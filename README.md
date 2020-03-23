# PA-Lab5
UAIC-FII-PA
# PA-Lab5
UAIC-FII-PA
Optional:{
  1. Implementarea se gaseste in CatalogUtil::saveText() si CatalogUtil::loadText()
  Un format text recunoscut este JSON-ul, si utilizand informatiile de la
  https://kodejava.org/how-to-read-and-write-java-object-to-json-file/
  am implementat similar cu metodele save() si load()
  
  2. A fost creata clasa Shell, cu interfata ShellInterface, implementand metodele readInput(), help() si execute()
  public void readInput():{
    metoda citeste inputul utilizatorului de la tastatura, redirectandu-l pentru a fi analizat de metoda execute(), care intoarce
    valoarea doar in momentul in care utilizatorul a specificat intreruperea comunicarii, pentru a putea oprii
    "Shell-ul"
    Daca se intampina erori, acestea sunt afisate, dar executia continua. Programul se opreste doar daca da eroare citirea din stdin
  }
  public boolean execute(String command){
    analizeaza comanda introdusa de utilizator, si daca o recunoaste redirecteaza catre o noua metoda care va executa cererea, altfel va
    returna o eroare custom
    -pentru comenzile list, view, report, va fi nevoie mai intai sa se apeleze load, deoarece altfel nu avem cum sa stim la ce catalog
    ne referim
    Pentru a fi independente comenzile, ar fi trebuit sa cream niste variabile de environment in care sa retinem cataloagele create, sau 
    la care s-a dat deja load, insa nu am facut asa ceva (tot trebuie sa precizezi calea fisier/catalog, deci am decis sa le fac continue)
    
  }
  public void help(){
    Enumera elementele specifice protocolului de comunicare cu Shell-ul
  }
  
  IMPORTANT: executia programului este  realizata prin urmarea instructiunilor din shell. Pentru inceput tastati "help".
  
  3. Am creat 5 clase care implementeaza comenzi, fiecare implementandu-si interfata.
  -am decis ca sa aiba metode statice, intrucat ii mai natural sa apelezi metoda ca instanta a clasei, intrucat nu exista variabile 
  specifice vreunui obiect.
  
  a.public class ViewCommand implements ViewInterface{
    in metoda view implementeaza aceasta comanda.
    -exista o comunicare intre utilizator si program, utilizatorul putand vedea un document dintr-un catalog doar daca apasa "da"
    -daca apasa da si apoi introduce un id, documentul respectiv este cautat.
    -daca  nu este gasit utilizatorul este instiintat, dar executia se continua.
    -daca este gasit se apeleaza metoda CatalogUtil.view(), implementata la Compulsory.
  }
  
  b. public class SaveCommand implements SaveInterface{
    -in metoda save, utilizatorul introduce numele si calea unui catalog pe care doreste sa il creeze, si este creat acel catalog
    -apoi utilizatorul introduce un numar de documente pe care doreste sa le adauge in acel catalog
    -pentru fiecare document se introduce id-ul, numele, locatia, tagurile
    -documentele sunt adaugate in catalog, si la final catalogul este salvat cu CatalogUtil.save(), de la Compulsory.
    -eventualele erori pot duce doar la intreruperea comenzii de save(), nu si a shellului
  }
  
  c. public class ListCommand implements ListInterface{
    -metoda list afiseaza pe ecran continutul catalogului:numele + documentele
  }
  
  d. public class LoadCommand implements LoadInterface{
    -metoda load cere utilizatorului sa introduca o cale catre locul unde se afla catalogul, care apoi va fi incarcat in memorie 
    -prin CatalogUtil.load
    -in caz de eroare se anunta utilizatorul de esec si se termina executia metodei, revenind in execute()
    -daca incarcarea catalogului a avut loc cu succes, utilizatorul poate sa opteze sa parareasca aceasta optiune, sau sa continue prin 
    -vizionarea fie a reportului html, fie prin comanda list, fie prin view
    
  }
  
  e. public class HTMLReportCommand implements HTMLReportInterface{
    -metoda reportHTML apeleaza metoda CatalogUtil.reportHTML()
    -
  }
  
  Pentru toate cele mentionate mai sus, exceptiile vor fi in mare parte tratate in Shell::readInput(), doar daca nu este afectata
  executia metodei si se poate reveni la ea sunt tratate direct in metoda.
  
  4. CatalogUtil.reportHTML(Catalog catalog): creaza un document html in care sunt scrise numele catalogului si continutul acestuia, adica
  lista si metadata documentelor componente. Pentru fiecare apel al functiei creaza cate un document html nou, cu nume diferit de cele
  anterioare (valabil doar pentru sesiunea curenta). La finalul sesiunii se presupune ca nu se mai stie nimic din sesiunea anterioara
  De asemenea, il deschide cu load()
  
  5. Exceptiile sunt tratate, afisate, existand si exceptii custom in cazurile in care era necesar. 
  
 6. JAR-ul a fost creat prin MAVEN package, se afla in folderul target.
  in acel folder, din cmd se porneste aplicatia prin : java -jar my-app-1.0-SNAPSHOT.jar
  
}

