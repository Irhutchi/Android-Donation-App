package ie.wit.donationx.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.donationx.models.DonationManager
import ie.wit.donationx.models.DonationModel
import timber.log.Timber

class ReportViewModel : ViewModel() {

    private val donationsList = MutableLiveData<List<DonationModel>>()

    val observableDonationsList: LiveData<List<DonationModel>>
        get() = donationsList

    init {
        load()
    }

    fun load() {
        try {
            DonationManager.findAll(donationsList)
            Timber.i("Retrofit Success : $donationsList.value")
        }
        catch (e: Exception) {
            Timber.i("Retrofit Error : $e.message")
        }
    }
    fun delete(id: String) {
        try {
            DonationManager.delete(id)
            Timber.i("Retrofit Delete Success")
        }
        catch (e: java.lang.Exception) {
            Timber.i("Retrofit Delete Error : $e.message")
        }
    }
}