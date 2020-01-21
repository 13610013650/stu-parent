 package com.java.clazz.test;

 import com.java.clazz.annotation.Test;
 import com.java.clazz.test.TestInterface;
 import com.java.clazz.utils.ClassUtil;
 import org.springframework.beans.factory.BeanClassLoaderAware;
 import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
 import org.springframework.context.annotation.AnnotationConfigUtils;
 import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
 import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
 import org.springframework.context.support.ClassPathXmlApplicationContext;
 import org.springframework.core.annotation.AnnotationUtils;
 import org.springframework.util.ClassUtils;
 import sun.reflect.annotation.AnnotationSupport;

 import javax.management.loading.ClassLoaderRepository;
 import java.io.IOException;
 import java.lang.reflect.InvocationTargetException;
 import java.lang.reflect.Method;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.Set;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.clazz.test
 * @ClassName: TestMain
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/18 20:58
 * @Version: 1.0
 */
public class TestMain {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ArrayList<Class> allClassByInterface =
                ClassUtil.getAllClassByInterface(TestInterface.class);
        Set<Class<?>> allInterfacesForClassAsSet = ClassUtils.getAllInterfacesForClassAsSet(TestInterface.class);
        Iterator<Class<?>> iterator = allInterfacesForClassAsSet.iterator();
        System.out.println("实现类的个数为:"+allInterfacesForClassAsSet.size());
        while (iterator.hasNext()){
            Class<?> next = iterator.next();
            System.out.println(next.getName());
        }
        Object defaultValue = AnnotationUtils.getDefaultValue(Test.class);
        System.out.println("defaultValue:"+defaultValue);
        for (int i = 0; i < allClassByInterface.size(); i++) {
            Class aClass = allClassByInterface.get(i);
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                method.invoke(null);
            }
        }


    }
}
