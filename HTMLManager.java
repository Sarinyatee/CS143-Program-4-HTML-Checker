import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;
  public Queue<HTMLTag> html;
  
  public HTMLManager() {
   tags = new LinkedList<>(); 
  }
  
  public HTMLManager(Queue<HTMLTag> html) {
   if(html == null) {
      throw new IllegalArgumentException("It is empty HTML file.");
   } 
      tags = new LinkedList<>(html);
   
  }
  
  public Queue<HTMLTag> getTags() {
   return tags; 
  }
  
  public String toString() {
   String result = "";
   int size = 0;
   
   if(tags.size() != 0) {
      size = tags.size();
   }
   for(int i = 0; i < size; i++) {
      HTMLTag val = tags.remove();
      result += val.toString();
      tags.add(val);      
   }
   return result;
  }
}
