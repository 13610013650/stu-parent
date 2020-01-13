 package com.stu.activiti.domain.service.impl;
 import com.stu.activiti.domain.service.ActivitiUserGroupService;
 import lombok.extern.slf4j.Slf4j;
 import org.activiti.engine.IdentityService;
 import org.activiti.engine.identity.Group;
 import org.activiti.engine.identity.User;
 import org.springframework.beans.BeanUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import java.util.UUID;

 /**
 * @ProjectName: ativiti-demo 
 * @Package: com.stu.activiti.domain.service.impl
 * @ClassName: ActivitiGroupServiceImpl
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/10 11:31
 * @Version: 1.0
 */

@Slf4j
@Service
public class ActivitiUserGroupServiceImpl implements ActivitiUserGroupService {

     @Autowired private IdentityService identityService;

     @Override
     public User addUser(User user) {
         String userid = UUID.randomUUID().toString();
         user.setId(userid);
         User user1 = identityService.newUser(userid);
         BeanUtils.copyProperties(user,user1);
         identityService.saveUser(user1);
         return user1;
     }

     @Override
     public void delUser(String userid) {
         identityService.deleteUser(userid);
     }

     @Override
     public Group addGroup(Group group) {
         String groupid = UUID.randomUUID().toString();
         group.setId(groupid);
         Group group1 = identityService.newGroup(groupid);
         BeanUtils.copyProperties(group,group1);
         identityService.saveGroup(group1);
         return group1;
     }

     @Override
     public void delGroup(String groupId) {
         identityService.deleteGroup(groupId);
     }

     @Override
     public void bindUserToGroup(String userid,String groupId){
         identityService.createMembership(userid,groupId);
     }

     @Override
     public void newGroupAndUser(User user, Group group) {
         identityService.saveUser(user);
         identityService.saveGroup(group);
         identityService.createMembership(user.getId(),group.getId());
     }
 }
