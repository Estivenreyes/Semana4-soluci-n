package com.example.ejerciociosemana4;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Recibido extends AppCompatActivity {

    TextView recibido1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibido);

        recibido1 = findViewById(R.id.recibido1);
        String resultado;
        String Ip = getIntent().getExtras().getString("Ip");

        new Thread(

                () ->{

                    try {
                        int prueba = 0;
                        while (prueba<6){
                            InetAddress inetAddress = InetAddress.getByName(Ip);
                            boolean isReachable = inetAddress.isReachable(1000);
                            Thread.sleep(2000);

                            if (isReachable) {

                                runOnUiThread(() ->

                                        {
                                            recibido1.setText(recibido1.getText().toString() + "Recibido\n");
                                        }

                                );
                            } else {

                                runOnUiThread(
                                        () -> {

                                            recibido1.setText(recibido1.getText().toString() + "Perdido \n");
                                        }

                                );
                            }

                            prueba++;
                        }
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

        ).start();
    }
}
