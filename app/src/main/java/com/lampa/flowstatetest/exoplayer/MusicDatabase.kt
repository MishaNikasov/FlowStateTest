package com.lampa.flowstatetest.exoplayer

class MusicDatabase {

    data class Song(
        val title: String?,
        val description: String?
    )

    suspend fun getAllSong(): List<Song> {
        return arrayListOf(Song("1", "desc"), Song("2", "desc"))
    }

}