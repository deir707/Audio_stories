package com.unipi.diodeir.unipi_audio_stories;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class TTS {
    private TextToSpeech tts;
    private TextToSpeech.OnInitListener initListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if (status==TextToSpeech.SUCCESS)
                tts.setLanguage(Locale.getDefault());
            }
    };
    public TTS(Context context){
        tts = new TextToSpeech(context,initListener);
    }
    public void speak(String message){
        tts.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
    }
    public void stop(){
        tts.stop();
    }
}
