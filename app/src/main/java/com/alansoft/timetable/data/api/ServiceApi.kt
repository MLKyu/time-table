package com.alansoft.timetable.data.api

import com.alansoft.timetable.data.request.MemoRequest
import com.alansoft.timetable.data.request.TimeTableRequest
import com.alansoft.timetable.data.response.LecturesResponse
import com.alansoft.timetable.data.response.MemoResponse
import com.alansoft.timetable.data.response.MsgResponse
import com.alansoft.timetable.data.response.TimeTableResponse
import retrofit2.http.*

/**
 * Created by LEE MIN KYU on 2021/06/11
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
interface ServiceApi {
    //    GET /lectures -> 전체 강의 목록을 반환합니다.
    @GET("/v1/programmers/lectures")
    suspend fun lectures(): LecturesResponse

    //    GET /lectures?code={강의코드} -> code 값과 동일한 강의 정보가 반환됩니다.
    @GET("/v1/programmers/lectures")
    suspend fun lecture(@Query("code") code: String): LecturesResponse

    //    GET /lectures?lecture={강의이름} -> lecture로 시작하는 과목명을 모두 반환합니다. (대소문자 구분)
    @GET("/v1/programmers/lectures")
    suspend fun lectures(@Query("lecture") lecture: String): LecturesResponse


    //    GET /timetable?user_key={사용자 ID 토큰} -> user_key로 등록 했던 강의 코드를 모두 반환
    @GET("/v1/programmers/timetable")
    suspend fun timetable(@Query("user_key") userKey: String): TimeTableResponse

    //    POST /timetable -> 사용자가 새로운 강의 코드를 추가합니다.
    @POST("/v1/programmers/timetable")
    suspend fun insertTimetable(@Body request: TimeTableRequest): MsgResponse

    //    DELETE /timetable -> 사용자의 추가된 강의 코드를 삭제합니다.
    @DELETE("/v1/programmers/timetable")
    suspend fun deleteTximetable(@Body request: TimeTableRequest): MsgResponse

    //    GET /memo?user_key={사용자 ID 토큰} -> 작성한 모든 메모를 조회할 수 있습니다.
    @GET("/v1/programmers/memo")
    suspend fun memo(@Query("user_key") userKey: String): MemoResponse

    //    GET /memo?user_key={사용자 ID 토큰}&code={강의 코드} -> code 이름의 특정 강의 메모만 조회할 수 있습니다.
    @GET("/v1/programmers/memo")
    suspend fun memo(
        @Query("user_key") userKey: String, @Query("code") code: String
    ): MemoResponse

    //    POST /memo -> 특정 강의에 메모를 추가 할 수 있습니다.
    @POST("/v1/programmers/memo")
    suspend fun insertTimetable(@Body request: MemoRequest)

    //    DELETE /memo -> 특정 강의의 메모를 삭제 할 수 있습니다.
    @DELETE("/v1/programmers/memo")
    suspend fun deleteTimetable(@Body request: MemoRequest)

}