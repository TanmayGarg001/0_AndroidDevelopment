package com.data.android.coffeeorderingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static byte numberOfCoffees = 0;
    private static String messageOutputForEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @SuppressLint("SetTextI18n")
    public void orderButton(View view) {
        double amount = numberOfCoffees * 3.25;
        String messageOutput = "";
        TextView customerName = findViewById(R.id.customer_name);
        TextView msg = findViewById(R.id.order_details);

        CheckBox checkBox = findViewById(R.id.whipped_cream);
        CheckBox checkBox2 = findViewById(R.id.chocolate);
        boolean isWhippedCream = checkBox.isChecked();
        boolean isChocolate = checkBox2.isChecked();
        if (amount == 0) {
            msg.setText("Please select your order");
        } else {
            if (isWhippedCream) {
                amount = amount + 0.5 * numberOfCoffees;
                messageOutput = messageOutput + "Whipped Cream \n";
            }
            if (isChocolate) {
                amount = amount + 0.75 * numberOfCoffees;
                messageOutput = messageOutput + "Chocolate \n";
            }
            messageOutput = "Name: " + customerName.getText().toString() + "\nTotal Price: $" + amount + " ( " + numberOfCoffees + " Coffees) \n" + messageOutput + "\nThanks\nHave a sweet day!";
            msg.setText(messageOutput);
        }
        displayPrice(amount);
        messageOutputForEmail = messageOutput;
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void mailIndent(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Bill");
        intent.putExtra(Intent.EXTRA_TEXT, messageOutputForEmail);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @SuppressLint("SetTextI18n")
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Integer.toString(number));
    }

    @SuppressLint("SetTextI18n")
    private void displayPrice(double price) {
        TextView amount = findViewById(R.id.price_text_view);
        amount.setText("$" + price);
    }

    public void incrementNumberOfCoffees(View view) {
        if (numberOfCoffees >= 50) {
            numberOfCoffees = 50;
            Toast.makeText(this, "You can't have more than 50 coffees!", Toast.LENGTH_SHORT).show();
        } else {
            numberOfCoffees++;
        }
        display(numberOfCoffees);
    }

    public void decrementNumberOfCoffees(View view) {
        if (numberOfCoffees <= 0) {
            numberOfCoffees = 0;
            Toast.makeText(this, "You can't order less than 0 coffees!", Toast.LENGTH_SHORT).show();
        } else {
            numberOfCoffees--;
        }
        display(numberOfCoffees);
    }

}