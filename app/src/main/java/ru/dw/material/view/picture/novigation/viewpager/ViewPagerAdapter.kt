package ru.dw.material.view.picture.novigation.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.dw.material.model.ResponseDataItemDay
import ru.dw.material.view.picture.novigation.HighPictureFragment
import ru.dw.material.view.picture.novigation.KEY_BUNDLE_PICTURE_HIGH
import ru.dw.material.view.picture.novigation.KEY_BUNDLE_PICTURE_LOW
import ru.dw.material.view.picture.novigation.LowPictureFragment

class ViewPagerAdapter(fm: FragmentManager, val responseDataItemDay: ResponseDataItemDay) :
    FragmentStatePagerAdapter(fm) {


    private fun bundleHigh():Bundle{
        val bundle = Bundle()
         bundle.putParcelable(KEY_BUNDLE_PICTURE_HIGH,responseDataItemDay)
        return bundle
    }
    private fun bundleLow():Bundle{
        val bundle = Bundle()
        bundle.putParcelable(KEY_BUNDLE_PICTURE_LOW,responseDataItemDay)
        return bundle
    }

    private val fragment = arrayOf(
        HighPictureFragment.newInstance(bundleHigh()),
        LowPictureFragment.newInstance(bundleLow())
    )

    override fun getCount(): Int {
        return fragment.size
    }

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "HIGH"
            else-> "LOW"
        }
    }

}