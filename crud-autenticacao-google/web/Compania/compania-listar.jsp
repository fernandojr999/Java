<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.util.*"
        import="modelo.Compania" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Companias</title>
    </head>
    <body>
        <h1>Listagem de compania</h1>
        <%
        ArrayList<Compania> registros = (ArrayList<Compania>) request.getAttribute("registros");
                 
        for (Compania i : registros) {
        
        %>

        <%= i.getId() %>, <%= i.getNome() %>, 
        <%= i.getDominio() %>, <%= i.getAno() %> 
        
        <!--| <a href="compania?op=d&q=<%= i.getId() %>">remover</a> 
        <!--<a href="compania?op=r&q=<%= i.getId() %>">exibir</a> | 
        <a href="compania?op=a&q=<%= i.getId() %>">atualizar</a> <br> -->
        <br> 
        <%
            }
        %>

        <%
            int qtdPaginas = (int)request.getAttribute("qtdPaginas");
            for (int i = 0; i < qtdPaginas; i++) {
        %>        
                <a href="compania?page=<%= i %>"><%= i+1 %></a>
        <%                
            }
        %>

    </body>
</html>
