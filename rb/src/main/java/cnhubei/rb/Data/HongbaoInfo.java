package cnhubei.rb.Data;

import android.view.accessibility.AccessibilityNodeInfo;

import java.io.Serializable;


public class HongbaoInfo implements Serializable {



    public String sender;
    public String money;
    public String time;

    public HongbaoInfo(String sender, String money, String time){
        this.sender = sender;
        this.money = money;
        this.time = time;
    }

    public static HongbaoInfo setInfo(AccessibilityNodeInfo node, String time) {
        AccessibilityNodeInfo hongbaoNode = node.getParent();

        return new HongbaoInfo(hongbaoNode.getChild(0).getText().toString(), hongbaoNode.getChild(2).getText().toString(), time);
    }



    @Override
    public String toString() {
        return "HongbaoInfo [sender=" + sender + ", money=" + money + ", time=" + time + "]";
    }

    public float getMoney() {
        return Float.parseFloat(money);
    }

    public String getSender() {
        return sender;
    }

    public String getTime() {
        return time;
    }
}
