package nthu.cs.excelsior.object;

import javax.persistence.Id;

public class VideoId {
	@Id private String id;
	public VideoId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
}
