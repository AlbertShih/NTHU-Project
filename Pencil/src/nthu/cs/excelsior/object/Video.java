package nthu.cs.excelsior.object;



import javax.persistence.Id;



public class Video{
	@Id private String id;
	private String path1;
	private String path2;
	private String path3;
	private String path4;
	private String path5;
	private String path6;
	private String path7;
	private String path8;
	private String path9;
	private String path10;
	
	private String title;
	private String description;
	
	private String userId;
	
	public Video() {
		super();
	}
	public Video(String id,String path1,
						String path2,
						String path3,
						String path4,
						String path5,
						String path6,
						String path7,
						String path8,
						String path9,
						String path10,
						String title,String description,String userId){
		 this.path1=path1;
		 this.path2=path2;
		 this.path3=path3;
		 this.path4=path4;
		 this.path5=path5;
		 this.path6=path6;
		 this.path7=path7;
		 this.path8=path8;
		 this.path9=path9;
		 this.path10=path10;
		 this.id=id;
		 this.title=title;
		 this.description=description;
		 this.userId=userId;
	}
	public String getPath1(){
		return path1;
	}
	public void setPath1(String path1){
		this.path1=path1;
	}
	public String getPath2(){
		return path2;
	}
	public void setPath2(String path2){
		this.path2=path2;
	}
	public String getPath3(){
		return path3;
	}
	public void setPath3(String path3){
		this.path3=path3;
	}
	public String getPath4(){
		return path4;
	}
	public void setPath4(String path4){
		this.path4=path4;
	}
	public String getPath5(){
		return path5;
	}
	public void setPath5(String path5){
		this.path5=path5;
	}
	public String getPath6(){
		return path6;
	}
	public void setPath6(String path6){
		this.path6=path6;
	}
	public String getPath7(){
		return path7;
	}
	public void setPath7(String path7){
		this.path7=path7;
	}
	public String getPath8(){
		return path8;
	}
	public void setPath8(String path8){
		this.path8=path8;
	}
	public String getPath9(){
		return path9;
	}
	public void setPath9(String path9){
		this.path9=path9;
	}
	public String getPath10(){
		return path10;
	}
	public void setPath10(String path10){
		this.path10=path10;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}
}
