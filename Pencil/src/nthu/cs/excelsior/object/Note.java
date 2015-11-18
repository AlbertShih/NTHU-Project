package nthu.cs.excelsior.object;

import javax.persistence.Id;


public class Note {
	@Id private String key;
	private String id;
	private String title;
	private String content;
	private String videoId;
	private String left;
	private String top;
	public Note() {
		super();
	}
	public Note(String key,String id,String title,String content,String videoId,String left,String top){
		 this.key=key; 
		 this.id=id;
		 this.content=content;
		 this.videoId=videoId;
		 this.title=title;
		 this.left=left;
		 this.top=top;
	}
	public String getKey(){
		return key;
	}
	public void setKey(String key){
		this.key=key;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getVideoId(){
		return videoId;
	}
	public void setVideoId(String videoId){
	   this.videoId=videoId; 
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
	   this.title=title; 
	}
	public String getLeft(){
		return left;
	}
	public void setLeft(String left){
	   this.left=left; 
	}
	public String getTop(){
		return top;
	}
	public void setTop(String top){
	   this.top=top; 
	}
}
