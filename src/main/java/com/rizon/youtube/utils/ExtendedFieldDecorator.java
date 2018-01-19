package com.rizon.youtube.utils;

import com.rizon.youtube.control.AbstractElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {
    private static final Logger LOG = Logger.getLogger(ExtendedFieldDecorator.class);

    public ExtendedFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
//        if (!AbstractElement.class.isAssignableFrom(field.getType()) && !super.isDecoratableList(field)) {
//            return null;
//        } else {
        ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        } else if (List.class.isAssignableFrom(field.getType())) {
            return proxyForListLocator(loader, locator, field);
        } else if (AbstractElement.class.isAssignableFrom(field.getType())) {
            return proxyForLocator(loader, locator, field);
        }
        return super.decorate(loader, field);
        // }
    }

    @SuppressWarnings("unchecked")
    protected <T> T proxyForLocator(ClassLoader loader, ElementLocator locator, Field field) {
        WebElement proxy = proxyForLocator(loader, locator);
        Class<?> clazz = field.getType();
        LOG.info("Create element " + clazz.getSimpleName() + " with locator : " + locator.toString());
        T element = null;
        try {
            element = (T) clazz.getConstructor(WebElement.class).newInstance(proxy);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return element;
    }

    @SuppressWarnings("unchecked")
    protected <T> List<T> proxyForListLocator(ClassLoader loader, ElementLocator locator, Field field) {
        Type genericType = field.getGenericType();
        Class<?> clazz = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        LOG.info("Create list of elements " + clazz.getSimpleName() + " with locator : " + locator.toString());
        InvocationHandler handler = new LocatingCustomElementListHandler<>(locator, clazz);
        List<T> proxy = (List<T>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return proxy;
    }

}
