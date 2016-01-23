package io.hapi.android;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import io.indico.Indico;

public class App extends Application {

  public static Context sContext;

  @Override public void onCreate() {
    super.onCreate();
    sContext = this;

    Indico.init(this, "207ba815c45383ca43b774a8aab0f569", null);
  }

  public static void toast(String s) {
    Toast.makeText(sContext, s, Toast.LENGTH_SHORT).show();
  }
}
