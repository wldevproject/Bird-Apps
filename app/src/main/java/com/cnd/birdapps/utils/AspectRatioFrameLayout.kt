package com.cnd.birdapps.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.cnd.birdapps.R

/**
 ** Written by CND_Studio 18/03/2021 12.52.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
class AspectRatioFrameLayout : FrameLayout {
    private var mAspectRatioWidth: Int = 0
    private var mAspectRatioHeight: Int = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        Init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

        Init(context, attrs)
    }


    private fun Init(context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.FixedAspectRatioFrameLayout)

        mAspectRatioWidth = a.getInt(R.styleable.FixedAspectRatioFrameLayout_aspectRatioWidth, 4)
        mAspectRatioHeight = a.getInt(R.styleable.FixedAspectRatioFrameLayout_aspectRatioHeight, 3)

        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val originalWidth = MeasureSpec.getSize(widthMeasureSpec)

        val originalHeight = MeasureSpec.getSize(heightMeasureSpec)

        val calculatedHeight = originalWidth * mAspectRatioHeight / mAspectRatioWidth

        val finalWidth: Int
        val finalHeight: Int

        if (calculatedHeight > originalHeight) {
            finalWidth = originalHeight * mAspectRatioWidth / mAspectRatioHeight
            finalHeight = originalHeight
        } else {
            finalWidth = originalWidth
            finalHeight = calculatedHeight
        }

        super.onMeasure(
            MeasureSpec.makeMeasureSpec(finalWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(finalHeight, MeasureSpec.EXACTLY))
    }


}