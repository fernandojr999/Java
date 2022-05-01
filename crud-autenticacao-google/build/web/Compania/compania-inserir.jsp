<%-- 
    Document   : form
    Created on : Mar 17, 2022, 3:09:25 PM
    Author     : friend
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adicionar compania</title>
    </head>
    <body>
        <h1>Adicionar compania</h1>
        <form action="compania" method="post">
            ID: <input type="text" name="id" value="2321545455"> <br>
            Nome: <input type="text" name="nome" value="Empresa do Fernando"> <br>
            Dominio: <input type="text" name="dominio" value="fernando.com.br"> <br>
            Ano: <input type="text" name="ano" value="2022"> <br>
            Industria: <input type="text" name="industria" value="Software"> <br>
            Tamanho: <input type="text" name="tamanho" value="Grande"> <br>
            Localização: <input type="text" name="localizacao" value="Blumenau-SC"> <br>
            Pais: <input type="text" name="pais" value="Nigéria"> <br>
            LinkedIn: <input type="text" name="linkedin" value="linkedin teste"> <br>
            Empregados Atual: <input type="number" name="empregados_atual" value="123456"> <br>
            Empregados Total: <input type="number" name="empregados_total" value="321144"> <br>
            
            <input type="submit" value="Cadastrar">
        </form>
    </body>
</html>
