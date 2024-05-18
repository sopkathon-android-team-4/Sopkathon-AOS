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

    private val _isPostSuccessful = MutableLiveData<Boolean>()
    val isPostSuccessful: LiveData<Boolean> get() = _isPostSuccessful

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
                        _isPostSuccessful.value = true
                    } else {
                        val error = response.code()
                        _isPostSuccessful.value = false
                    }
                }

                override fun onFailure(call: Call<ResponseConcernsDto>, t: Throwable) {
                    _isPostSuccessful.value = false
                }
            }
        )
    }
}
