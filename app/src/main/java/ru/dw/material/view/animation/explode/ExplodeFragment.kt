package ru.dw.material.view.animation.explode

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import ru.dw.material.R
import ru.dw.material.databinding.FragmentExplodeBinding

class ExplodeFragment : Fragment() {


    private var _binding: FragmentExplodeBinding? = null
    private val binding: FragmentExplodeBinding
        get() = _binding!!
    private var isOpen: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExplodeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = Adapter()


    }


    companion object {
        @JvmStatic
        fun newInstance() = ExplodeFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.recycler_item,
                    parent,
                    false
                ) as View
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {buttom->
                val epicenter = Rect()
                    buttom.getGlobalVisibleRect(epicenter)
                val transitionExplode = Explode()
                val transitionFade = Fade().addTarget(buttom)
                transitionFade.duration = 3000

                transitionExplode.epicenterCallback = object:Transition.EpicenterCallback(){
                    override fun onGetEpicenter(transition: Transition): Rect {
                        return  epicenter
                    }
                }
                transitionExplode.duration = 2000
                transitionExplode.excludeTarget(buttom,true)
                val transitionSet = TransitionSet()
                transitionSet.addTransition(transitionExplode)
                transitionSet.addTransition(transitionFade)

                TransitionManager.beginDelayedTransition(binding.recyclerView,transitionSet)
                binding.recyclerView.adapter = null

            }
        }

        override fun getItemCount(): Int {
            return 28
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}




