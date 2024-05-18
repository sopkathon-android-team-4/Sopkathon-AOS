import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.sopkathon_aos.data.ServiceModule
import com.sopt.sopkathon_aos.data.request.RequestConcernsDto
import com.sopt.sopkathon_aos.data.response.ResponseConcernsDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _letterText = MutableLiveData("")
    val letterText: LiveData<String> get() = _letterText

    private val _isButtonEnabled = MutableLiveData<Boolean>(false)
    val isButtonEnabled: LiveData<Boolean> get() = _isButtonEnabled

    fun onTextInput(input: CharSequence) {
        _letterText.value = input.toString()

        _isButtonEnabled.value = input.toString().isNotBlank()
    }

    fun onPostConcerns(request: RequestConcernsDto) {
        ServiceModule.concernsService.postConcerns(request = request).enqueue(
            object : Callback<ResponseConcernsDto> {
                override fun onResponse(
                    call: Call<ResponseConcernsDto>,
                    response: Response<ResponseConcernsDto>,
                ) {
                    if (response.isSuccessful) {
                        Log.e("Test", response.body().toString())

                    } else {
                        val error = response.code()
                        Log.e("Test", error.toString())

                    }
                }

                override fun onFailure(call: Call<ResponseConcernsDto>, t: Throwable) {
                    Log.e("Test", t.toString())
                }
            }
        )
    }
}
