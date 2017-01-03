package cnhubei.rb.Base.Views;

import android.os.Bundle;

import com.cnhubei.gaf.mvp.bijection.Presenter;
import com.cnhubei.gaf.mvp.expansion.data.BeamDataActivityPresenter;

public class PluginBasePresenter extends Presenter<PluginBaseActivity<BeamDataActivityPresenter>> {
    @Override
    protected void onCreate(PluginBaseActivity<BeamDataActivityPresenter> view, Bundle savedState) {
        super.onCreate(view, savedState);
    }
}
