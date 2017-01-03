package cnhubei.rb.Interface;

import android.content.Context;
import cnhubei.rb.Data.Config;
import cnhubei.rb.Service.S_RedPacketService;

public abstract class C_BaseAccessbilityJob implements I_AccessbilityJob{
    private S_RedPacketService service;

    @Override
    public void onCreateJob(S_RedPacketService service) {this.service = service;}

    public Context getContext(){
        return service.getApplicationContext();
    }

    public Config getConfig(){
        return service.getConfig();
    }

    public S_RedPacketService getService(){
        return service;
    }
}
