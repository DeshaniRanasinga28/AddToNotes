package com.jkit.mynotesapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jkit.mynotesapplication.fragment.ViewNoteListFragment;

public class ViewNotesActivity extends AppCompatActivity implements ViewNoteListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        setTitle("View Notes");

        ViewNoteListFragment viewNoteListFragment = ViewNoteListFragment.newInstance("", "");
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, viewNoteListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
