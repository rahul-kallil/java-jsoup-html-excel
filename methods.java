package GeeBeeCleanHTML;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class methods 
{
	public void Remove_empty_tags(Document doc)
	{
	   for (Element element : doc.select("*"))
	   {
	    if (!element.hasText()) {
	    	 doc.select("br");
	        element.remove();
	    }
	} 
	}	
	
public void Remove_link(Document doc) {
		
		doc.select("a").unwrap();
	    
		}
	public void Remove_class(Document doc)
	{
		Elements el = doc.getAllElements();{
			
			for (Element e : el) {
			        e.removeAttr("class");   
			    }
			
			}
		}
	public void Remove_id(Document doc)
	{
		Elements el = doc.getAllElements();{
			for (Element e : el) {    
			        e.removeAttr("id");  
			    }
			
			}
		
	}
	public void Remove_inline_styles(Document doc)
	{
		Elements el = doc.getAllElements();{
			
		for (Element e : el) {
			        e.removeAttr("style");
			    }
			
			}
		doc.select("style").remove();
		
	}
	public void Remove_span(Document doc)
	{
		doc.select("span").unwrap();
		
	}
	public void Remove_img(Document doc)
	{
		doc.select("img").remove();
		
	}
	
	public void Convert_tags(Document doc)
	{
		Elements element_b=doc.select("b");
		
		element_b.tagName("strong");
		
		Elements element_i=doc.select("i");
		
		element_i.tagName("em");
	}	
}
