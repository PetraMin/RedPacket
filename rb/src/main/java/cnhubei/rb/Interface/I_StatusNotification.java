package cnhubei.rb.Interface;

import android.app.Notification;

/**
 * <p>Created 16/2/7 下午5:48.</p>
 * <p><a href="mailto:730395591@qq.com">Email:730395591@qq.com</a></p>
 * <p><a href="http://www.happycodeboy.com">LeonLee Blog</a></p>
 *
 * @author LeonLee
 */
public interface I_StatusNotification {

    String getPackageName();
    Notification getNotification();
}
