package com.stu.check.util;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Scan for eligible classes
 *
 * @author: CipherCui
 */
public class ClassScanner implements ResourceLoaderAware {

    private final List<TypeFilter> includeFilters = new LinkedList<>();
    private final List<TypeFilter> excludeFilters = new LinkedList<>();

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);

    /**
     * scan classes with annotations
     *
     * @param basePackages base package array
     * @param annotations  annotations
     * @return eligible class
     */
    @SafeVarargs
    public static Set<Class<?>> scan(String[] basePackages, Class<? extends Annotation>... annotations) {
        ClassScanner cs = new ClassScanner();
        if (annotations != null && annotations.length != 0) {
            for (Class<? extends Annotation> a : annotations) {
                cs.addIncludeFilter(new AnnotationTypeFilter(a));
            }
        }
        Set<Class<?>> classes = new HashSet<>();
        for (String s : basePackages) {
            classes.addAll(cs.doScan(s));
        }
        return classes;
    }

    /**
     * scan classes with annotations
     *
     * @param basePackages base package string
     * @param annotations  annotations
     * @return eligible class
     */
    @SafeVarargs
    public static Set<Class<?>> scan(String basePackages, Class<? extends Annotation>... annotations) {
        return ClassScanner.scan(StringUtils.tokenizeToStringArray(basePackages, ",; \t\n"), annotations);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }

    public void addIncludeFilter(TypeFilter includeFilter) {
        this.includeFilters.add(includeFilter);
    }

    public void addExcludeFilter(TypeFilter includeFilter) {
        this.excludeFilters.add(includeFilter);
    }

    public Set<Class<?>> doScan(String basePackage) {
        Set<Class<?>> classes = new HashSet<>();
        try {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + org.springframework.util.ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage))
                    + "/**/*.class";
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                    boolean isEmpty = includeFilters.size() == 0 && excludeFilters.size() == 0;
                    if (isEmpty || matches(metadataReader)) {
                        try {
                            classes.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException ex) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", ex);
        }
        return classes;
    }

    private boolean matches(MetadataReader metadataReader) throws IOException {
        for (TypeFilter tf : this.excludeFilters) {
            if (tf.match(metadataReader, this.metadataReaderFactory)) {
                return false;
            }
        }
        for (TypeFilter tf : this.includeFilters) {
            if (tf.match(metadataReader, this.metadataReaderFactory)) {
                return true;
            }
        }
        return false;
    }

}
