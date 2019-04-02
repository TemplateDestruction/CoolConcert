package com.breakout.myapplication.concerts.concert.songs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.breakout.myapplication.R;

import static com.breakout.myapplication.model.song_text.TYPE_UNKNOWN;


public class SongActivity extends AppCompatActivity {

    private static final int LOADER_SONG = 0;
    private String type = TYPE_UNKNOWN;

    private static final String KEY_TYPE = "TYPE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_layout);
    }

//        loadSong();
//
//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                loadSong();
//            }
//        });

    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        loadSong();
//
//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                loadSong();
//            }
//        });
//    }
//    private void loadSong() {
//        getLoaderManager().initLoader(LOADER_SONG, null, new LoaderManager.LoaderCallbacks<List<Item>>() {
//
//            @SuppressLint("StaticFieldLeak")
//            @Override
//            public Loader <song_text> onCreateLoader(int id, Bundle args) {
//                return new AsyncTaskLoade <song_text> (getContext()) {
//                    @Override
//                    public <song_text> loadInBackground() {
//                        try {
//                            <song_text> song_text = EducationService.getSongText(eventID).execute().body();
//                            return song_text;
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            return null;
//                        }
//
//                    }
//                };
//            }
//
//            @Override
//            public void onLoadFinished(Loader<List<Item>> loader, List<Item> items) {
//                if (items == null) {
//                    showError("Произошла ошибка");
//                } else {
//                    adapter.setItems(items);
//                }
//                refresh.setRefreshing(true);
//                refresh.setRefreshing(false);
//            }
//
//            @Override
//            public void onLoaderReset(Loader<List<Item>> loader) {
//
//            }
//        }).forceLoad();
//    }}