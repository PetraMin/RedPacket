package cnhubei.rb.Utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 
 * 避免toast多次连续弹出,造成不好的体验.并且简化了代码
 * @author yc
 *
 */

public class ToastUtils {
	private static Toast toast = null;

	public static void showToast(Context context, String msg) {
		if (toast == null) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			toast.setText(msg);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}

	public static void showToast(Context context, int resId) {
		String msg = context.getString(resId);
		if (toast == null) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			toast.setText(msg);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}

	public static void showToastInCenter(Context context, String msg) {
		if (toast == null) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			toast.setText(msg);
		} else {
			toast.setText(msg);
		}
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	

}
