package com.example.demoproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.collectiondemoapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<EmployeeData> list;
    EmployeeAdapter adapter;
    RecyclerView recyclerView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //pass values into array
        list = new ArrayList<>();
        list.add(new EmployeeData("ram", R.drawable.ic_launcher_foreground, 1));
        list.add(new EmployeeData("mohan", R.drawable.ic_launcher_foreground, 2));
        list.add(new EmployeeData("aashish", R.drawable.ic_launcher_foreground, 3));
        list.add(new EmployeeData("ankit", R.drawable.ic_launcher_foreground, 4));
        list.add(new EmployeeData("arun", R.drawable.ic_launcher_foreground, 5));
        list.add(new EmployeeData("satish", R.drawable.ic_launcher_foreground, 6));
        list.add(new EmployeeData("deepak", R.drawable.ic_launcher_foreground, 7));
        list.add(new EmployeeData("manish", R.drawable.ic_launcher_foreground, 8));
        list.add(new EmployeeData("aryan", R.drawable.ic_launcher_foreground, 9));
        list.add(new EmployeeData("advik", R.drawable.ic_launcher_foreground, 10));






        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new EmployeeAdapter(list);
        recyclerView.setHasFixedSize(true);
        //set layout here
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);


    }


    //for search menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.onCloseSearchView();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}