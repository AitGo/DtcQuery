package com.xtool.dtcquery.http;

import com.xtool.dtcquery.entity.DtcDTO;
import com.xtool.dtcquery.entity.UserDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by xtool on 2017/8/21.
 */

public interface PostActivation {

    String BASE_URL = "http://192.168.52.1:8080/DtcQuery/";

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("app/queryDtcByDcodeJson.action")
    Observable<List<DtcDTO>> postDtcQuery(@Body DtcDTO dtcDTO);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("app/userLogin.action")
    Observable<List<UserDTO>> postUserLogin(@Body UserDTO userDTO);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("app/userLogout.action")
    Observable<List<UserDTO>> postUserLogout(@Body UserDTO userDTO);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("app/upasswordUpdate.action")
    Observable<List<UserDTO>> postEditPassword(@Body UserDTO userDTO);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("app/userinfoUpdate.action")
    Observable<List<UserDTO>> postEditUserInfo(@Body UserDTO userDTO);



}
