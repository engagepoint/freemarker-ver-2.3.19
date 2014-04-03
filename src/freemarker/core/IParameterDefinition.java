package freemarker.core;

import java.util.List;
import java.util.Set;

/**
 * Created by oleksiialmiz on 3/27/14.
 */
public interface IParameterDefinition {

    String getKey();

    void setKey(String key);

    String getTypeAsString();

    void setTypeAsString(String type);

    Set getSubTypes();

    IParameterDefinition getSubType(String subTypeKey);

    IParameterDefinition getSubType(int index);

    void addSubType(IParameterDefinition subType);

    String toString(int ind);

    boolean isOptional();

    void setOptional(boolean optional);

    String getDefaultValue();

    void setDefaultValue(String defaultValue);

    String getGpDefaultValue();

    void setGpDefaultValue(String gpDefaultValue);

    List getSubDefinitions();
}
