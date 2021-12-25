package com.hassan.fypuidesign;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lectures#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lectures extends Fragment {
    MediaPlayer mediaPlayer;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public lectures() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment lectures.
     */
    // TODO: Rename and change types and number of parameters
    public static lectures newInstance(String param1, String param2) {
        lectures fragment = new lectures();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lectures, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String LanguageForLectures=lecturesArgs.fromBundle(getArguments()).getLanguageForLectures();
        String SubjectsForLectures=lecturesArgs.fromBundle(getArguments()).getSubjectLectures();
        String ModeOfLearning=lecturesArgs.fromBundle(getArguments()).getModeOfLearning();
        if (LanguageForLectures=="UrduL"){
            if (mediaPlayer==null){
                mediaPlayer=MediaPlayer.create(getActivity(),R.raw.lecturesurdu);
            }
            else
            mediaPlayer.release();
            mediaPlayer.start();
        }
        else {
            if (mediaPlayer==null){
                mediaPlayer=MediaPlayer.create(getActivity(),R.raw.lecturespushto);
            }
            else
                mediaPlayer.release();
            mediaPlayer.start();
        }
        NavController navController = Navigation.findNavController(view);
        ImageView backarrow = view.findViewById(R.id.backarrow);
        ImageView audioLectures = view.findViewById(R.id.audioLectures);
        audioLectures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer!=null){
                    mediaPlayer.release();
                    audioLectures.setImageResource(R.drawable.mutedaudio);
                }
                else {
                    if(LanguageForLectures=="UrduL") {
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.lecturesurdu);
                    }
                    else{
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.lecturespushto);
                    }
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    audioLectures.setImageResource(R.drawable.speaker);
                }
            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action=lecturesDirections.actionLecturesToLearningMode(LanguageForLectures,SubjectsForLectures);
                NavOptions navOptions=new NavOptions.Builder().setPopUpTo(R.id.learningMode,true).build();
                navController.navigate(action,navOptions);
                mediaPlayer.release();
            }
        });
    }
}