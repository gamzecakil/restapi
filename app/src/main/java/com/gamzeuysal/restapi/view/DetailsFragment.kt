package com.gamzeuysal.restapi.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gamzeuysal.restapi.PhoneSingleton
import com.gamzeuysal.restapi.R
import com.gamzeuysal.restapi.model.PhoneModel
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    private var argumentId = 0
    var selectedPhone :PhoneModel?=null
    private val TAG = "DetailsFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Singleton objeye kaydettiğimiz objeyi alalım
        selectedPhone = PhoneSingleton.choosenPhone
        selectedPhone?.let {
            /*
            Log.d(TAG,"id :${selectedPhone!!.id}")
            Log.d(TAG,"marka:${selectedPhone!!.brand}")
            Log.d(TAG,"model:${selectedPhone!!.model}")
            Log.d(TAG,"fiyat:${selectedPhone!!.price}")

            for(item in selectedPhone!!.color)
            {
                Log.d(TAG,"renkleri :${item}")
            }
            Log.d(TAG,"kamera ön: ${selectedPhone!!.camera.frontCameraPiksel}")
            Log.d(TAG,"kamera arka :${selectedPhone!!.camera.backCameraPiksel}")
            Log.d(TAG,"stok :${selectedPhone!!.stocks}")
            Log.d(TAG,"firma :${selectedPhone!!.firmName}")

             */
            selectedBrandTextView.text = selectedPhone!!.brand
            selectedModelTextView.text = selectedPhone!!.model
            selectedPriceTextView.text = selectedPhone!!.price.toString()

        }

        //fragment dan gelen id yi alalım
        //eger argument bos degilse
        arguments?.let {
            argumentId = DetailsFragmentArgs.fromBundle(it).id
            println(argumentId)
        }
    }
}