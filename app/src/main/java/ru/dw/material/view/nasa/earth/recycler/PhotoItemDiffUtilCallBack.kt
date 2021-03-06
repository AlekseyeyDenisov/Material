package ru.dw.material.view.nasa.earth.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.dw.material.dto.ResponseEarth

class PhotoItemDiffUtilCallBack: DiffUtil.ItemCallback<ResponseEarth>() {
    override fun areItemsTheSame(
        oldPhotoResponse: ResponseEarth,
        newPhotoResponse: ResponseEarth
    ): Boolean {
        return oldPhotoResponse.image == newPhotoResponse.image
    }

    override fun areContentsTheSame(
        oldPhotoResponse: ResponseEarth,
        newPhotoResponse: ResponseEarth
    ): Boolean {
        return oldPhotoResponse == newPhotoResponse
    }
}