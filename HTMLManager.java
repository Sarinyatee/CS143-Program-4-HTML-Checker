import java.util.*;

public class HTMLManager {
   private Queue<HTMLTag> tags;
   
   public HTMLManager() {
      tags = new LinkedList<>(); 
   }
   
   public HTMLManager(Queue<HTMLTag> html) {
      if (html == null) {
         throw new IllegalArgumentException("It is empty HTML file.");
      }
      tags = new LinkedList<>(html);
   }
   
   public Queue<HTMLTag> getTags() {
      return tags; 
   }
   
   public void fixHTML() {
      Stack<HTMLTag> stack = new Stack<>();
      int size = tags.size();
      
      for (int i = 0; i < size; i++) {
         HTMLTag tag = tags.remove();
         
         if (tag.isSelfClosing()) {
            tags.add(tag);
         } else if (tag.isOpening()) {
            tags.add(tag);
            stack.push(tag);
         } else {
            if (stack.isEmpty()) {
            // No matching open tag — do nothing        
            } else if (stack.peek().matches(tag)) {
               tags.add(tag);
               stack.pop();
            } else {
            tags.add(stack.pop().getMatching());   
            }
         }
      }

      while (!stack.isEmpty()) {
         tags.add(stack.pop().getMatching());
      }
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