/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


import Model.Municipio;
import Model.Regiao;
import Model.UF;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author José Alexandre
 */
public class test {
    String url = "http://localhost:8080/padeb_web_service/getServlet";
    public test() {
        
    }

   
    public void main(String[] args) throws FileNotFoundException {
    }
    
    public List<Municipio> getMunicipios() {
        InputStream in = null;
        try {
            HttpClient client = new HttpClient();
            PostMethod postMethod = new PostMethod(url);
            postMethod.addParameter("idSerie", "0");
//            postMethod.addParameter("tipoRede", "0");
//            postMethod.addParameter("localizacao", "0");
//            postMethod.addParameter("capital", "0");
            postMethod.addParameter("filtro", "filtroMunicipio");
            int statusCode = client.executeMethod(postMethod);
            if (statusCode != -1) {
                in = postMethod.getResponseBodyAsStream();
            }
            ObjectMapper mapper = new ObjectMapper();
            List<Municipio> listaMunicipios = (List<Municipio>) mapper.readValue(in, List.class);

            return listaMunicipios;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;        
    }
    
    public List<UF> getUFs() {
        InputStream in = null;
        try {
            HttpClient client = new HttpClient();
            PostMethod postMethod = new PostMethod(url);
            postMethod.addParameter("idSerie", "5");
            postMethod.addParameter("tipoRede", "0");
            postMethod.addParameter("localizacao", "0");
            postMethod.addParameter("capital", "0");
            postMethod.addParameter("filtro", "filtroUF");
            int statusCode = client.executeMethod(postMethod);
            if (statusCode != -1) {
                in = postMethod.getResponseBodyAsStream();
            }
            ObjectMapper mapper = new ObjectMapper();
            List<UF> listaUFs = (List<UF>) mapper.readValue(in, List.class);
//            listaUFs.get(0).getNome();
//            Gson gson = new Gson();
//            List<UF> listaUFs = gson.fromJson(in, new TypeToken<List<UF>>(){}.getType());

            return listaUFs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;        
    }
    
    public List<Regiao> getRegioes() {
        InputStream in = null;
        try {
            HttpClient client = new HttpClient();
            PostMethod postMethod = new PostMethod(url);
            postMethod.addParameter("idSerie", "5");
            postMethod.addParameter("tipoRede", "0");
            postMethod.addParameter("localizacao", "0");
            postMethod.addParameter("capital", "0");
            postMethod.addParameter("filtro", "filtroRegiao");
            int statusCode = client.executeMethod(postMethod);
            if (statusCode != -1) {
                in = postMethod.getResponseBodyAsStream();
            }
            ObjectMapper mapper = new ObjectMapper();
            List<Regiao> listaRegioes = (List<Regiao>) mapper.readValue(in, List.class);
//            listaUFs.get(0).getNome();
//            Gson gson = new Gson();
//            List<UF> listaUFs = gson.fromJson(in, new TypeToken<List<UF>>(){}.getType());

            return listaRegioes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;        
    }
    
    public List<Municipio> getMunicipiosForChart(List<Municipio> _municipios, List<UF> _ufs, List<Regiao> _regioes, int _serie, int _tipoRede, int _localizacao) {
        InputStream in = null;
        if (_municipios.isEmpty()) {
            return new ArrayList<Municipio>();
        }
        try {
            HttpClient client = new HttpClient();
            PostMethod postMethod = new PostMethod(url);
            StringBuilder strBuilder = new StringBuilder();
            try {
                for (Municipio _municipio : _municipios) {
                    strBuilder.append(_municipio.getMunicipio());
                    strBuilder.append(";");
                }
                postMethod.addParameter("idMunicipio", strBuilder.toString());
            } catch (Exception e) {
            }
            strBuilder.delete(0 , strBuilder.length());
            try {
                for (UF _uf : _ufs) {
                    strBuilder.append(_uf.getUf());
                    strBuilder.append(";");
                }
                postMethod.addParameter("idUF", strBuilder.toString());
            } catch (Exception e) {
            }
            strBuilder.delete(0 , strBuilder.length());
            try {
                for (Regiao _regiao : _regioes) {
                    strBuilder.append(_regiao.getRegiao());
                    strBuilder.append(";");
                }
                postMethod.addParameter("idRegiao", strBuilder.toString());
            } catch (Exception e) {
            }            
            postMethod.addParameter("idSerie", String.valueOf(_serie));
            postMethod.addParameter("tipoRede", String.valueOf(_tipoRede));
            postMethod.addParameter("localizacao", String.valueOf(_localizacao));
            postMethod.addParameter("filtro", "filtroMunicipio");
            int statusCode = client.executeMethod(postMethod);
            if (statusCode != -1) {
                in = postMethod.getResponseBodyAsStream();
            }
            ObjectMapper mapper = new ObjectMapper();
            List<Municipio> listaMunicipios = (List<Municipio>) mapper.readValue(in, List.class);

            return listaMunicipios;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;        
    }
    
    public List<UF> getUFsForChart(List<UF> _ufs, List<Regiao> _regioes, int _serie, int _tipoRede, int _localizacao, int _capital) {
        InputStream in = null;
        if (_ufs.isEmpty()) {
            return new ArrayList<UF>();
        }
        try {
            HttpClient client = new HttpClient();
            PostMethod postMethod = new PostMethod(url);
            StringBuilder strBuilder = new StringBuilder();
            try {
                for (UF _uf : _ufs) {
                    strBuilder.append(_uf.getUf());
                    strBuilder.append(";");
                }
                postMethod.addParameter("idUF", strBuilder.toString());
            } catch (Exception e) {
            }
            strBuilder.delete(0 , strBuilder.length());
            try {
                for (Regiao _regiao : _regioes) {
                    strBuilder.append(_regiao.getRegiao());
                    strBuilder.append(";");
                }
                postMethod.addParameter("idRegiao", strBuilder.toString());
            } catch (Exception e) {
            }            
            postMethod.addParameter("idSerie", String.valueOf(_serie));
            postMethod.addParameter("tipoRede", String.valueOf(_tipoRede));
            postMethod.addParameter("localizacao", String.valueOf(_localizacao));
            postMethod.addParameter("capital", String.valueOf(_capital));
            postMethod.addParameter("filtro", "filtroUF");
            int statusCode = client.executeMethod(postMethod);
            if (statusCode != -1) {
                in = postMethod.getResponseBodyAsStream();
            }
            ObjectMapper mapper = new ObjectMapper();
            List<UF> listaUFs = (List<UF>) mapper.readValue(in, List.class);

            return listaUFs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;        
    }
    
    public List<Regiao> getRegioesForChart(List<Regiao> _regioes, int _serie, int _tipoRede, int _localizacao, int _capital) {
        InputStream in = null;
        if (_regioes.isEmpty()) {
            return new ArrayList<Regiao>();
        }
        try {
            HttpClient client = new HttpClient();
            PostMethod postMethod = new PostMethod(url);
            StringBuilder strBuilder = new StringBuilder();
            try {
                for (Regiao _regiao : _regioes) {
                    strBuilder.append(_regiao.getRegiao());
                    strBuilder.append(";");
                }
                postMethod.addParameter("idRegiao", strBuilder.toString());
            } catch (Exception e) {
            }            
            postMethod.addParameter("idSerie", String.valueOf(_serie));
            postMethod.addParameter("tipoRede", String.valueOf(_tipoRede));
            postMethod.addParameter("localizacao", String.valueOf(_localizacao));
            postMethod.addParameter("capital", String.valueOf(_capital));
            postMethod.addParameter("filtro", "filtroRegiao");
            int statusCode = client.executeMethod(postMethod);
            if (statusCode != -1) {
                in = postMethod.getResponseBodyAsStream();
            }
            ObjectMapper mapper = new ObjectMapper();
            List<Regiao> listaRegioes = (List<Regiao>) mapper.readValue(in, List.class);

            return listaRegioes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;        
    }
}
