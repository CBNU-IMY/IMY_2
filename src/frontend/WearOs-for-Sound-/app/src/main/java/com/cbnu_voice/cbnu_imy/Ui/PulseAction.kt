package com.cbnu_voice.cbnu_imy.Ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cbnu_voice.cbnu_imy.Api.Pulse.PulseBuilder
import com.cbnu_voice.cbnu_imy.Api.RetrofitBuilder
import com.cbnu_voice.cbnu_imy.Data.User
import com.cbnu_voice.cbnu_imy.Dto.Pulse.DailyPulseDto
import com.cbnu_voice.cbnu_imy.databinding.ActivityPulseBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityPulseBinding
class PulseAction : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPulseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.testGetPulse.setOnClickListener {     //회원목록 클릭시
            PulseList() //회원조회
        }


    }
    fun PostPulse(user: User){

    }

    /**
     * 리스트 형식으로 DailyPulseDto 형식에 맞는 데이터를 가져온다.
     */
    fun PulseList(){
        val textviewresult= binding.textViewResult
        val pulseList = PulseBuilder.pulseApi.getPulseResponse()
        pulseList.enqueue(object : Callback<List<DailyPulseDto>> { // 비동기 방식 통신 메소드
            override fun onResponse( // 통신에 성공한 경우
                call: Call<List<DailyPulseDto>>,
                response: Response<List<DailyPulseDto>>
            ) {
                if(response.isSuccessful()){ // 응답 잘 받은 경우
                    Log.d("RESPONSE: ", response.body().toString())
                    textviewresult.setText(response.body().toString())
                }else{
                    // 통신 성공 but 응답 실패
                    Log.d("RESPONSE", "FAILURE")
                }
            }

            override fun onFailure(call: Call<List<DailyPulseDto>>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }

        })
    }
}