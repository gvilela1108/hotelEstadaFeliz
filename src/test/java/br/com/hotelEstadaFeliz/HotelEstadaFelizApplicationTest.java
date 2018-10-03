package br.com.hotelEstadaFeliz;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hotelEstadaFeliz.dto.DadosCliente;
import br.com.hotelEstadaFeliz.dto.DadosFuncionario;
import br.com.hotelEstadaFeliz.dto.DadosHospedagem;
import br.com.hotelEstadaFeliz.dto.DadosHotel;
import br.com.hotelEstadaFeliz.dto.DadosLogin;
import br.com.hotelEstadaFeliz.dto.DadosProduto;
import br.com.hotelEstadaFeliz.dto.DadosQuarto;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HotelEstadaFelizApplicationTest {

    private RestTemplate restTemplate;  

    final String BASE_PATH = "http://localhost:9090/hotelEstadaFeliz/rest";
     
    @Before
    public void testInit() throws Exception {  
        restTemplate = new RestTemplate();
    }
    
    @Test
    public void doLogin() throws JsonProcessingException {
    	DadosLogin login = new DadosLogin();
    	login.setEmail("a@a.com");
    	login.setSenha("teste");
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/login",login, Map.class);
    	Assert.assertTrue(map.containsKey("tipoLogin"));
    }
     
    @Test
    public void getConsultarClienteTest() throws JsonProcessingException{
    	DadosCliente cliente = new DadosCliente();
    	cliente.setCpf(12345678903l);
    	cliente.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/consultarCliente",cliente, Map.class);
    	Assert.assertTrue(map.containsKey("cliente"));
    }
    
    @Test
    public void inserirClienteTest() throws JsonProcessingException, URISyntaxException{
    	DadosCliente cliente = new DadosCliente();
    	cliente.setCpf(12345678903l);
    	cliente.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/inserirCliente",cliente, Map.class);
    	Assert.assertTrue(map.containsKey("cliente"));
    }

    @Test
    public void atualizarClienteTest() throws JsonProcessingException, URISyntaxException{
    	DadosCliente cliente = new DadosCliente();
    	cliente.setCpf(12345678903l);
    	cliente.setIdTipoFuncionario(1l);
    	cliente.setTelefone("1112345678");

    	restTemplate.put(BASE_PATH + "/atualizarCliente", cliente);
    	
    	URI uri = new URI(BASE_PATH + "/atualizarCliente");
    	RequestEntity<DadosCliente> requestEntity = RequestEntity.put(uri).body(cliente);
    	ResponseEntity<Map> result = restTemplate.exchange(requestEntity, Map.class);
 
    	Assert.assertTrue(result.getBody().containsKey("cliente"));
    }
    
    @Test
    public void excluirClienteTest() throws JsonProcessingException, URISyntaxException{
        String url = BASE_PATH + "/deletarCliente";

    	DadosCliente cliente = new DadosCliente();
    	cliente.setCpf(12345678903l);
    	cliente.setIdTipoFuncionario(1l);
    	
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(cliente);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON) ;
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(jsonInString, headers);
        ResponseEntity<Map> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, Map.class);
        Assert.assertTrue(result.getBody().containsKey("cliente"));
        
    }
    
    @Test
    public void getConsultarFuncionarioTest() throws JsonProcessingException{
    	DadosFuncionario funcionario = new DadosFuncionario();
    	funcionario.setCpf(12345678903l);
    	funcionario.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/consultarFuncionario",funcionario, Map.class);
    	Assert.assertTrue(map.containsKey("funcionario"));
    }
    
    @Test
    public void inserirFuncionarioTest() throws JsonProcessingException, URISyntaxException{
    	DadosFuncionario funcionario = new DadosFuncionario();
    	funcionario.setCpf(12345678903l);
    	funcionario.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/inserirFuncionario",funcionario, Map.class);
    	Assert.assertTrue(map.containsKey("funcionario"));
    }

    @Test
    public void atualizarFuncionarioTest() throws JsonProcessingException, URISyntaxException{
    	DadosFuncionario funcionario = new DadosFuncionario();
    	funcionario.setCpf(12345678903l);
    	funcionario.setIdTipoFuncionario(1l);
    	funcionario.setTelefone("1112345678");

    	restTemplate.put(BASE_PATH + "/atualizarFuncionario", funcionario);
    	
    	URI uri = new URI(BASE_PATH + "/atualizarFuncionario");
    	RequestEntity<DadosFuncionario> requestEntity = RequestEntity.put(uri).body(funcionario);
    	ResponseEntity<Map> result = restTemplate.exchange(requestEntity, Map.class);
 
    	Assert.assertTrue(result.getBody().containsKey("funcionario"));
    }
    
    @Test
    public void excluirFuncionarioTest() throws JsonProcessingException, URISyntaxException{
        String url = BASE_PATH + "/deletarFuncionario";

    	DadosFuncionario funcionario = new DadosFuncionario();
    	funcionario.setCpf(12345678903l);
    	funcionario.setIdTipoFuncionario(1l);
    	
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(funcionario);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON) ;
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(jsonInString, headers);
        ResponseEntity<Map> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, Map.class);
        Assert.assertTrue(result.getBody().containsKey("funcionario"));
        
    }
    
    @Test
    public void getConsultarHospedagemTest() throws JsonProcessingException{
    	DadosHospedagem hospedagem = new DadosHospedagem();
    	hospedagem.setIdQuarto("1");
    	hospedagem.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/consultarHospedagem",hospedagem, Map.class);
    	Assert.assertTrue(map.containsKey("hospedagem"));
    }
    
    @Test
    public void inserirHospedagemTest() throws JsonProcessingException, URISyntaxException{
    	DadosHospedagem hospedagem = new DadosHospedagem();
    	hospedagem.setIdQuarto("1");
    	hospedagem.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/inserirHospedagem",hospedagem, Map.class);
    	Assert.assertTrue(map.containsKey("hospedagem"));
    }

    @Test
    public void atualizarHospedagemTest() throws JsonProcessingException, URISyntaxException{
    	DadosHospedagem hospedagem = new DadosHospedagem();
    	hospedagem.setId("1");
    	hospedagem.setIdTipoFuncionario(1l);
    	hospedagem.setIdQuarto("1");

    	restTemplate.put(BASE_PATH + "/atualizarHospedagem", hospedagem);
    	
    	URI uri = new URI(BASE_PATH + "/atualizarHospedagem");
    	RequestEntity<DadosHospedagem> requestEntity = RequestEntity.put(uri).body(hospedagem);
    	ResponseEntity<Map> result = restTemplate.exchange(requestEntity, Map.class);
 
    	Assert.assertTrue(result.getBody().containsKey("hospedagem"));
    }
    
    @Test
    public void excluirHospedagemTest() throws JsonProcessingException, URISyntaxException{
        String url = BASE_PATH + "/deletarHospedagem";

    	DadosHospedagem hospedagem = new DadosHospedagem();
    	hospedagem.setId("1");
    	hospedagem.setIdTipoFuncionario(1l);
    	
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(hospedagem);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON) ;
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(jsonInString, headers);
        ResponseEntity<Map> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, Map.class);
        Assert.assertTrue(result.getBody().containsKey("hospedagem"));
        
    }
    
    @Test
    public void getConsultarHotelTest() throws JsonProcessingException{
    	DadosHotel hotel = new DadosHotel();
    	hotel.setId("1");
    	hotel.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/consultarHotel",hotel, Map.class);
    	Assert.assertTrue(map.containsKey("hotel"));
    }
    
    @Test
    public void inserirHotelTest() throws JsonProcessingException, URISyntaxException{
    	DadosHotel hotel = new DadosHotel();
    	hotel.setId("1");
    	hotel.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/inserirHotel",hotel, Map.class);
    	Assert.assertTrue(map.containsKey("hotel"));
    }

    @Test
    public void atualizarHotelTest() throws JsonProcessingException, URISyntaxException{
    	DadosHotel hotel = new DadosHotel();
    	hotel.setId("1");
    	hotel.setIdTipoFuncionario(1l);
    	hotel.setTelefone("1112345678");

    	restTemplate.put(BASE_PATH + "/atualizarHotel", hotel);
    	
    	URI uri = new URI(BASE_PATH + "/atualizarHotel");
    	RequestEntity<DadosHotel> requestEntity = RequestEntity.put(uri).body(hotel);
    	ResponseEntity<Map> result = restTemplate.exchange(requestEntity, Map.class);
 
    	Assert.assertTrue(result.getBody().containsKey("hotel"));
    }
    
    @Test
    public void excluirHotelTest() throws JsonProcessingException, URISyntaxException{
        String url = BASE_PATH + "/deletarHotel";

    	DadosHotel hotel = new DadosHotel();
    	hotel.setId("1");
    	hotel.setIdTipoFuncionario(1l);
    	
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(hotel);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON) ;
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(jsonInString, headers);
        ResponseEntity<Map> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, Map.class);
        Assert.assertTrue(result.getBody().containsKey("hotel"));
        
    }
    
    @Test
    public void getConsultarProdutoTest() throws JsonProcessingException{
    	DadosProduto produto = new DadosProduto();
    	produto.setId("1");
    	produto.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/consultarProduto",produto, Map.class);
    	Assert.assertTrue(map.containsKey("produto"));
    }
    
    @Test
    public void inserirProdutoTest() throws JsonProcessingException, URISyntaxException{
    	DadosProduto produto = new DadosProduto();
    	produto.setId("1");
    	produto.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/inserirProduto",produto, Map.class);
    	Assert.assertTrue(map.containsKey("produto"));
    }

    @Test
    public void atualizarProdutoTest() throws JsonProcessingException, URISyntaxException{
    	DadosProduto produto = new DadosProduto();
    	produto.setId("1");
    	produto.setIdTipoFuncionario(1l);
    	produto.setQuantidade(10l);

    	restTemplate.put(BASE_PATH + "/atualizarProduto", produto);
    	
    	URI uri = new URI(BASE_PATH + "/atualizarProduto");
    	RequestEntity<DadosProduto> requestEntity = RequestEntity.put(uri).body(produto);
    	ResponseEntity<Map> result = restTemplate.exchange(requestEntity, Map.class);
 
    	Assert.assertTrue(result.getBody().containsKey("produto"));
    }
    
    @Test
    public void excluirProdutoTest() throws JsonProcessingException, URISyntaxException{
        String url = BASE_PATH + "/deletarProduto";

    	DadosProduto produto = new DadosProduto();
    	produto.setId("1");
    	produto.setIdTipoFuncionario(1l);
    	
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(produto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON) ;
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(jsonInString, headers);
        ResponseEntity<Map> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, Map.class);
        Assert.assertTrue(result.getBody().containsKey("produto"));
        
    }
    
    @Test
    public void getConsultarQuartoTest() throws JsonProcessingException{
    	DadosQuarto quarto = new DadosQuarto();
    	quarto.setId("1");
    	quarto.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/consultarQuarto",quarto, Map.class);
    	Assert.assertTrue(map.containsKey("quarto"));
    }
    
    @Test
    public void inserirQuartoTest() throws JsonProcessingException, URISyntaxException{
    	DadosQuarto quarto = new DadosQuarto();
    	quarto.setId("1");
    	quarto.setIdTipoFuncionario(1l);
    	Map<String,Object> map = restTemplate.postForObject(BASE_PATH + "/inserirQuarto",quarto, Map.class);
    	Assert.assertTrue(map.containsKey("quarto"));
    }

    @Test
    public void atualizarQuartoTest() throws JsonProcessingException, URISyntaxException{
    	DadosQuarto quarto = new DadosQuarto();
    	quarto.setId("1");
    	quarto.setIdTipoFuncionario(1l);
    	quarto.setDescricao("teste");

    	restTemplate.put(BASE_PATH + "/atualizarQuarto", quarto);
    	
    	URI uri = new URI(BASE_PATH + "/atualizarQuarto");
    	RequestEntity<DadosQuarto> requestEntity = RequestEntity.put(uri).body(quarto);
    	ResponseEntity<Map> result = restTemplate.exchange(requestEntity, Map.class);
 
    	Assert.assertTrue(result.getBody().containsKey("quarto"));
    }
    
    @Test
    public void excluirQuartoTest() throws JsonProcessingException, URISyntaxException{
        String url = BASE_PATH + "/deletarQuarto";

    	DadosQuarto quarto = new DadosQuarto();
    	quarto.setId("1");
    	quarto.setIdTipoFuncionario(1l);
    	
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(quarto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON) ;
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(jsonInString, headers);
        ResponseEntity<Map> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, Map.class);
        Assert.assertTrue(result.getBody().containsKey("quarto"));
        
    }
}
