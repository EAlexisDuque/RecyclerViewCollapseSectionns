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
import com.example.collapsingsection.R
import com.example.collapsingsection.adapters.RequirementAdapter
import com.example.collapsingsection.models.RequirementItem

/**
 * @author Alexis Duque on 2019-05-20.
 * @company Condor Labs
 * @email eduque@condorlabs.io
 */
class RequirementItemViewHolder(
    inflater: LayoutInflater, private val parent: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.requirement_item_template, parent, false)) {

    //<editor-fold desc="VARIABLES">
    private var tvReqTag: TextView? = null
    private var tvReqTitle: TextView? = null
    private var tvReqSubtitle: TextView? = null
    private var tvFooter: TextView? = null
    private var rvReqList: RecyclerView? = null
    private var ivFooter: ImageView? = null
    private var requirementsHeight = 0
    private var elementsAreCollapsing = false
    //</editor-fold>

    init {
        tvReqTag = itemView.findViewById(R.id.requirementItemTagTextView)
        tvReqTitle = itemView.findViewById(R.id.requirementSectionTitleTextView)
        tvReqSubtitle = itemView.findViewById(R.id.requirementSectionSubtitleTextView)
        rvReqList = itemView.findViewById(R.id.requirementsListRecyclerView)
        ivFooter = itemView.findViewById(R.id.footerImageView)
        tvFooter = itemView.findViewById(R.id.footerTextView)
    }

    fun bind(requirementItem: RequirementItem) {
        tvReqTag?.text = requirementItem.itemTag
        tvReqTitle?.text = requirementItem.title
        tvReqSubtitle?.text = requirementItem.subtitle
        tvFooter?.text = "Show all elements"

        rvReqList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RequirementAdapter(requirementItem.requirementsList)
        }

        (parent.context as? AppCompatActivity)?.windowManager?.defaultDisplay?.let { display ->
            rvReqList?.measure(display.width, display.height)
            requirementsHeight = rvReqList?.measuredHeight ?: 0
        }

        if (requirementItem.startCollapsed) {
            elementsAreCollapsing = true
            updateRecyclerViewHeight()
            rotateRowIndicator()
        }


        ivFooter?.setOnClickListener { collapseElements(requirementsHeight) }
    }

    private fun collapseElements(requirementsHeight: Int) {
        if (elementsAreCollapsing) {
            collapseElements(0, requirementsHeight, false)
        } else {
            collapseElements(requirementsHeight, 0, true)
        }

        elementsAreCollapsing = !elementsAreCollapsing
    }

    private fun collapseElements(from: Int, to: Int, isCollapsed: Boolean) {
        tvFooter?.text = if (isCollapsed) "Show all elements" else "Hide"

        val animator = ValueAnimator.ofInt(from, to).setDuration(300)?.apply {
            addUpdateListener {
                updateRecyclerViewHeight(it.animatedValue as Int)
                rotateRowIndicator(
                    rotationValue(
                        it.animatedValue as Int,
                        if (from > to) from else to
                    )
                )
            }
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

    private fun updateRecyclerViewHeight(height: Int = 0) {
        rvReqList?.apply {
            layoutParams?.height = height
            requestLayout()
        }
    }

    private fun rotateRowIndicator(rotation: Float = 0f) {
        ivFooter?.rotation = -rotation
    }

    private fun rotationValue(currentValue: Int, limit: Int): Float {
        return (180f * ((currentValue * 100f) / limit)) / 100f
    }
}
