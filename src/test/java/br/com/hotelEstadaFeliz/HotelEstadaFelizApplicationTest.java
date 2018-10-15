package br.com.hotelEstadaFeliz;

import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

import br.com.hotelEstadaFeliz.beans.Cliente;
import br.com.hotelEstadaFeliz.beans.Funcionario;
import br.com.hotelEstadaFeliz.beans.Hospedagem;
import br.com.hotelEstadaFeliz.beans.Hotel;
import br.com.hotelEstadaFeliz.beans.Login;
import br.com.hotelEstadaFeliz.beans.Produto;
import br.com.hotelEstadaFeliz.beans.Quarto;
import br.com.hotelEstadaFeliz.dto.DadosCliente;
import br.com.hotelEstadaFeliz.dto.DadosFuncionario;
import br.com.hotelEstadaFeliz.dto.DadosHospedagem;
import br.com.hotelEstadaFeliz.dto.DadosHotel;
import br.com.hotelEstadaFeliz.dto.DadosLogin;
import br.com.hotelEstadaFeliz.dto.DadosProduto;
import br.com.hotelEstadaFeliz.dto.DadosQuarto;

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
    	login.setSenha("asdf");
    	List<Login> teste = restTemplate.postForObject(BASE_PATH + "/login",login, List.class);
    	assertNotNull(teste);
    }
    
    @Test
    public void getConsultarTodosClienteTest() throws JsonProcessingException{
    	List<Cliente> teste = restTemplate.getForObject(BASE_PATH + "/consultarTodosClientes", List.class);
    	assertNotNull(teste);
    }
    
    
    @Test
    public void getConsultarClienteTest() throws JsonProcessingException{
    	DadosCliente cliente = new DadosCliente();
    	cliente.setCpf(12345678903l);
    	cliente.setIdTipoFuncionario(1l);
    	List<Cliente> teste = restTemplate.postForObject(BASE_PATH + "/consultarCliente",cliente, List.class);
    	assertNotNull(teste);
    }
    
    @Test
    public void inserirClienteTest() throws JsonProcessingException, URISyntaxException{
    	DadosCliente cliente = new DadosCliente();
    	cliente.setCpf(12345678903l);
    	cliente.setIdTipoFuncionario(1l);
    	List<Cliente> teste = restTemplate.postForObject(BASE_PATH + "/inserirCliente",cliente, List.class);
    	assertNotNull(teste);
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
    	ResponseEntity<List> result = restTemplate.exchange(requestEntity, List.class);
 
    	assertNotNull(result);
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
        ResponseEntity<List> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, List.class);
        assertNotNull(result);
        
    }
    
    @Test
    public void getConsultarTodosFuncionarioTest() throws JsonProcessingException{
    	List<Funcionario> teste = restTemplate.getForObject(BASE_PATH + "/consultarTodosFuncionarios", List.class);
    	assertNotNull(teste);
    }
    
    
    @Test
    public void getConsultarFuncionarioTest() throws JsonProcessingException{
    	DadosFuncionario funcionario = new DadosFuncionario();
    	funcionario.setCpf(12345678903l);
    	funcionario.setIdTipoFuncionario(1l);
    	List<Funcionario> teste = restTemplate.postForObject(BASE_PATH + "/consultarFuncionario",funcionario, List.class);
    	assertNotNull(teste);
    }
    
    @Test
    public void inserirFuncionarioTest() throws JsonProcessingException, URISyntaxException{
    	DadosFuncionario funcionario = new DadosFuncionario();
    	funcionario.setCpf(12345678903l);
    	funcionario.setIdTipoFuncionario(1l);
    	List<Funcionario> teste = restTemplate.postForObject(BASE_PATH + "/inserirFuncionario",funcionario, List.class);
    	assertNotNull(teste);
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
    	ResponseEntity<List> result = restTemplate.exchange(requestEntity, List.class);
 
    	assertNotNull(result);
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
        ResponseEntity<List> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, List.class);
        assertNotNull(result);
        
    }
    
    @Test
    public void getConsultarTodosHospedagemTest() throws JsonProcessingException{
    	List<Hospedagem> teste = restTemplate.getForObject(BASE_PATH + "/consultarTodasHospedagens", List.class);
    	assertNotNull(teste);
    }
    
    
    @Test
    public void getConsultarHospedagemTest() throws JsonProcessingException{
    	DadosHospedagem hospedagem = new DadosHospedagem();
    	hospedagem.setIdQuarto("1");
    	hospedagem.setIdTipoFuncionario(1l);
    	List<Hospedagem> teste = restTemplate.postForObject(BASE_PATH + "/consultarHospedagem",hospedagem, List.class);
    	assertNotNull(teste);
    }
    
    @Test
    public void inserirHospedagemTest() throws JsonProcessingException, URISyntaxException{
    	DadosHospedagem hospedagem = new DadosHospedagem();
    	hospedagem.setIdQuarto("1");
    	hospedagem.setIdTipoFuncionario(1l);
    	List<Hospedagem> teste = restTemplate.postForObject(BASE_PATH + "/inserirHospedagem",hospedagem, List.class);
    	assertNotNull(teste);
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
    	ResponseEntity<List> result = restTemplate.exchange(requestEntity, List.class);
 
    	assertNotNull(result);
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
        ResponseEntity<List> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, List.class);
        assertNotNull(result);
        
    }
    
    @Test
    public void getConsultarTodosHotelTest() throws JsonProcessingException{
    	List<Hotel> teste = restTemplate.getForObject(BASE_PATH + "/consultarTodosHoteis", List.class);
    	assertNotNull(teste);
    }
    
    @Test
    public void getConsultarHotelTest() throws JsonProcessingException{
    	DadosHotel hotel = new DadosHotel();
    	hotel.setId("1");
    	hotel.setIdTipoFuncionario(1l);
    	List<Hotel> teste = restTemplate.postForObject(BASE_PATH + "/consultarHotel",hotel, List.class);
    	assertNotNull(teste);
    }
    
    @Test
    public void inserirHotelTest() throws JsonProcessingException, URISyntaxException{
    	DadosHotel hotel = new DadosHotel();
    	hotel.setId("1");
    	hotel.setIdTipoFuncionario(1l);
    	List<Hotel> teste = restTemplate.postForObject(BASE_PATH + "/inserirHotel",hotel, List.class);
    	assertNotNull(teste);
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
    	ResponseEntity<List> result = restTemplate.exchange(requestEntity, List.class);
 
    	assertNotNull(result);
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
        ResponseEntity<List> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, List.class);
        assertNotNull(result);
        
    }
    
    @Test
    public void getConsultarTodosProdutoTest() throws JsonProcessingException{
    	List<Produto> teste = restTemplate.getForObject(BASE_PATH + "/consultarTodosProdutos", List.class);
    	assertNotNull(teste);
    }
    
    
    @Test
    public void getConsultarProdutoTest() throws JsonProcessingException{
    	DadosProduto produto = new DadosProduto();
    	produto.setId("1");
    	produto.setIdTipoFuncionario(1l);
    	List<Produto> teste = restTemplate.postForObject(BASE_PATH + "/consultarProduto",produto, List.class);
    	assertNotNull(teste);
    }
    
    @Test
    public void inserirProdutoTest() throws JsonProcessingException, URISyntaxException{
    	DadosProduto produto = new DadosProduto();
    	produto.setId("1");
    	produto.setIdTipoFuncionario(1l);
    	List<Produto> teste = restTemplate.postForObject(BASE_PATH + "/inserirProduto",produto, List.class);
    	assertNotNull(teste);
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
    	ResponseEntity<List> result = restTemplate.exchange(requestEntity, List.class);
 
    	assertNotNull(result);
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
        ResponseEntity<List> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, List.class);
        assertNotNull(result);
        
    }
    
    @Test
    public void getConsultarTodosQuartoTest() throws JsonProcessingException{
    	List<Quarto> teste = restTemplate.getForObject(BASE_PATH + "/consultarTodosQuartos", List.class);
    	assertNotNull(teste);
    }
    
    
    @Test
    public void getConsultarQuartoTest() throws JsonProcessingException{
    	DadosQuarto quarto = new DadosQuarto();
    	quarto.setId("1");
    	quarto.setIdTipoFuncionario(1l);
    	List<Quarto> teste = restTemplate.postForObject(BASE_PATH + "/consultarQuarto",quarto, List.class);
    	assertNotNull(teste);
    }
    
    @Test
    public void inserirQuartoTest() throws JsonProcessingException, URISyntaxException{
    	DadosQuarto quarto = new DadosQuarto();
    	quarto.setId("1");
    	quarto.setIdTipoFuncionario(1l);
    	List<Quarto> teste = restTemplate.postForObject(BASE_PATH + "/inserirQuarto",quarto, List.class);
    	assertNotNull(teste);
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
    	ResponseEntity<List> result = restTemplate.exchange(requestEntity, List.class);
 
    	assertNotNull(result);
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
        ResponseEntity<List> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, List.class);
        assertNotNull(result);
        
    }
    
}
