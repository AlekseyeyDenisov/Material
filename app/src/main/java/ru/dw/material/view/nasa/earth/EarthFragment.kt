package ru.dw.material.view.nasa.earth

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
import ru.dw.material.view.nasa.earth.recycler.AdapterPhotoItemNasa
import ru.dw.material.view.nasa.earth.viewmodel.AppStateFragmentEarth
import ru.dw.material.view.nasa.earth.viewmodel.EarthViewModel
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType


class EarthFragment : Fragment() {
    private var _binding: FragmentListPhotoBinding? = null
    private val binding: FragmentListPhotoBinding get() = _binding!!
    private val viewModel: EarthViewModel by lazy {
        ViewModelProvider(this)[EarthViewModel::class.java]
    }
    private val adapterPhoto = AdapterPhotoItemNasa()


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
        GuideView.Builder(requireContext())
            .setTitle(getString(R.string.select_day_button))
            .setContentText(getString(R.string.you_can_choose))
            .setDismissType(DismissType.anywhere) //optional - default DismissType.targetView
            .setTargetView(binding.fabNewDateEpic)
            .setContentTextSize(12) //optional
            .setTitleTextSize(14) //optional
            .build()
            .show()

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
        fun newInstance() = EarthFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initRecycler() {
        binding.recyclerItem.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerItem.adapter = adapterPhoto
    }

    private fun renderData(appStateFragmentEarth: AppStateFragmentEarth) {
        when (appStateFragmentEarth) {
            is AppStateFragmentEarth.Loading -> {
                visibilityLoading(true)
            }
            is AppStateFragmentEarth.Error -> {
                visibilityLoading(false)
                Toast.makeText(requireContext(), appStateFragmentEarth.error, Toast.LENGTH_SHORT)
                    .show()

            }
            is AppStateFragmentEarth.Success -> {
                visibilityLoading(false)
                if (appStateFragmentEarth.responseDataListEarth.size > 0) {
                    val list = appStateFragmentEarth.responseDataListEarth
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