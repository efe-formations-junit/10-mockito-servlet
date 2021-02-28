package fr.formation.servlet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class PerimetreServletTest  {
	
	@Mock
    HttpServletRequest request;
 
    @Mock
    HttpServletResponse response;
    
    
	@Test
	public void testPerimetreOK() throws IOException, ServletException {
		
		double value = 4 * Math.PI * 2;
		String res = String.valueOf(value).substring(0, 5);
		
		System.out.println(res);
		when(request.getParameter("rayon")).thenReturn("4");
		
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
         
        when(response.getWriter()).thenReturn(pw);
        
        PerimetreServlet myServlet =new PerimetreServlet();
        myServlet.doGet(request, response);
        String result = sw.getBuffer().toString().trim();
        assertTrue(result.contains(res));
	}
    
	@Test
	public void testPerimetreRayonNegatif() throws IOException, ServletException {
		
		when(request.getParameter("rayon")).thenReturn("-4");
		
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
         
        when(response.getWriter()).thenReturn(pw);
        
        PerimetreServlet myServlet =new PerimetreServlet();
        myServlet.doGet(request, response);
        String result = sw.getBuffer().toString().trim();
        assertTrue(result.contains("strictement positif"));
	}
    
	@Test
	public void testPerimetreRayonNonNumerique() throws IOException, ServletException {
		
		when(request.getParameter("rayon")).thenReturn("coucou");
		
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
         
        when(response.getWriter()).thenReturn(pw);
        
        PerimetreServlet myServlet =new PerimetreServlet();
        myServlet.doGet(request, response);
        String result = sw.getBuffer().toString().trim();
        assertTrue(result.contains("etre un entier"));
	}
    
	@Test
	public void testPerimetreRayonNull() throws IOException, ServletException {
		
		when(request.getParameter("rayon")).thenReturn(null);
		
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
         
        when(response.getWriter()).thenReturn(pw);
        
        PerimetreServlet myServlet =new PerimetreServlet();
        myServlet.doGet(request, response);
        String result = sw.getBuffer().toString().trim();
        assertFalse(result.contains("du cercle de rayon"));
	}

}
