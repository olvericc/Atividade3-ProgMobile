package com.example.atividade3;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private View lightGreen, lightYellow, lightRed;
    private TextView message;
    private Button startButton;
    private final Handler handler = new Handler();
    private Runnable runnable;
    private int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightGreen = findViewById(R.id.light_green);
        lightYellow = findViewById(R.id.light_yellow);
        lightRed = findViewById(R.id.light_red);
        message = findViewById(R.id.message);
        startButton = findViewById(R.id.start_button);

        startButton.setOnClickListener(v -> startSemaphore());
    }

    private void startSemaphore()
    {
        startButton.setEnabled(false);

        runnable = new Runnable()
        {
            @Override
            public void run()
            {
                switch (state)
                {
                    case 0:
                        lightGreen.setBackgroundColor(getResources().getColor(R.color.green));
                        lightYellow.setBackgroundColor(getResources().getColor(R.color.black));
                        lightRed.setBackgroundColor(getResources().getColor(R.color.black));
                        message.setText("Pode seguir em frente!");
                        state = 1;
                        break;
                    case 1:
                        lightGreen.setBackgroundColor(getResources().getColor(R.color.black));
                        lightYellow.setBackgroundColor(getResources().getColor(R.color.yellow));
                        message.setText("ATENÇÃO! Vai fechar!");
                        state = 2;
                        break;
                    case 2:
                        lightYellow.setBackgroundColor(getResources().getColor(R.color.black));
                        lightRed.setBackgroundColor(getResources().getColor(R.color.red));
                        message.setText("PARE! Senão, leva multa!");
                        state = 0;
                        break;
                }
                handler.postDelayed(this, 3000);
            }
        };
        handler.post(runnable);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
