package ru.dw.material.view.pictureoftheday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.dw.material.R
import ru.dw.material.databinding.FragmentPictureOfTheDayBinding
import ru.dw.material.utils.ConstantNasa.CONSTANT_VIDEO
import ru.dw.material.view.pictureoftheday.novigation.viewpager.ViewPagerAdapter


class PictureOfTheDayFragment : Fragment() {
    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding
        get() = _binding!!
    private val viewModel: PictureOfTheDayFragmentViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayFragmentViewModel::class.java]
    }
    private val TODAY_PICTURE = 0
    private val YESTERDAY_PICTURE = 1
    private val DAY_BEFOR_YESTERDAY_PICTURE = 2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initViewModel()
        tabSelected()
        bottomSheet()


    }

    private fun bottomSheet() {
        val bottomSheetBehavior =
            BottomSheetBehavior.from(binding.bottomSheetLayout.bottomSheetContainer)
        bottomSheetBehavior.setPeekHeight(300, true)
    }


    private fun tabSelected() {
        binding.tableLayoutDay.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        viewModel.sendRequest(TODAY_PICTURE)
                    }
                    1 -> {
                        viewModel.sendRequest(YESTERDAY_PICTURE)
                    }
                    2 -> {
                        viewModel.sendRequest(DAY_BEFOR_YESTERDAY_PICTURE)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    private fun renderData(pictureOfTheDayAppState: PictureOfTheDayAppState) {
        when (pictureOfTheDayAppState) {
            is PictureOfTheDayAppState.Loading -> {
                visibilityLoading(true)

            }
            is PictureOfTheDayAppState.Error -> {
                visibilityLoading(false)
                Log.d("@@@", "renderData Error: ${pictureOfTheDayAppState.error}")
                Toast.makeText(requireContext(), pictureOfTheDayAppState.error, Toast.LENGTH_SHORT)
                    .show()

            }
            is PictureOfTheDayAppState.Success -> {

                visibilityLoading(false)
                if (pictureOfTheDayAppState.responseDataItemDay.mediaType == CONSTANT_VIDEO) {
                    binding.tabLayoutViewPager.visibility = View.GONE
                    binding.vewPage.visibility = View.GONE
                    binding.imageView.visibility = View.VISIBLE
                    binding.imageView.apply {
                        load(R.drawable.video)
                        setOnClickListener {
                            startActivity(Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse(pictureOfTheDayAppState.responseDataItemDay.url)
                            })
                        }
                    }

                } else {
                    binding.vewPage.visibility = View.VISIBLE
                    binding.tabLayoutViewPager.visibility = View.VISIBLE
                    binding.imageView.visibility = View.GONE
                    binding.vewPage.adapter =
                        ViewPagerAdapter(this, pictureOfTheDayAppState.responseDataItemDay)
                    binding.bottomSheetLayout.title.text =
                        pictureOfTheDayAppState.responseDataItemDay.title
                    binding.bottomSheetLayout.explanation.text =
                        pictureOfTheDayAppState.responseDataItemDay.explanation
                    TabLayoutMediator(
                        binding.tabLayoutViewPager, binding.vewPage
                    ) { tab, position ->
                        when (position) {
                            0 -> {tab.text = getString(R.string.high_photo)}
                            1 -> {tab.text = getString(R.string.low_photo)}
                        }


                    }.attach()

                }

            }
        }
    }

    private fun visibilityLoading(visibility: Boolean) {
        if (visibility) binding.loadingPicture.visibility = View.VISIBLE
        else binding.loadingPicture.visibility = View.GONE
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.sendRequest(0)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheDayFragment()
    }
}