import java.io.IOException;
import java.net.URISyntaxException;

public interface ShellInterface {
    public void readInput() throws IOException, CustomException;
    public void help();
    public boolean execute(String command) throws CustomException, IOException, ClassNotFoundException, URISyntaxException;
}
