package com.example.myapplication;

import static android.icu.text.DisplayContext.LENGTH_SHORT;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

public class MainFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener {
    private ArrayList<Track> trackModels = new ArrayList<>();
    RecyclerViewAdapter adapter = new RecyclerViewAdapter(trackModels, this);
    MainActivity activity;
    private int[] trackImages = {
            R.drawable.track1, R.drawable.track2, R.drawable.track3, R.drawable.track4,
            R.drawable.track5, R.drawable.track6, R.drawable.track7, R.drawable.track8,
            R.drawable.track9, R.drawable.track10, R.drawable.track11, R.drawable.track12,
            R.drawable.track13
    };
    private Hashtable<String, Track> tracks= new Hashtable<>();


    public MainFragment() {}

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setUpTrackModels();
        initRecyclerView(view);

        SearchView searchView;
        searchView = view.findViewById(R.id.searchText);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });
        return view;
    }

    private void filterList(String text) {
        ArrayList<Track> filteredList = new ArrayList<>();
        for(Track track : trackModels){
            if(track.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                    track.getArtist().toLowerCase().contains(text.toLowerCase()) ||
                    track.getBpm().toLowerCase().contains(text.toLowerCase()) ||
                    track.getKey().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(track);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(getActivity(), "No tracks found", Toast.LENGTH_SHORT).show();
        }else{
            adapter.setFilteredList(filteredList);
        }
    }


    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setUpTrackModels() {
        String[] trackIds = getResources().getStringArray(R.array.ids);
        String[] trackTitles = getResources().getStringArray(R.array.titles);
        String[] trackArtists = getResources().getStringArray(R.array.artists);
        String[] trackAlbums = getResources().getStringArray(R.array.albums);
        String[] trackLabels = getResources().getStringArray(R.array.labels);
        String[] trackCamelots = getResources().getStringArray(R.array.camelots);
        String[] trackKeys = getResources().getStringArray(R.array.keys);
        String[] trackBpms = getResources().getStringArray(R.array.bpms);
        String[] trackPops = getResources().getStringArray(R.array.pops);
        String[] trackReleases = getResources().getStringArray(R.array.releases);
        String[] trackSamples = getResources().getStringArray(R.array.samples);
        String[] trackRemixes = getResources().getStringArray(R.array.remixes);
        String[] trackSampleLabels = getResources().getStringArray(R.array.samples_text);
        String[] trackRemixLabels = getResources().getStringArray(R.array.remixes_text);

        for (int i = 0; i < trackIds.length; i++) {
            tracks.put(trackIds[i],new Track(trackIds[i],
                                        trackImages[i],
                                        trackArtists[i],
                                        trackTitles[i],
                                        trackBpms[i],
                                        trackKeys[i],
                                        trackCamelots[i],
                                        trackReleases[i],
                                        trackAlbums[i],
                                        trackLabels[i],
                                        trackPops[i],
                                        trackSamples[i],
                                        trackRemixes[i],
                                        trackSampleLabels[i],
                                        trackRemixLabels[i]));
            trackModels.add(new Track(
                    trackIds[i],
                    trackImages[i],
                    trackArtists[i],
                    trackTitles[i],
                    trackBpms[i],
                    trackKeys[i],
                    trackCamelots[i],
                    trackReleases[i],
                    trackAlbums[i],
                    trackLabels[i],
                    trackPops[i],
                    trackSamples[i],
                    trackRemixes[i],
                    trackSampleLabels[i],
                    trackRemixLabels[i]));
        }
    }

    @Override
    public void onItemClick(Track track) {
        Fragment fragment = TrackFragment.newInstance(
                track.getId(),
                track.getImage(),
                track.getArtist(),
                track.getTitle(),
                track.getBpm(),
                track.getKey(),
                track.getCamelot(),
                track.getRelease(),
                track.getAlbum(),
                track.getLabel(),
                track.getPop(),
                track.getSamplelabel(),
                track.getRemixlabel(),
                tracks.get(track.getSampleid()),
                tracks.get(track.getRemixid()));

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, fragment, "track_fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}