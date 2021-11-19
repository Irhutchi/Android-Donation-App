package ie.wit.donationx.models


import androidx.lifecycle.MutableLiveData
import ie.wit.donationx.api.DonationClient
import ie.wit.donationx.api.DonationWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}


object DonationManager : DonationStore {

    var donations = ArrayList<DonationModel>()

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

    override fun findById(id:String) : DonationModel? {
        val foundDonation: DonationModel? = donations.find { it._id == id }
        return foundDonation
    }

    override fun create(donation: DonationModel) {

        val call = DonationClient.getApi().post(donation)

        call.enqueue(object : Callback<DonationWrapper> {
            override fun onResponse(call: Call<DonationWrapper>,
                                    response: Response<DonationWrapper>
            ) {
                val donationWrapper = response.body()
                if (donationWrapper != null) {
                    Timber.i("Retrofit ${donationWrapper.message}")
                    Timber.i("Retrofit ${donationWrapper.data.toString()}")
                }
            }

            override fun onFailure(call: Call<DonationWrapper>, t: Throwable) {
                Timber.i("Retrofit Error : $t.message")
            }
        })
    }

//    override fun delete(id: String) {
//        TODO("Not yet implemented")
//    }

    fun logAll() {
        Timber.v("** Donations List **")
        donations.forEach { Timber.v("Donate ${it}") }
    }
}