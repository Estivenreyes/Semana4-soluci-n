package com.example.ejerciociosemana4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText input1, input2, input3, input4;
    Button botonping, botonbuscarH;
    String ip;
    TextView miIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);
        input4 = findViewById(R.id.input4);
        botonping = findViewById(R.id.botonping);
        botonbuscarH = findViewById(R.id.botonbuscarH);
        botonping.setOnClickListener(this);
        botonbuscarH.setOnClickListener(this);

        miIp = findViewById(R.id.ip);

        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        miIp.setText(Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress()));


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.botonping:

                ip = input1.getText().toString() + "." + input2.getText().toString() + "." + input3.getText().toString() + "." + input4.getText().toString();

                Intent intent = new Intent(this, Recibido.class);
                intent.putExtra("Ip", ip);
                startActivity(intent);

                break;

            case R.id.botonbuscarH:

                Intent intent1 = new Intent(this, IpRecibido.class);
                startActivity(intent1);

                break;


        }

    }

    public static String formatIpAddress(int ip) {
        return String.format(Locale.US, "%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
    }

}