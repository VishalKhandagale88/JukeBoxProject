public class Playlist {
    private int PlaylistId;
    private String playlistName;
    private int userid;

    private int SongId;
    private int PodcastId;

    public int getPlaylistId() {
        return PlaylistId;
    }

    public void setPlaylistId(int playlistId) {
        PlaylistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getSongId() {
        return SongId;
    }

    public void setSongId(int songId) {
        SongId = songId;
    }

    public int getPodcastId() {
        return PodcastId;
    }

    public void setPodcastId(int podcastId) {
        PodcastId = podcastId;
    }

    public Playlist(int playlistId, String playlistName, int userid, int songId, int podcastId) {
        PlaylistId = playlistId;
        this.playlistName = playlistName;
        this.userid = userid;
        SongId = songId;
        PodcastId = podcastId;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "PlaylistId=" + PlaylistId +
                ", playlistName='" + playlistName + '\'' +
                ", userid=" + userid +
                ", SongId=" + SongId +
                ", PodcastId=" + PodcastId +
                '}';
    }
}
