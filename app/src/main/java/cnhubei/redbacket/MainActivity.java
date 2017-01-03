package cnhubei.redbacket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cnhubei.rb.Activity.A_RedPacketActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_rp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_rp = (Button) findViewById(R.id.btn_rp);
        btn_rp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, A_RedPacketActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
