package com.yxxx.javasec.deserialize;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class Calc implements Serializable {
    private boolean canPopCalc = false;
    private String cmd = "ls -al";

    public Calc() {
    }

    private void readObject(ObjectInputStream objectInputStream) throws Exception {
        objectInputStream.defaultReadObject();
        if (this.canPopCalc) {
            Runtime.getRuntime().exec(this.cmd);
        }
    }
}
