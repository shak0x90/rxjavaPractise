package com.example.rxjavapractise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjavapractise.adapter.AlbumAdapter
import com.example.rxjavapractise.api.RetrofiltRequset
import com.example.rxjavapractise.databinding.ActivityMainBinding
import com.example.rxjavapractise.datamodel.albumResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AlbumAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        adapter = AlbumAdapter(this, ArrayList<albumResponse>())
        binding.ViewRecylerID.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapter
        }
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            getObserable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> getObserser(response as ArrayList<albumResponse>) },
                    { t -> onfailure(t) })
        )

    }

    private fun getObserable(): Observable<List<albumResponse>> {
        return RetrofiltRequset.api.getalbum()

    }

    private fun getObserser(albumList: ArrayList<albumResponse>) {
        if (albumList.size > 0) {
            adapter.setData(albumList)
        }


    }

    private fun onfailure(t: Throwable) {
        Toast.makeText(applicationContext, "$t", Toast.LENGTH_SHORT).show()
    }
}