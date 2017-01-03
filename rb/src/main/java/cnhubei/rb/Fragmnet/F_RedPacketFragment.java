package cnhubei.rb.Fragmnet;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cnhubei.rb.Activity.A_SettingActivity;
import cnhubei.rb.Adapter.RPListAdapter;
import cnhubei.rb.Data.Config;
import cnhubei.rb.Data.HongbaoInfo;
import cnhubei.rb.R;
import cnhubei.rb.Service.S_RedPacketNotificationService;
import cnhubei.rb.Service.S_RedPacketService;
import cnhubei.rb.Utils.CacheUtils;

public class F_RedPacketFragment extends Fragment {
    private Dialog mTipsDialog;
    private Activity mActivity;
    private Button btn_start, btn_set, btn_notification;
    //    private RecyclerView rv_list;
    private ListView lv_list;
    private boolean notificationChangeByUser = true;
    private List<HongbaoInfo> list;
    private RPListAdapter adapter;

    private BroadcastReceiver qhbConnectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("MainActivity", "receive-->" + action);
            if(Config.ACTION_QIANGHONGBAO_SERVICE_CONNECT.equals(action)) {
                if (mTipsDialog != null) {
                    mTipsDialog.dismiss();
                }
            } else if(Config.ACTION_QIANGHONGBAO_SERVICE_DISCONNECT.equals(action)) {
                showOpenAccessibilityServiceDialog();
            } else if(Config.ACTION_NOTIFY_LISTENER_SERVICE_CONNECT.equals(action)) {
//                if(mMainFragment != null) {
//                    mMainFragment.updateNotifyPreference();
//                }
            } else if(Config.ACTION_NOTIFY_LISTENER_SERVICE_DISCONNECT.equals(action)) {
//                if(mMainFragment != null) {
//                    mMainFragment.updateNotifyPreference();
//                }
            }
        }
    };

    /** 更新快速读取通知的设置*/
    public void updateNotifyPreference() {
        boolean running = S_RedPacketNotificationService.isNotificationServiceRunning();
        boolean enable = Config.getConfig(getActivity()).isEnableNotificationService();
//        if( enable && running && !notificationPref.isChecked()) {
//            QHBApplication.eventStatistics(getActivity(), "notify_service", String.valueOf(true));
//            notificationChangeByUser = false;
//            notificationPref.setChecked(true);
//        } else if((!enable || !running) && notificationPref.isChecked()) {
//            notificationChangeByUser = false;
//            notificationPref.setChecked(false);
//        }
    }

    /** 打开通知栏设置*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private void openNotificationServiceSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
            Toast.makeText(mActivity, R.string.tips, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        Config.getConfig(getActivity()).setNotificationServiceEnable(true);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Config.ACTION_QIANGHONGBAO_SERVICE_CONNECT);
        filter.addAction(Config.ACTION_QIANGHONGBAO_SERVICE_DISCONNECT);
        filter.addAction(Config.ACTION_NOTIFY_LISTENER_SERVICE_DISCONNECT);
        filter.addAction(Config.ACTION_NOTIFY_LISTENER_SERVICE_CONNECT);
        mActivity.registerReceiver(qhbConnectReceiver, filter);
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState)
//    {
//        super.onActivityCreated(savedInstanceState);
//        list = CacheUtils.readCacheObject(getActivity(), Config.PREFERENCE_RED_PACHET_CACHE);
//        if (list != null && list.size() > 0){
//            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rv_list.getContext());
//            rv_list.setLayoutManager(layoutManager);
//            adapter = new RPInforAdapter(getActivity(), list);
//            rv_list.setAdapter(adapter);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_redpacket, null);
        btn_start = (Button) view.findViewById(R.id.btn_start);
        btn_set = (Button) view.findViewById(R.id.btn_set);
        btn_notification = (Button) view.findViewById(R.id.btn_notification);
        lv_list = (ListView) view.findViewById(R.id.lv_list);

        if (android.os.Build.VERSION.SDK_INT > 18){
            btn_notification.setVisibility(View.VISIBLE);
        } else {
            btn_notification.setVisibility(View.GONE);
        }


//        rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
//        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//        if (list != null && list.size() > 0){
//            adapter = new RPInforAdapter(getActivity(), list);
//            rv_list.setAdapter(adapter);
//        }

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!S_RedPacketService.isRunning()) {
                    showOpenAccessibilityServiceDialog();
                }
            }
        });
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), A_SettingActivity.class));
            }
        });
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    Toast.makeText(getActivity(), "该功能只支持安卓4.3以上的系统", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!notificationChangeByUser) {
                    notificationChangeByUser = true;
                    return;
                }



                if(!S_RedPacketNotificationService.isNotificationServiceRunning()) {
                    openNotificationServiceSettings();
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        list = CacheUtils.readCacheObject(getActivity(), Config.PREFERENCE_RED_PACHET_CACHE);
        if (list != null && list.size() > 0){
            adapter = new RPListAdapter(getActivity(), list);
            lv_list.setAdapter(adapter);
        }
        if(S_RedPacketService.isRunning()) {
            if(mTipsDialog != null) {
                mTipsDialog.dismiss();
            }
        } else {
            showOpenAccessibilityServiceDialog();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /** 显示未开启辅助服务的对话框*/
    private void showOpenAccessibilityServiceDialog() {
        if(mTipsDialog != null && mTipsDialog.isShowing()) {
            return;
        }
        View view = mActivity.getLayoutInflater().inflate(R.layout.d_tips_dialog, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccessibilityServiceSettings();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(R.string.open_service_title);
        builder.setView(view);
        builder.setPositiveButton(R.string.open_service_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openAccessibilityServiceSettings();
            }
        });
        mTipsDialog = builder.show();
    }

    /** 打开辅助服务的设置*/
    private void openAccessibilityServiceSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
            Toast.makeText(mActivity, R.string.tips, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
