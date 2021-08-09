package songlib.view;
import java.io.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.*;
import songlib.app.Song;

public class songlibController {
	@FXML ListView<Song> listid;
	@FXML Button addBtn;
	@FXML Button editBtn;
	@FXML Button deleteBtn;
	@FXML TextField addSongTxt;
	@FXML TextField addArtistTxt;
	@FXML TextField addYearTxt;
	@FXML TextField addAlbumTxt;
	@FXML TextField editSongTxt;
	@FXML TextField editArtistTxt;
	@FXML TextField editYearTxt;
	@FXML TextField editAlbumTxt;
	public ObservableList<Song> songs1 = FXCollections.observableArrayList(); //Contains all the songs
    File f1=new File("songs.txt"); //file that is read from
    Song d; //global variable
    Song item;
    int index;
	
	
	
	
public boolean isLetter(String q) {
	boolean c=false;
	if(q.contains("A")||q.contains("B")||q.contains("C")||q.contains("D")||q.contains("E")||
	q.contains("F")||q.contains("G")||q.contains("H")||q.contains("I")||q.contains("J")||
	q.contains("K")||q.contains("L")||q.contains("M")||q.contains("N")||q.contains("O")||
	q.contains("P")||q.contains("Q")||q.contains("R")|q.contains("S")||q.contains("T")||
	q.contains("U")||q.contains("V")||q.contains("W")||q.contains("X")||q.contains("Y")||
	q.contains("Z")||q.contains("a")||q.contains("b")|q.contains("c")||q.contains("d")||q.contains("e")||q.contains("f")||q.contains("g")||
	q.contains("h")||q.contains("i")||q.contains("j")||q.contains("k")||q.contains("l")||q.contains("m")||q.contains("n")||q.contains("o")||
	q.contains("p")||q.contains("q")||q.contains("r")||q.contains("s")||q.contains("t")||q.contains("u")||q.contains("v")||q.contains("w")||
	q.contains("x")||q.contains("y")||q.contains("z")) {
		c=true;
	}
	
	return c;
}
public ObservableList<Song> sort1(ObservableList<Song>x){              //sorts the song list
	ObservableList<Song> y =FXCollections.observableArrayList();
	for(int i=0; i<x.size(); i++) {
		if(i==0) {
			y.add(x.get(i));
		}
		else {
			for(int f=0; f<y.size(); f++) {
				Song one=y.get(f);   //Empty
				Song two=x.get(i);    //old
				if(two.getName().compareToIgnoreCase(one.getName())>0) {
					if(f+1==y.size()) {
						y.add(two);
						break;
					}
					else {
						continue;
					}
				}
				else if(two.getName().compareToIgnoreCase(one.getName())==0) {
					if(two.getArtist().compareToIgnoreCase(one.getArtist())>0) {
						if(f+1==y.size()) {
							y.add(two);
							break;
						}
						else {
							continue;
						}
					}
					else {
						y.set(f, two);
						y.add(f+1,one);
						break;
					}
				}
				else {
					y.set(f, two);
					y.add(f+1,one);
					break;
				}
			}
		}
}
	songs1=y;
	return songs1;
}
  
public boolean search(Song n) {                    //checks for a duplicate song
	boolean x=false;
	for(int i=0; i<songs1.size(); i++) {
		if(songs1.get(i).getName().compareToIgnoreCase(n.getName())==0 && songs1.get(i).getArtist().compareToIgnoreCase(n.getArtist())==0) {
			x=true;
		}
		else {
			continue;
		}
	}
	
	return x;
}
public boolean editsearch(Song n, String name, String artist) {                    
	//checks for a duplicate song
	Song f=new Song("","","","",false,false);
	for(int b=0; b<songs1.size(); b++) {
		if(songs1.get(b).getName().compareToIgnoreCase(n.getName())==0 && songs1.get(b).getArtist().compareToIgnoreCase(n.getArtist())==0) {
			f=songs1.remove(b);
			break;
		}
		else {
			continue;
		}
	}
	
	boolean x=false;
	for(int i=0; i<songs1.size(); i++) {
		if(songs1.get(i).getName().compareToIgnoreCase(name)==0 && songs1.get(i).getArtist().compareToIgnoreCase(artist)==0) {
			x=true;
		}
		else {
			continue;
		}
	}
		songs1.add(f);
		songs1=sort1(songs1);
		listid.setItems(songs1);
	return x;
}




public void populate(File f) {   //every time the application is opened it adds all the previous songs to songs1
	try {
		Scanner x =new Scanner(f1);
		songs1.clear();
		while(x.hasNextLine()) {
			String a =x.nextLine();
			StringTokenizer s1 = new StringTokenizer(a,"|");
			ArrayList<String>c = new ArrayList<String>();
			while(s1.hasMoreTokens()) {
				c.add(s1.nextToken());
			}
			if(c.size()==6) {
				d = new Song(c.get(0),c.get(1),c.get(2),c.get(3),true,true);
			}
			else if (c.size()==5) {
				if (c.get(3).compareToIgnoreCase("true")==0){
					d = new Song(c.get(0),c.get(1),c.get(2),"",true,false);		
				}else if(c.get(4).compareToIgnoreCase("true")==0) {
					d = new Song(c.get(0),c.get(1),"",c.get(2),false,true);
				}
			}
			else {
				d = new Song(c.get(0),c.get(1),"","",false,false);
			}
			songs1.add(d);
			
		}
		if(songs1.size()>0) {       //sorts songs1           
			songs1=sort1(songs1);
			Song q =songs1.get(0);
			listid.setItems(songs1);
			listid.getSelectionModel().select(0);
			if(songs1.size()!=0) {
				editSongTxt.setText(q.getName());
				editArtistTxt.setText(q.getArtist());
				if(q.getAlbumBool()!=false) {
					editAlbumTxt.setText(q.getAlbum());
				}
				if(q.getYearBool()!=false) {
					editYearTxt.setText(q.getYear());
				}	
			}	
		}		
	}
	catch(IOException e) {
		System.out.println(e);
	}
}

	//Work on this
public void add(ActionEvent e) throws IOException {   //Adds song to songs1,selects it from panel
	Button b = (Button)e.getSource();
	if (!confirmation("add")) {
		return;
	}
	FileWriter d =new FileWriter(f1,true);
	BufferedWriter k =new BufferedWriter(d);
	Song insert= new Song("","","","",false,false);
	//checks whether input errors are there
	boolean x = (isLetter(addYearTxt.getText())||(isLetter(addYearTxt.getText())==false)&&addYearTxt.getText().isBlank()==false &&Integer.parseInt(addYearTxt.getText())<0)||addSongTxt.getText().contains("|") || addArtistTxt.getText().contains("|")||(addAlbumTxt.getText().isBlank()==false && addAlbumTxt.getText().contains("|"))||(addYearTxt.getText().isBlank()==false && addYearTxt.getText().contains("|"))||addSongTxt.getText().isBlank()||addArtistTxt.getText().isBlank();
	if(b==addBtn) {
		System.out.println(Boolean.toString(x));
		if (addAlbumTxt.getText().isBlank()==true || addYearTxt.getText().isBlank()==true){
			if(addAlbumTxt.getText().isBlank()==true && addYearTxt.getText().isBlank()==false) {
				if(x==false) {
					insert=new Song(addSongTxt.getText().strip(),addArtistTxt.getText().strip(),"",addYearTxt.getText().strip(),false,true);
				}
				else {
					TextInputDialog dialog = new TextInputDialog("Press Ok");
					dialog.setTitle("Error");
					dialog.setHeaderText("Input error");
					dialog.show();
				}
			}
			else if(addAlbumTxt.getText().isBlank()==false && addYearTxt.getText().isBlank()==true) {
				if(x==false) {
					insert=new Song(addSongTxt.getText().strip(),addArtistTxt.getText().strip(),addAlbumTxt.getText().strip(),"",true,false);
				}
				else {
					TextInputDialog dialog = new TextInputDialog("Press Ok");
					dialog.setTitle("Error");
					dialog.setHeaderText("Input error");
					dialog.show();
				}
				
			}
			else {
				if(x==false) {
					insert=new Song(addSongTxt.getText().strip(),addArtistTxt.getText().strip(),"","",false,false);
				}
				else {
					TextInputDialog dialog = new TextInputDialog("Press Ok");
					dialog.setTitle("Error");
					dialog.setHeaderText("Input error");
					dialog.show();
				}
				
			}
		}
		else {
			if(x==false) {
				insert=new Song(addSongTxt.getText().strip(),addArtistTxt.getText().strip(),addAlbumTxt.getText().strip(),addYearTxt.getText().strip(),true,true);	
			}
			else {
				TextInputDialog dialog = new TextInputDialog("Press Ok");
				dialog.setTitle("Error");
				dialog.setHeaderText("Input error");
				dialog.show();
			}
		}
		if(insert.getName().compareToIgnoreCase("")!=0) {
			boolean one=insert.getAlbumBool();
			boolean two=insert.getYearBool();
			String first=Boolean.toString(one);
			String second=Boolean.toString(two);
			//inserts the song into the txt file and inserts song into songs1 
			if(search(insert)==false) {
				songs1.add(insert);
				try {
						k.write(addSongTxt.getText().strip()+"|"+addArtistTxt.getText().strip()+"|"+addAlbumTxt.getText().strip()+"|"+addYearTxt.getText().strip()+"|"+first.strip()+"|"+second.strip()+"\n");
				}
				catch(Exception j){
					System.out.println(j);
				}
				finally {
					k.close();
					d.close();
				}
					
				}
			else {
				TextInputDialog dialog = new TextInputDialog("Press Ok");
				dialog.setTitle("Error");
				dialog.setHeaderText("There is already a song with this name and this artist");
				dialog.show();
			}
			//sorts the songs1,sets the display screen and selects the added song
				songs1=sort1(songs1);
				listid.setItems(songs1);
				listid.getSelectionModel().select(insert);
				editSongTxt.setText(insert.getName());
				editArtistTxt.setText(insert.getArtist());
				editAlbumTxt.clear();
				editYearTxt.clear();
				if(insert.getAlbumBool()!=false) {
					editAlbumTxt.setText(insert.getAlbum());
				}
				if(insert.getYearBool()!=false) {
					editYearTxt.setText(insert.getYear());
				}
				
				
		}
		
			
			
		}
		//resets fields
			addArtistTxt.clear();
			addAlbumTxt.clear();
			addYearTxt.clear();
			addSongTxt.clear();
		}
	


	
	public void delete(ActionEvent e) throws IOException {
		//Work on selection of deletion
		if (!confirmation("delete")) {
			return;
		}
		item = listid.getSelectionModel().getSelectedItem();
		index = listid.getSelectionModel().getSelectedIndex();
		String name=editSongTxt.getText();
		String artist=editArtistTxt.getText();
		String album=editAlbumTxt.getText();
		String year=editYearTxt.getText();
		Boolean first=true;
		Boolean second=true;
		if(item==null) {
			listid.getSelectionModel().select(index);
		}
		else
		{
			ArrayList<String>b=new ArrayList<String>();
			Scanner x=new Scanner(f1);
			while(x.hasNextLine()) {
				String a = x.nextLine();
				StringTokenizer s1=new StringTokenizer(a, "|");
				boolean c=false;
				String one=s1.nextToken();
				String two=s1.nextToken();
				if(one.compareToIgnoreCase(item.getName())==0 && two.compareToIgnoreCase(item.getArtist())==0) {
					continue;
				}
				else {
					b.add(a);	
				}	
			}
			
			FileWriter d =new FileWriter(f1,false);
			BufferedWriter k =new BufferedWriter(d);
			for(int i=0; i<b.size(); i++) {
				k.write(b.get(i)+"\n");
			}
			k.close();
			d.close();
			
				int j=listid.getSelectionModel().getSelectedIndex();
				if(songs1.size()==1) {
					songs1.remove(j);
					listid.setItems(songs1);
					editSongTxt.clear();
					editArtistTxt.clear();
					editAlbumTxt.clear();
					editYearTxt.clear();
				}
				else if(j+1<songs1.size()) {
					songs1.remove(j);
					listid.setItems(songs1);
					listid.getSelectionModel().select(j);
				}
				else if(j+1>=songs1.size()) {
					songs1.remove(j);
					listid.setItems(songs1);
					listid.getSelectionModel().select(j);
				}
		}
		
	}
	public void edit(ActionEvent e) throws IOException {
		if (!confirmation("edit")) {
			return;
		}
		boolean error =false;
		item = listid.getSelectionModel().getSelectedItem();
		index = listid.getSelectionModel().getSelectedIndex();
		String name=editSongTxt.getText();
		String artist=editArtistTxt.getText();
		String album=editAlbumTxt.getText();
		String year=editYearTxt.getText();
		Boolean first=true;
		Boolean second=true;
		if(item==null) {
			listid.getSelectionModel().select(index);
		}
		else {
			
		if(item.getAlbum().isBlank()) {
			first=false;
		}
		if(item.getYear().isBlank()) {
			second=false;
		}
		boolean z=editsearch(new Song(item.getName(),item.getArtist(),item.getAlbum(),item.getYear(),first,second),
				             name,artist);
		if(z) {
			TextInputDialog dialog = new TextInputDialog("Press Ok");
			dialog.setTitle("Error");
			dialog.setHeaderText("There is already a song with this name and this artist");
			dialog.show();
		}
		else {
		if(name!=null|| year!=null || album !=null || artist !=null)
		{
			 error=  name.isBlank()||name.contains("|")||
				   artist.isBlank()||artist.contains("|")		
				||(year.contains("|")&& !year.isBlank())||
					(isLetter(year)==true||(!year.isBlank() && !isLetter(year)&&Integer.parseInt(year)<0))||
					(!album.isEmpty() &&album.contains("|"));
		}

		if(error==true) {
			TextInputDialog dialog = new TextInputDialog("Press Ok");
			dialog.setTitle("Please try again with valid input");
			dialog.setHeaderText("Input error");
			dialog.show();
			listid.getSelectionModel().select(index);
		}
		else {			
		ArrayList<String>b=new ArrayList<String>();
		Scanner x=new Scanner(f1);
		while(x.hasNextLine()) {
			String a = x.nextLine();
			StringTokenizer s1=new StringTokenizer(a, "|");
			boolean c=false;
			String one=s1.nextToken();
			String two=s1.nextToken();
			if(one.compareToIgnoreCase(item.getName())==0 && two.compareToIgnoreCase(item.getArtist())==0) {
				continue;
			}
			else {
				b.add(a);	
			}	
		}
		Scanner y =new Scanner(f1);
		while(y.hasNextLine()) {
			String a=y.nextLine();
			if(b.contains(a)) {
				continue;
			}
			else {
				if(album.isEmpty() && year.isEmpty()) {
					String f=name.strip()+"|"+artist.strip()+"|"+"|"+"|"+"false".strip()+"|"+"false".strip();
					a=a.replace(a.subSequence(0, a.length()), f);
					b.add(a);
				}
				else if(!album.isEmpty()&&year.isEmpty()) {
					String f=name.strip()+"|"+artist.strip()+"|"+album.strip()+"|"+"|"+"true".strip()+"|"+"false".strip();
					a=a.replace(a.subSequence(0, a.length()),f);
					b.add(a);
				}
				
				else if((album.isEmpty()&& !year.isEmpty())){
					String f=name.strip()+"|"+artist.strip()+"|"+"|"+year.strip()+"|"+"false".strip()+"|"+"true".strip();
					a=a.replace(a.subSequence(0, a.length()),f);
					b.add(a);
				}
				else {
					String f=name.strip()+"|"+artist.strip()+"|"+album.strip()+"|"+year.strip()+"|"+"true".strip()+"|"+"true".strip();
					a=a.replace(a.subSequence(0, a.length()),f);
					b.add(a);
				}
				
				
				
	
			}
			
			
			
		}
		
		FileWriter d =new FileWriter(f1,false);
		BufferedWriter k =new BufferedWriter(d);
		for(int i=0; i<b.size(); i++) {
			k.write(b.get(i)+"\n");
		}
		k.close();
		d.close();
		populate(f1);	
		}
		}
		}
	}
	
	private boolean confirmation(String action) {
		TextInputDialog dialog = new TextInputDialog("Yes");
		dialog.setTitle("Confirmation");
		dialog.setHeaderText("Are you sure you want to " + action + " this song? Type in \"Yes\" (Case Sensitive) to confirm,"
				+ " Type in anything else to stop.");
		Optional<String> answer = dialog.showAndWait();
		String ans = answer.get();
		if (ans.equals("Yes")) {
			return true;
		}
		return false;
	}
	
	
 public void start(Stage mainStage) {	
	populate(f1);
	
//	// set listener for the items
	listid
	.getSelectionModel()
	.selectedIndexProperty()
	.addListener(
			(obs, oldVal, newVal) -> 
			showItemInputDialog(mainStage));
}

	private void showItemInputDialog(Stage mainStage) {                
		Song item = listid.getSelectionModel().getSelectedItem();
		if(item!=null) {
		int index = listid.getSelectionModel().getSelectedIndex();
		editSongTxt.setText(item.getName());
		editArtistTxt.setText(item.getArtist());
		editAlbumTxt.setText(item.getAlbum());
		editYearTxt.setText(item.getYear());
		}
////		TextInputDialog dialog = new TextInputDialog(item);
////		dialog.initOwner(mainStage); dialog.setTitle("List Item");
////		dialog.setHeaderText("Selected Item (Index: " + index + ")");
////		dialog.setContentText("Enter name: ");
////
////		Optional<String> result = dialog.showAndWait();
////		if (result.isPresent()) { obsList.set(index, result.get()); }
//	//}
 
}
}