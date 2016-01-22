package com.example.menuproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    ShareActionProvider shareActionProvider;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu); // 메뉴 Inflate 하기, xml로
//        MenuItem item = menu.add(0, 100, 5,"100 추가" ); // 코드로 추가하기
//        item.setIcon(R.mipmap.ic_launcher);
        getMenuInflater().inflate(R.menu.menu1, menu); // 메뉴 Inflate 하기, xml로

        MenuItem item = menu.findItem(R.id.menu_share);
        shareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(item);
        shareActionProvider.setShareIntent(makeIntent());

        return super.onCreateOptionsMenu(menu);
    }

    Intent makeIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "test");
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 실제 메뉴 보여지는 곳
        switch (item.getItemId()){
            case R.id.menu1 :
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2 :
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 100 :
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
