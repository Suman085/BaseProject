package com.mic.rims.api.services;

import com.mic.rims.mvp.model.User;
import com.mic.rims.utils.ApiEndPoints;

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
    Observable<User> authenticate(@Field("usr") String username,
                                  @Field("pass") String password);

}
