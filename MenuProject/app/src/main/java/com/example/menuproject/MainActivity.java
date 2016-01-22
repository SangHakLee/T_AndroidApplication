package com.example.menuproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    MyImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button);

        registerForContextMenu(btn); // 꼭 해줘야한다.

        myImageView = (MyImageView)findViewById(R.id.view);
        registerForContextMenu(myImageView);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) { // 메뉴 선택시
        switch (item.getItemId()){
            case R.id.cmenu1:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.cmenu2:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 101:
                Toast.makeText(this, "저장", Toast.LENGTH_SHORT).show();
                break;
            case 102:
                Toast.makeText(this, "삭제", Toast.LENGTH_SHORT).show();
                break;
            case 103:
                Toast.makeText(this, "편집", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { // 메뉴 넣어준다.
        switch (v.getId()){
            case R.id.button :
                getMenuInflater().inflate(R.menu.cmenu, menu);
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
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
