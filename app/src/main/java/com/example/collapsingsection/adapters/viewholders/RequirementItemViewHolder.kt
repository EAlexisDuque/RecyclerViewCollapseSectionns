package com.example.collapsingsection.adapters.viewholders

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.collapsingsection.R
import com.example.collapsingsection.adapters.RequirementAdapter
import com.example.collapsingsection.adapters.RequirementSectionAdapter
import com.example.collapsingsection.models.RequirementItem

/**
 * @author Alexis Duque on 2019-05-20.
 * @company Condor Labs
 * @email eduque@condorlabs.io
 */
class RequirementItemViewHolder(
    inflater: LayoutInflater, private val parent: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.requirement_item_template, parent, false)),
    RequirementSectionAdapter.CollapseElementsListener {

    private var tvReqTag: TextView? = null
    private var tvReqTitle: TextView? = null
    private var tvReqSubtitle: TextView? = null
    private var rvReqList: RecyclerView? = null
    private var footer: ImageView? = null

    init {
        tvReqTag = itemView.findViewById(R.id.requirementItemTagTextView)
        tvReqTitle = itemView.findViewById(R.id.requirementSectionTitleTextView)
        tvReqSubtitle = itemView.findViewById(R.id.requirementSectionSubtitleTextView)
        rvReqList = itemView.findViewById(R.id.requirementsListRecyclerView)
        footer = itemView.findViewById(R.id.footerImageView)
    }

    fun bind(
        requirementItem: RequirementItem,
        requirementSectionAdapter: RequirementSectionAdapter? = null,
        heightOfItem: (height: Int?) -> Unit
    ) {
        requirementSectionAdapter?.setCollapseElementsListener(this)
        tvReqTag?.text = requirementItem.itemTag
        tvReqTitle?.text = requirementItem.title
        tvReqSubtitle?.text = requirementItem.subtitle
        footer?.setOnClickListener {
            requirementSectionAdapter?.collapseElements()
        }

        rvReqList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RequirementAdapter(requirementItem.requirementsList)
        }

        (parent.context as? AppCompatActivity)?.windowManager?.defaultDisplay?.let { display ->
            rvReqList?.measure(display.width, display.height)
            heightOfItem(rvReqList?.measuredHeight)
        }
    }

    override fun collapseElements(from: Int, to: Int, isCollapsed: Boolean) {
        val animator = ValueAnimator.ofInt(from, to).setDuration(300)?.apply {
            addUpdateListener { updateRecyclerViewHeight(it.animatedValue as Int) }
        } ?: return

        startValueAnimator(animator)
    }

    private fun startValueAnimator(valueAnimator: ValueAnimator) {
        AnimatorSet().apply {
            play(valueAnimator)
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }

    private fun updateRecyclerViewHeight(height: Int) {
        rvReqList?.apply {
            layoutParams?.height = height
            requestLayout()
        }
    }
}
