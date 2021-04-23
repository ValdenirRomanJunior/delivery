package br.com.dynamous.delivery;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.dynamous.delivery.domain.Categoria;
import br.com.dynamous.delivery.domain.Cidade;
import br.com.dynamous.delivery.domain.Cliente;
import br.com.dynamous.delivery.domain.Endereco;
import br.com.dynamous.delivery.domain.Estado;
import br.com.dynamous.delivery.domain.ItemPedido;
import br.com.dynamous.delivery.domain.Pagamento;
import br.com.dynamous.delivery.domain.PagamentoComBoleto;
import br.com.dynamous.delivery.domain.PagamentoComCartao;
import br.com.dynamous.delivery.domain.Pedido;
import br.com.dynamous.delivery.domain.Produto;
import br.com.dynamous.delivery.domain.enums.EstadoPagamento;
import br.com.dynamous.delivery.domain.enums.TipoCliente;
import br.com.dynamous.delivery.repositories.CategoriaRepository;
import br.com.dynamous.delivery.repositories.CidadeRepository;
import br.com.dynamous.delivery.repositories.ClienteRepository;
import br.com.dynamous.delivery.repositories.EnderecoRepository;
import br.com.dynamous.delivery.repositories.EstadoRepository;
import br.com.dynamous.delivery.repositories.ItemPedidoRepository;
import br.com.dynamous.delivery.repositories.PagamentoRepository;
import br.com.dynamous.delivery.repositories.PedidoRepository;
import br.com.dynamous.delivery.repositories.ProdutoRepository;

@SpringBootApplication
public class DeliveryApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null,"escritorio");	
		
		Produto p1 = new Produto(null,"computador",200.88);
		Produto p2 = new Produto(null,"impressora",30.00);
		Produto p3 = new Produto(null,"mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepo.saveAll(Arrays.asList(cat1,cat2));
		produtoRepo.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1= new Estado (null,"minas");
		Estado est2= new Estado (null,"sao paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"sao paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepo.saveAll(Arrays.asList(est1,est2));
		cidadeRepo.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1= new Cliente(null,"maria","maria@hotmail.com","4567932456",TipoCliente.PESSOAFIICA);
		
		cli1.getTelefones().addAll(Arrays.asList("457895434","4567892354"));
		
		Endereco e1 = new Endereco(null,"Rua das missoes","300","Apt 200","jardim","00993",cli1,c1);
		Endereco e2 = new Endereco(null,"Rua das passos","200","Apt 100","cumbuco","00994",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));	
		
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm");
		
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,e1);	
		Pedido ped2 = new Pedido(null,sdf.parse("10/00/2018 11:32"),cli1,e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2018 00:00"),null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepo.saveAll(Arrays.asList(pgto1,pgto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,1,80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
