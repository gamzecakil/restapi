package com.gamzeuysal.restapi.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamzeuysal.restapi.PhoneSingleton
import com.gamzeuysal.restapi.R
import com.gamzeuysal.restapi.adapter.RecyclerViewAdapter
import com.gamzeuysal.restapi.model.PhoneModel
import com.gamzeuysal.restapi.service.PhoneAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ListFragment : Fragment(),RecyclerViewAdapter.Listener{

    private  val TAG = "ListFragment"
    private val BASE_URL="http://192.168.1.105:3000"
    private var phoneLists:ArrayList<PhoneModel>?=null
    private var recyclerViewAdapter:RecyclerViewAdapter?=null
    var layoutManager:RecyclerView.LayoutManager?=null

    //Disposable
    private var compositeDisposable:CompositeDisposable?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize compositeDisposable
        compositeDisposable = CompositeDisposable()

        //RecyclerView

        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        loadData()
    }
    private  fun loadData()
    {
        //Observable
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(PhoneAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError{e-> Log.d("TAG","doOnError ${e.localizedMessage}")}
            .subscribe(this::handleResponse)

        )

    }
    //API 'den gelen cevabın değerlendirildiği handleResponse fonksiyonunu yazalım
    private fun handleResponse(phoneList:List<PhoneModel>)
    {
        //Apı den gelen veriler doluyor
        phoneLists = ArrayList(phoneList)

        //Apı den gelen tüm modelleri bastıralım
        for(item in phoneList)
        {
            Log.d(TAG,"id :${item.id}")
            Log.d(TAG,"marka:${item.brand}")
            Log.d(TAG,"model:${item.model}")
            Log.d(TAG,"fiyat:${item.price}")

            for (item1 in item.color)
            {
                Log.d(TAG,"renkleri :${item1}")
            }
            Log.d(TAG,"kamera ön: ${item.camera.frontCameraPiksel}")
            Log.d(TAG,"kamera arka :${item.camera.backCameraPiksel}")
            Log.d(TAG,"stok :${item.stocks}")
            Log.d(TAG,"firma :${item.firmName}")
            Log.d(TAG,"---------")
            Log.d(TAG,"resim url :${item.imageUrl}")

        }

        phoneLists?.let {
            //eger liste boş değilse
            recyclerViewAdapter = RecyclerViewAdapter(phoneLists!!,this@ListFragment,requireContext())
            recyclerView.adapter = recyclerViewAdapter
        }
    }

    override fun onItemClick(phoneModel: PhoneModel) {
        Toast.makeText(requireContext(),"Clicked :${phoneModel.model}",Toast.LENGTH_LONG).show()

        //Singleton objesine kaydedelim tıkalanan modeli kaydedelim.
        PhoneSingleton.choosenPhone = phoneModel
        //tıklananın id'sini gönderelim
        val action = ListFragmentDirections.actionListFragmentToDetailsFragment(phoneModel.id)
        var view = fragmentContainerView.view
        view?.let {
            fragmentContainerView.view?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}