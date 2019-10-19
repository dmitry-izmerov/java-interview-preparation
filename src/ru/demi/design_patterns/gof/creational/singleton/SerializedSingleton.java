package ru.demi.design_patterns.gof.creational.singleton;

import java.io.Serializable;

public class SerializedSingleton implements Serializable {

    private static final long serialVersionUID = -7604766932017737115L;

    private SerializedSingleton(){}
    
    private static class SingletonHelper{
        private static final SerializedSingleton instance = new SerializedSingleton();
    }
    
    static SerializedSingleton getInstance(){
        return SingletonHelper.instance;
    }

    // it's magic for serialization of singleton
    protected Object readResolve() {
        return getInstance();
    }
}