package nthu.cs.excelsior.object;

import javax.persistence.Id;

public class Profile{
	@Id private String id;
	private String displayName;
	//private String email;
	private String imageUrl;
	private String language;
	private String ageRange;
	public Profile() {
		super();
	}
	public Profile(String id,String displayName,String imageUrl,String language,String ageRange){
		 this.id=id;
		 this.displayName=displayName;
		 //this.email=email;
		 this.imageUrl=imageUrl;
		 this.language=language;
		 this.ageRange=ageRange; 	 
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}	
	public String getDisplayName(){
		return displayName;
	}
	public void setDisplayName(String displayName){
		this.displayName=displayName;
	}
//	public String getEmail(){
//		return email;
//	}
//	public void setEmail(String email){
//		this.email=email;
//	}
	public String getImageUrl(){
		return imageUrl;
	}
	public void setImageUrl(String imageUrl){
		this.imageUrl=imageUrl;
	}
	public String getLanguage(){
		return language;
	}
	public void setLanguage(String language){
		this.language=language;
	}
	public String getAgeRange(){
		return ageRange;
	}
	public void setAgeRange(String ageRange){
		this.ageRange=ageRange;
	}
}
