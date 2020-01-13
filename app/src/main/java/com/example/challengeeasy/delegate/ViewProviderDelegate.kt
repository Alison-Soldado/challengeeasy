package com.example.challengeeasy.delegate

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KProperty

class ViewProviderDelegate<out T : View>(@IdRes idRes: Int) : NullableViewProviderDelegate<T>(idRes) {

    override operator fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return super.getValue(thisRef, property)!!
    }

    override operator fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return super.getValue(thisRef, property)!!
    }

    override operator fun getValue(thisRef: View, property: KProperty<*>): T {
        return super.getValue(thisRef, property)!!
    }

    override operator fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): T {
        return super.getValue(thisRef, property)!!
    }
}

open class NullableViewProviderDelegate<out T : View>(@IdRes private val idRes: Int) {

    private var view: T? = null

    open operator fun getValue(thisRef: Activity, property: KProperty<*>): T? {
        view?.let { if (!it.isAttachedToWindow) view = null }
        view = view ?: thisRef.findViewById(idRes)
        return view
    }

    open operator fun getValue(thisRef: Fragment, property: KProperty<*>): T? {
        view?.let { if (!it.isAttachedToWindow) view = null }
        view = view ?: thisRef.view?.findViewById(idRes)
        return view
    }

    open operator fun getValue(thisRef: View, property: KProperty<*>): T? {
        view = view ?: thisRef.findViewById(idRes)
        return view
    }

    open operator fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): T? {
        view = view ?: thisRef.itemView.findViewById(idRes)
        return view
    }
}

fun <T : View> viewProvider(@IdRes idRes: Int) =
    ViewProviderDelegate<T>(idRes)

fun <T : View> nullableViewProvider(@IdRes idRes: Int) =
    NullableViewProviderDelegate<T>(idRes)