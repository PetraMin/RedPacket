package cnhubei.rb.Activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import cnhubei.rb.Fragmnet.F_SettingFragment;
import cnhubei.rb.R;

public class A_SettingActivity extends Activity {
    private F_SettingFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_redpacket);
        fragment = new F_SettingFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.ll_fragment, fragment);
        transaction.commitAllowingStateLoss();
    }
}
