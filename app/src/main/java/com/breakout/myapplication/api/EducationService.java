package com.breakout.myapplication.api;


import com.breakout.myapplication.model.song_text;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EducationService {

    //endpoint = https://www.udemy.com/api-2.0/
//    @GET("courses/?page_size=20")
////    Observable<Example> getUdemyResponse(@Query("page") int pageNumber);
////
////    @GET("courses/{course_id}/reviews/")
////    Observable<ReviewsResponse> getUdemyReviews(@Path("course_id") Integer courseId);

    @GET("courses/{event_id}/reviews/")
    Observable<song_text> getSongText(@Path("event_id") Integer eventID);





}
