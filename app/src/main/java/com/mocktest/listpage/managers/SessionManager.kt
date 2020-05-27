package com.mocktest.listpage.managers

import android.content.Context

class SessionManager(mContext: Context) : BaseManager<SessionManager>(mContext, SessionManager::class.java) {

    private val mSessionName = "SessionName"

    /**
     * fun to set title
     */
    fun setSessionName(sessionName: String) {
        save {

            putString(mSessionName, sessionName)
        }
    }

    /**
     * fun to get title
     */
    fun getSessionName() = getSharedPreferences().getString(mSessionName, null)
}