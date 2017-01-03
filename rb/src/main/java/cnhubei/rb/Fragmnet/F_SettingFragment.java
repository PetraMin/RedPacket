package cnhubei.rb.Fragmnet;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import cnhubei.rb.Data.Config;
import cnhubei.rb.R;

public class F_SettingFragment extends PreferenceFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.f_setting);

        //微信红包模式
        final ListPreference wxMode = (ListPreference) findPreference(Config.KEY_WECHAT_MODE);
        wxMode.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int value = Integer.parseInt(String.valueOf(newValue));
                preference.setSummary(wxMode.getEntries()[value]);
                return true;
            }
        });

        final EditTextPreference delayEditTextPre = (EditTextPreference) findPreference(Config.KEY_WECHAT_DELAY_TIME);
        delayEditTextPre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if("0".equals(String.valueOf(newValue))) {
                    preference.setSummary("");
                } else {
                    preference.setSummary("已延时" + newValue + "毫秒");
                }
                return true;
            }
        });
        String delay = delayEditTextPre.getText();
        if("0".equals(String.valueOf(delay))) {
            delayEditTextPre.setSummary("");
        } else {
            delayEditTextPre.setSummary("已延时" + delay  + "毫秒");
        }

        //打开微信红包后
        final ListPreference wxAfterOpenPre = (ListPreference) findPreference(Config.KEY_WECHAT_AFTER_OPEN_HONGBAO);
        wxAfterOpenPre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int value = Integer.parseInt(String.valueOf(newValue));
                preference.setSummary(wxAfterOpenPre.getEntries()[value]);
                return true;
            }
        });
        wxAfterOpenPre.setSummary(wxAfterOpenPre.getEntries()[Integer.parseInt(wxAfterOpenPre.getValue())]);

        //获取微信红包后
        final ListPreference wxAfterGetPre = (ListPreference) findPreference(Config.KEY_WECHAT_AFTER_GET_HONGBAO);
        wxAfterGetPre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int value = Integer.parseInt(String.valueOf(newValue));
                preference.setSummary(wxAfterGetPre.getEntries()[value]);
                return true;
            }
        });

        findPreference(Config.KEY_NOTIFY_SOUND).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                QHBApplication.eventStatistics(getActivity(), "notify_sound", String.valueOf(newValue));
                return true;
            }
        });

        findPreference(Config.KEY_NOTIFY_VIBRATE).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                QHBApplication.eventStatistics(getActivity(), "notify_vibrate", String.valueOf(newValue));
                return true;
            }
        });

        findPreference(Config.KEY_NOTIFY_NIGHT_ENABLE).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                QHBApplication.eventStatistics(getActivity(), "notify_night", String.valueOf(newValue));
                return true;
            }
        });
    }

}
