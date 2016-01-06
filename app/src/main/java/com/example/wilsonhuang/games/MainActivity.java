package com.example.wilsonhuang.games;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wilsonhuang.games.myFragments.GuessNumberFragment;
import com.example.wilsonhuang.games.myFragments.OOXXFragment;

public class MainActivity extends Activity {
    private Context context;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private CharSequence drawerTitle;
    private CharSequence contentTitle;

    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        contentTitle = drawerTitle = getTitle();

        drawerList = (ListView) findViewById(R.id.left_drawer_list);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerList.setAdapter(new ArrayAdapter<String>(
                context,
                R.layout.drawer_list_item,
                Games.gameNames));
        drawerList.setOnItemClickListener(new DrawerClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_open, R.string.drawer_close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(drawerTitle);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getActionBar().setTitle(contentTitle);
            }
        };

        actionBarDrawerToggle.syncState();

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about_me) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("關於我...");
            builder.setMessage("資訊管理學系\n黃建寧\n10322014B");
            builder.create().show();
        } else if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }


        return super.onOptionsItemSelected(item);
    }

    private class DrawerClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment = null;

        if (position == 0) {
            fragment = new GuessNumberFragment();
        } else if (position == 1) {
            fragment = new OOXXFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.content_main, fragment, String.valueOf(position))
                .commit();

        currentItem = position;

        drawerList.setItemChecked(position, true);

        setTitle(Games.gameNames[position]);

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void setTitle(CharSequence title) {
        contentTitle = title;
        getActionBar().setTitle(contentTitle);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


}
