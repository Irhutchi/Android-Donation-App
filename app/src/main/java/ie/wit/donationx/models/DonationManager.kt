package ie.wit.donationx.models


import androidx.lifecycle.MutableLiveData
import ie.wit.donationx.api.DonationClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

object DonationManager : DonationStore {

    val donations = ArrayList<DonationModel>()

    override fun findAll(donationsList: MutableLiveData<List<DonationModel>>) {

        val call = DonationClient.getApi().getall()

        call.enqueue(object : Callback<List<DonationModel>> {
            override fun onResponse(call: Call<List<DonationModel>>,
                                    response: Response<List<DonationModel>>
            ) {
                donationsList.value = response.body() as ArrayList<DonationModel>
                Timber.i("Retrofit JSON = ${response.body()}")
            }

            override fun onFailure(call: Call<List<DonationModel>>, t: Throwable) {
                Timber.i("Retrofit Error : $t.message")
            }
        })
    }

    override fun findById(id:Long) : DonationModel? {
        val foundDonation: DonationModel? = donations.find { it.id == id }
        return foundDonation
    }

    override fun create(donation: DonationModel) {
        donation.id = getId()
        donations.add(donation)
        logAll()
    }

    fun logAll() {
        Timber.v("** Donations List **")
        donations.forEach { Timber.v("Donate ${it}") }
    }
}