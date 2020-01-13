 package com.stu.design.chain;
/**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.chain
 * @ClassName: LeaderInfo
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/30 14:43
 * @Version: 1.0
 */
public abstract class LeaderInfo {

     int canAuditMoney;

     LeaderInfo superLeaderInfo;

    public abstract void setCanAuditMoney(int money);

    public abstract void handler(ApplyInfo applyInfo);

    public abstract void setLeaderInfo(LeaderInfo leaderInfo);

    public void dealInfo(ApplyInfo applyInfo){
        if (applyInfo.getMoney() <= canAuditMoney){
            handler(applyInfo);
        }else{
            superLeaderInfo.handler(applyInfo);
        }
    }

}
