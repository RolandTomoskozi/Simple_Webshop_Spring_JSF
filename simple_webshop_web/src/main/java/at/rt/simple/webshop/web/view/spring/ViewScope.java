package at.rt.simple.webshop.web.view.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Implementiert den JSF ViewScope als Spring-Scope.
 */
public class ViewScope implements Scope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> viewMap = getViewMap();
        return viewMap.computeIfAbsent(name, k -> objectFactory.getObject());
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> viewMap = getViewMap();
        Object bean = viewMap.get(name);
        if (bean != null) {
            viewMap.remove(name);
        }
        return bean;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // Not implemented yet (optional method)
    }

    @Override
    public String getConversationId() {
        return null;
    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    private Map<String, Object> getViewMap() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
    }
}
