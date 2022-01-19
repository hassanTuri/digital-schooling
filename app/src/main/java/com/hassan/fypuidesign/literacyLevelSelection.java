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
 * Use the {@link literacyLevelSelection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class literacyLevelSelection extends Fragment implements Animation.AnimationListener {
    MediaPlayer mediaPlayer;
    String LanguageSel;
    int fullIlliterate=0;
    int semiIlliterate=0;
    int countForLoopLiteracy=0;
    ImageView semiliterate;
    ImageView full_illiterate;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public literacyLevelSelection() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment literacyLevelSelection.
     */
    // TODO: Rename and change types and number of parameters
    public static literacyLevelSelection newInstance(String param1, String param2) {
        literacyLevelSelection fragment = new literacyLevelSelection();
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
        return inflater.inflate(R.layout.fragment_literacy_level_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String LanguageSelect=literacyLevelSelectionArgs.fromBundle(getArguments()).getLanguageselection();
        String LanguageBackFromSubjects=literacyLevelSelectionArgs.fromBundle(getArguments()).getLanguageselection();
        NavController navController= Navigation.findNavController(view);
        ImageView back_lang=view.findViewById(R.id.back_lang);
        semiliterate=view.findViewById(R.id.semiliterate);
        full_illiterate=view.findViewById(R.id.full_illiterate);
        ImageView audioLiteracyLevel=view.findViewById(R.id.audioLiteracyLevel);
        animmi();
        if(LanguageSelect!=null) {
            LanguageSel = LanguageSelect;

            if (LanguageSel == "PashtoL") {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.illetracylevelselectionpushto);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                } else {
                    mediaPlayer.release();
                }
            } else {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.illiteracylevelselectionurdu);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                } else {
                    mediaPlayer.release();
                }

            }
        }
        else {
            LanguageSel=LanguageBackFromSubjects;
            if (LanguageSel == "PashtoL") {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.illetracylevelselectionpushto);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                } else {
                    mediaPlayer.release();
                }
            } else {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.illiteracylevelselectionurdu);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                } else {
                    mediaPlayer.release();
                }

            }

        }

        audioLiteracyLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer!=null){
                    mediaPlayer.release();
                    mediaPlayer=null;
                    audioLiteracyLevel.setImageResource(R.drawable.mutedaudio);
                }
                else {
                    if(LanguageSel=="UrduL") {
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.illiteracylevelselectionurdu);
                    }
                    else{
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.illetracylevelselectionpushto);
                    }
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    audioLiteracyLevel.setImageResource(R.drawable.speaker);
                }
            }
        });
        back_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOptions=new NavOptions.Builder().setPopUpTo(R.id.languageSelectionFragment,true).build();
                navController.navigate(R.id.action_literacyLevelSelection_to_languageSelectionFragment,null,navOptions);
                mediaPlayer.release();
            }
        });
        semiliterate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action;
                if(LanguageSel=="UrduL") {
                    action = literacyLevelSelectionDirections.actionLiteracyLevelSelectionToSubjects("UrduL", "SemiIlliterate");
                }
                else{
                    action = literacyLevelSelectionDirections.actionLiteracyLevelSelectionToSubjects("PashtoL", "SemiIlliterate");
                }
                navController.navigate(action);
                mediaPlayer.release();
            }
        });
        full_illiterate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action;
                if(LanguageSel=="UrduL") {
                 action = literacyLevelSelectionDirections.actionLiteracyLevelSelectionToSubjects("UrduL", "FullyIlliterate");

                }
                else {
                   action  = literacyLevelSelectionDirections.actionLiteracyLevelSelectionToSubjects("PashtoL", "FullyIlliterate");
                }
                navController.navigate(action);
                mediaPlayer.release();
            }
        });
    }

    public void animmi(){
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.scaleanimationforliteracyselection);
        if (semiIlliterate==0 && fullIlliterate==0) {
            animation.setAnimationListener(this);
            full_illiterate.setAnimation(animation);
            fullIlliterate=1;
            semiIlliterate=1;


        }
        else {
            animation.setAnimationListener(this);
           semiliterate.setAnimation(animation);
            semiIlliterate=0;
            fullIlliterate=0;

        }
    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (countForLoopLiteracy==3) {
            countForLoopLiteracy=0;
            return;
        }
        countForLoopLiteracy+=1;
        animmi();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}