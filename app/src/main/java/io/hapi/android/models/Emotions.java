package io.hapi.android.models;

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import io.indico.enums.FacialEmotion;
import io.indico.results.IndicoResult;
import io.indico.utils.IndicoException;

public class Emotions {

  private final int happy, sad, angry, fear, surprise, neutral;

  private Emotions(int happy, int sad, int angry, int fear, int surprise, int neutral) {
    this.happy = happy;
    this.sad = sad;
    this.angry = angry;
    this.fear = fear;
    this.surprise = surprise;
    this.neutral = neutral;
  }

  @Nullable public static Emotions fromIndicoResult(IndicoResult indicoResult) {
    try {
      final Map<FacialEmotion, Double> map = indicoResult.getFer();
      final Map<FacialEmotion, Integer> intMap = new HashMap<>(map.size());
      for (FacialEmotion emotion : FacialEmotion.values()) {
        intMap.put(emotion, (int) (map.get(emotion) * 100));
      }

      int happy = intMap.get(FacialEmotion.Happy);
      int sad = intMap.get(FacialEmotion.Sad);
      int angry = intMap.get(FacialEmotion.Angry);
      int fear = intMap.get(FacialEmotion.Fear);
      int surprise = intMap.get(FacialEmotion.Surprise);
      int neutral = intMap.get(FacialEmotion.Neutral);

      return new Emotions(happy, sad, angry, fear, surprise, neutral);
    } catch (IndicoException e) {
      return null;
    }
  }

  public int getHappy() {
    return happy;
  }

  public int getSad() {
    return sad;
  }

  public int getAngry() {
    return angry;
  }

  public int getFear() {
    return fear;
  }

  public int getSurprise() {
    return surprise;
  }

  public int getNeutral() {
    return neutral;
  }
}
