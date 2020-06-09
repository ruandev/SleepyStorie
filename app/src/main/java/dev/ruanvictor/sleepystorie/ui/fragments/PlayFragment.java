package dev.ruanvictor.sleepystorie.ui.fragments;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import dev.ruanvictor.sleepystorie.R;

import static java.lang.String.format;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private TextView textTimeLeft;
    private SeekBar seekBar;
    private AudioManager audioManager;

    public PlayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.amoras);
        mediaPlayer.start();

        seekBar = view.findViewById(R.id.seekBar);
        seekBar.postDelayed(updateSeekBar, 15);
        textTimeLeft = view.findViewById(R.id.textTimeLeft);
        Button buttonForward10 = view.findViewById(R.id.buttonForward10);
        Button buttonReplay10 = view.findViewById(R.id.buttonReplay10);
        Button buttonPlayPause = view.findViewById(R.id.buttonPlayPause);
        Button buttonVolumeUp = view.findViewById(R.id.buttonVolumeUp);
        Button buttonVolumeDown = view.findViewById(R.id.buttonVolumeDown);

        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        int durationInSeconds = mediaPlayer.getDuration() / 1000;
        textTimeLeft.setText(format("%d %s", durationInSeconds, "seconds left"));

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        buttonForward10.setOnClickListener(v -> {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+10000);
            seekBar.setProgress(seekBar.getProgress()+10000);
        });

        buttonReplay10.setOnClickListener(v -> {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-10000);
            seekBar.setProgress(seekBar.getProgress()-10000);
        });

        buttonPlayPause.setOnClickListener(v -> {
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                buttonPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_play_circle_filled_black_24dp));
            } else {
                mediaPlayer.start();
                buttonPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_pause_circle_filled_24));
            }
        });

        buttonVolumeUp.setOnClickListener(v -> {
            int currentVolum = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolum+1, AudioManager.FLAG_SHOW_UI);
        });

        buttonVolumeDown.setOnClickListener(v -> {
            int currentVolum = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolum-1, AudioManager.FLAG_SHOW_UI);
        });

        return view;
    }

    private Runnable updateSeekBar = new Runnable() {
        public void run() {
            int totalDuration = mediaPlayer.getDuration();
            int currentDuration = mediaPlayer.getCurrentPosition();

            int remainingInSeconds = (totalDuration-currentDuration)/1000;

            textTimeLeft.setText(format("%d %s", remainingInSeconds, "seconds"));

            seekBar.setProgress(currentDuration);

            seekBar.postDelayed(this, 15);
        }
    };

}
