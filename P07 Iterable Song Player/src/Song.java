/**
 * This is the class for the Song object
 *
 * @author Kavan Gandhi
 * @author Chinmay Koimuttum
 */
public class Song {
  private String songName; // name or title of the song
  private String artist; // artist of the song
  private String duration; // duration of the song

  /**
   * Creates a new Song given the song name, the name of the artist, and the duration of the song
   * 
   * @param songName - title of the song
   * @param artist   - name of the artist who performed this song
   * @param duration - duration of the song in the format mm:ss
   *
   * @throws IllegalArgumentException - with a descriptive error message if either of songName, or
   *                                  artist, or duration is null or is blank, or if the duration is
   *                                  not formatted as mm:ss where both mm and ss are in the 0 .. 59
   *                                  range.
   */
  public Song(String songName, String artist, String duration) throws IllegalArgumentException {
    if ((songName.isBlank() || songName == null)) {
      throw new IllegalArgumentException("songName is invalid");
    }
    if ((artist.isBlank() || artist == null)) {
      throw new IllegalArgumentException("artist is invalid");
    }
    if ((duration.isBlank() || duration == null)) {
      throw new IllegalArgumentException("duration is invalid");
    }
    String[] durationAsArray = duration.split(":");
    if (durationAsArray.length != 2) {
      throw new IllegalArgumentException("duration is invalid");
    }
    try {
      if (0 > Integer.parseInt(durationAsArray[0]) || Integer.parseInt(durationAsArray[0]) > 59) {
        throw new IllegalArgumentException("duration is invalid");
      }
      if (0 > Integer.parseInt(durationAsArray[1]) || Integer.parseInt(durationAsArray[1]) > 59) {
        throw new IllegalArgumentException("duration is invalid");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("duration is invalid");
    }

    this.songName = songName;
    this.artist = artist;
    this.duration = duration;
  }

  /**
   * Gets the title or name of this song
   *
   * @return the title or name of this song
   */
  public String getSongName() {
    return songName;
  }

  /**
   * Gets the name of the artist who performed this song
   *
   * @return the artist who performed this song
   */
  public String getArtist() {
    return artist;
  }

  /**
   * Gets the duration of this song
   *
   * @return the duration
   */
  public String getDuration() {
    return duration;
  }

  /**
   * Returns a string representation of this song. This string should be formatted as follows.
   * "songName---artist---duration" where songName is the title of the song, artist is the name of
   * the artist, and duration is the duration of the song.
   *
   * @return a string representation of this song.
   */
  @Override
  public String toString() {
    return songName + "---" + artist + "---" + duration;
  }

  /**
   * Returns true when this song's name and artist strings equals to the other song's name and
   * artist strings, and false otherwise. Note that this method takes an Object rather than a Song
   * argument, so that it Overrides Object.equals(Object). If an object that is not an instance of
   * Song is ever passed to this method, it should return false.
   * 
   * @param other - Song object to compare this object to
   *
   * @return true when the this song has matching name and artist with respect to another song (case
   *         insensitive comparison)
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Song)) {
      return false;
    }
    if (!(this.songName.equals(((Song) other).getSongName().trim()))) {
      return false;
    }
    if (!(this.artist.equals(((Song) other).getArtist().trim()))) {
      return false;
    }
    return true;
  }
}
