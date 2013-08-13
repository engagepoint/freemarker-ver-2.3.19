package freemarker.testcase;

import junit.framework.TestCase;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.reference.DefaultEncoder;

/**
 * User: denis.bilyk
 * Date: 8/13/13
 * Time: 12:44 PM
 */
public class EaspiLoggerTest extends TestCase {
    private  static Encoder encoder;
    private String string = "<html>logger.test</html>";

    public EaspiLoggerTest(String name) {
        super(name);
        encoder = DefaultEncoder.getInstance();
    }

    public void testEaspiLogger(){
        assertEquals("&lt;html&gt;logger.test&lt;&#x2f;html&gt;",encoder.encodeForHTML(string));
    }
}
