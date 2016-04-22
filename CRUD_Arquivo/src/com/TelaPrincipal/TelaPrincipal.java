package com.TelaPrincipal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.produto.Produto;
import com.recursos.InOut;

public class TelaPrincipal {
	
	private static ArrayList<Produto> listaProduto = new ArrayList<Produto>();
	
	public static void main(String[] args) throws IOException{
		Menu();
		System.out.println("Programa Finzalizado");
	}
	
	public static void Menu() throws IOException{
		int op;
		do{
		String opcoes = "Digite um dos Numeros abaixo:\n"+
						"1 - Cadastrar Produto\n"+
						"2 - Lista Produtos\n"+
						"3 - Alterar Produtos\n"+
						"4 - Procurar por Produto\n"+
						"5 - Deletar Produto\n"+
						"6 - Apagar Produtos\n"+
						"0 - Sair";
		op = InOut.InInt(opcoes);
		switch(op){
			case 0:
				InOut.OutMessage("O programa será Finalizado", "Atenção");
				break;
			case 1:
				CadastrarProduto();
				break;
			case 2:
				ListaProduto();
				break;
			case 3:
				AlterarProduto();
				break;
			case 4:
				ProcurarProduto();
				break;
			case 5:
				DeletarProduto();
				break;
			case 6:
				ApagarProduto();
				break;
			default:
				InOut.OutMessage("Opção Invalida!", "Erro!");
				break;		
		}
		}while(op != 0);
	}
	
	public static void CadastrarProduto() {
		String nome = InOut.InString("Insira o Nome do Produto:");
		double preco = InOut.InInt("Digite o Preço do Produto:");
		Produto produto = new Produto(nome, preco);
		listaProduto.add(produto);
	}
	
	public static void ListaProduto() throws IOException{
		if(listaProduto.isEmpty()){
			InOut.OutMessage("Nenhum Produto Cadastrado");
			return;
		}
		FileWriter arq = new FileWriter("listaProduto.txt");
		PrintWriter gravaArq = new PrintWriter(arq);
		String relatorio = "";
		gravaArq.printf("---------Lista de Produto---------\r\n");
		for(int i = 0; i < listaProduto.size(); i++){
			Produto prod = listaProduto.get(i);
			gravaArq.printf(" - |CODIGO| %d |NOME| %s |PREÇO| %f\r\n", 
							prod.getCodigo(), prod.getNome(), prod.getPreco());
			
			relatorio += "\nCodigo: " + prod.getCodigo() + 
						 "\nNome: " + prod.getNome() +
						 "\nPreço: R$" + prod.getPreco()+
						 "\n----------------------------------------------------------\r";
		}
		gravaArq.close();
		InOut.OutMessage(relatorio);
	}
	
	public static void AlterarProduto(){
		if(listaProduto.size() == 0){
			InOut.OutMessage("Lista Vazia");
			return;
		}
		String nomeProdutoPesquisar = InOut.InString("Digite o Nome do Produto que deseja procurar:");
		for(int i=0; i < listaProduto.size(); i++){
			Produto produto = listaProduto.get(i);
			
			if(nomeProdutoPesquisar.equalsIgnoreCase(produto.getNome())){
				String nomeNovo = InOut.InString("Digite o novo Nome do produto:");
				produto.setNome(nomeNovo);
				InOut.OutMessage("Nome alterado com sucesso");
				break;
			}
		}
		InOut.OutMessage("Produto não encontrado");
	}
	
	public static void ProcurarProduto(){
		String exibir = "";
		String nomeArq = "listaProduto.txt";
		String linha = "";
		File arq = new File(nomeArq);
		
		if(arq.exists()){
			exibir = "RELATORIO";
			try{
				 FileReader abrindo = new FileReader(nomeArq);
				 BufferedReader leitor = new BufferedReader(abrindo);
				 while(true){
					 linha = leitor.readLine();
					 if(linha == null)
						 break;
					 exibir += linha + "\n";
				 }
				 leitor.close();
			}catch(Exception e){
				InOut.OutMessage("Erro: \n"+e.getMessage(), "ERRO");
			}
			InOut.OutMessage(exibir, "LISTA DE PRODUTOS");
		}else{
			InOut.OutMessage("Arquivo inexistente", "ERRO");
		}
	}
	
	public static void DeletarProduto(){
		if(listaProduto.size() == 0){
			InOut.OutMessage("Lista Vazia");
			return;
		}
		String nomeProdutoPesquisar = InOut.InString("Digite o Nome do Produto que deseja Deletar:");
		for(int i=0; i < listaProduto.size(); i++){
			Produto produto = listaProduto.get(i);
			
			if(nomeProdutoPesquisar.equalsIgnoreCase(produto.getNome())){
				listaProduto.remove(i);
				InOut.OutMessage("Produto deletado com Sucesso!");
				break;
			}
		}
		InOut.OutMessage("Nome alterado com sucesso");
	}
	
	public static void ApagarProduto() {
		if(listaProduto.isEmpty()){
			InOut.OutMessage("Nenhum Produto Cadastrado");
			return;
		}
		listaProduto.clear();
		InOut.OutMessage("Todos os Produto foram Apagados!");
	}
}
