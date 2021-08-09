package songlib.app;

public class Song {
	private String name,artist,album;
	private String year;
	private boolean album2,year2; //represents whether someone actually filled these fields 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public boolean getAlbumBool() {
		return album2;
	}
	public boolean getYearBool() {
		return year2;
	}
	public Song(String name1,String artist1, String album1, String year1,boolean album3,boolean year3) {
		this.name=name1;
		this.artist=artist1;
		this.album=album1;
		this.year=year1;
		this.album2=album3;
		this.year2=year3;
	}
	@Override
	public String toString() {
		return name + "             " +artist;
	}
	
	
}
