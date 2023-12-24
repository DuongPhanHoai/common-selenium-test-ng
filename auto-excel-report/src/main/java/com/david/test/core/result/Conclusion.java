package com.david.test.core.result;

import java.util.ArrayList;

public class Conclusion {
    String name = null;
    int index = -1;
    String result = null;

    public int getIndex() {
        return index;
    }

    public Conclusion(String name, String result, int index) {
        this.name = name;
        this.result = result;
        this.index = index;
    }

    public boolean isName(String name) {
        if (this.name != null && name != null) return this.name.equalsIgnoreCase(name);
        return false;
    }

    // ******** FACTORY ********
    static ArrayList<Conclusion> instances = new ArrayList<>();

    public static int getInstanceIndex(String name) {
        for (int iExistingResult = (instances.size() - 1); iExistingResult >= 0; iExistingResult--)
            if (instances.get(iExistingResult).isName(name))
                return instances.get(iExistingResult).getIndex();
        return -1;
    }

    public static Conclusion addConclusion(String name, String result) {
        int foundIndex = getInstanceIndex(name) + 1;
        Conclusion conclusion = new Conclusion(name, result, foundIndex);
        instances.add(new Conclusion(name, result, foundIndex));
        return conclusion;
    }
}
