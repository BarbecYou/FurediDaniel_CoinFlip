package hu.petrik.android_10_13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView coinImg;
    private Button fejBtn;
    private Button irasBtn;
    private TextView dobasokTextView;
    private TextView gyozelemTextView;
    private TextView veresegTextView;

    private boolean isValasztottFej;
    private boolean isVeletlenFej;
    private Random rnd;

    private int dobasokSzama;
    private int gyozelmekSzama;
    private int veresegekSzama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addListeners();
    }

    private void addListeners() {
        fejBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isValasztottFej = true;
                coinFlip();
            }
        });
        irasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isValasztottFej = false;
                coinFlip();
            }
        });
    }

    private void coinFlip() {
        int temp = rnd.nextInt(2);
        for (int i = 0; i < 2; i++) {
            if (temp == 0){
                isVeletlenFej = true;
            }else {
                isVeletlenFej = false;
            }
        }
        if (isVeletlenFej){
            coinImg.setImageResource(R.drawable.heads);
            Toast.makeText(this, "A dobás eredménye: Fej!", Toast.LENGTH_SHORT).show();
        }else {
            coinImg.setImageResource(R.drawable.tails);
            Toast.makeText(this, "A dobás eredménye: Írás!", Toast.LENGTH_SHORT).show();
        }
        if (isVeletlenFej == isValasztottFej){
            gyozelmekSzama++;
        }else {
            veresegekSzama++;
        }
        dobasokSzama++;
        dobasokTextView.setText(String.format("Dobások: %d", dobasokSzama));
        gyozelemTextView.setText(String.format("Győzelem: %d", gyozelmekSzama));
        veresegTextView.setText(String.format("Vereség: %d", veresegekSzama));

        if (dobasokSzama == 5){
            if (veresegekSzama < gyozelmekSzama){
                new AlertDialog.Builder(MainActivity.this).setTitle("Győzelem")
                        .setMessage("Szeretne új játékot kezdeni?")
                        .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ujJatek();
                            }
                        }).setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).setCancelable(false).show();
            }
            else {
                new AlertDialog.Builder(MainActivity.this).setTitle("Vereség")
                        .setMessage("Szeretne új játékot kezdeni?")
                        .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ujJatek();
                            }
                        }).setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).setCancelable(false).show();
            }
        }
    }

    private void ujJatek() {
        dobasokSzama = 0;
        gyozelmekSzama = 0;
        veresegekSzama = 0;

        coinImg.setImageResource(R.drawable.heads);

        dobasokTextView.setText(String.format("Dobások: %d", dobasokSzama));
        gyozelemTextView.setText(String.format("Győzelem: %d", gyozelmekSzama));
        veresegTextView.setText(String.format("Vereség: %d", veresegekSzama));
    }

    private void init(){
        this.coinImg = findViewById(R.id.coinImg);
        this.fejBtn = findViewById(R.id.fejBtn);
        this.irasBtn = findViewById(R.id.irasBtn);
        this.dobasokTextView = findViewById(R.id.dobasokTextView);
        this.gyozelemTextView= findViewById(R.id.gyozelemTextView);
        this.veresegTextView= findViewById(R.id.veresegTextView);
        rnd = new Random();

        ujJatek();
    }
}