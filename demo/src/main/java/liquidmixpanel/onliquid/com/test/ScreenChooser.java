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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_screen_chooser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
