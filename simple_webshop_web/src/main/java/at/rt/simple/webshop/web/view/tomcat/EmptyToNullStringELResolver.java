package at.rt.simple.webshop.web.view.tomcat;

import javax.el.ELContext;
import javax.el.ELResolver;
import java.beans.FeatureDescriptor;
import java.util.Iterator;

/**
 * Bugfix for Tomcat that submits empty string as blank instead of null.<br>
 *
 * see: https://stackoverflow.com/questions/33334192/tomcat-8-and-9-coerce-behaviour-null-strings-are-incorrectly-set-as-empty-str
 */
public class EmptyToNullStringELResolver extends ELResolver {

    @Override
    public Class<?> getCommonPropertyType(ELContext context, Object base) {
        return String.class;
    }

    @Override
    public Object convertToType(ELContext context, Object value, Class<?> targetType) {
        if (value == null && targetType == String.class) {
            context.setPropertyResolved(true);
        }

        return value;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
        return null;
    }

    @Override
    public Class<?> getType(ELContext context, Object base, Object property) {
        return null;
    }

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        return null;
    }

    @Override
    public boolean isReadOnly(ELContext context, Object base, Object property) {
        return true;
    }

    @Override
    public void setValue(ELContext context, Object base, Object property, Object value) {
        // NOOP.
    }

}
