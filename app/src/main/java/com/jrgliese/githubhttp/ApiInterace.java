package com.jrgliese.githubhttp;

import android.provider.ContactsContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jörg.Liese on 16.06.2017.
 */

public interface ApiInterace {
    @GET("/organizations")
    Call<List<Organization>> orgList();
}
