package com.example.collapsingsection.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.collapsingsection.adapters.viewholders.RequirementItemViewHolder
import com.example.collapsingsection.models.RequirementItem

/**
 * @author Alexis Duque on 2019-05-20.
 * @company Condor Labs
 * @email eduque@condorlabs.io
 */
class RequirementSectionAdapter(
    private val requirementItemList: List<RequirementItem>
) : RecyclerView.Adapter<RequirementItemViewHolder>() {

    private var collapseElementsListener: CollapseElementsListener? = null
    private var elementsAreCollapsing = false
    private var limitToCollapseElements: Int = 0
    private var sumOfHeightElements: Int = 0
    private var elementsLoaded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequirementItemViewHolder {
        return RequirementItemViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holderItem: RequirementItemViewHolder, position: Int) {
        holderItem.bind(requirementItemList[position], this){ height ->
            if (!elementsLoaded) {
                sumOfHeightElements += height ?: 0

                if (position == requirementItemList.size - 1) elementsLoaded = true
            }
        }
    }

    override fun getItemCount(): Int = requirementItemList.size

    fun setCollapseElementsListener(collapseElementsListener: CollapseElementsListener) {
        this.collapseElementsListener = collapseElementsListener
    }

    fun collapseElements() {
        collapseElementsListener?.let {
            if (elementsAreCollapsing) {
                it.collapseElements(limitToCollapseElements, sumOfHeightElements, false)
            } else {
                it.collapseElements(sumOfHeightElements, limitToCollapseElements, true)
            }

            elementsAreCollapsing = !elementsAreCollapsing
        }
    }

    interface CollapseElementsListener {

        fun collapseElements(from: Int, to: Int, isCollapsed: Boolean)
    }
}
