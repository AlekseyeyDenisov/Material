package ru.dw.material.view.nasa.earth.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.dw.material.BuildConfig
import ru.dw.material.MyApp
import ru.dw.material.R
import ru.dw.material.databinding.ItemPhotoBinding
import ru.dw.material.dto.ResponseEarth
import ru.dw.material.repository.PictureOfTheDayRetrofitImpl.NASA_BASE_URL

class HolderAdapterPhotoItem(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(responseEarth: ResponseEarth) {
        val pathUrl = MyApp.pref.getTDateDayUrl()
        ItemPhotoBinding.bind(itemView).apply {
            //val url = "${NASA_BASE_URL}EPIC/archive/natural/2022/05/01/png/${responseEpic.image}.png?api_key=${BuildConfig.NASA_API_KEY}"
            val url = "${NASA_BASE_URL}EPIC/archive/natural/$pathUrl/png/${responseEarth.image}.png?api_key=${BuildConfig.NASA_API_KEY}"
            photoItem.load(url) { placeholder(R.drawable.loadig) }

        }

    }

}