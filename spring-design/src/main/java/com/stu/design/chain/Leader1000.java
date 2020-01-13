package com.stu.design.chain;

/**
 * 申请500已下的金额可以批，大于500会向上级回报
 */
public class Leader1000 extends LeaderInfo {

    @Override
    public void setCanAuditMoney(int money) {
        super.canAuditMoney = money;
    }

    @Override
    public void handler(ApplyInfo applyInfo) {
        applyInfo.getApply();
        System.out.print("     我是Leader1000  你需要申请的" + applyInfo.getMoney() + "我批准了");
    }

    @Override
    public void setLeaderInfo(LeaderInfo leaderInfo) {
        super.superLeaderInfo = leaderInfo;
    }

}