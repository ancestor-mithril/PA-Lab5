import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document1> documents = new ArrayList<>();

    public Catalog(){

    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void add(Document1 doc) {
        documents.add(doc);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Document1> getDocuments() {
        return documents;
    }

    public void addDocument(Document1 document){
        this.documents.add(document);
    }

    /**
     * functie care cauta intr-o lista un Document dupa id
     * @param id
     * @return documentul gasit, sau null
     */
    public Document1 findById(String id) {
        /*
        for (Document1 d : documents)
            if (d.getId()==id)
                return d;
        throw new ClassNotFoundException("Document not found");
         */
        return documents.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);

    }
}