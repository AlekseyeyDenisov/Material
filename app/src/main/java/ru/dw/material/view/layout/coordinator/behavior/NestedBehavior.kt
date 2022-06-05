package ru.dw.material.view.layout.coordinator.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class NestedBehavior(
    context: Context,
    attrs: AttributeSet? = null
) : CoordinatorLayout.Behavior<View>(context, attrs) {


    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return (dependency is AppBarLayout || dependency is Button)
    }

    private var barHeight = 0
    private var startButtonY = 0F
    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {

        if (dependency is AppBarLayout) {

            child.y = dependency.height.toFloat() + dependency.y
            barHeight = dependency.height


        }
        if (dependency is Button) {
            if (startButtonY == 0F){
                startButtonY = dependency.y
            }
            dependency.x = child.y
            dependency.y =  startButtonY
            dependency.alpha = 2 - abs(2 * child.y) / barHeight.toFloat()
            //Log.d(TAG, "onDependentViewChanged startButtonY: ${( abs(2 * child.y) / barHeight.toFloat())}")
            //Log.d(TAG, "onDependentViewChanged dependency.y: ${dependency.y}")
            //Log.d(TAG, "onDependentViewChanged barHeight: ${dependency.y - barHeight}")
            //Log.d(TAG, "onDependentViewChanged dependency.y: ${dependency.y}")




        }


        return super.onDependentViewChanged(parent, child, dependency)
    }

}