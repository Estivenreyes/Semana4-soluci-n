package com.example.ejerciociosemana4;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpRecibido extends AppCompatActivity {

    int ip;
    String ipRecibida;
    TextView recibido2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iprecibido);
        recibido2 = findViewById(R.id.recibido2);
        new Thread(

                () -> {
                    while (ip < 255) {

                        String datoFinal = Integer.toString(ip);

                        try {

                            Thread.sleep(1000);
                            InetAddress inetAddress = InetAddress.getByName(ipRecibida + "" + datoFinal);
                            Boolean isReachable = inetAddress.isReachable(1000);
                            if (isReachable) {

                                runOnUiThread(

                                        () -> {

                                            recibido2.setText(recibido2.getText().toString() + "" + ipRecibida + "" + datoFinal + "\n");


                                        }
                                );

                            }

                            ip++;
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }

                }
        ).start();

    }
}
