package com.example.myapplication.data

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.*

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val dbBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "tasks"
                )
//                dbBuilder.addCallback(object : Callback() {
//                    override fun onOpen(@NonNull db: SupportSQLiteDatabase) {
//                        super.onOpen(db)
//                        db.execSQL("INSERT INTO tasks (title, priority, progress, deadline, estimateTimeMinutes) VALUES (${"example"}, 1, 20, ${Date(2022, 4, 1)}, 60)")
//                        System.out.println("Database has been created.")
//                    }
//                })

                val instance = dbBuilder.build()
                INSTANCE = instance

                return instance
            }
        }
    }

}