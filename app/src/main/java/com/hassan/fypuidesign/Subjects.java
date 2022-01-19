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
 * Use the {@link Subjects#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Subjects extends Fragment implements Animation.AnimationListener {
        MediaPlayer mediaPlayer;
        String LanguageFromSubOrLit;
    ImageView englishS ;
    ImageView urduS ;
    ImageView mathsS ;
    int countForSubjeU=0, countForSubE=0,countForSubM=0,countForLoopSub=0;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Subjects() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Subjects.
     */
    // TODO: Rename and change types and number of parameters
    public static Subjects newInstance(String param1, String param2) {
        Subjects fragment = new Subjects();
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
        return inflater.inflate(R.layout.fragment_subjects, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String LanguageSe=SubjectsArgs.fromBundle(getArguments()).getLanguageSelectedSubject();
        String Illiteracy=SubjectsArgs.fromBundle(getArguments()).getLiteracyLevel();
        String LanguageBackFromLearningMode=SubjectsArgs.fromBundle(getArguments()).getLanguageSelectedSubject();
        NavController navController = Navigation.findNavController(view);
         englishS = view.findViewById(R.id.englishS);
         urduS = view.findViewById(R.id.urduS);
         mathsS = view.findViewById(R.id.mathsS);
        ImageView back_language = view.findViewById(R.id.back_language);
        ImageView audioSubjectSelection = view.findViewById(R.id.audioSubjectSelection);
        animma();
        if (LanguageSe!=null) {
            LanguageFromSubOrLit=LanguageSe;
            if (LanguageSe == "UrduL") {
                if (mediaPlayer == null)
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.subjectselectionurdu);
                else
                    mediaPlayer.release();
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            } else {
                if (mediaPlayer == null)
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.subjectselectionpushto);
                else
                    mediaPlayer.release();
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        }
        else {
            LanguageFromSubOrLit=LanguageBackFromLearningMode;
            if (LanguageBackFromLearningMode == "UrduL") {
                if (mediaPlayer == null)
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.subjectselectionurdu);
                else
                    mediaPlayer.release();
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            } else {
                if (mediaPlayer == null)
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.subjectselectionpushto);
                else
                    mediaPlayer.release();
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }

        }

        audioSubjectSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer!=null){
                    mediaPlayer.release();
                    audioSubjectSelection.setImageResource(R.drawable.mutedaudio);
                }
                else {
                    if(LanguageSe=="UrduL") {
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.subjectselectionurdu);
                    }
                    else{
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.subjectselectionpushto);
                    }
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    audioSubjectSelection.setImageResource(R.drawable.speaker);
                }
            }
        });


        back_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               NavDirections action=SubjectsDirections.actionSubjectsToLiteracyLevelSelection(LanguageFromSubOrLit);
                NavOptions navOptions=new NavOptions.Builder().setPopUpTo(R.id.literacyLevelSelection,true).build();
                navController.navigate(action,navOptions);
                mediaPlayer.release();
            }
        });

        englishS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action=SubjectsDirections.actionSubjectsToLearningMode(LanguageFromSubOrLit,"EnglishS");
                navController.navigate(action);
                mediaPlayer.release();
            }
        });
        urduS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action=SubjectsDirections.actionSubjectsToLearningMode(LanguageFromSubOrLit,"UrduS");
                navController.navigate(action);
                mediaPlayer.release();
            }
        });
        mathsS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action=SubjectsDirections.actionSubjectsToLearningMode(LanguageFromSubOrLit,"MathsS");
                navController.navigate(action);
                mediaPlayer.release();
            }
        });
    }
        public void animma(){
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.scaleanimationforliteracyselection);
        if (countForSubM==0 && countForSubjeU==0 && countForSubE==0){
            animation.setAnimationListener(this);
            englishS.setAnimation(animation);
            countForSubE=1;
        }
        else if (countForSubM==0 && countForSubjeU==0 && countForSubE==1){
            animation.setAnimationListener(this);
            urduS.setAnimation(animation);
            countForSubjeU=1;
            countForSubE=0;
        }
        else {
            animation.setAnimationListener(this);
            mathsS.setAnimation(animation);
            countForSubjeU=0;
        }
        }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (countForLoopSub==5) {
            countForLoopSub=0;
            return;
        }
        countForLoopSub+=1;
        animma();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}