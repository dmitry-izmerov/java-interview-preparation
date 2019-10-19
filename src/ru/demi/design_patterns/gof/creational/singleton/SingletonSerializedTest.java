package ru.demi.design_patterns.gof.creational.singleton;

import java.io.*;

public class SingletonSerializedTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializedSingleton instanceOne = SerializedSingleton.getInstance();
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("test"))) {
            out.writeObject(instanceOne);
        }
        
        //deserailize from file to object
        SerializedSingleton instanceTwo;
        try (ObjectInput in = new ObjectInputStream(new FileInputStream("test"))) {
            instanceTwo = (SerializedSingleton) in.readObject();
        }
        
        System.out.println("instanceOne hashCode=" + instanceOne.hashCode());
        System.out.println("instanceTwo hashCode=" + instanceTwo.hashCode());
    }
}