package ru.dw.material.view.earth.components

import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.MaterialDatePicker


interface OnDatePicker{
    fun getResultDate(dateMiles:Long)
}
class DayPickersDate(
    private val activity: FragmentActivity
) {
   fun materialDatePicker(onDatePicker: OnDatePicker){
        val dateRangePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select dates")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
        dateRangePicker.show(activity.supportFragmentManager, "tagDataPiker")
        dateRangePicker.addOnPositiveButtonClickListener {
            onDatePicker.getResultDate(it)
        }
    }

}