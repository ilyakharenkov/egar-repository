package ru.egartech.systemObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Registry {

    private final List<BaseObjects> baseObjects;

    public Registry(List<BaseObjects> baseObjects){
        this.baseObjects = baseObjects;
    }

    private final Map<String, BaseObjects> listRegistry = new HashMap<>();

    public Map<String, BaseObjects> execute(){
        baseObjects.forEach(obj -> {
            listRegistry.put(obj.getMarker(), obj);
        });
        return listRegistry;
    }



}
