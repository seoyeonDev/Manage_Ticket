package hall;

public class HallVo {

   private String id;
   private String location;
   private String name;
   
   public HallVo () {}

   public HallVo(String id, String location, String name) {
      super();
      this.id = id;
      this.location = location;
      this.name = name;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getLocation() {
      return location;
   }

   public void setLocation(String location) {
      this.location = location;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "HallVo [id=" + id + ", location=" + location + ", name=" + name + "]";
   }

}