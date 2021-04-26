package repo.impl;



import entity.Song;
import repo.SongRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utility.DataCredentials.*;

/**
 * Created by Moon on 23/04/2021
 */
public class SongRepositoryImpl implements SongRepository {

    @Override
    public int save(Song song) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql= "INSERT INTO javadevt_genk_08.Song (title, artist, genre, year, cost) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, song.getTitle());
            ps.setString(2, song.getArtist());
            ps.setString(3, song.getGenre());
            ps.setInt(4, song.getYear());
            ps.setDouble(5, song.getCost());
             int result= ps.executeUpdate();

             return result;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    @Override
    public List<Song> retrieveAll() {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Song ");

            ResultSet rs= ps.executeQuery();
            List<Song> songs = new ArrayList<>();

            while(rs.next()){
               songs.add(new Song(rs.getLong("id"),
                       rs.getString("title"),
                       rs.getString("artist"),
                       rs.getString("genre"),
                       rs.getInt("year"),
                       rs.getDouble("cost")));
            }
            return songs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    @Override
    public Song getSongById(int id) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Song WHERE id=?");
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            Song song = new Song();
            //TODO: check null song

            while(rs.next()){
                song= new Song(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("cost"));
            }
            return song;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    @Override
    public int updateSong(Song song) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql= "UPDATE javadevt_genk_08.Song SET cost=? WHERE id=?";

            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, song.getCost());
            ps.setLong(2,song.getId());
            int result= ps.executeUpdate();
            return result;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }


    }

    @Override
    public int deleteSong(Song song) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql= "DELETE FROM javadevt_genk_08.Song WHERE id=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setLong(1,song.getId());
            return ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    @Override
    public int deleteSongById(int id) {

        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql= "DELETE FROM javadevt_genk_08.Song WHERE id=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setLong(1,id);
            return ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }



    @Override
    public List<Song> getSongsByTitle(String searchKeyword) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Song WHERE title LIKE ?");
            ps.setString(1, "%" + searchKeyword + "%");
            ResultSet rs= ps.executeQuery();
            List<Song> songs = new ArrayList<>();

            while(rs.next()){
                songs.add(new Song(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("cost")));
            }
            return songs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

    @Override
    public List<Song> getSongsByArtist(String searchKeyword) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Song WHERE artist LIKE ?");
            ps.setString(1, "%" + searchKeyword + "%");
            ResultSet rs= ps.executeQuery();
            List<Song> songs = new ArrayList<>();

            while(rs.next()){
                songs.add(new Song(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("cost")));
            }
            return songs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

    @Override
    public List<Song> getSongsByGenre(String searchKeyword) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Song WHERE genre LIKE ?");
            ps.setString(1, "%" + searchKeyword + "%");
            ResultSet rs= ps.executeQuery();
            List<Song> songs = new ArrayList<>();

            while(rs.next()){
                songs.add(new Song(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("cost")));
            }
            return songs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

    @Override
    public List<Song> getSongsByYear(int searchYear) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Song WHERE year=? ");
            ps.setInt(1, searchYear);
            ResultSet rs= ps.executeQuery();
            List<Song> songs = new ArrayList<>();

            while(rs.next()){
                songs.add(new Song(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("cost")));
            }
            return songs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

    //Limit by 10 For pagination
    @Override
    public List<Song> selectLimitedSongsByTitle(String searchKeyword) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Song WHERE title LIKE ? LIMIT 10");
            ps.setString(1, "%" + searchKeyword + "%");
            ResultSet rs= ps.executeQuery();
            List<Song> songs = new ArrayList<>();

            while(rs.next()){
                songs.add(new Song(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("cost")));
            }
            return songs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

    @Override
    public List<Song> selectLimitedSongsByArtist(String searchKeyword) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Song WHERE artist LIKE ? LIMIT 10");
            ps.setString(1, "%" + searchKeyword + "%");
            ResultSet rs= ps.executeQuery();
            List<Song> songs = new ArrayList<>();

            while(rs.next()){
                songs.add(new Song(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("cost")));
            }
            return songs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

    @Override
    public List<Song> selectLimitedSongsByGenre(String searchKeyword) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Song WHERE genre LIKE ? LIMIT 10");
            ps.setString(1, "%" + searchKeyword + "%");
            ResultSet rs= ps.executeQuery();
            List<Song> songs = new ArrayList<>();

            while(rs.next()){
                songs.add(new Song(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("cost")));
            }
            return songs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

    @Override
    public List<Song> selectLimitedSongsByYear(int searchYear) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Song WHERE year=? LIMIT 10");
            ps.setInt(1, searchYear);
            ResultSet rs= ps.executeQuery();
            List<Song> songs = new ArrayList<>();

            while(rs.next()){
                songs.add(new Song(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("cost")));
            }
            return songs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }
}
