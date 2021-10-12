package com.samsul.consumerapp

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {

    const val AUTHORITY = "com.samsul.githubuser"
    const val SCHEME = "content"

    internal class FavoriteUserColums(): BaseColumns {
        companion object {
            const val TABLE_NAME = "tb_favorite"
            const val ID = "id"
            const val USERNAME = "name"
            const val AVATAR_URL = "image"

            val CONTENT_URI = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}