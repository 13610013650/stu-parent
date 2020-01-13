 package com.stu.activiti.domain.service;

 import org.activiti.engine.identity.Group;
 import org.activiti.engine.identity.User;

 /**
 * @ProjectName: ativiti-demo 
 * @Package: com.stu.activiti.domain.service
 * @ClassName: ProcessGroupService
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/10 11:29
 * @Version: 1.0
 */
public interface ActivitiUserGroupService {

    User addUser(User user);

    void delUser(String userid);

    Group addGroup(Group group);

    void delGroup(String groupId);

    void bindUserToGroup(String userid, String groupId);

    void newGroupAndUser(User user, Group group);

}
