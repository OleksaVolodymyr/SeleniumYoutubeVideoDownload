package com.rizon.youtube.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LocatingCustomElementListHandler<T> implements InvocationHandler {
    private static final Logger LOG = Logger.getLogger(LocatingCustomElementListHandler.class);
    private final ElementLocator locator;
    private final Class<?> clazz;

    public LocatingCustomElementListHandler(ElementLocator locator, Class<?> clazz) {
        this.locator = locator;
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        List<WebElement> elements = this.locator.findElements();
        List<T> customs = new ArrayList<>();
        if (elements.isEmpty()) return null;
        for (WebElement element : elements) {
            customs.add((T) clazz.getConstructor(WebElement.class).newInstance(element));
        }
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
            throw e.getCause();
        }
    }
}
