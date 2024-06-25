package com.flashnews.model.database

import androidx.room.TypeConverter
import com.flashnews.model.dto.Source

class SourceTypeConverter {

    @TypeConverter
    fun fromSource(source: Source) : String{
        return source.name;
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name,name);
    }
}