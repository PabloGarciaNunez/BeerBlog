package com.example.blogdelcervecero.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.example.blogdelcervecero.R

class LoadingView : RelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        inflate(context, R.layout.view_loading, this)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    /**
     * Show loading view
     */
    fun show() {

        visibility = VISIBLE
    }

    /**
     * Hide loading view
     */
    fun hide() {

        visibility = GONE
    }

}