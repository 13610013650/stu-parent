 package com.stu.activiti.domain.service.impl;

 import com.stu.activiti.domain.service.ActivitiHistoryService;
 import com.stu.activiti.domain.service.ActivitiRuntimeService;
 import com.stu.activiti.enu.BpmsActivityTypeEnum;
 import com.stu.activiti.utils.UtilMisc;
 import org.activiti.bpmn.model.BpmnModel;
 import org.activiti.bpmn.model.FlowNode;
 import org.activiti.bpmn.model.SequenceFlow;
 import org.activiti.engine.HistoryService;
 import org.activiti.engine.ProcessEngine;
 import org.activiti.engine.ProcessEngines;
 import org.activiti.engine.RepositoryService;
 import org.activiti.engine.history.HistoricActivityInstance;
 import org.activiti.engine.history.HistoricProcessInstance;
 import org.activiti.engine.history.HistoricTaskInstance;
 import org.activiti.engine.runtime.ProcessInstance;
 import org.activiti.engine.task.Task;
 import org.activiti.image.ProcessDiagramGenerator;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import javax.imageio.ImageIO;
 import java.awt.image.BufferedImage;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 import java.util.function.Predicate;
 import java.util.stream.Collectors;

 /**
 * @ProjectName: ativiti-demo 
 * @Package: com.stu.activiti.domain.service.impl
 * @ClassName: ActivitiHistoryServiceImpl
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/8 15:20
 * @Version: 1.0
 */
 @Service
public class ActivitiHistoryServiceImpl implements ActivitiHistoryService {

     @Autowired private HistoryService historyService;

     @Autowired private RepositoryService repositoryService;

     @Autowired private ProcessEngine processEngine;


     @Override
     public List<HistoricTaskInstance> queryProcessHistoryTask(String userId) {
         List<HistoricTaskInstance> taskist = historyService
                 .createHistoricTaskInstanceQuery()
                 .taskAssignee(userId).list();
         return taskist;
     }

     @Override
     public InputStream queryProcessImage(String instanceId) {
         InputStream imageStream = null;
         try {
             // 获取历史流程实例
             HistoricProcessInstance historicProcessInstance = historyService
                     .createHistoricProcessInstanceQuery()
                     .processInstanceId(instanceId)
                     .singleResult();

             // 获取流程中已经执行的节点，按照执行先后顺序排序
             List<HistoricActivityInstance> historicActivityInstanceList = historyService
                     .createHistoricActivityInstanceQuery()
                     .processInstanceId(instanceId)
                     .orderByHistoricActivityInstanceId()
                     .asc().list();

             // 构造已执行的节点ID集合
             List<String> executedActivityIdList = new ArrayList<String>();
             for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                 executedActivityIdList.add(activityInstance.getActivityId());
             }

             // 获取bpmnModel
             BpmnModel bpmnModel = repositoryService
                     .getBpmnModel(historicProcessInstance.getProcessDefinitionId());
             // 获取流程已发生流转的线ID集合
             List<String> flowIds = this.getExecutedFlows(bpmnModel, historicActivityInstanceList);

             // 使用默认配置获得流程图表生成器，并生成追踪图片字符流
             ProcessDiagramGenerator processDiagramGenerator = processEngine
                     .getProcessEngineConfiguration()
                     .getProcessDiagramGenerator();
             imageStream = processDiagramGenerator
                     .generateDiagram(bpmnModel, "png", executedActivityIdList, flowIds, "宋体", "微软雅黑", "黑体", null, 2.0);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return imageStream;
     }

    /**
     * @Author ZhangSheng
     * @param
     * @Description 获取流程已发生流转的线ID集合
     */
     private List<String> getExecutedFlows(BpmnModel bpmnModel,
                                           List<HistoricActivityInstance> historicActivityInstances) {
         // 流转线ID集合
         List<String> flowIdList = new ArrayList<String>();
         // 全部活动实例
         List<FlowNode> historicFlowNodeList = new LinkedList<FlowNode>();

         historicActivityInstances.stream().forEach(h->{
             FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(h.getActivityId(), true);
             historicFlowNodeList.add(flowNode);
         });

         List<HistoricActivityInstance> finishedActInsList =historicActivityInstances.stream()
                 .filter(h -> h.getEndTime()!=null).collect(Collectors.toList());

         // 遍历已完成的活动实例，从每个实例的outgoingFlows中找到已执行的
         FlowNode currentFlowNode = null;
         for (HistoricActivityInstance currentActivityInstance : finishedActInsList) {
             // 获得当前活动对应的节点信息及outgoingFlows信息
             currentFlowNode = (FlowNode) bpmnModel
                     .getMainProcess()
                     .getFlowElement(currentActivityInstance.getActivityId(), true);
             List<SequenceFlow> sequenceFlowList = currentFlowNode.getOutgoingFlows();

             /**
              * 遍历outgoingFlows并找到已已流转的
              * 满足如下条件认为已已流转：
              * 1.当前节点是并行网关或包含网关，则通过outgoingFlows能够在历史活动中找到的全部节点均为已流转
              * 2.当前节点是以上两种类型之外的，通过outgoingFlows查找到的时间最近的流转节点视为有效流转
              */
             final FlowNode[] targetFlowNode = {null};
             if (BpmsActivityTypeEnum.PARALLEL_GATEWAY.getType()
                     .equals(currentActivityInstance.getActivityType())
                     || BpmsActivityTypeEnum.INCLUSIVE_GATEWAY.getType()
                     .equals(currentActivityInstance.getActivityType())) {
                 // 遍历历史活动节点，找到匹配Flow目标节点的
                 flowIdList = sequenceFlowList.stream().filter(new Predicate<SequenceFlow>(){
                     @Override
                     public boolean test(SequenceFlow sequenceFlow) {
                         targetFlowNode[0] = (FlowNode) bpmnModel.getMainProcess()
                                 .getFlowElement(sequenceFlow.getTargetRef(), true);
                         return historicFlowNodeList.contains(targetFlowNode[0]);
                     }
                 }).map(SequenceFlow::getId).collect(Collectors.toList());
             } else {
                 List<Map<String, String>> tempMapList = new LinkedList<Map<String,String>>();
                 // 遍历历史活动节点，找到匹配Flow目标节点的
                 Map<String, HistoricActivityInstance> hActivityIns = historicActivityInstances.stream()
                         .collect(
                                 Collectors.toMap(
                                         HistoricActivityInstance::getActivityId,
                                         historicActivityInstance -> historicActivityInstance
                                 )
                         );
                 sequenceFlowList.stream().forEach(s ->{
                     HistoricActivityInstance historicActivityInstance = hActivityIns.get(s.getId());
                     if (historicActivityInstance!=null) {
                         String s1 = String.valueOf(historicActivityInstance.getStartTime().getTime());
                         Map<String, String> sMap = UtilMisc.toMap("flowId", s.getId(), "activityStartTime", s1);
                         tempMapList.add(sMap);
                     }
                 });
                 // 遍历匹配的集合，取得开始时间最早的一个
                 long earliestStamp = 0L;
                 String flowId = null;
                 for (Map<String, String> map : tempMapList) {
                     long activityStartTime = Long.valueOf(map.get("activityStartTime"));
                     if (earliestStamp == 0 || earliestStamp >= activityStartTime) {
                         earliestStamp = activityStartTime;
                         flowId = map.get("flowId");
                     }
                 }
                 flowIdList.add(flowId);
             }
         }
         return flowIdList;
     }

 }
