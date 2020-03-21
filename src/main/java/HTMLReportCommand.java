import java.io.IOException;
import java.net.URISyntaxException;

public class HTMLReportCommand implements HTMLReportInterface{
    public static void reportHTML(Catalog catalog) throws IOException, URISyntaxException {
        CatalogUtil.reportHTML(catalog);
    }
}
