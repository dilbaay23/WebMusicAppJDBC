package repo;

import entity.Song;

import java.util.List;

/**
 * Created by Moon on 23/04/2021
 */
public interface SongRepository {
    int save(Song song);
    List<Song> retrieveAll();
    Song getSongById(int id);
    int updateSong(Song song);
    int deleteSong(Song song);
    int deleteSongById(int id);

    List<Song> getSongsByTitle(String searchKeyword);

    List<Song> getSongsByArtist(String searchKeyword);

    List<Song> getSongsByGenre(String searchKeyword);

    List<Song> getSongsByYear(int searchYear);

    List<Song> selectLimitedSongsByYear(int searchYear);

    List<Song> selectLimitedSongsByArtist(String searchYear);

    List<Song> selectLimitedSongsByTitle(String searchYear);

    List<Song> selectLimitedSongsByGenre(String searchYear);
}
