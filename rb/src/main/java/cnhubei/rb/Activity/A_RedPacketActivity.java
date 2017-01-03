package cnhubei.rb.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import cnhubei.rb.Fragmnet.F_RedPacketFragment;
import cnhubei.rb.R;

public class A_RedPacketActivity extends FragmentActivity {
    private F_RedPacketFragment f_redPacketFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_redpacket);
        f_redPacketFragment = new F_RedPacketFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.ll_fragment, f_redPacketFragment);
        transaction.commit();
    }
}
