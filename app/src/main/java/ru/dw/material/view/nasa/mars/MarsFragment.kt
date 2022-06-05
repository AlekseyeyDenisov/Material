package ru.dw.material.view.nasa.mars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dw.material.MyApp
import ru.dw.material.R
import ru.dw.material.databinding.FragmentListPhotoBinding
import ru.dw.material.utils.convertDateFormatApi
import ru.dw.material.utils.convertDateFormatUrlImages
import ru.dw.material.view.nasa.earth.components.DayPickersDate
import ru.dw.material.view.nasa.earth.components.OnDatePicker
import ru.dw.material.view.nasa.mars.recycler.AdapterPhotoItemMars
import ru.dw.material.view.nasa.mars.viewmodel.AppStateFragmentMars
import ru.dw.material.view.nasa.mars.viewmodel.MarsViewModel


class MarsFragment : Fragment() {
    private var _binding: FragmentListPhotoBinding? = null
    private val binding: FragmentListPhotoBinding get() = _binding!!
    private val viewModel: MarsViewModel by lazy {
        ViewModelProvider(this)[MarsViewModel::class.java]
    }
    private val adapterPhoto = AdapterPhotoItemMars()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecycler()
        initFab()
        initView()

    }

    private fun initView() {
        binding.datePhotosEarth.text = MyApp.pref.getTDateDayUrl()
    }

    private fun initFab() {
        binding.fabNewDateEpic.setOnClickListener {
            DayPickersDate(requireActivity()).materialDatePicker(object : OnDatePicker {
                override fun getResultDate(dateMiles: Long) {
                    MyApp.pref.setDateDayApi(convertDateFormatApi(dateMiles))
                    MyApp.pref.setDateDayUrl(convertDateFormatUrlImages(dateMiles))

                    viewModel.sendRequest()

                }
            })

        }
    }

    private fun initViewModel() {
        viewModel.sendRequest()
        viewModel.getLiveData().observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MarsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initRecycler() {
        binding.recyclerItem.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerItem.adapter = adapterPhoto
    }

    private fun renderData(appStateFragmentMars: AppStateFragmentMars) {
        when (appStateFragmentMars) {
            is AppStateFragmentMars.Loading -> {
                visibilityLoading(true)


            }
            is AppStateFragmentMars.Error -> {
                visibilityLoading(false)
                Toast.makeText(requireContext(), appStateFragmentMars.error, Toast.LENGTH_SHORT)
                    .show()

            }
            is AppStateFragmentMars.Success -> {
                visibilityLoading(false)
                if (appStateFragmentMars.responseDataListEarth.size > 0) {
                    val list = appStateFragmentMars.responseDataListEarth
                    adapterPhoto.submitList(list)
                    binding.datePhotosEarth.text = MyApp.pref.getTDateDayAPi()
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.no_photos_his_day),
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }
    }

    private fun visibilityLoading(visibility: Boolean) {
        if (visibility) binding.loadingPicture.visibility = View.VISIBLE
        else binding.loadingPicture.visibility = View.GONE
    }


}