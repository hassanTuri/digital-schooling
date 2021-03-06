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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearningMode#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningMode extends Fragment implements Animation.AnimationListener {
        MediaPlayer mediaPlayer;
        int countForLoopLearning=0,countForWriting=0,countForReading=0;
    ImageView writing ;
    ImageView reading ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LearningMode() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearningMode.
     */
    // TODO: Rename and change types and number of parameters
    public static LearningMode newInstance(String param1, String param2) {
        LearningMode fragment = new LearningMode();
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
        return inflater.inflate(R.layout.fragment_learning_mode, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String LanguageForLearningMode=LearningModeArgs.fromBundle(getArguments()).getLanguageForLearningMode();
        String Subjects=LearningModeArgs.fromBundle(getArguments()).getSubjectChoosen();
        String LanguageBackLect=LearningModeArgs.fromBundle(getArguments()).getLanguageForLearningMode();
        if (LanguageForLearningMode=="UrduL"){
            if (mediaPlayer==null)
            mediaPlayer=MediaPlayer.create(getActivity(),R.raw.subjectsmodulesurdu);
            else
                mediaPlayer.release();
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
        else {
            if (mediaPlayer==null)
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.subjectmodulespushto);
            else
                mediaPlayer.release();
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
        NavController navController = Navigation.findNavController(view);
         writing = view.findViewById(R.id.writing);
         reading = view.findViewById(R.id.reading);
         animmat();
        ImageView back_subjects = view.findViewById(R.id.back_subjects);
        ImageView audioForLearningMode = view.findViewById(R.id.speakerVoice);

        back_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action=LearningModeDirections.actionLearningModeToSubjects(LanguageForLearningMode,Subjects);
                NavOptions navOptions=new NavOptions.Builder().setPopUpTo(R.id.subjects,true).build();
                navController.navigate(action,navOptions);
                mediaPlayer.release();
            }
        });
        audioForLearningMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer!=null){
                    mediaPlayer.release();
                    mediaPlayer=null;
                   audioForLearningMode.setImageResource(R.drawable.mutedaudio);
                }
                else {
                    if(LanguageForLearningMode=="UrduL") {
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.subjectsmodulesurdu);
                    }
                    else{
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.subjectmodulespushto);
                    }
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    audioForLearningMode.setImageResource(R.drawable.speaker);
                }
            }
        });
        writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action=LearningModeDirections.actionLearningModeToLectures(Subjects,LanguageForLearningMode,"Writting");
                navController.navigate(action);
                mediaPlayer.release();
            }
        });
        reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action=LearningModeDirections.actionLearningModeToLectures(Subjects,LanguageForLearningMode,"Reading");
                navController.navigate(action);
                mediaPlayer.release();
            }
        });
    }
    public void animmat(){
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.scaleforsubjectlearningmode);
        if (countForReading==0 && countForWriting==0) {
            animation.setAnimationListener(this);
            writing.setAnimation(animation);
            countForReading=1;
            countForWriting=1;


        }
        else {
            animation.setAnimationListener(this);
            reading.setAnimation(animation);
            countForWriting=0;
            countForReading=0;

        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (countForLoopLearning==3) {
            countForLoopLearning=0;
            return;
        }
        countForLoopLearning+=1;
        animmat();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}