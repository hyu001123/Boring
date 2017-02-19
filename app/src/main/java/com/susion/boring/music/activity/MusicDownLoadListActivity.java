package com.susion.boring.music.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.susion.boring.R;
import com.susion.boring.base.BaseActivity;
import com.susion.boring.base.BaseRVAdapter;
import com.susion.boring.base.ItemHandler;
import com.susion.boring.base.ItemHandlerFactory;
import com.susion.boring.base.view.LoadMoreRecycleView;
import com.susion.boring.music.itemhandler.MusicDownIH;

public class MusicDownLoadListActivity extends BaseActivity {

    private LoadMoreRecycleView mRv;

    public static void start(Context context) {
        Intent intent = new Intent(context, MusicDownLoadListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_music_down_load_list;
    }

    @Override
    public void findView() {
        mRv = (LoadMoreRecycleView) findViewById(R.id.list_view);
        mRv.setAdapter(new BaseRVAdapter() {
            @Override
            protected void initHandlers() {
                registerItemHandler(0, new ItemHandlerFactory() {
                    @Override
                    public ItemHandler newInstant(int viewType) {
                        return new MusicDownIH();
                    }
                });
            }

            @Override
            protected int getViewType(int position) {
                return 0;
            }
        });
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

}
