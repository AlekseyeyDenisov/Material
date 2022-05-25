package ru.dw.material.view.picture

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.dw.material.R
import ru.dw.material.databinding.FragmentPictureOfTheDayBinding
import ru.dw.material.view.MainActivity
import ru.dw.material.view.PictureOfTheDayAppState


class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding
        get() = _binding!!
    private val viewModel: PictureOfTheDayFragmentViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayFragmentViewModel::class.java]
    }


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

    }

    private fun menuActionBar() {
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }


    private fun chipGroup() {
        binding.today.setOnClickListener {
            viewModel.sendRequest(0)
        }
        binding.yesterday.setOnClickListener {
            viewModel.sendRequest(1)
        }
        binding.tdby.setOnClickListener {
            viewModel.sendRequest(2)
        }
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
                Log.d("@@@", "onOptionsItemSelected app_bar_settings: ")
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
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