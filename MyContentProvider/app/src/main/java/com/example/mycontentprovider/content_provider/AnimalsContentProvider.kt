package com.example.mycontentprovider.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.mycontentprovider.constants.Constants
import com.example.mycontentprovider.db.db.AnimalsDb
import java.net.URI


class AnimalsContentProvider: ContentProvider() {

     companion object {
        val ANIMAL_URI = Uri.parse("content://" + Constants.AnimalProvider.BASE_PACKAGE + "/animals")
        val uri_matcher = UriMatcher(UriMatcher.NO_MATCH)
        init {
            uri_matcher.addURI(Constants.AnimalProvider.BASE_PACKAGE, "animals", 1)
        }
        fun match_uri(uri: Uri): Int {
            return uri_matcher.match(uri)
        }
    }


    private lateinit var db: AnimalsDb


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        when(match_uri(uri)) {
            1 -> {
                val select = """SELECT ${StringBuilder().apply {
                    var delim = ""
                    for (col in projection!!) {
                        append(delim)
                        append(col)
                        delim = ","
                    }
                }} FROM ANIMALS WHERE ${selection}"""

                val query = SimpleSQLiteQuery(select, selectionArgs)
                return db.animlasDao().get_animals(query)
            }
        }
        return null
    }

    override fun onCreate(): Boolean {
        db = AnimalsDb.getInstance(context)!!
        return true
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}