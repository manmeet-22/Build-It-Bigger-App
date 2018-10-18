package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.manmeet.jokefromandroidlibrary.JokesActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private static SimpleIdlingResource idlingResource;
    /** Variable used to save the joke received after the async task for the IdlingResource test */
    public String jokeReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public SimpleIdlingResource getIdlingResource() {
        if (idlingResource == null)
            idlingResource = new SimpleIdlingResource();
        return idlingResource;
    }

    @Override
    protected void onStart() {
        super.onStart();
        idlingResource = getIdlingResource();
    }

    public void tellJoke(View view) {
        new JokesAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
    }

    public class JokesAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

        private MyApi myApiService = null;
        private Context context;

        public JokesAsyncTask() {
        }

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;

            try {
                return myApiService.sayJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }

        }


        @Override
    protected void onPreExecute() {
        super.onPreExecute();
        idlingResource.setIdleState(false);
    }

        @Override
        protected void onPostExecute(String joke) {
            idlingResource.setIdleState(true);
            jokeReceived = joke;
            Intent intent = new Intent(MainActivity.this, JokesActivity.class);
            intent.putExtra("joke", joke);
            startActivity(intent);
        }
    }
}
