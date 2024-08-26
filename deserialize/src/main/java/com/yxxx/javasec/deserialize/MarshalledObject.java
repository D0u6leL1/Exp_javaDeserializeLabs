
package com.yxxx.javasec.deserialize;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class MarshalledObject implements Serializable {
    private byte[] bytes = null;

    public MarshalledObject() {
    }

    public Object readResolve() throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object obj = objectInputStream.readObject();
        objectInputStream.close();
        return obj;
    }
}
