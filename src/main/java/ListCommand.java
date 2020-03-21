public class ListCommand implements ListInterface{

    /**
     * metoda list afiseaza pe ecran continutul catalogului:numele + documentele
     * @param catalog
     */
    public static void list(Catalog catalog){
        System.out.println("Catalog");
        System.out.println("Nume: "+catalog.getName());
        for (Document1 d:catalog.getDocuments()){
            System.out.println(d.toString());
        }
    }
}
