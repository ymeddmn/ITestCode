package com.example.lifecycle.fragmentlifecycle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author mayong
 * @date 创建时间 2017/6/22
 * @description 长按更改添加fragment状态 ，点击切换下次切换的方法
 */
public class MainActivity extends AppCompatActivity {
    private String text1[] = {"add", "replace", "remove", "hied"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = (Button) findViewById(R.id.btn_fragment1);
        Button btn2 = (Button) findViewById(R.id.btn_fragment2);
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        btn1.setOnClickListener(new MyClickListener(fragment1));
        btn2.setOnClickListener(new MyClickListener(fragment2));
        btn1.setOnLongClickListener(new MyLongClickListener(fragment1));
        btn2.setOnLongClickListener(new MyLongClickListener(fragment2));
        btn1.setText(text1[0]);
        btn2.setText(text1[0]);
        btn1.setTag(1);
        btn2.setTag(1);

    }

    private void add(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl, fragment);
        transaction.commit();
    }

    private void replace(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl, fragment);
        transaction.commit();
    }

    private void hide(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

    private void remove(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }

    private void switchFragment(int index, Fragment fragment, View view) {
        switch (index) {
            case 0:
                add(fragment);
                break;
            case 1:
                replace(fragment);
                break;
            case 2:
                remove(fragment);
                break;
            case 3:
                hide(fragment);
                break;
        }

    }

    class MyLongClickListener implements View.OnLongClickListener {
        private Fragment fragment;

        public MyLongClickListener(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public boolean onLongClick(View v) {
            int tag = (int) v.getTag();
            tag %= 4;
            switchFragment(tag, fragment, v);
            return true;
        }
    }

    class MyClickListener implements View.OnClickListener {
        private Fragment fragment;

        public MyClickListener(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v) {
            int index = (int) v.getTag();
            index++;
            index %= 4;
            ((Button) (v)).setText(text1[index]);
            v.setTag(index);
        }
    }
}
