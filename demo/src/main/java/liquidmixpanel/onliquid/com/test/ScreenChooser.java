package liquidmixpanel.onliquid.com.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ScreenChooser extends Activity {

    private Button bLocalytics;
    private Button bMixPanel;
    private Button bGoogleAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_chooser);
        setTitle("Choose Analytics");

        bLocalytics = (Button) findViewById(R.id.localytics);
        bMixPanel   = (Button) findViewById(R.id.mixPanel);
        bGoogleAnalytics = (Button) findViewById(R.id.googleAnalytics);

        bLocalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(ScreenChooser.this, MainActivity.class);
                intent.putExtra("choice",1);
                startActivity(intent);
            }
        });

        bMixPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(ScreenChooser.this, MainActivity.class);
                intent.putExtra("choice",2);
                startActivity(intent);
            }
        });

        bGoogleAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(ScreenChooser.this, MainActivity.class);
                intent.putExtra("choice",3);
                startActivity(intent);
            }
        });
    }
}
