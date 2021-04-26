package service.impl;

import entity.Song;
import repo.SongRepository;
import repo.impl.SongRepositoryImpl;
import service.SongService;

import java.util.List;

/**
 * Created by Moon on 23/04/2021
 */
public class SongServiceImpl implements SongService {
    SongRepository songRepository = new SongRepositoryImpl();

    @Override
    public void save(Song song) {
        if(songRepository.save(song)>0) {
            System.out.println("New song saved successfully");
        }else{
            System.out.println("DB Error");
        }

    }

    @Override
    public void retrieveAll() {
        List<Song> songs = songRepository.retrieveAll();
        for (Song song : songs) {
            System.out.println(song);
        }

    }

    @Override
    public void getSongById(int id) {
        Song song = songRepository.getSongById(id);
        System.out.println(song);

    }

    @Override
    public void updateSong(Song song) {
        if(songRepository.updateSong(song)>0) {
            System.out.println("Updated successfully");
        }else{
            System.out.println("DB Error");
        }

    }

    @Override
    public void deleteSong(Song song) {

        if(songRepository.deleteSong(song)>0) {
            System.out.println("Song deleted  successfully");
        }else{
            System.out.println("DB Error");
        }
    }

    @Override
    public void deleteSongById(int id) {

        if(songRepository.deleteSongById(id)>0) {
            System.out.println("Song By id deleted  successfully");
        }else{
            System.out.println("DB Error");
        }
    }

    @Override
    public List<Song> getSongsByTitle(String searchKeyword) {
        int numberOfSongs=songRepository.selectLimitedSongsByTitle(searchKeyword).size();
        if(numberOfSongs>0) {
            return songRepository.selectLimitedSongsByTitle(searchKeyword);
        }else if(numberOfSongs==0){
            System.out.println("There is no Song by this keyword");
            return songRepository.selectLimitedSongsByTitle(searchKeyword);
        }else{
            System.out.println("DB Error");
            return null;
        }
    }

    @Override
    public List<Song> getSongsByArtist(String searchKeyword) {
        int numberOfSongs=songRepository.selectLimitedSongsByArtist(searchKeyword).size();
        if(numberOfSongs>0) {
            return songRepository.selectLimitedSongsByArtist(searchKeyword);
        }else if(numberOfSongs==0){
            System.out.println("There is no Song by this keyword");
            return songRepository.selectLimitedSongsByArtist(searchKeyword);
        }else{
            System.out.println("DB Error");
            return null;
        }

    }

    @Override
    public List<Song> getSongsByGenre(String searchKeyword) {

        int numberOfSongs=songRepository.selectLimitedSongsByGenre(searchKeyword).size();
        if(numberOfSongs>0) {
            return songRepository.selectLimitedSongsByGenre(searchKeyword);
        }else if(numberOfSongs==0){
            System.out.println("There is no Song by this keyword");
            return songRepository.selectLimitedSongsByGenre(searchKeyword);
        }else{
            System.out.println("DB Error");
            return null;
        }
    }

    @Override
    public List<Song> getSongsByYear(int searchYear) {

        int numberOfSongs=songRepository.selectLimitedSongsByYear(searchYear).size();
        if(numberOfSongs>0) {
            return songRepository.selectLimitedSongsByYear(searchYear);
        }else if(numberOfSongs==0){
            System.out.println("There is no Song by this year");
            return songRepository.selectLimitedSongsByYear(searchYear);
        }else{
            System.out.println("DB Error");
            return null;
        }
    }
}
