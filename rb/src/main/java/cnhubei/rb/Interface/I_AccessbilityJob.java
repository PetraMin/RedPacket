package cnhubei.rb.Interface;

import android.view.accessibility.AccessibilityEvent;

import cnhubei.rb.Service.S_RedPacketService;

public interface I_AccessbilityJob {
    String getTargetPackgeName();

    void onCreateJob(S_RedPacketService service);

    void onReceiveJob(AccessibilityEvent event);

    void onStopJob();

    void onNotificationPosted(I_StatusNotification service);

    boolean isEnable();
}
