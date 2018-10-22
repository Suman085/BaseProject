package com.mic.debrismanagement.api.services;

import com.mic.debrismanagement.mvp.model.User;
import com.mic.debrismanagement.utils.ApiEndPoints;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Suman
 */

public interface AuthenticationService {

    @POST(ApiEndPoints.AUTHENTICATION)
    @FormUrlEncoded
    Observable<User> authenticate(@Field("username") String username,
                                  @Field("password") String password);

}
