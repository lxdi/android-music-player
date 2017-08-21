package com.example.alexander.musicplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.alexander.musicplayer.entities.Playlist;

import java.util.ArrayList;

/**
 * Created by Alexander on 18.08.2017.
 */

public class PlaylistContentFragment extends Fragment {

    Playlist currentPlaylist;
    MainActivity mainActivity;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) inflater.getContext();
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.playlist_content, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
                android.R.layout.simple_list_item_1, currentPlaylist.getTracks());
        ListView trackList = ll.findViewById(R.id.tracks_list);
        trackList.setAdapter(adapter);
        trackList.setOnItemClickListener(getOnTrackClickListener());
        ll.findViewById(R.id.buttonChooseFiles).setOnClickListener(getChooseFilesButtonListener());
        ll.findViewById(R.id.back).setOnClickListener(getBackButtonListener());

        return ll;
    }

    private View.OnClickListener getChooseFilesButtonListener(){
        return new View.OnClickListener() {
            public void onClick(View v) {
                mainActivity.showChooseFilesFragment((ArrayList<String>) currentPlaylist.getTracks());
            }
        };
    }

    private View.OnClickListener getBackButtonListener(){
        return new View.OnClickListener() {
            public void onClick(View v) {
                mainActivity.getSupportFragmentManager().popBackStack();
            }
        };
    }

    private AdapterView.OnItemClickListener getOnTrackClickListener(){
        return new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                TrackControllerAdapter trackController = mainActivity.getTrackController();
                trackController.setPlaylist(currentPlaylist);
                trackController.playSong(position);
            }
        };
    }


    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }
    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }
}
