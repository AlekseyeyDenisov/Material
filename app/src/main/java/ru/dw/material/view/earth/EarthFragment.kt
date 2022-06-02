package ru.dw.material.view.earth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dw.material.MyApp
import ru.dw.material.R
import ru.dw.material.databinding.FragmentEarthBinding
import ru.dw.material.utils.ConstantNasa.TAG
import ru.dw.material.utils.convertDateFormatApi
import ru.dw.material.utils.convertDateFormatUrlImages
import ru.dw.material.view.earth.components.DayPickersDate
import ru.dw.material.view.earth.components.OnDatePicker
import ru.dw.material.view.earth.recycler.AdapterPhotoItemNasa
import ru.dw.material.view.viewmodel.AppStateFragmentEpic
import ru.dw.material.view.viewmodel.EpicViewModel


class EarthFragment : Fragment() {
    private var _binding: FragmentEarthBinding? = null
    private val binding: FragmentEarthBinding get() = _binding!!
    private val viewModel: EpicViewModel by lazy {
        ViewModelProvider(this)[EpicViewModel::class.java]
    }
    private val adapterPhoto = AdapterPhotoItemNasa()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
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
            DayPickersDate(requireActivity()).materialDatePicker(object :OnDatePicker{
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

    private fun renderData(appStateFragmentEpic: AppStateFragmentEpic) {
        when (appStateFragmentEpic) {
            is AppStateFragmentEpic.Loading -> {
                //visibilityLoading(true)




            }
            is AppStateFragmentEpic.Error -> {
                //visibilityLoading(false)
                Log.d("@@@", "renderData Error: ${appStateFragmentEpic.error}")
                Toast.makeText(requireContext(), appStateFragmentEpic.error, Toast.LENGTH_SHORT)
                    .show()

            }
            is AppStateFragmentEpic.Success -> {
                Log.d(TAG, "renderData: " + appStateFragmentEpic.responseDataListEarth.size)
                if (appStateFragmentEpic.responseDataListEarth.isNotEmpty()){
                    val list = appStateFragmentEpic.responseDataListEarth
                    adapterPhoto.submitList(list)
                    binding.datePhotosEarth.text = MyApp.pref.getTDateDayAPi()
                }else{
                    Toast.makeText(requireContext(), getString(R.string.no_photos_his_day), Toast.LENGTH_LONG).show()
                }

            }
        }
    }


}