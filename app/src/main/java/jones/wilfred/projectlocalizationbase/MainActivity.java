package jones.wilfred.projectlocalizationbase;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView colorTextView;
    View colorSwatch;
    TextView priceTextView;
    Button buyButton;
    String[] colors;
    DrawerLayout drawerLayout;
    RecyclerView drawerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorTextView = (TextView) findViewById(R.id.colorNameTextView);
        colorSwatch = findViewById(R.id.colorSwatchBuy);
        priceTextView = (TextView) findViewById(R.id.priceTextView);
        buyButton = (Button) findViewById(R.id.buyButton);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        colors = getResources().getStringArray(R.array.colors);
        drawerRecyclerView = (RecyclerView) findViewById(R.id.drawer);

        drawerRecyclerView.setAdapter(new DrawerAdapter(colors));
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                String dateString = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
                String toastString = String.format(getResources().getString(R.string.purchase_message), dateString);
                Toast.makeText(MainActivity.this, toastString, Toast.LENGTH_LONG).show();
            }
        });

        drawerLayout.openDrawer(drawerRecyclerView);

        float price = 19.99f;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setCurrency(Currency.getInstance("USD"));
        String priceString = numberFormat.format(price);
        priceTextView.setText(priceString);

    }

    public void updateScreen(int position) {
        String currentColor = colors[position];
        colorTextView.setText(currentColor);
        colorSwatch.setBackgroundColor(Colors.codes[position]);
        drawerLayout.closeDrawer(drawerRecyclerView);
    }

}

