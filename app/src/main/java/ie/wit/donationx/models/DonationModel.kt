package ie.wit.donationx.models

import android.os.Parcelable
//import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class DonationModel(var id: Long = 0,
                         val paymenttype: String = "N/A",
                         val paymentmethod: String = "N/A",
                         val amount: Int = 0) : Parcelable

