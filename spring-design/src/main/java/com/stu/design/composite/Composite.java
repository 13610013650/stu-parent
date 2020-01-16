package com.stu.design.composite;

import java.util.ArrayList;

public class Composite extends Component {

    private ArrayList<Component> components = new ArrayList<Component>();

    public void add(Component component) {
        components.add(component);
    }

    public void remove(Component component) {
        components.remove(component);
    }

    public ArrayList<Component> getChildren() {
        return components;
    }

    @Override
    public void doSomething() {
        components.stream().forEach(component -> {
            component.doSomething();
        });
    }
}