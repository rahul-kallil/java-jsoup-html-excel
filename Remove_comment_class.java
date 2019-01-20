package GeeBeeCleanHTML;
import org.jsoup.nodes.Node;


public class Remove_comment_class  {

	
    public  void removeComments(Node node) {

        for (int i = 0; i < node.childNodes().size();) {
            Node child = node.childNode(i);
            if (child.nodeName().equals("#comment"))
                child.remove();
            else {
                removeComments(child);
                i++;
            }
       }
        
    }       
}