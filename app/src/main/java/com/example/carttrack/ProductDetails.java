package com.example.carttrack;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductDetails extends AppCompatActivity {

    TextView ProductPrice,ProductName,ProductQuantity,ProductExpiry;
    ImageView ProductImage;
    ElegantNumberButton NoButton;
    String ProductID=" ";
    Button addtocart;
    String Pname,Pprice,Pquantity,Pexpiry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        ProductName=findViewById(R.id.pdt_details_pname);
        ProductPrice=findViewById(R.id.pdt_details_price);
        ProductQuantity=findViewById(R.id.pdt_details_qty);
        ProductImage=findViewById(R.id.pdt_details_img);
        NoButton=findViewById(R.id.pdt_noofitems);
        ProductExpiry=findViewById(R.id.pdt_details_expiry);
        addtocart=findViewById(R.id.addtocart);
        String code =getIntent().getStringExtra("barcode");
        System.out.println("sarangpk");
        getProductDetails(code);

    }

    public void getProductDetails(String ProductID)
    {

        DatabaseReference productsRef= FirebaseDatabase.getInstance().getReference().child("Products");
        System.out.println(ProductID);
        productsRef.child(ProductID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {

                    Products products=snapshot.getValue(Products.class);
                    Pname = products.getCategory();
                    Pprice = products.getPrice();
                    Pquantity = products.getBrand();
                    Pexpiry = products.getBarcode();
//                    ProductName.setText("sarang");
                    ProductPrice.setText(Pprice);
                    ProductQuantity.setText(Pquantity);
                    ProductExpiry.setText(Pexpiry);
//                    Picasso.get().load(products.getImage()).into(ProductImage);
                    ProductName.setText(Pname);
                }
                else{
                    ProductName.setText("sarangpk");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
class Products {
    private long barcode,price;
    private String brand,category;
    public Products()
    {

    }

    public Products(long barcode, String brand, String category, long price) {
        this.barcode = barcode;
        this.brand = brand;
        this.category = category;
        //this.expiry = expiry;
        //this.name = name;
        this.price = price;
        //this.quantity = quantity;
    }

//    public String getPid() {
//        return pid;
//    }
//
//    public void setPid(String pid) {
//        this.pid = pid;
//    }

//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public String getImage() {
//        return image;
//    }

    public String getBarcode() {
        String b=Long.toString(barcode);
        return b;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

//    public String getExpiry() {
//        return expiry;
//    }
//
//    public String getName() {
//        return name;
//    }

    public String getPrice() {
        String s=Long.toString(price);
        return s;
    }

//    public String getQuantity() {
//        return quantity;
//    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategory(String category) {
        this.category = category;
    }

//    public void setExpiry(String expiry) {
//        this.expiry = expiry;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public void setPrice(long price) {
        this.price = price;
    }

//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }
}
