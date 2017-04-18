import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Document doc;

        try {
            doc = Jsoup.connect("http://example.com")
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .post();

            System.out.println(doc);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
