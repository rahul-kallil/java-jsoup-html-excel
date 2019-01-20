package GeeBeeCleanHTML;

import java.awt.event.ActionEvent;
import java.util.regex.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.record.CFRuleBase.ComparisonOperator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColorScaleFormatting;
import org.apache.poi.xssf.usermodel.XSSFConditionalFormattingRule;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheetConditionalFormatting;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import javax.swing.JOptionPane;
import GeeBeeCleanHTML.choosePropFile;


public class cleanFinal extends JFrame implements ActionListener, Callback  {

  	/**
	 * Public constants

	 */
	 static Document doc;
	 //static PrintStream pout;
	// static FileOutputStream fout;
	public static final String[] FILE_TYPES = new String[] { "xls", "xlsx" };

	/**
	 * Singleton object
	 */
	private static cleanFinal instance = null;

	/**
	 * Java Swing components
	 */
	private static JFrame frame;

	private JPanel panel;
	public static JFileChooser fileChooser;
	private static JButton openBtn;

	/**
	 * Internal constants
	 */
	private static final String CONTAINER_TITLE = "CleanHTMLFromExcelSheet";
	private static final String OPEN_BTN_ACTIVE_TEXT = "Open an excel file";
	private static final String OPEN_BTN_INACTIVE_TEXT = "Parsing the XLSX file...";
	private static final String EXIT_BTN_ACTIVE_TEXT = "Exit now";
	private static final String FILE_FILTER_TITLE = "All Microsoft EXCEL files (*.xlsx, *.xls)";
	private static final String GAP = "   ";
	//public static File fl;

	private static Set<String> tagsIDontWantToNest = new HashSet<>(Arrays.asList("!--","a","b","big","body","br","center",
			"dd","dl","em","dt","embed","font","form","h1","h2","h3","h3","h4","h5","h6","head",
			"hr","html","i","img","input","!DOCTYPE","abbr","acronym","address","applet","area",
			"article","aside","audio","base","basefont","bdi","bdo","blockquote","button","canvas",
			"caption","cite","code","col","colgroup","data","datalist","dd","del","details","dfn",
			"dialog","dir","div","fieldset","figcaption","figure","font","footer","form","frame",
			"frameset","head","header","hr","html","i","iframe","img","input","ins","kbd","label",
			"legend","li","link","main","map","mark","menu","menuitem","meta","meter","nav","noframes",
			"noscript","object","ol","optgroup","option","output","p","param","picture","pre","progress",
			"q","rt","rp","ruby","s","samp","script","small","section","select","source","span","strike",
			"strong","style","sub","summary","sup","svg","table","tbody","td","template","textarea","tfoot",
			"th","thead","time","title","tr","track","tt","u","ul","var","video","wbr"));

	private static boolean hasSameTypeAncestor(Element element) {

	    Tag tag = element.tag();
	    //handle only these tags: <b> <i> <u>
	    if (tagsIDontWantToNest.contains(tag.getName())) {
	        for (Element el : element.parents()) {
	            if (el.tag().equals(tag)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	protected cleanFinal() {
		
		new choosePropFile();
		fileChooser = new JFileChooser();
		
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new FileNameExtensionFilter(
				FILE_FILTER_TITLE, FILE_TYPES));
		
		openBtn = new JButton(OPEN_BTN_ACTIVE_TEXT);
		openBtn.addActionListener(this);
		
		//exitBtn = new JButton(EXIT_BTN_ACTIVE_TEXT);
		//exitBtn.addActionListener(this);
		
		//chooseButton=new JButton("Choose Destination Folder");
		//chooseButton.addActionListener(this);
		panel = new JPanel();
		panel.add(openBtn);
		//panel.add(propopenBtn);
		//panel.add(chooseButton);
		//panel.add(new JLabel(GAP));
		//panel.add(exitBtn);
		
				frame = new JFrame(CONTAINER_TITLE);
		
		frame.getContentPane().add(panel,"Center");
	    frame.setSize(panel.getPreferredSize());
	   
		//frame.setSize(500, 90);
		
		
		
		frame.setResizable (true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		//frame.setVisible(true);
		frame.pack();
		
		
		
		
	}

	/**
	 * Singleton class get method
	 * 
	 * @return
	 */
	public static cleanFinal getInstance() {
		if (instance == null) {
			instance = new cleanFinal();
		}
		return instance;
	}

	public static void setOpenBtnEnabled(boolean isEnabled) {
		openBtn.setEnabled(isEnabled);
		openBtn.setText(isEnabled ? OPEN_BTN_ACTIVE_TEXT
				: OPEN_BTN_INACTIVE_TEXT);
		//chooseButton.setEnabled(isEnabled);
		frame.pack();
	}

	private void parseXLS() {

		setOpenBtnEnabled(false);
		XLS2HTMLParser parser = new XLS2HTMLParser(
				fileChooser.getSelectedFile(), this);
		new Thread(parser).start();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		
		
		if (ae.getSource() == openBtn) {
			int retVal = fileChooser.showOpenDialog(frame);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				parseXLS();
			}
			
		} 
		
			
		
		else 
			//if (ae.getSource() == exitBtn) 
			{
			System.exit(0);
		}
	}

	public void execute() {
		setOpenBtnEnabled(true);
		
	}

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		cleanFinal.getInstance();
	//	fout=new FileOutputStream("Output after cleaning GEEBEE.txt");
		//pout=new PrintStream(fout);
		//pout.println(doc);
		
		
		//pout.close();
		//fout.close();
		
		//JFrame frame = new JFrame("");
		
		
	}
	public static String convertToString(Object[][] M) {
	    String separator = " !!#";
	    
	    StringBuffer result = new StringBuffer();

	    // iterate over the first dimension
	    for (int i = 0; i < M.length; i++) {
	        // iterate over the second dimension
	    	//result.append(" [ ");
	        for(int j = 0; j < M[i].length; j++){
	            result.append(M[i][j]);
	            result.append(separator);
	            
	        }
	        // remove the last separator
	        result.setLength(result.length() - separator.length());
	        // add a line break.
	        result.append(" ] ");
	    }
	    return result.toString();
	}
	static void parse() throws IOException,NullPointerException

	{
		File f=fileChooser.getSelectedFile();
	
	 XSSFRow row;
     XSSFCell cell;
     Object[][] value = null;
     double[][] nums = null;
     FileInputStream inputStream = new FileInputStream(f.getAbsolutePath());
     XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
  // get sheet number
     int sheetCn = workbook.getNumberOfSheets();

     for (int cn = 0; cn < sheetCn; cn++) {

         // get 0th sheet data
         XSSFSheet sheet = workbook.getSheetAt(cn);

         // get number of rows from sheet
         int rows = sheet.getPhysicalNumberOfRows();

         // get number of cell from row
         int cells=1;
         if (isRowEmpty(sheet.getRow(cn))== true){
             row = sheet.createRow(cn);
             System.out.println("ROW EMPTY");
             //cells=row.getPhysicalNumberOfCells();
         } 
         else{
         cells = sheet.getRow(cn).getPhysicalNumberOfCells();
         }
         value = new Object[rows][cells];
         
         /*if(value.length>32000)
         {
        	 System.out.println("\n 32000 limit reached");
         }*/
         for (int r = 0; r < rows; r++) {
             row = sheet.getRow(r); // bring row
            MissingCellPolicy null_as_blank = row.CREATE_NULL_AS_BLANK;
            if(row==null)
            {
            	System.out.println("empty row");
            }
            
             if (row != null) {
                 for (int c = 0; c < cells; c++) {
                     cell = row.getCell(c,Row.RETURN_BLANK_AS_NULL);
                     nums = new double[rows][cells];
                     if(cell == null)
                     {
                    	 
                    	 System.out.println("empty cell");
                     }
                     if (cell != null ) {
                    	 
                         switch (cell.getCellType()) {

                         case XSSFCell.CELL_TYPE_FORMULA:
                             value[r][c] = cell.getCellFormula();
                             break;

                         case XSSFCell.CELL_TYPE_NUMERIC:
                             value[r][c] = ""
                                  + cell.getNumericCellValue();
                              break;

                         case XSSFCell.CELL_TYPE_STRING:
                             value[r][c] = ""
                                  + cell.getStringCellValue();
                             if(cell.getStringCellValue().length()>32766)
                             {
                             System.out.println("32767 limit reached");
                             JOptionPane.showMessageDialog(fileChooser, " Character Limit Reached For Cell No "+cell.getRowIndex(),"ERROR",JOptionPane.INFORMATION_MESSAGE);
                             System.out.println(cell.getRowIndex());
                             setOpenBtnEnabled(true);
                     		//XLS2HTMLParser parser = new XLS2HTMLParser(
                     				//fileChooser.getSelectedFile(), XLS2HTMLParser());
                     		//new Thread(parser).start();
                             }
                             break;

                         case XSSFCell.CELL_TYPE_BLANK:
                            value[r][c] = ""+cell.getStringCellValue();
                            if(cell.getStringCellValue().length()>32766)
                            {
                            System.out.println("32000 limit reached");
                            }
                            break;

                         case XSSFCell.CELL_TYPE_ERROR:
                            value[r][c] = ""+cell.getErrorCellValue();
                          break;
                      default:
                      }
                     // System.out.print(value[r][c]);

                  } //else {
                      //System.out.print("[null]\t");
                  //}
              } // for(c)
              //System.out.print("\n");
          }
      } // for(r)
  }

     System.out.println(Runtime.getRuntime().freeMemory()); //free memory avaialable
 	System.out.println("initial heap size"+Runtime.getRuntime().totalMemory()); //initial heap size
 	System.out.println("max heap size"+Runtime.getRuntime().maxMemory());
 	System.out.println("USED MEMORY"+(Runtime.getRuntime().maxMemory()-
 			Runtime.getRuntime().freeMemory()));
 	
 	//next partitn nai baba toh
 String s= convertToString(value);
//pout.println("READING DONE \n\n");
 doc = Jsoup.parse(s);
 for (Element el : doc.getAllElements()) {
     if (hasSameTypeAncestor(el)) {
         el.unwrap();
     }
 }

	//doc = Jsoup.parse(s,"",Parser.xmlParser()); 
 
//PROPERTIES
    
	
	//prop.load(new FileInputStream("C:\\Users\\admin\\Desktop\\GEEBEE\\config.properties"));
    
   new properties();
   
   
	
	
	//pout.println("\n\n\n\n CLEANING DONE \n\n");
//pout.println(doc);
   
 //Mimimum acceptable free memory you think your app needs
   long minRunningMemory = (1024*1024);

   Runtime runtime = Runtime.getRuntime();

   if(runtime.freeMemory()<minRunningMemory)
    System.gc();
	String docToString=doc.toString();
	Properties p=new Properties();
	
	//System.out.println("chosen prop fiile: "+propFileChooser.getSelectedFile()); 
	  // p.load(new FileInputStream("C:\\Users\\admin\\Desktop\\GEEBEE\\props\\GeeBeeProperties.properties"));
	   
	
	String absPath=choosePropFile.f1.getAbsolutePath(); 
	absPath.replace("\\", "\\\\");  
	
	
	p.load(new FileInputStream(absPath));
	
	
	
	if(p.getProperty("Remove_nbsp_method") != null)
	   {
			
			docToString =docToString.replaceAll("&nbsp;", "");
			//doc.select(":").remove();
	   }
	   
	
	//System.out.println("Document to string" +docToString);
	docToString=docToString.replaceAll("null","");
	//docToString=docToString.trim().replaceAll(" +", " ");
	docToString=docToString.trim();
	docToString= docToString.replaceAll("\\<html\\>","");
	docToString= docToString.replaceAll("\\<body\\>","");
	
	docToString= docToString.replaceAll("\\<.body\\>","");
	docToString= docToString.replaceAll("\\<.html\\>","");
	if(docToString.contains("amp;"))
	{
		docToString=docToString.replaceAll("amp;","");
	}
	/*Pattern pt=Pattern.compile("/^!!#.+",Pattern.DOTALL);
	Matcher mt=pt.matcher(docToString);
	boolean matchFound=mt.matches();
if(matchFound==true)
	{
		docToString=mt.replaceAll("");
	}*/
	
	
	//docToString= docToString.replace("&lt;//body&gt;<//html>", "");
	String[] rows=docToString.split(" ] ");
	String[][] matrix=new String[rows.length][];
	int r=0;
	for(String row1 : rows)
	{
		matrix[r++]=row1.split("!!#");
	}
	
	/*String[] strArray=docToString.split(",");
	for(int i=0;i<strArray.length;i++)
	{
		System.out.println(strArray);
		
	}*/
	
	//System.out.println("outside loop-----------------\n" +strArray);
	XSSFWorkbook wb= new XSSFWorkbook();
   	XSSFSheet sheet1=wb.createSheet("new sheet");
   	CreationHelper helper=wb.getCreationHelper();
   	
  StringBuilder sb=new StringBuilder();
  
	//String htmlstring=doc.toString();
	//String innerhtmlstring=doc.html();
	//System.out.println(innerhtmlstring);
	
	
	 XSSFWorkbook wb1= new XSSFWorkbook();
	   	XSSFSheet sheet11=wb1.createSheet("new sheet");
	   	String[][] exceldata=matrix;
	   	int rowCount=0;
	   	XSSFCellStyle Ystyle=wb1.createCellStyle();
		Ystyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		Ystyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
	   	Ystyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
	
	   	
	  	CellStyle Rstyle=wb1.createCellStyle();
	   	Rstyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
	   	Row row1 = null;
	    Cell cell1=null;
	   	for(String[] o1:exceldata)
	   	{																										//for o1 open
	   		row1=sheet11.createRow(rowCount++);
	   		int columnCount=0;
	  
	   		for(Object field: o1)
	   		{ 																									//for field open

	   			cell1=row1.createCell(columnCount++);
	   			if(field instanceof String)
	   			{																									//if open
	   				cell1.setCellValue((String)field);
	   				

	   			}																									//if close
	   			
	   			else if(field instanceof Integer)
	   			{																								//else if open
	   				cell1.setCellValue((Integer)field);

	   			}																								//else if close
	   		
	   		}																									//for field close
	   		
	   		
	   	} 							
	XSSFSheetConditionalFormatting cond1=sheet1.getSheetConditionalFormatting();
	XSSFConditionalFormattingRule rule1=cond1.createConditionalFormattingRule(ComparisonOperator.EQUAL,"Program_Title");
	  
	XSSFConditionalFormattingRule rule2=cond1.createConditionalFormattingRule("YELLOW");
	rule2.createPatternFormatting();
	   	//for o1 close
	   	
	   	
	   //	JFileChooser chooser;
		//chooser = new JFileChooser(); 
		
	    //chooser.setCurrentDirectory(new java.io.File("."));
	   // File file = null;
	    //chooser.setDialogTitle(choosertitle);
	    //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	 	//String fileName = file.getName();
		//String folderName = file.getParent();

	   	//File newFile = new File(folderName + '\\' + fileName + '-' + System.currentTimeMillis() + "xlsx");
	   	Properties path_prop=new Properties();
	   //	path_prop.load(new FileInputStream("C:\\Users\\admin\\Desktop\\GEEBEE\\props\\GeeBeeProperties.properties"));
	   	
	   	path_prop.load(new FileInputStream(absPath));
	   	
	   	/*line 359: has file path it has to be changed acc to users system
	   	 * */
	   	 try{
	   		 
	   		if(path_prop.getProperty("path") != null)
	   		{
	   			String folder_string=path_prop.getProperty("path");
	   			String file_name=fileChooser.getSelectedFile().getName();
	   			String add_clean_to_fileName=folder_string.concat("Cleaned_");
	   			String full_file_name=add_clean_to_fileName.concat(file_name);
	   			File newFile=new File(full_file_name);
	   			//String path_string=folder_string.concat();
	   			FileOutputStream os=new FileOutputStream(newFile);
	   			wb1.write(os);
	   			//System.out.println("path=" + path_prop.getProperty("path") + "\t file name:" + file_name);
	   		}
	   		}
	   	 catch(Exception e)
	   	 {
	   		 System.out.println("Exception occurred");
	   		 
	   	 }
	   	 
	   	 
	   	
	   	//try(FileOutputStream os=new FileOutputStream(new java.io.File(".xlsx")))
	   	//{																												//try open
	   		//wb1.write(os);
	   		
	   //	}																										//try close
	
	}
	private static boolean isRowEmpty(XSSFRow row) {
		// TODO Auto-generated method stub
		if (row == null){
            return true;
        }
        else {
            return false;
        }
	}
}//class close
interface Callback {
	public void execute();
}

final class XLS2HTMLParser  implements Runnable {
File newFile;
File tempFile;
//unimplemented method


	 File file;
	private Callback callback;
public void run() {
		
		try {
			cleanFinal.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		callback.execute();
	}
	XLS2HTMLParser(File file, Callback callback) {
		this.file = file;
		this.callback = callback;
	}
}







