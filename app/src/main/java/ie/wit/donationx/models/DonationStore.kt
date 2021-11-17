package ie.wit.donationx.models

import androidx.lifecycle.MutableLiveData

interface DonationStore {
    fun findAll(donationsList: MutableLiveData<List<DonationModel>>)
    fun findById(id: Long) : DonationModel?
    fun create(donation: DonationModel)
}