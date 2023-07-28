package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//标识方法自动填充

//目标是方法
@Target(ElementType.METHOD)

//@Retention修饰注解，用来表示注解的生命周期，生命周期的长短取决于@Retention的属性RetentionPolicy指定的值
//RetentionPolicy.SOURCE	表示注解只保留在源文件，当java文件编译成class文件，就会消失	源文件	    只是做一些检查性的操作，，比如 @Override 和 @SuppressWarnings
//RetentionPolicy.CLASS	    注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期	class文件（默认）	要在编译时进行一些预处理操作，比如生成一些辅助代码（如 ButterKnife）
//RetentionPolicy.RUNTIME	注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在	运行时也存在	需要在运行时去动态获取注解信息
@Retention(RetentionPolicy.RUNTIME	)
public @interface Autofill {
    //UPDATE 和 INSERT
    OperationType value();
}
