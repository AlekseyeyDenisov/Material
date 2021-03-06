package ru.dw.material.view.nasa.pictureoftheday.novigation.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.dw.material.dto.ResponseDataItemDay
import ru.dw.material.view.nasa.pictureoftheday.novigation.HighPictureFragment
import ru.dw.material.view.nasa.pictureoftheday.novigation.LowPictureFragment

class ViewPagerAdapter(fa: Fragment,responseDataItemDay: ResponseDataItemDay) : FragmentStateAdapter(fa) {

    private val fragment = arrayOf(
        HighPictureFragment.newInstance(HighPictureFragment.bundle(responseDataItemDay)),
        LowPictureFragment.newInstance(LowPictureFragment.bundle(responseDataItemDay))
    )

    override fun getItemCount(): Int  = fragment.size

    override fun createFragment(position: Int): Fragment = fragment[position]


}