import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _letterText = MutableLiveData("")
    val letterText: LiveData<String> get() = _letterText

    private val _isButtonEnabled = MutableLiveData<Boolean>(false)
    val isButtonEnabled: LiveData<Boolean> get() = _isButtonEnabled

    fun onTextInput(input: CharSequence) {
        _letterText.value = input.toString()

        _isButtonEnabled.value = input.toString().isNotBlank()
    }
}
