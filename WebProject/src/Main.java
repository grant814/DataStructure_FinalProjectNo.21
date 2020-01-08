

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		String keyword = request.getParameter("keyword");
		GoogleQuery google = new GoogleQuery(keyword);
		HashMap<String, String> query = google.query();
		
		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for(Entry<String, String> entry : query.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    s[num][0] = key;
		    s[num][1] = value;
		    num++;
		}
		
		
		//sorting
		int[] counts = new int[s.length];
		for(int i = 0 ; i < s.length; i++) {
			String[] a = s[i];
			WordCounter counter = new WordCounter(a[1]);
//			int count = 0;
			int count = counter.countKeyword(keyword);
			count += counter.countKeyword("浪漫");
			count += counter.countKeyword("氣氛");
			count += counter.countKeyword("情侶");
			count += counter.countKeyword("約會");
			counts[i] = count;
		}
	    //counts = [3,6,7,4,9,10]
		// s = [["nccu", "nccu.edu.tw"], [],
		/*for(int i = 0; i < counts.length; i++)
		{
			int e = counts[i];
			if(e)
		
		}*/
		
		int min = -1;
		for(int i=0; i<counts.length; i++) {
			for(int j = i; j < counts.length; j++) {
				if(min == -1)
					min = j;
				else if(counts[j] < counts[min])
					min = j;
			}
			int temp = counts[i];
			counts[i] = counts[min];
			counts[min] = temp;
			String[] tp = s[i];
			s[i] = s[min];
			s[min] = tp;
			min = -1;
		}
		
		request.getRequestDispatcher("googleitem.jsp")
		 .forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
