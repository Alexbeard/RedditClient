package com.reddit.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import com.reddit.R


class StateErrorView : NestedScrollView {

    private lateinit var retryVg: LinearLayout
    private lateinit var imageView: ImageView
    private lateinit var messageTxt: TextView
    private var onRetryClickListener: OnRetryClickListener? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context) {
        View.inflate(context, R.layout.error_screen, this)
        retryVg = findViewById(R.id.retryVg)
        imageView = findViewById(R.id.image)
        messageTxt = findViewById(R.id.text)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        init(context)
        val typedArray = context.theme.obtainStyledAttributes(attrs,
                R.styleable.StateErrorView, 0, 0) ?: return
        try {
            val messageText = typedArray.getString(R.styleable.StateErrorView_state_error_message)
            val isSmall = typedArray.getBoolean(R.styleable.StateErrorView_state_error_small, false)
            val drawable = typedArray.getDrawable(R.styleable.StateErrorView_state_error_src)
            val gravity = typedArray.getInt(R.styleable.StateErrorView_state_error_gravity, 2)

            when (gravity) {
                1 -> retryVg.gravity = Gravity.CENTER
                2 -> retryVg.gravity = Gravity.CENTER or Gravity.TOP
            }
            if (isSmall) {
                imageView.visibility = View.GONE
            } else {
                imageView.visibility = View.VISIBLE
            }

            if (messageText != null) {
                messageTxt.text = messageText
            }
            if (drawable != null) {
                imageView.setImageDrawable(drawable)
            }
            retryVg.setOnClickListener {
                if (onRetryClickListener != null) {
                    onRetryClickListener!!.onRetryClicked()
                }
            }
        } finally {
            typedArray.recycle()
        }
        isFillViewport = true
    }

    private fun decreaseViewSize(view: View, factor: Int) {
        val width = view.layoutParams.width / factor
        val height = view.layoutParams.height / factor
        val layoutParams = view.layoutParams
        layoutParams.width = width
        layoutParams.height = height
        view.layoutParams = layoutParams
    }

    private fun setTextSize(textView: TextView, size: Int) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
    }

    fun setOnRetryClickListener(onRetryClickListener: OnRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener
    }

    interface OnRetryClickListener {
        fun onRetryClicked()
    }
}
