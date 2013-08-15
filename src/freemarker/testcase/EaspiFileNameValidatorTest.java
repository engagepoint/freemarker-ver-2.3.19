package freemarker.testcase;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.Validator;
import org.owasp.esapi.reference.DefaultEncoder;
import org.owasp.esapi.reference.DefaultValidator;

/**
 * User: leonid.marushevskiy
 */
public class EaspiFileNameValidatorTest extends TestCase {

    public EaspiFileNameValidatorTest(String name) {
        super(name);
    }

    public void testEaspiFileNameValidator(){
        assertTrue(isValidFileName("test.exe"));
        assertTrue(isValidFileName("test.txt"));
        assertTrue(isValidFileName("test.ftl"));
        assertTrue(isValidFileName("test.fm"));       
        
        assertTrue(!isValidFileName("test.slkf;lsdkf;"));      
        assertTrue(isValidFileName("test"));      
    }
    
    private static boolean isValidFileName(String name) {
        List list = new ArrayList();
        list.add("HTMLEntityCodec");
        Encoder encoder = new DefaultEncoder(list);
        Validator instance = new DefaultValidator(encoder);
        List extentions = new ArrayList(ESAPI.securityConfiguration().getAllowedFileExtensions());
        extentions.add("ftl");
        extentions.add("fm");
        extentions.add("");
        instance.isValidFileName("FileTemplateLoader", "doc/text.txt", extentions, false);

        return instance.isValidFileName("FileTemplateLoader", name, extentions, false);
    }
}
