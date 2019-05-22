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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequirementItemViewHolder {
        return RequirementItemViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holderItem: RequirementItemViewHolder, position: Int) {
        holderItem.bind(requirementItemList[position])
    }

    override fun getItemCount(): Int = requirementItemList.size
}
