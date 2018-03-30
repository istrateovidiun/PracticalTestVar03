package ro.pub.cs.systems.eim.practic.practicaltest01var03firstactivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var03FirstActivity extends Activity {

    private EditText name_edittext = null;
    private EditText grupa_edittext = null;
    private Button second_app = null;
    private Button display_button = null;
    private CheckBox check_name = null;
    private CheckBox check_grupa = null;
    private TextView displayed = null;

    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_first);


        name_edittext = (EditText)findViewById(R.id.edittext_name);
        grupa_edittext = (EditText)findViewById(R.id.edittext_grupa);
        display_button = (Button)findViewById(R.id.button_display);
        second_app = (Button)findViewById((R.id.button_second_app));
        check_name = (CheckBox)findViewById(R.id.check_name);
        check_grupa = (CheckBox)findViewById(R.id.check_grupa);
        displayed = (TextView)findViewById(R.id.textview);

        display_button.setOnClickListener(buttonClickListener);
        second_app.setOnClickListener(buttonClickListener);

        if(savedInstanceState != null) {
            if (savedInstanceState.containsKey("grupa")) {
                grupa_edittext.setText(savedInstanceState.getString("grupa").toString());
            } else {
                grupa_edittext.setText("");
            }
            if (savedInstanceState.containsKey("name")) {
                name_edittext.setText(savedInstanceState.getString("name").toString());
            } else {
                name_edittext.setText("");
            }
        } else {
            name_edittext.setText("");
            grupa_edittext.setText("");
        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[MessageDate]", intent.getStringExtra("message"));
        }
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_display:
                    String name = "", grupa = "";
                    if (check_name.isChecked()) {
                        name = name_edittext.getText().toString();
                    }
                    if (check_grupa.isChecked()) {
                        grupa = grupa_edittext.getText().toString();
                    }

                    displayed.setText(name + " " + grupa);

                    if (check_grupa.isChecked() && check_name.isChecked()) {
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
                        intent.putExtra("name", name_edittext.getText().toString());
                        intent.putExtra("grupa", grupa_edittext.getText().toString());
                        getApplicationContext().startService(intent);
                    }
                    for (int index = 0; index < 4; index++) {
                        intentFilter.addAction("TestTest" + index);
                    }

                    break;
                case R.id.button_second_app:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03SecondaryActivity.class);
                    String nume  = name_edittext.getText().toString();
                    String grupa_2 = grupa_edittext.getText().toString();
                    intent.putExtra("name", nume);
                    intent.putExtra("grupa", grupa_2);
                    startActivityForResult(intent, 1);
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("name", name_edittext.getText().toString());
        savedInstanceState.putString("grupa", grupa_edittext.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
