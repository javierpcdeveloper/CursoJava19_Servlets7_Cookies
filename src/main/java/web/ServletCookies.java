package web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet")
public class ServletCookies extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
        boolean nuevoUsuario=true;
        Cookie[] cookies=req.getCookies();
        if(cookies!=null){
            for(Cookie c:cookies){
                if(c.getName().equals("visitanteRecurrente") && c.getValue().equals("si")){
                    nuevoUsuario=false;
                    break;
                }
            }
        }
        String mensaje=null;
        if(nuevoUsuario){
            Cookie visitanteCookie=new Cookie("visitanteRecurrente","si");
            res.addCookie(visitanteCookie);
            mensaje="Gracias por visitar nuestro sitio por primera vez";
        }else{
            mensaje="Gracias por visitar nuevamente nuestra web";
        }
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        out.print("Mensaje:"+mensaje);
        out.close();
    }
}