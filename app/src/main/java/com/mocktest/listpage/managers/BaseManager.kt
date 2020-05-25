package com.mocktest.listpage.managers

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import at.favre.lib.armadillo.Armadillo

/**
 * abstract to keep all app Shared preferences in one place
 */
abstract class BaseManager<T>(mContext: Context, clazz: Class<T>) {
    //Encrypted shared preference
    private var mSharedPreferences: SharedPreferences =
        Armadillo.create(mContext, clazz.simpleName)
            .encryptionFingerprint(mContext)
            .build()

    /**
     * Share preference instance
     */
    protected fun getSharedPreferences() = mSharedPreferences

    /**
     * save preference
     */
    protected fun save(lamb: SharedPreferences.Editor.() -> Unit) {
        mSharedPreferences.edit {
            lamb(this)
            commit()
        }
    }

    /**
     * fun to clear all data in current preference
     */
    fun clear() {
        mSharedPreferences.edit {
            clear()
            commit()
        }
    }
}