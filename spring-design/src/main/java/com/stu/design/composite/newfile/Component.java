 package com.stu.design.composite.newfile;

 import java.util.ArrayList;
 import java.util.List;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.composite.newfile
 * @ClassName: Composite
 * @Author: ZhangSheng
 * @Description: 抽象一个组件
 * @Date: 2020/1/15 10:28
 * @Version: 1.0
 */
public abstract class Component {

    /**
     * 抽象组件的 operation 方法
     */
    public abstract void operation();

    public abstract void add(Component component);

    public abstract void remove(Component component);

     public abstract List<Component> getComponents();

}
/**
 * @Description: 抽象组件的树枝构建
 */
class Composite extends Component{

    private List<Component> components = new ArrayList<Component>();

    @Override
    public void add(Component component){
        components.add(component);
    }

    @Override
    public void remove(Component component){
        components.remove(component);
    }

    @Override
    public List<Component> getComponents(){
        return this.components;
    }


    @Override
    public void operation() {
//      System.out.println("Composite's method operation.");
        components.stream().forEach(component -> {
            component.operation();
        });
    }
}

class LeafAge extends Component{

    @Override
    public void operation() {
        System.out.println("LeafAge's method operation.");
    }

    @Override
    public void add(Component component) {

    }

    @Override
    public void remove(Component component) {

    }

    @Override
    public List<Component> getComponents() {
        return null;
    }
}
/**
 * @Author ZhangSheng
 * @param
 * @Description
 */
class Test{
    public static void main(String[] args) {

       Component component = new Composite();
       Component component2 = new LeafAge();

       component.add(component2);

       component.operation();
    }
}


