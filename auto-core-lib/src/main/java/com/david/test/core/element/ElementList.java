package com.david.test.core.element;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.remote.RemoteWebDriver;

public class ElementList extends Element implements List<Element> {
    public ElementList(RemoteWebDriver driver, String name) {
        super(driver, name);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Element> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Element element) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Element> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Element> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {}

    @Override
    public Element get(int index) {
        return null;
    }

    @Override
    public Element set(int index, Element element) {
        return null;
    }

    @Override
    public void add(int index, Element element) {}

    @Override
    public Element remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Element> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Element> listIterator(int index) {
        return null;
    }

    @Override
    public List<Element> subList(int fromIndex, int toIndex) {
        return null;
    }
}
