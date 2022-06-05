package ru.dw.material.view.nasa.mars.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.dw.material.dto.PhotosItem

class PhotoItemMarsDiffUtilCallBack: DiffUtil.ItemCallback<PhotosItem>() {
    override fun areItemsTheSame(
        oldPhotoResponse: PhotosItem,
        newPhotoResponse: PhotosItem
    ): Boolean {
        return oldPhotoResponse.imgSrc == newPhotoResponse.imgSrc
    }

    override fun areContentsTheSame(
        oldPhotoResponse: PhotosItem,
        newPhotoResponse: PhotosItem
    ): Boolean {
        return oldPhotoResponse == newPhotoResponse
    }
}