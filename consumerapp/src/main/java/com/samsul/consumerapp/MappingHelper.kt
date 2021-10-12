package com.samsul.consumerapp

import android.database.Cursor

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<DataSearchUser> {
        val list = ArrayList<DataSearchUser>()
        if(cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColums.ID))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColums.USERNAME))
                val avatar_url = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColums.AVATAR_URL))

                list.add(
                    DataSearchUser(
                        username,id,avatar_url
                    )
                )

            }
        }
        return list
    }
}
