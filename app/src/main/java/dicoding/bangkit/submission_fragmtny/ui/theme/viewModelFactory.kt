package dicoding.bangkit.submission_fragmtny.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dicoding.bangkit.submission_fragmtny.ui.Main.mainViewmodel

class viewModelFactory(private val pref: settingpreference) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(mainViewmodel::class.java)) {
            return mainViewmodel(pref) as T
        }else if (modelClass.isAssignableFrom(themeViewModel::class.java)){
            return themeViewModel(pref)as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}