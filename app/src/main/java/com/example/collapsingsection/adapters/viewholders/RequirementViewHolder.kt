package com.example.collapsingsection.adapters.viewholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.collapsingsection.R
import com.example.collapsingsection.models.Requirement

/**
 * @author Alexis Duque on 2019-05-20.
 * @company Condor Labs
 * @email eduque@condorlabs.io
 */
class RequirementViewHolder(
    inflater: LayoutInflater, parent: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.requirement_template, parent, false)) {

    private var tvReqTitle: TextView? = null
    private var tvReqSubtitle: TextView? = null

    init {
        itemView.apply {
            layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        tvReqTitle = itemView.findViewById(R.id.requirementTitleTextView)
        tvReqSubtitle = itemView.findViewById(R.id.requirementSubtitleTextView)
    }

    fun bind(requirement: Requirement) {
        tvReqTitle?.text = requirement.title
        tvReqSubtitle?.text = requirement.subtitle
    }
}
