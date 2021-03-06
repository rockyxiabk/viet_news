package com.viet.news.core.ext

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.viet.news.core.R
import com.viet.news.core.config.BlurTransformation
import com.viet.news.core.config.GlideApp
import com.viet.news.core.config.GlideRequest
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * @Author Aaron
 * @Email aaron@magicwindow.cn
 * @Description
 */

/**
 * 占位符矩形
 */
fun ImageView.load(url: String?, placeholderRes: Int = R.drawable.shape_default_rec_bg, errorRes: Int = R.drawable.shape_default_rec_bg) {
    get(url).placeholder(placeholderRes)
            .error(errorRes)
            .into(this)
}

/**
 * 占位符圆角矩形
 */
fun ImageView.loadRound(url: String?, centerCrop: Boolean = false) {
    get(url).placeholder(R.drawable.shape_default_round_bg)
            .error(R.drawable.shape_default_round_bg)
            .transform(RoundedCornersTransformation(dp2px(10, context), 0, centerCrop = centerCrop))
            .into(this)
}

/**
 * 占位符圆形
 */
fun ImageView.loadCircle(drawable: Drawable?) {
    get(drawable).placeholder(R.drawable.shape_default_circle_bg)
            .apply(RequestOptions.circleCropTransform())
            .error(R.drawable.shape_default_circle_bg)
            .into(this)
}

fun ImageView.loadCircle(resourceID: Int?) {
    get(resourceID).placeholder(R.drawable.shape_default_circle_bg)
            .apply(RequestOptions.circleCropTransform())
            .error(R.drawable.shape_default_circle_bg)
            .into(this)
}

fun ImageView.loadCircle(url: String?) {
    get(url).placeholder(R.drawable.ic_default_article)
            .apply(RequestOptions.circleCropTransform())
            .error(R.drawable.ic_error_article)
            .into(this)
}

fun ImageView.loadBlur(url: String?) {
    get(url).placeholder(R.drawable.shape_default_rec_bg)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(5, 10)))
            .error(R.drawable.shape_default_rec_bg)
            .into(this)
}

fun ImageView.get(resourceID: Int?): GlideRequest<Drawable> = GlideApp.with(context).load(resourceID)
fun ImageView.get(url: String?): GlideRequest<Drawable> = GlideApp.with(context).load(url)
fun ImageView.get(drawable: Drawable?): GlideRequest<Drawable> = GlideApp.with(context).load(drawable)

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun dp2px(value: Int, context: Context): Int {
    val scale = context.resources.displayMetrics.density
    return (value * scale + 0.5f).toInt()
}


object MainHandler : Handler(Looper.getMainLooper())

fun runOnUIThread(uiWork: () -> Unit) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        uiWork()
    } else {
        MainHandler.post(uiWork)
    }
}