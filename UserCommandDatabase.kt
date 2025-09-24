package com.metroai.data

import android.content.Context
import androidx.room.*

@Entity
data class UserCommand(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val trigger: String,
    val response: String
)

@Dao
interface UserCommandDao {
    @Query("SELECT * FROM UserCommand")
    fun getAll(): List<UserCommand>

    @Insert
    fun insert(command: UserCommand)
}

@Database(entities = [UserCommand::class], version = 1)
abstract class UserCommandDatabase : RoomDatabase() {
    abstract fun commandDao(): UserCommandDao

    companion object {
        private var INSTANCE: UserCommandDatabase? = null
        fun getDatabase(context: Context): UserCommandDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserCommandDatabase::class.java,
                    "user_command_db"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }
}