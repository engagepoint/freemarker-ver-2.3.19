package freemarker.testcase;

import freemarker.template.utility.StringUtil;
import java.io.File;
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
    
    private static final boolean SEP_IS_SLASH = File.separatorChar == '/';

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
        assertTrue(isValidFileName("src/test/resources/helloworld.ftl"));   
    }
    
    public void testRecursiveSubstringDeletion(){
        assertEquals("/etc/test/test.ftl", replaceSubstringRecursively("/etc/.../...//test/test.ftl", "../"));
    }
    
    public void testIsValidFilePath(){
        assertTrue(isNotValidFilePath("src/resources/test.ftl"));
    }
    
    private boolean isValidFileName(String name) {
        name = name.substring(name.lastIndexOf("/")+1);
        name = name.substring(name.lastIndexOf("\\")+1);
        List list = new ArrayList();
        list.add("HTMLEntityCodec");
        Encoder encoder = new DefaultEncoder(list);
        Validator instance = new DefaultValidator(encoder);
        List extentions = new ArrayList(ESAPI.securityConfiguration().getAllowedFileExtensions());
        extentions.add("ftl");
        extentions.add("fm");
        extentions.add("");
        return instance.isValidFileName("FileTemplateLoader", name, extentions, false);
    }

    private String replaceSubstringRecursively(String name, String substring) {
        if (name == null) {
            return name;
        }
        if (name.indexOf(substring) >= 0) {
            name = StringUtil.replace(name, substring, "", true, false);
            return replaceSubstringRecursively(name, substring);
        }
        return name;
    }

    private boolean isNotValidFilePath(String sanitizedName) {
        if (sanitizedName == null || sanitizedName.isEmpty()) {
            return false;
        }

        if (sanitizedName.length() > 255) {
            return false;
        }
        String fileName = sanitizedName.substring(sanitizedName.lastIndexOf(File.separatorChar) + 1);
        String filePath = sanitizedName.substring(0, sanitizedName.lastIndexOf(File.separatorChar) + 1);

        return true;
    }
    
    
}
