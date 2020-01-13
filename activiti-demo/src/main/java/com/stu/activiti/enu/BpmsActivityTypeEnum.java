package com.stu.activiti.enu;

public enum BpmsActivityTypeEnum {
    /**
     * @Description 开始事件节点
     */
    START_EVENT("startEvent", "开始事件"),
    /**
     * @Description 结束事件节点
     */
    END_EVENT("endEvent", "结束事件"),
    /**
     * @Description 用户任务节点
     */
    USER_TASK("userTask", "用户任务"),
    /**
     * @Description 排他网关节点
     */
    EXCLUSIVE_GATEWAY("exclusiveGateway", "排他网关"),
    /**
     * @Description 并行网关节点
     */
    PARALLEL_GATEWAY("parallelGateway", "并行网关"),
    /**
     * @Description 包含网关节点
     */
    INCLUSIVE_GATEWAY("inclusiveGateway", "包含网关");
    
    private String type;
    private String name;
    
    private BpmsActivityTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}