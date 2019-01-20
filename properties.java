package GeeBeeCleanHTML;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import GeeBeeCleanHTML.Remove_comment_class;

public class properties  {
	properties() throws IOException
	{
	methods a=new methods();
    OutputStream os = null;
    Properties propObject = new Properties();
    
 
//creating properties file
try {
 
   // propObject.load(new FileInputStream("C:\\Users\\admin\\Desktop\\GEEBEE\\props\\GeeBeeProperties.properties"));
	String absPath=choosePropFile.f1.getAbsolutePath(); 
	absPath.replace("\\", "\\\\");  
	
	propObject.load(new FileInputStream(absPath));
   
    System.out.println("Properties file read");
    
    
} catch (FileNotFoundException e) {
    e.printStackTrace();
}


Remove_comment_class c=new Remove_comment_class();

if(propObject.getProperty("removeComments") != null)
{
	c.removeComments(cleanFinal.doc);
	
	}
if(propObject.getProperty("Remove_empty_tags") != null)
{

	a.Remove_empty_tags(cleanFinal.doc);
	
}
if(propObject.getProperty("Remove_span") != null)
{
	a.Remove_span(cleanFinal.doc);
	
	}
if(propObject.getProperty("Remove_img") != null)
{
	a.Remove_img(cleanFinal.doc);
	
	}
if(propObject.getProperty("Convert_tags") != null)
{
	a.Convert_tags(cleanFinal.doc);
	
	}


 if(propObject.getProperty("Remove_link") != null)
{
	a.Remove_link(cleanFinal.doc);
	
	}
if(propObject.getProperty("Remove_class") != null)
{
	a.Remove_class(cleanFinal.doc);
	
	}
if(propObject.getProperty("Remove_id") != null)
{
	a.Remove_id(cleanFinal.doc);
	
	}
 if(propObject.getProperty("Remove_inline_styles") != null)
{
	a.Remove_inline_styles(cleanFinal.doc);

	}
 if(propObject.getProperty("RemoveTagAttributes") != null)
 {

 	  Elements el = cleanFinal.doc.getAllElements();
 {
   
 	  for (Element e : el) 
 	 {
 e.removeAttr("class");  
 e.removeAttr("accept");
 e.removeAttr("accept-charset");  
 e.removeAttr("accesskey");  
 e.removeAttr("action");  
 e.removeAttr("align");  
 e.removeAttr("alt");
 e.removeAttr("async");  
 e.removeAttr("autocomplete");  
 e.removeAttr("autofocus");  
 e.removeAttr("autoplay");  
 e.removeAttr("autosave");
 e.removeAttr("bgcolor");  
 e.removeAttr("border");  
 e.removeAttr("buffered");  
 e.removeAttr("challenge");  
 e.removeAttr("charset");
 e.removeAttr("checked");  
 e.removeAttr("cite");  
 e.removeAttr("code");   
 e.removeAttr("codebase");
 e.removeAttr("color");  
 e.removeAttr("cols");  
 e.removeAttr("colspan");  
 e.removeAttr("content");  
 e.removeAttr("contenteditable");
 e.removeAttr("contextmenu");  
 e.removeAttr("controls");  
 e.removeAttr("coords");  
 e.removeAttr("crossorigin");  
 e.removeAttr("data");
 e.removeAttr("data-*");  
 e.removeAttr("datetime");  
 e.removeAttr("default");  
 e.removeAttr("defer");  
 e.removeAttr("dir");
 e.removeAttr("dirname");  
 e.removeAttr("disabled");  
 e.removeAttr("download");  
 e.removeAttr("draggable");  
 e.removeAttr("dropzone");
 e.removeAttr("enctype");  
 e.removeAttr("for");  
 e.removeAttr("form	");  
 e.removeAttr("formaction");  
 e.removeAttr("headers");  
 e.removeAttr("height");  
 e.removeAttr("hidden");  
 e.removeAttr("high");
 e.removeAttr("href");  
 e.removeAttr("hreflang");  
 e.removeAttr("http-equiv");  
 e.removeAttr("icon");
 e.removeAttr("id");  
 e.removeAttr("integrity");  
 e.removeAttr("ismap");  
 e.removeAttr("itemprop");   
 e.removeAttr("keytype");
 e.removeAttr("kind");  
 e.removeAttr("label");  
 e.removeAttr("lang");  
 e.removeAttr("language");  
 e.removeAttr("list");
 e.removeAttr("loop");  
 e.removeAttr("low");  
 e.removeAttr("manifest");  
 e.removeAttr("max");  
 e.removeAttr("maxlength");
 e.removeAttr("minlength");  
 e.removeAttr("media");  
 e.removeAttr("method");  
 e.removeAttr("min");  
 e.removeAttr("multiple");
 e.removeAttr("muted");  
 e.removeAttr("name");  
 e.removeAttr("novalidate");  
 e.removeAttr("open");  
 e.removeAttr("optimum");
 e.removeAttr("pattern");  
 e.removeAttr("ping");  
 e.removeAttr("placeholder");  
 e.removeAttr("poster");
 e.removeAttr("preload");  
 e.removeAttr("radiogroup");  
 e.removeAttr("readonly");  
 e.removeAttr("rel");
 e.removeAttr("required");  
 e.removeAttr("reversed");  
 e.removeAttr("rows");  
 e.removeAttr("rowspan");  
 e.removeAttr("sandbox");  
 e.removeAttr("scope");  
 e.removeAttr("scoped");  
 e.removeAttr("seamless");  
 e.removeAttr("selected");  
 e.removeAttr("shape");  
 e.removeAttr("size");  
 e.removeAttr("sizes");  
 e.removeAttr("slot");  
 e.removeAttr("span");  
 e.removeAttr("spellcheck");  
 e.removeAttr("src");  
 e.removeAttr("srcdoc");  
 e.removeAttr("srclang");  
 e.removeAttr("srcset");  
 e.removeAttr("start");   
 e.removeAttr("step");  
 e.removeAttr("style");  
 e.removeAttr("summary");   
 e.removeAttr("tabindex");  
 e.removeAttr("target");  
 e.removeAttr("title");    
 e.removeAttr("type");  
 e.removeAttr("usemap");  
 e.removeAttr("value"); 
 e.removeAttr("valign");
 e.removeAttr("width");  
 e.removeAttr("wrap");
 
 //table related tags
 e.removeAttr("cellspacing");
 e.removeAttr("cellpadding");
 e.removeAttr("nowrap");
 e.removeAttr("sizcache");
 e.removeAttr("sizset");
 e.removeAttr("frame");
 e.removeAttr("rules");
 e.removeAttr("sortable");

//check files for the attributes not listed and put it here
 e.removeAttr("onsubmit");
e.removeAttr("role");

//norquest
e.removeAttr("aria-expanded");

//ohio
e.removeAttr("xmlns:a");
 	 }



 }


 }

else
{
	System.out.println("No tag/attribute selected to get cleaned");
}


//cleanFinal.pout.println(cleanFinal.doc + "\n\n-------------------------------------------\n" );
//cleanFinal.pout.close();
//cleanFinal.fout.close(); 

}
}
