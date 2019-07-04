package com.example.movierating1;

import android.app.ListActivity;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.database.Cursor;
import android.net.Uri;
import android.content.ContentResolver;


public class ReviewListActivity extends ListActivity {
    /* Variable declarations */
    private ListView mRatingList;
    private List<String> mReviewData = new ArrayList<String>();


    /* Override the onCreate() method */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviewlist);

        /* Variable initialization */
        mRatingList = (ListView)findViewById(android.R.id.list);
        String columns[] = new String[] { "name" };

        Uri movie = Uri.parse(

                "content://com.movierating.provider.Movie/reviews/");

        ContentResolver cr = getContentResolver();

        Cursor c = cr.query(movie, columns, null, null, null );

        if (c.moveToFirst()) {

            do {

                // Get the field values

                mReviewData.add(c.getString(c

                        .getColumnIndex("name")));

            } while (c.moveToNext());

        }

        if (c != null && !c.isClosed()) {

            c.close();}


        /* Create an array adapter to initialize the list view */
        ArrayAdapter<String> mReviewList = new
                ArrayAdapter<String>(this,R.layout.listentry, mReviewData);
        setListAdapter(mReviewList);

        /* Create OnItemClickListener for the list view */
        mRatingList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int
                    arg2, long arg3) {
                // TODO Auto-generated method stub
                /* Create an intent to invoke the Display Review activity */
                Intent mReviewListIntent = new
                        Intent(ReviewListActivity.this,
                        DisplayReviewActivity.class);

		/* Create a Bundle object to pass the id of the selected
	         record */
                Bundle mBundle = new Bundle();
                mBundle.putInt("id", arg2);
                mReviewListIntent.putExtras(mBundle);
		/* Start an activity by using the details included in the
	         intent */
                startActivity(mReviewListIntent);
            }
        });
    }


}

