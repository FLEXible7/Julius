/*package com.example.julius.dummy.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.selects.select

@Dao
interface DealDAO {

    @Insert
    fun saveDeal(deal : DealEntities)

    @Query ("select * from DealEntities")
    fun readDeal() : List<DealEntities>
}*/