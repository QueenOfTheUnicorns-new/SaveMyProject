package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Shop extends AppCompatActivity {
    View view;


    Button shop_back;

    Button top_shop_button_1;
    Button top_shop_button_2;
    Button top_shop_button_3;
    Button bottom_shop_button_1;
    Button bottom_shop_button_2;
    Button bottom_shop_button_3;

    TextView quality_item_top_1;
    TextView quality_item_top_2;
    TextView quality_item_top_3;
    TextView quality_item_bottom_1;
    TextView quality_item_bottom_2;
    TextView quality_item_bottom_3;

    TextView price_item_top_1;
    TextView price_item_top_2;
    TextView price_item_top_3;
    TextView price_item_bottom_1;
    TextView price_item_bottom_2;
    TextView price_item_bottom_3;

    ArrayList<Item> items;

    TextView shop_money;

    Character hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



        view = getWindow().getDecorView();

        view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    view.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        });




        hero = (Character) getIntent().getSerializableExtra("hero_get");

        Generator generator = new Generator(MainActivity.getStage(),MainActivity.getLevel());

        items = generator.itemForShopGenerator(hero.getHeroItems());

        shop_back = findViewById(R.id.shop_back);

        shop_money = findViewById(R.id.shop_money);

        top_shop_button_1 = findViewById(R.id.top_shop_button_1);
        top_shop_button_2 = findViewById(R.id.top_shop_button_2);
        top_shop_button_3 = findViewById(R.id.top_shop_button_3);

        bottom_shop_button_1 = findViewById(R.id.bottom_shop_button_1);
        bottom_shop_button_2 = findViewById(R.id.bottom_shop_button_2);
        bottom_shop_button_3 = findViewById(R.id.bottom_shop_button_3);

        quality_item_top_1 = findViewById(R.id.quality_item_top_1);
        quality_item_top_2 = findViewById(R.id.quality_item_top_2);
        quality_item_top_3 = findViewById(R.id.quality_item_top_3);

        quality_item_bottom_1 = findViewById(R.id.quality_item_bottom_1);
        quality_item_bottom_2 = findViewById(R.id.quality_item_bottom_2);
        quality_item_bottom_3 = findViewById(R.id.quality_item_bottom_3);

        price_item_top_1 = findViewById(R.id.price_item_top_1);
        price_item_top_2 = findViewById(R.id.price_item_top_2);
        price_item_top_3 = findViewById(R.id.price_item_top_3);

        price_item_bottom_1 = findViewById(R.id.price_item_bottom_1);
        price_item_bottom_2 = findViewById(R.id.price_item_bottom_2);
        price_item_bottom_3 = findViewById(R.id.price_item_bottom_3);

        top_shop_button_1.setText(generator.infoAboutItemInShop(items.get(0)).split("\\.")[0]);
        top_shop_button_2.setText(generator.infoAboutItemInShop(items.get(1)).split("\\.")[0]);
        top_shop_button_3.setText(generator.infoAboutItemInShop(items.get(2)).split("\\.")[0]);
        bottom_shop_button_1.setText(generator.infoAboutItemInShop(items.get(3)).split("\\.")[0]);
        bottom_shop_button_2.setText(generator.infoAboutItemInShop(items.get(4)).split("\\.")[0]);
        bottom_shop_button_3.setText(generator.infoAboutItemInShop(items.get(5)).split("\\.")[0]);

        quality_item_top_1.setText(generator.infoAboutItemInShop(items.get(0)).split("\\.")[1]);
        quality_item_top_2.setText(generator.infoAboutItemInShop(items.get(1)).split("\\.")[1]);
        quality_item_top_3.setText(generator.infoAboutItemInShop(items.get(2)).split("\\.")[1]);
        quality_item_bottom_1.setText(generator.infoAboutItemInShop(items.get(3)).split("\\.")[1]);
        quality_item_bottom_2.setText(generator.infoAboutItemInShop(items.get(4)).split("\\.")[1]);
        quality_item_bottom_3.setText(generator.infoAboutItemInShop(items.get(5)).split("\\.")[1]);

        price_item_top_1.setText(String.valueOf(generator.priceOfItemInStore(items.get(0))));
        price_item_top_2.setText(String.valueOf(generator.priceOfItemInStore(items.get(1))));
        price_item_top_3.setText(String.valueOf(generator.priceOfItemInStore(items.get(2))));
        price_item_bottom_1.setText(String.valueOf(generator.priceOfItemInStore(items.get(3))));
        price_item_bottom_2.setText(String.valueOf(generator.priceOfItemInStore(items.get(4))));
        price_item_bottom_3.setText(String.valueOf(generator.priceOfItemInStore(items.get(5))));

        quality_item_top_1.setTextColor(generator.colorOfItemRarity(items.get(0)));
        quality_item_top_2.setTextColor(generator.colorOfItemRarity(items.get(1)));
        quality_item_top_3.setTextColor(generator.colorOfItemRarity(items.get(2)));
        quality_item_bottom_1.setTextColor(generator.colorOfItemRarity(items.get(3)));
        quality_item_bottom_2.setTextColor(generator.colorOfItemRarity(items.get(4)));
        quality_item_bottom_3.setTextColor(generator.colorOfItemRarity(items.get(5)));

        shop_money.setText(String.valueOf(hero.getMoney()));

        ButtonTreatment bt = new ButtonTreatment();

        shop_back.setOnClickListener(bt);
        top_shop_button_1.setOnClickListener(bt);
        top_shop_button_2.setOnClickListener(bt);
        top_shop_button_3.setOnClickListener(bt);
        bottom_shop_button_1.setOnClickListener(bt);
        bottom_shop_button_2.setOnClickListener(bt);
        bottom_shop_button_3.setOnClickListener(bt);

    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }


    private class ButtonTreatment implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.shop_back:
                    shop_back.setEnabled(false);
                    Intent i = new Intent(Shop.this, Locate.class);
                    i.putExtra("hero_get", hero);
                    MainActivity.setLevel(MainActivity.getLevel() + 1);
                    startActivity(i);
                    break;

                case R.id.top_shop_button_1:
                    top_shop_button_1.setEnabled(false);
                    int priceItem1 = Integer.parseInt(price_item_top_1.getText().toString());
                    if(hero.getMoney() >= priceItem1) {
                        hero.setMoney(hero.getMoney() - priceItem1);
                        shop_money.setText(String.valueOf(hero.getMoney()));
                        ArrayList<Item> hi = hero.getHeroItems();
                        hi.add(items.get(0));
                        hero.setHeroItems(hi);
                        top_shop_button_1.setText("Куплено");
                        quality_item_top_1.setText("");
                        price_item_top_1.setText("-");
                    } else {
                        Toast.makeText(Shop.this,"Недостаточно денег",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.top_shop_button_2:
                    top_shop_button_2.setEnabled(false);
                    int priceItem2 = Integer.parseInt(price_item_top_2.getText().toString());
                    if(hero.getMoney() >= priceItem2) {
                        hero.setMoney(hero.getMoney() - priceItem2);
                        shop_money.setText(String.valueOf(hero.getMoney()));
                        ArrayList<Item> hi = hero.getHeroItems();
                        hi.add(items.get(1));
                        hero.setHeroItems(hi);
                        top_shop_button_2.setText("Куплено");
                        quality_item_top_2.setText("");
                        price_item_top_2.setText("-");
                    } else {
                        Toast.makeText(Shop.this,"Недостаточно денег",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.top_shop_button_3:
                    top_shop_button_3.setEnabled(false);
                    int priceItem3 = Integer.parseInt(price_item_top_3.getText().toString());
                    if(hero.getMoney() >= priceItem3) {
                        hero.setMoney(hero.getMoney() - priceItem3);
                        shop_money.setText(String.valueOf(hero.getMoney()));
                        ArrayList<Item> hi = hero.getHeroItems();
                        hi.add(items.get(2));
                        hero.setHeroItems(hi);
                        top_shop_button_3.setText("Куплено");
                        quality_item_top_3.setText("");
                        price_item_top_3.setText("-");
                    } else {
                        Toast.makeText(Shop.this,"Недостаточно денег",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.bottom_shop_button_1:
                    bottom_shop_button_1.setEnabled(false);
                    int priceItem4 = Integer.parseInt(price_item_bottom_1.getText().toString());
                    if(hero.getMoney() >= priceItem4) {
                        hero.setMoney(hero.getMoney() - priceItem4);
                        shop_money.setText(String.valueOf(hero.getMoney()));
                        ArrayList<Item> hi = hero.getHeroItems();
                        hi.add(items.get(3));
                        hero.setHeroItems(hi);
                        bottom_shop_button_1.setText("Куплено");
                        quality_item_bottom_1.setText("");
                        price_item_bottom_1.setText("-");
                    } else {
                        Toast.makeText(Shop.this,"Недостаточно денег",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.bottom_shop_button_2:
                    bottom_shop_button_2.setEnabled(false);
                    int priceItem5 = Integer.parseInt(price_item_bottom_2.getText().toString());
                    if(hero.getMoney() >= priceItem5) {
                        hero.setMoney(hero.getMoney() - priceItem5);
                        shop_money.setText(String.valueOf(hero.getMoney()));
                        ArrayList<Item> hi = hero.getHeroItems();
                        hi.add(items.get(4));
                        hero.setHeroItems(hi);
                        bottom_shop_button_2.setText("Куплено");
                        quality_item_bottom_2.setText("");
                        price_item_bottom_2.setText("-");
                    } else {
                        Toast.makeText(Shop.this,"Недостаточно денег",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.bottom_shop_button_3:
                    bottom_shop_button_3.setEnabled(false);
                    int priceItem6 = Integer.parseInt(price_item_bottom_3.getText().toString());
                    if(hero.getMoney() >= priceItem6) {
                        hero.setMoney(hero.getMoney() - priceItem6);
                        shop_money.setText(String.valueOf(hero.getMoney()));
                        ArrayList<Item> hi = hero.getHeroItems();
                        hi.add(items.get(5));
                        hero.setHeroItems(hi);
                        bottom_shop_button_3.setText("Куплено");
                        quality_item_bottom_3.setText("");
                        price_item_bottom_3.setText("-");
                    } else {
                        Toast.makeText(Shop.this,"Недостаточно денег",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

}
