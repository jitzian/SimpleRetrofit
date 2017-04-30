package test.simple.example.raian.com.org.verysimpleretrofit;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.simple.example.raian.com.org.verysimpleretrofit.constants.Constants;
import test.simple.example.raian.com.org.verysimpleretrofit.model.ResultGithub;
import test.simple.example.raian.com.org.verysimpleretrofit.rest.RestService;

public class MainActivity extends AppCompatActivity implements TextView.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mButton;
    private TextView mTextView;
    private EditText mEditText;

    private RestService restService;
    private List<ResultGithub>lstResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        setContent();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.endPoint)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restService = retrofit.create(RestService.class);
    }

    public void setContent(){
        Log.d(TAG, "setContent");
        mButton = (Button) findViewById(R.id.mButon);
        mTextView = (TextView) findViewById(R.id.mTextView);
        mEditText = (EditText) findViewById(R.id.mEditText);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mButon:
                Log.d(TAG, "Button Clicked::" + mEditText.getText());
                Call<List<ResultGithub>> lstRes = restService.getRepos(mEditText.getText().toString());
                lstRes.enqueue(new Callback<List<ResultGithub>>() {
                    @Override
                    public void onResponse(Call<List<ResultGithub>> call, Response<List<ResultGithub>> response) {
                        Log.d(TAG, "onResponse");
                        lstResponse = response.body();
                        Log.d(TAG, "onResponse::" + lstResponse);
                    }

                    @Override
                    public void onFailure(Call<List<ResultGithub>> call, Throwable t) {
                        Log.d(TAG, "onFailure:" + t.getMessage());
                    }
                });
                break;
        }
    }
}
