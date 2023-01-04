public class PodcastEpisodes {
    private int podcastEpisodeId;
    private String DurationOfPodcastEpisode;
    private int podcastId;
    private float EpisodeNumber;
    private String pathOfEpisode;

    private String podcastepisodename;

    public PodcastEpisodes(int podcastEpisodeId, String durationOfPodcastEpisode, int podcastId, float episodeNumber, String pathOfEpisode, String podcastepisodename) {
        this.podcastEpisodeId = podcastEpisodeId;
        DurationOfPodcastEpisode = durationOfPodcastEpisode;
        this.podcastId = podcastId;
        EpisodeNumber = episodeNumber;
        this.pathOfEpisode = pathOfEpisode;
        this.podcastepisodename = podcastepisodename;
    }

    public int getPodcastEpisodeId() {
        return podcastEpisodeId;
    }

    public void setPodcastEpisodeId(int podcastEpisodeId) {
        this.podcastEpisodeId = podcastEpisodeId;
    }

    public String getDurationOfPodcastEpisode() {
        return DurationOfPodcastEpisode;
    }

    public void setDurationOfPodcastEpisode(String durationOfPodcastEpisode) {
        DurationOfPodcastEpisode = durationOfPodcastEpisode;
    }

    public int getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(int podcastId) {
        this.podcastId = podcastId;
    }

    public float getEpisodeNumber() {
        return EpisodeNumber;
    }

    public void setEpisodeNumber(float episodeNumber) {
        EpisodeNumber = episodeNumber;
    }

    public String getPathOfEpisode() {
        return pathOfEpisode;
    }

    public void setPathOfEpisode(String pathOfEpisode) {
        this.pathOfEpisode = pathOfEpisode;
    }

    public String getPodcastepisodename() {
        return podcastepisodename;
    }

    public void setPodcastepisodename(String podcastepisodename) {
        this.podcastepisodename = podcastepisodename;
    }

    @Override
    public String toString() {
        return "PodcastEpisodes{" +
                "pocastEpisodeId=" + podcastEpisodeId +
                ", DurationOfPodcastEpisode='" + DurationOfPodcastEpisode + '\'' +
                ", podcastId=" + podcastId +
                ", EpisodeNumber=" + EpisodeNumber +
                ", pathOfEpisode='" + pathOfEpisode + '\'' +
                ", podcastepisodename='" + podcastepisodename + '\'' +
                '}';
    }
}
