 package com.java.clazz.aware;

 import org.springframework.beans.factory.BeanClassLoaderAware;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.clazz.aware
 * @ClassName: ClassLoaderUtil
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/19 1:07
 * @Version: 1.0
 */
@Component
public class ClassLoaderUtil implements BeanClassLoaderAware {

     private static ClassLoader classLoader;

     @Override
     public void setBeanClassLoader(ClassLoader classLoader) {
         ClassLoaderUtil.classLoader = classLoader;
     }

     private static ClassLoader getClassLoader(){
         System.out.println("classLoader:"+classLoader);
         return classLoader;
     }

     public static String getPathUrl(){
         System.out.println("getClassLoader().getResource():"+getClassLoader().getResource("/"));
         return getClassLoader().getResource("/").getPath();
     }

 }
