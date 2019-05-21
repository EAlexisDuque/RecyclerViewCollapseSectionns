package com.example.collapsingsection.adapters

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet

/**
 * @author Alexis Duque on 2019-05-20.
 * @company Condor Labs
 * @email eduque@condorlabs.io
 */
class CustomLinearLayoutManager : LinearLayoutManager {

    private var isScrollEnabled = true

    constructor(context: Context) : super(context)

    constructor(
        context: Context, orientation: Int, reverseLayout: Boolean
    ) : super(context, orientation, reverseLayout)

    constructor(
        context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    fun setScrollEnabled(isScrollEnabled: Boolean) {
        this.isScrollEnabled = isScrollEnabled
    }

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollHorizontally()
    }
}
