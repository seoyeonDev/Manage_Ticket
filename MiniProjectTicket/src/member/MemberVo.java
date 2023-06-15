package member;

public class MemberVo {

   private String id;
   private String pwd;
   private String name;
   private String phone;

   public MemberVo () {}
   
   public MemberVo(String id, String pwd, String name, String phone) {
      super();
      this.id = id;
      this.pwd = pwd;
      this.name = name;
      this.phone = phone;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPwd() {
      return pwd;
   }

   public void setPwd(String pwd) {
      this.pwd = pwd;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   @Override
   public String toString() {
      return "[  id: " + id + "\n | pwd: " + pwd + "\n | 이름: " + name + "\n | 전화번호: " + phone + " ] \n";
   }


}