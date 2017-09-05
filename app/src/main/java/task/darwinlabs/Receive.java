package task.darwinlabs;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ClipboardManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Receive extends AppCompatActivity {
    ImageView backbutton,copytoclipboard;
    TextView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        backbutton = (ImageView) findViewById(R.id.ivback);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        copytoclipboard=(ImageView)findViewById(R.id.ivcopytoclip);
        add=(TextView)findViewById(R.id.tvaddress);
        copytoclipboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label",add.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(Receive.this,"Copied to Clipboard",Toast.LENGTH_LONG).show();
            }
        });
    }
}
