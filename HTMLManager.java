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
  
  public void fixHTML() {
   Stack<HTMLTag> stack = new Stack<>();
   Queue<HTMLTag> fixed = new LinkedList<>();
   
   while (!tags.isEmpty()) {
      HTMLTag tag = tags.remove();
      if(tag.isSelfClosing()) {
         fixed.add(tag);
      } else if(tag.isOpening()) {
         fixed.add(tag);
         stack.push(tag);
      } else {
         if(stack.isEmpty()) {
         // No matching open tag — do nothing
         } else if (stack.peek().matches(tag)) {
            fixed.add(tag);
            stack.pop();
         } else {
            fixed.add(stack.pop().getMatching());
            tags.add(tag);                                        
         }
      }
   }
 
   while (!stack.isEmpty()) {
      fixed.add(stack.pop().getMatching());
   }
   tags = fixed;
  }
   
  public String toString() {
   String result = "";
        int size = tags.size();
        for (int i = 0; i < size; i++) {
            HTMLTag tag = tags.remove();
            result += tag.toString().trim() + "\n";
            tags.add(tag);      
    }    
    return result;
  }
}