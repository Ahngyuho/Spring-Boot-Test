package com.example.demo.test;

import java.lang.reflect.Method;

public class TestControllerMain {
    public static void main(String[] args) {
        AnnotationTest annotationTest = new AnnotationTest();

        // 해당 클래스의 .getClass 를 통해 어떤 정보를 가져옴...
        // 그래서 런타임에 이 .getClass 를 이용해서 이 클래스의 메타정보를 가져옴
        // 클래스 메타 정보를 런타임에 가져오는 방법 -> 클래스.getClass();
        Class<? extends AnnotationTest> aClass = annotationTest.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        //
        for(Method m : aClass.getDeclaredMethods()){
            SimpleMapping simpleMapping = m.getAnnotation(SimpleMapping.class);
            if (simpleMapping != null)
                System.out.println("["+simpleMapping.value() + "] -> " + m); // 애노테이션에 지정된 값 조회 가능
        }
    }
}
