package ru.dw.material.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import ru.dw.material.R
import ru.dw.material.databinding.FragmentPictureOfTheDayBinding
import ru.dw.material.view.MainActivity
import ru.dw.material.view.picture.bottonnonigation.BurgerBottomNavigationDrawerFragment
import ru.dw.material.view.picture.bottonnonigation.SettingsBottomNavigationDrawerFragment


class PictureOfTheDayFragment : Fragment() {
    private var isMain = true
    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding
        get() = _binding!!
    private val viewModel: PictureOfTheDayFragmentViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayFragmentViewModel::class.java]
    }
    private val TODAY_PICTURE = 0
    private val YESTERDAY_PICTURE = 1
    private val DAY_BEFOR_YESTERDAY_PICTURE = 2
    private val URL_WIKIPEDIA = "https://en.wikipedia.org/wiki/"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuActionBar()
        initViewModel()
        chipGroup()
        fabListener()
        searchWikipedia()

    }

    private fun searchWikipedia() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("$URL_WIKIPEDIA${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun fabListener() {
        val bottomSheetBehavior =
            BottomSheetBehavior.from(binding.bottomSheetLayout.bottomSheetContainer)
        binding.fab.setOnClickListener {
            if (isMain) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_null)

                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            } else {
                binding.bottomAppBar.navigationIcon = (ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_hamburger_menu_bottom_bar
                ))
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }
            isMain = !isMain
        }

    }

    private fun menuActionBar() {
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    private fun chipGroup() {
        binding.tableLayoutDay.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("@@@", "onTabSelected: ${tab?.position}")
                when(tab?.position){
                    0->{viewModel.sendRequest(TODAY_PICTURE)}
                    1->{viewModel.sendRequest(YESTERDAY_PICTURE)}
                    2->{viewModel.sendRequest(DAY_BEFOR_YESTERDAY_PICTURE)}
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.sendRequest(0)
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
                binding.imageView.load(pictureOfTheDayAppState.responseDataItemDay.url)
                binding.bottomSheetLayout.title.text =
                    pictureOfTheDayAppState.responseDataItemDay.title
                binding.bottomSheetLayout.explanation.text =
                    pictureOfTheDayAppState.responseDataItemDay.explanation
            }
        }
    }

    private fun visibilityLoading(visibility: Boolean) {
        if (visibility) binding.loadingPicture.visibility = View.VISIBLE
        else binding.loadingPicture.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> {
                Log.d("@@@", "onOptionsItemSelected app_bar_fav: ")
            }
            R.id.app_bar_settings -> {
                SettingsBottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")
            }
            android.R.id.home -> {
                BurgerBottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}