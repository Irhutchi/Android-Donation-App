package ie.wit.donationx.main

import android.app.Application
//import ie.wit.donationx.models.DonationMemStore
import ie.wit.donationx.models.DonationStore
import timber.log.Timber

class DonationXApp : Application() {

    lateinit var donationsStore: DonationStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
//        donationsStore = DonationMemStore() // create donationStore
        Timber.i("DonationX Application Started")
    }
}