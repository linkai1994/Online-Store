import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload{

	private List<String> filenamelist;	
	
	private Map<String,String> requestvalues;
	
	public Map<String, String> getRequetvalues() {
		return requestvalues;
	}


	public List<String> getFilename() {
		return filenamelist;
	}

	public void setFilename(List<String> filenamelist) {
		this.filenamelist = filenamelist;
	}

	public Upload(){
		
		this.requestvalues=new HashMap<String,String>();
		this.filenamelist=new ArrayList<String>();
	}
	
	public void uploadcontent(HttpServletRequest request)
	{
		if(ServletFileUpload.isMultipartContent(request))
		{
			//实例化工厂对象
			FileItemFactory factory=new DiskFileItemFactory();
			//实例化上传对象
			ServletFileUpload upload=new ServletFileUpload(factory);
			
			
			try {
				//得到表单中的所有对象
				List<?> items =upload.parseRequest(request);
				
				Iterator<?> iter=items.iterator();
				
				
				while(iter.hasNext())
				{
					FileItem item=(FileItem)iter.next();
					
					if(!item.isFormField())
					{
						String tempfilename=item.getName();
						
						//System.out.println(filename);
						
						int index=tempfilename.lastIndexOf("\\");
						
						tempfilename=tempfilename.substring(index+1);
						
						index=tempfilename.indexOf(".");
						
						String extension=tempfilename.substring(index);
						
						String filename=java.util.UUID.randomUUID().toString()+extension;
									
						String realpath=request.getSession().getServletContext().getRealPath("image/upload");
						
						File file=new File(realpath, filename);
						
						item.write(file);
						
						this.filenamelist.add(filename);
					}
					else
					{
						requestvalues.put(item.getFieldName(), item.getString());
					}
					
				}			
				
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		
		
	
		

}
