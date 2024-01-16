package cz.utb.fai.imgrestapp.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow


@Dao
interface ApodInfoDao {
    @Query("select * from apod_info WHERE date = :date")
    fun getPictureByDate(date: String): Flow<ApodInfoDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(picture: ApodInfoDTO)
}

    @Database(entities = [ApodInfoDTO::class], version = 1)
    abstract class MyRoomDatabase : RoomDatabase() {
        abstract val apodInfoDao: ApodInfoDao

    }

    private lateinit var INSTANCE: MyRoomDatabase

fun getDatabase(context: Context): MyRoomDatabase {
    synchronized(MyRoomDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MyRoomDatabase::class.java,
                "my_room_database").build()
        }
    }
    return INSTANCE
}