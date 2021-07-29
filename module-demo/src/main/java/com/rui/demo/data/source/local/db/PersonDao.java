package com.rui.demo.data.source.local.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao   // Database access object
public interface PersonDao {
    @Insert
    void insertWords(Person... people);

    @Update
    void updateWords(Person... people);

    @Delete
    void deleteWords(Person... people);

    @Query("DELETE FROM Person")
    void deleteAllWords();

    @Query("SELECT * FROM Person ORDER BY ID DESC")
    LiveData<List<Person>> getAllWordsLive();

}
