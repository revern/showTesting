package com.example.almaz.showtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almaz.showtesting.models.PostsResponse;
import com.example.almaz.showtesting.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    List list = new ArrayList();
    User user;
    public void setList(List list){

    }
    @BindView(R.id.main_username_tv) TextView mUserNameTv;

    private final String[] mScope = new String[]{
            VKScope.FRIENDS,
            VKScope.WALL,
            VKScope.GROUPS,
            VKScope.DIRECT,
            VKScope.PHOTOS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeVKSdk();
        user = new User("Linar", "Ogay");
    }

    @OnClick(R.id.main_username_tv) void onClick(){
        refreshNews();
    }

    public String getUserName(){
        return "Almaz";
    }

    public String getUserName(String lastName){
        return "Almaz " + lastName;
    }

    private void initializeVKSdk(){
        VKSdk.wakeUpSession(this, new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {
                VKSdk.login(MainActivity.this, mScope);
            }

            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(), "Wake up error", Toast.LENGTH_LONG).show();
            }
        });
    }

    //load first pack of news/posts
    public void refreshNews(){
        VKRequest request =
                new VKRequest("newsfeed.get", VKParameters.from(
                        VKApiConst.FILTERS, "post",
                        VKApiConst.FIELDS, "text"));

        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                Gson mGson = new GsonBuilder().create();

                PostsResponse postsResponse = mGson
                        .fromJson(response.responseString, PostsResponse.class);
                PostsResponse.Response.Item[] posts = postsResponse.response.items;

                mUserNameTv.setText(posts[0].date + "");
//                mGroups.clear();
//                mProfiles.clear();
//                takeAuthors(postsResponse);
//                mPosts.clear();
//                for(int i=0;i<posts.length;i++){
//                    mPosts.add(posts[i]);
//                }
//                lastPostInfo = postsResponse.response.next_from;
//                refreshAdapter();
            }
        });
    }
}
