package com.example.myapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myapplication.TypeConverters.DateConverter
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "task_table")
@TypeConverters(DateConverter::class)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val priority: Int,
    val progress: Int,
    val deadline: Date,
    val estimateTimeMinutes: Int
): Parcelable {
    override fun toString(): String {
        return "Yo im ${progress}% done with task: '${title}'!"
    }
}