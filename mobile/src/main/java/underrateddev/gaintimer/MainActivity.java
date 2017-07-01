package underrateddev.gaintimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void set_timer (final View view) {
        String sets = ((EditText) findViewById (R.id.sets)).getText ().toString (),
               work = ((EditText) findViewById (R.id.work)).getText ().toString (),
               rest = ((EditText) findViewById (R.id.rest)).getText ().toString ();

        Intent intent = new Intent (MainActivity.this, TimerActivity.class);
        intent.putExtra ("sets", sets);
        intent.putExtra ("work", work);
        intent.putExtra ("rest", rest);
        startActivity (intent);
    }
}
