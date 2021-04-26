package service;

import entity.Song;

import java.util.List;

/**
 * Created by Moon on 23/04/2021
 */
public interface SongService {
    void save(Song song);
    void retrieveAll();
    void  getSongById(int id);
    void updateSong(Song song);
    void deleteSong(Song song);
    void deleteSongById(int id);

    List<Song> getSongsByTitle(String searchKeyword);

    List<Song> getSongsByArtist(String searchKeyword);

    List<Song> getSongsByGenre(String searchKeyword);

    List<Song> getSongsByYear(int searchKey);
}
