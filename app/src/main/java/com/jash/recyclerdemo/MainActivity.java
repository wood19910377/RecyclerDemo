package com.jash.recyclerdemo;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnChildClickListener {

    private RecyclerView recycler;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = ((RecyclerView) findViewById(R.id.recycler));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format("第%03d条数据", i));
        }
        adapter = new MyAdapter(this, list);
        adapter.setListener(this);
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, 5, 0, 5);
            }
        });
        MyItemAnimator animator = new MyItemAnimator();
        animator.setRemoveDuration(2000);
        animator.setAddDuration(2000);
        recycler.setItemAnimator(animator);
    }

    @Override
    public void onChildClick(int position) {
//        adapter.remove(position);
        adapter.add(position, "新增数据");
    }
}
