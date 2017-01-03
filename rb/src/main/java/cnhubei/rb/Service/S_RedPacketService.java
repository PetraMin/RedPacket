package cnhubei.rb.Service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import cnhubei.rb.Utils.ToastUtils;
import cnhubei.rb.Data.Config;
import cnhubei.rb.Interface.C_WXJob;
import cnhubei.rb.Interface.I_AccessbilityJob;
import cnhubei.rb.Interface.I_StatusNotification;

public class S_RedPacketService extends AccessibilityService{
    private List<I_AccessbilityJob> list;
    private Map<String, I_AccessbilityJob> map;
    private static final Class[] ACCESSBILITY_JOBS = {C_WXJob.class};
    private static S_RedPacketService service;

    @Override
    public void onCreate() {
        super.onCreate();
        list = new ArrayList<>();
        map = new HashMap<>();

        for (Class cls : ACCESSBILITY_JOBS){
            try {
                Object obj = cls.newInstance();
                if (obj instanceof I_AccessbilityJob) {
                    //这里为什么不直接初始化？？？
                    I_AccessbilityJob job = (I_AccessbilityJob) obj;
                    job.onCreateJob(this);

                    list.add(job);
                    map.put(job.getTargetPackgeName(), job);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        service = this;

        //发送广播，已经连接上了
        Intent intent = new Intent(Config.ACTION_QIANGHONGBAO_SERVICE_CONNECT);
        sendBroadcast(intent);

        ToastUtils.showToast(this, "已开启抢红包服务！");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        String apkName = String.valueOf(event.getPackageName());
        if (list != null && !list.isEmpty()){
            for (I_AccessbilityJob job : list){
                if (apkName.equals(job.getTargetPackgeName()) && job.isEnable()){
                    job.onReceiveJob(event);
                }
            }
        }
    }

    @Override
    public void onInterrupt() {
        Log.e("xx", "service interrupt");
        ToastUtils.showToast(this, "中断抢红包服务");
    }

    public Config getConfig() {
        return Config.getConfig(this);
    }

    /**
     * 判断当前服务是否正在运行
     * */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean isRunning() {
        if(service == null) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) service.getSystemService(Context.ACCESSIBILITY_SERVICE);
        AccessibilityServiceInfo info = service.getServiceInfo();
        if(info == null) {
            return false;
        }
        List<AccessibilityServiceInfo> list = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        Iterator<AccessibilityServiceInfo> iterator = list.iterator();

        boolean isConnect = false;
        while (iterator.hasNext()) {
            AccessibilityServiceInfo i = iterator.next();
            if(i.getId().equals(info.getId())) {
                isConnect = true;
                break;
            }
        }
        if(!isConnect) {
            return false;
        }
        return true;
    }


    /** 接收通知栏事件*/
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static void handeNotificationPosted(I_StatusNotification notificationService) {
        if(notificationService == null) {
            return;
        }
        if(service == null || service.map == null) {
            return;
        }
        String pack = notificationService.getPackageName();
        I_AccessbilityJob job = service.map.get(pack);
        if(job == null) {
            return;
        }
        job.onNotificationPosted(notificationService);
    }


}
