package freemarker.core;

import freemarker.template.*;

/**
 * Created by oleksiialmiz on 3/27/14.
 */
public interface IPlaceholderCaptureModel extends TemplateHashModel, TemplateSequenceModel, TemplateScalarModel, TemplateDateModel, TemplateBooleanModel, TemplateNumberModel {

    void getExpression(Expression expr);

    IParameterDefinition getModel();
}
