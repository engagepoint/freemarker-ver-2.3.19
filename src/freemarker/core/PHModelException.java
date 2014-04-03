package freemarker.core;

import freemarker.template.TemplateModel;

/**
 * Created by oleksiialmiz on 3/27/14.
 */
public class PHModelException extends InvalidReferenceException {

    private static final long serialVersionUID = 1L;
    private TemplateModel model;

    public PHModelException(Environment env) {
        super(env);
    }

    public PHModelException(Environment env, TemplateModel model) {
        super(env);
        this.model = model;
    }

    public IPlaceholderCaptureModel getModel() {
        if (model instanceof IPlaceholderCaptureModel) {
            return (IPlaceholderCaptureModel) model;
        }
        return null;
    }

}