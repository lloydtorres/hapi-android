package io.hapi.android;

import android.net.Uri;
import android.support.annotation.Nullable;

import java.io.IOException;

import io.hapi.android.models.Emotions;
import io.indico.Indico;
import io.indico.network.IndicoCallback;
import io.indico.results.IndicoResult;
import io.indico.utils.IndicoException;
import rx.Observable;

public class IndicoApi {

  @Nullable public static Observable<Emotions> getEmotions(Uri imageUri) {
    return Observable.create(subscriber -> {
      try {
        Indico.fer.predict(imageUri, new IndicoCallback<IndicoResult>() {
          @Override public void handle(IndicoResult result) throws IndicoException {
            final Emotions emotions = Emotions.fromIndicoResult(result);
            if (emotions == null) {
              subscriber.onError(new Throwable("Failed to parse emotions from IndicoResult"));
            } else {
              subscriber.onNext(emotions);
            }
          }
        });
      } catch (IndicoException | IOException e) {
        subscriber.onError(e);
      }
    });
  }
}
