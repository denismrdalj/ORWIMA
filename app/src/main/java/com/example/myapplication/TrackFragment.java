package com.example.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class TrackFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener, SampleViewAdapter.ItemClickListener, RemixViewAdapter.ItemClickListener{
    private ArrayList<Track> trackSample = new ArrayList<>();
    private ArrayList<Track> trackRemix = new ArrayList<>();
    private int[] trackImages = {
            R.drawable.track1, R.drawable.track2, R.drawable.track3, R.drawable.track4,
            R.drawable.track5, R.drawable.track6, R.drawable.track7, R.drawable.track8,
            R.drawable.track9, R.drawable.track10, R.drawable.track11, R.drawable.track12,
            R.drawable.track13
    };
    private Hashtable<String, Track> tracks= new Hashtable<>();
    private String mId;
    private int mImage;
    private String mArtist;
    private String mTitle;
    private String mBpm;
    private String mKey;
    private String mCamelot;
    private String mRelease;
    private String mAlbum;
    private String mLabel;
    private String mPop;
    private String mSampleLabel;
    private String mRemixLabel;

    private String ARG_Id="";
    private String ARG_Image="";
    private String ARG_Artist="";
    private String ARG_Title="";
    private String ARG_Bpm="";
    private String ARG_Key="";
    private String ARG_Camelot="";
    private String ARG_Release="";
    private String ARG_Album="";
    private String ARG_Label="";
    private String ARG_Pop="";
    private String ARG_Sample="";
    private String ARG_Remix="";
    private String ARG_SampleLabel="";
    private String ARG_RemixLabel="";

    public TrackFragment() {}

    public static TrackFragment newInstance(String id, int image, String artist, String title,
                                            String bpm, String key, String camelot,
                                            String release, String album, String label,
                                            String pop, String sampleLabel, String remixLabel,
                                            Track sample, Track remix) {
        TrackFragment fragment = new TrackFragment();
        Bundle args = new Bundle();
        fragment.mId=id;
        fragment.mImage=image;
        fragment.mArtist=artist;
        fragment.mTitle=title;
        fragment.mBpm=bpm;
        fragment.mKey=key;
        fragment.mCamelot=camelot;
        fragment.mRelease=release;
        fragment.mAlbum=album;
        fragment.mLabel=label;
        fragment.mPop=pop;
        fragment.mSampleLabel=sampleLabel;
        fragment.mRemixLabel=remixLabel;
        fragment.trackSample.add(sample);
        fragment.trackRemix.add(remix);
        args.putString(fragment.ARG_Id, id);
        args.putInt(fragment.ARG_Image, image);
        args.putString(fragment.ARG_Artist, artist);
        args.putString(fragment.ARG_Title, title);
        args.putString(fragment.ARG_Bpm, bpm);
        args.putString(fragment.ARG_Key, key);
        args.putString(fragment.ARG_Camelot, camelot);
        args.putString(fragment.ARG_Release, release);
        args.putString(fragment.ARG_Album, album);
        args.putString(fragment.ARG_Label, label);
        args.putString(fragment.ARG_Pop, pop);
        args.putString(fragment.ARG_SampleLabel, sampleLabel);
        args.putString(fragment.ARG_RemixLabel, remixLabel);
        args.putParcelable(fragment.ARG_Sample, sample);
        args.putParcelable(fragment.ARG_Remix, remix);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track,container,false);
        setUpTrackModels();
        initSampleRecyclerView(view);
        initRemixRecyclerView(view);
        displayData(view);
        return view;
    }

    public void displayData(View view){
        ImageView imageView = view.findViewById(R.id.imageViewTrack);
        TextView artistView = view.findViewById(R.id.trackArtist);
        TextView titleView = view.findViewById(R.id.trackTitle);
        TextView bpmView = view.findViewById(R.id.trackBPM);
        TextView keyView = view.findViewById(R.id.trackKey);
        TextView camelotView = view.findViewById(R.id.trackCamelot);
        TextView releaseView = view.findViewById(R.id.trackRelease);
        TextView albumView = view.findViewById(R.id.trackAlbum);
        TextView labelView = view.findViewById(R.id.trackLabel);
        TextView popView = view.findViewById(R.id.trackPop);
        TextView sampleView = view.findViewById(R.id.samples_text);
        TextView remixView = view.findViewById(R.id.remixes_text);
        ImageButton closeButton = view.findViewById(R.id.close_button);
        imageView.setImageResource(mImage);
        artistView.setText(mArtist);
        titleView.setText(mTitle);
        bpmView.setText(mBpm);
        keyView.setText(mKey);
        camelotView.setText(mCamelot);
        releaseView.setText(mRelease);
        albumView.setText(mAlbum);
        labelView.setText(mLabel);
        popView.setText(mPop);
        sampleView.setText(mSampleLabel);
        remixView.setText(mRemixLabel);
        closeButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                closeFragments();
            }
        });
    }

    private void closeFragments(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
    }

    private void initSampleRecyclerView(View view) {
        RecyclerView sampleView = view.findViewById(R.id.samples_recycler);
        LinearLayoutManager slayoutManager = new LinearLayoutManager(getActivity());
        sampleView.setLayoutManager(slayoutManager);
        SampleViewAdapter sampleAdapter = new SampleViewAdapter(trackSample, this);
        sampleView.setAdapter(sampleAdapter);
    }
    private void initRemixRecyclerView(View view) {
        RecyclerView remixView = view.findViewById(R.id.remixes_recycler);
        LinearLayoutManager rlayoutManager = new LinearLayoutManager(getActivity());
        remixView.setLayoutManager(rlayoutManager);
        RemixViewAdapter remixAdapter = new RemixViewAdapter(trackRemix, this);
        remixView.setAdapter(remixAdapter);
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
            transaction.replace(R.id.track_fragment, fragment, "track_fragment");
            transaction.addToBackStack(null);
            transaction.commit();

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
        }
    }
}