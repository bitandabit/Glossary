import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class GlossaryTest {

    @Test
    public void generateWordHeader_KnownWordInARow() {
        SimpleWriter file = new SimpleWriter1L("test.html");
        String word = "Look";
        String definition = "to make sure or take care";
        boolean result = false;

        Queue<String> glossary = new Queue1L<>();
        glossary.enqueue("sure");
        glossary.enqueue("care");
        glossary.enqueue("take");
        glossary.enqueue("make");

        Glossary.generateWordHeader(file, word, definition, glossary);

        SimpleReader fileR = new SimpleReader1L("test.html");

        while (!fileR.atEOS()) {
            String t = fileR.nextLine();
            if (t.equals("<blockquote>to <a href=\"make.html\">make</a>"
                    + " <a href=\"sure.html\">sure</a> "
                    + "or <a href=\"take.html\">take</a> "
                    + "<a href=\"care.html\">care</a></blockquote>")) {
                result = true;
            }
        }
        fileR.close();
        file.close();

        assertEquals(true, result);
    }

    @Test
    public void generateWordHeader_KnownWordAtBeginingAndEnd() {
        SimpleWriter file = new SimpleWriter1L("test.html");
        String word = "Look";
        String definition = "to make sure or take care";
        boolean result = false;

        Queue<String> glossary = new Queue1L<>();
        glossary.enqueue("to");
        glossary.enqueue("care");

        Glossary.generateWordHeader(file, word, definition, glossary);

        SimpleReader fileR = new SimpleReader1L("test.html");

        while (!fileR.atEOS()) {
            String t = fileR.nextLine();
            if (t.equals(
                    "<blockquote><a href=\"to.html\">to</a> make sure or take "
                            + "<a href=\"care.html\">care</a></blockquote>")) {
                result = true;
            }
        }
        fileR.close();
        file.close();

        assertEquals(true, result);
    }

}
