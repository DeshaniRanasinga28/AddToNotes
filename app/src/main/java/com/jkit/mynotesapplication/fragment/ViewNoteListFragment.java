package com.jkit.mynotesapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jkit.mynotesapplication.R;
import com.jkit.mynotesapplication.UpdateActivity;
import com.jkit.mynotesapplication.adapter.CustomCursorAdapter;
import com.jkit.mynotesapplication.db.DBManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewNoteListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewNoteListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewNoteListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    View rootView;

    private CustomCursorAdapter customCursorAdapter;
    private DBManager dbManager;
    private ListView listView;



    public ViewNoteListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewNoteListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewNoteListFragment newInstance(String param1, String param2) {
        ViewNoteListFragment fragment = new ViewNoteListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        dbManager = new DBManager(getActivity());
        dbManager.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_view_note_list, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getListView();
    }

    public void getListView(){
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
                Cursor item = (Cursor) customCursorAdapter.getItem(position);

                String id = item.getString(item.getColumnIndex("_id"));
                String title = item.getString(item.getColumnIndex("title"));
                String des = item.getString(item.getColumnIndex("des"));

                Intent intent = new Intent(getActivity(), UpdateActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("des", des);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Handler().post(
                new Runnable() {
                    @Override
                    public void run() {
                        customCursorAdapter = new CustomCursorAdapter(getActivity(), dbManager.featch());
                        listView.setAdapter(customCursorAdapter);
                    }
                }
        );
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
