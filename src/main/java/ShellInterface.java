import java.io.IOException;
import java.net.URISyntaxException;

public interface ShellInterface {
    public void readInput() throws IOException, CustomException;
    public void loadView() throws IOException, ClassNotFoundException, URISyntaxException;
    public void save() throws IOException;
    public void reportHTML(Catalog catalog) throws IOException, URISyntaxException;
    public void help();
    public boolean execute(String command) throws CustomException, IOException, ClassNotFoundException, URISyntaxException;
}
