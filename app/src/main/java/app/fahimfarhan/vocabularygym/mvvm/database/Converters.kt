package app.fahimfarhan.vocabularygym.mvvm.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@Suppress("RedundantSemicolon")
class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun getArrayList(json: String?): ArrayList<Int> {
            val listType = object : TypeToken<ArrayList<Int?>?>() {}.type
            return Gson().fromJson(json, listType)
        }


        @TypeConverter
        @JvmStatic
        fun saveArrayList(list: ArrayList<Int?>?): String {
            val gson = Gson()
            return gson.toJson(list)
        }
    }
}
