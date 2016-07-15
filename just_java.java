package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class just_java extends AppCompatActivity {


    int quantity = 0;

    /**
     * This app displays an order form to order coffee.
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_java);
    }

    public int increment (View view){
        quantity = quantity +1;
        displayQuantity(quantity);
        displayPrice(getPrice());
        return quantity;

    }
    // This method will decrease the orders of coffee and reflect the price
    public int decrement (View view) {

        if (quantity == 0)
        {
            quantity = 0;
        }
        else {
            quantity = quantity -1;
        }

        displayQuantity(quantity);
        displayPrice(getPrice());
        return quantity;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /** This method finds the persons name and converts it to text.
     */
    private String findName (){
        EditText personsName = (EditText) findViewById(R.id.personsName);
        String name = personsName.getText().toString();
        return name;
    }


    /**
     *   when someone adds or subtracts a topping the price is instantly reflected
     */

    public int updatePrice (View view){
        displayPrice(getPrice());
        return 0;


    }



    /**
     * This method takes the quantity and returns the price  based on options chosen
     *
     */

    public int getPrice (){

        boolean boxCheckedWhippedCream = ((CheckBox) findViewById(R.id.wantsWhippedCream)).isChecked();
        boolean boxCheckedChocolate = ((CheckBox) findViewById(R.id.wantsChocolate)).isChecked();

        int coffeePrice = 5;
        int whippedCreamPrice = 1;
        int chocolatePrice = 2;

        if (boxCheckedWhippedCream == false)
        {
            whippedCreamPrice = 0;
        }

        if (boxCheckedChocolate == false)
        {
            chocolatePrice = 0;
        }

        int totalPrice = quantity * (coffeePrice+whippedCreamPrice+chocolatePrice);
        return totalPrice;
    }

    /**  this method takes in a will take in the quanity variable calculate it out and display
     * the person ordering the drink, the quantity, and total cost along with a Thank You!
     */





    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        boolean boxCheckedWhippedCream = ((CheckBox) findViewById(R.id.wantsWhippedCream)).isChecked();

        boolean boxCheckedChocolate = ((CheckBox) findViewById(R.id.wantsChocolate)).isChecked();


        String orderSummary = (
                "Name: " + findName() + System.lineSeparator() +
                        "Add Whipped Cream?  " + boxCheckedWhippedCream + System.lineSeparator() +
                        "Add Chocolate? " + boxCheckedChocolate + System.lineSeparator() +
                        "Quantity: " + quantity + System.lineSeparator() +
                        "Total: " + getPrice() + System.lineSeparator() +
                        "Thank You!"
        );

        Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
        sendEmail.setData(Uri.parse("mailto:")); // only email apps should handle this
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + findName());
        sendEmail.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (sendEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendEmail);
        }

    }
}
