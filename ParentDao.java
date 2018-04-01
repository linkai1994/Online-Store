import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParentDao {
	protected HttpServletRequest _request;
	protected HttpServletResponse _response;
	public ParentDao(HttpServletRequest request,HttpServletResponse response){
		_request=request;
		_response=response;
	}
}
