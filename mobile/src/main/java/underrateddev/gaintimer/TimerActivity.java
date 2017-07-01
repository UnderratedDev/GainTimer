package underrateddev.gaintimer;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    int sets, work, rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        final Intent intent = getIntent ();
        sets = Integer.parseInt (intent.getStringExtra ("sets"));
        work = Integer.parseInt (intent.getStringExtra ("work"));
        rest = Integer.parseInt (intent.getStringExtra ("rest"));
        start_timer ();
    }

    private void start_timer () {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                final TextView timer       = (TextView) (findViewById(R.id.timer)),
                        set_tracker        = (TextView) (findViewById(R.id.set_counter)),
                        status             = (TextView) (findViewById(R.id.status));
                int work_counter, set_counter = 0, rest_tracker;
                while (set_counter != sets) {
                    work_counter = work;
                    while (work_counter > 0) {
                        final int work_print = work_counter;
                        runOnUiThread (new Runnable () {
                            @Override
                            public void run() {
                                status.setText ("WORK");
                                timer.setText("" + work_print);
                            }
                        });

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace ();
                        }
                        --work_counter;
                    }
                    ++set_counter;
                    final int print_set = set_counter;
                    rest_tracker = rest;
                    runOnUiThread (new Runnable () {
                        @Override
                        public void run() {
                            set_tracker.setText ("Set " + print_set + " completed");
                            status.setText ("REST");
                        }
                    });
                    while (rest_tracker > 0) {
                        final int rest_print = rest_tracker;
                        runOnUiThread (new Runnable () {
                            @Override
                            public void run() {
                                timer.setText("" + rest_print);
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace ();
                        }
                        --rest_tracker;
                    }
                }
                return null;
            }
        }.execute ();
    }
}
