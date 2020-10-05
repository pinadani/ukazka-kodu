package cz.pinadani.ukazkakodu.data.model.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * The Data Access Object for the UserData class.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id=:id ")
    suspend fun getUser(id: Int): UserData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserData)

}