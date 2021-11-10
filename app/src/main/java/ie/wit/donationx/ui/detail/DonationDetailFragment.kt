package ie.wit.donationx.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import ie.wit.donationx.R

class DonationDetailFragment : Fragment() {

    companion object {
        fun newInstance() = DonationDetailFragment()
    }

    private lateinit var viewModel: DonationDetailViewModel
    private val args by navArgs<DonationDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_donation_detail, container, false)

        Toast.makeText(context,"Donation ID Selected : ${args.donationid}",Toast.LENGTH_LONG).show()

        return view
    }


}