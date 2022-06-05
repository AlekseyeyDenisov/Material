package ru.dw.material.view.nasa.pictureoftheday


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import ru.dw.material.R
import ru.dw.material.databinding.FragmentPictureOfTheDayBinding
import ru.dw.material.utils.ConstantNasa.CONSTANT_VIDEO
import ru.dw.material.utils.getDaysAgo
import ru.dw.material.view.main.MainActivity
import ru.dw.material.view.nasa.pictureoftheday.novigation.viewpager.ViewPagerAdapter
import ru.dw.material.view.nasa.pictureoftheday.viewmodel.AppStateFragmentDay
import ru.dw.material.view.nasa.pictureoftheday.viewmodel.PictureOfTheDayFragmentViewModel


class PictureOfTheDayFragment : Fragment() {
    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding
        get() = _binding!!
    private val viewModel: PictureOfTheDayFragmentViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayFragmentViewModel::class.java]
    }
    private val TODAY_PICTURE = 0
    private val YESTERDAY_PICTURE = 1
    private val DAY_VIDEO = "2022-05-29"
    lateinit var  youTubePlayerView:YouTubePlayerView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initYouTube()
        setHasOptionsMenu(true)
        initViewModel()
        tabSelected()
        bottomSheet()
    }



    private fun initYouTube() {
        youTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)
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
                        viewModel.sendRequest(getDaysAgo(TODAY_PICTURE))
                    }
                    1 -> {
                        viewModel.sendRequest(getDaysAgo(YESTERDAY_PICTURE))
                    }
                    2 -> {
                        viewModel.sendRequest(DAY_VIDEO)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    private fun renderData(appStateFragmentDay: AppStateFragmentDay) {
        when (appStateFragmentDay) {
            is AppStateFragmentDay.Loading -> {
                visibilityLoading(true)
                    binding.loadingPicture
            }
            is AppStateFragmentDay.Error -> {
                visibilityLoading(false)
                Log.d("@@@", "renderData Error: ${appStateFragmentDay.error}")
                Toast.makeText(requireContext(), appStateFragmentDay.error, Toast.LENGTH_SHORT)
                    .show()

            }
            is AppStateFragmentDay.Success -> {

                visibilityLoading(false)
                if (appStateFragmentDay.responseDataItemDay.mediaType == CONSTANT_VIDEO) {
                    isVisibleVideo(true)
                    showNasaVideo(parseUrl(appStateFragmentDay.responseDataItemDay.url),true)
                } else {
                    isVisibleVideo(false)
                    showNasaVideo(parseUrl(appStateFragmentDay.responseDataItemDay.url),false)
                    binding.vewPage.adapter =
                        ViewPagerAdapter(this, appStateFragmentDay.responseDataItemDay)
                    binding.bottomSheetLayout.title.text =
                        appStateFragmentDay.responseDataItemDay.title
                    binding.bottomSheetLayout.explanation.text =
                        appStateFragmentDay.responseDataItemDay.explanation
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

    private fun isVisibleVideo(visible:Boolean) {
        if (visible){
            binding.tabLayoutViewPager.visibility = View.GONE
            binding.vewPage.visibility = View.GONE
            binding.youtubePlayerView.visibility = View.VISIBLE
        }else{
            binding.vewPage.visibility = View.VISIBLE
            binding.tabLayoutViewPager.visibility = View.VISIBLE
            binding.youtubePlayerView.visibility = View.GONE
        }

    }

    private fun parseUrl(idVideo: String): String =  idVideo.removeSurrounding(
            "https://www.youtube.com/embed/","?rel=0"
    )


    private fun showNasaVideo(videoId:String,isPlay:Boolean){
        youTubePlayerView.getYouTubePlayerWhenReady(object :YouTubePlayerCallback{
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                if (isPlay)youTubePlayer.loadVideo(videoId,0F)
                else youTubePlayer.pause()
            }
        })
    }


    private fun visibilityLoading(visibility: Boolean) {
        if (visibility) binding.loadingPicture.visibility = View.VISIBLE
        else binding.loadingPicture.visibility = View.GONE
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.sendRequest( getDaysAgo(TODAY_PICTURE))
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        youTubePlayerView.release()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheDayFragment()
    }
}