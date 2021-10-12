package com.samsul.githubuser.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.samsul.githubuser.data.database.UserDao
import com.samsul.githubuser.data.database.UserDatabase

class UserContentProvider : ContentProvider() {

    companion object {
        const val AUTHORITY = "com.samsul.githubuser"
        const val TABLE_NAME = "tb_favorite"
        const val ID_FAVORITE_USER_DATA = 1
        val uriMathcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMathcher.addURI(AUTHORITY, TABLE_NAME, ID_FAVORITE_USER_DATA)
        }
    }

    private lateinit var userDao: UserDao

    override fun onCreate(): Boolean {
        userDao = context?.let {
            UserDatabase.getDatabase(it).userDao()
        }!!
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor: Cursor?
        when(uriMathcher.match(uri)) {
            ID_FAVORITE_USER_DATA -> {
                cursor = userDao.findAll()
                if(context!=null) {
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> {
                cursor = null
            }
        }
        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }


    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}