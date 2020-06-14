package com.viniciuslisboa.lojaderoupas.business.bean;

import java.util.ArrayList;
import java.util.List;

import com.viniciuslisboa.lojaderoupas.business.enums.CategoriaEnum;

public class CatalogoBean {

	private List<RoupaBean> roupas;

	public CatalogoBean() {
		
		roupas = new ArrayList<>();
		roupas.add(novaRoupa("Camisa", CategoriaEnum.ROUPA_MASCULINA, 49.9f, "camisa.jpg"));
		roupas.add(novaRoupa("Calça", CategoriaEnum.ROUPA_MASCULINA, 80f, "calca.jpg"));
		roupas.add(novaRoupa("Regata", CategoriaEnum.ROUPA_FEMININA, 15f, "regata.jpg"));
		roupas.add(novaRoupa("Vestido", CategoriaEnum.ROUPA_FEMININA, 120f, "vestido.jpg"));
		roupas.add(novaRoupa("Macacão", CategoriaEnum.ROUPA_FEMININA, 70.99f, "macacao.jpg"));
		roupas.add(novaRoupa("Roupa de Grávida", CategoriaEnum.ROUPA_FEMININA, 80f, "roupa-de-gravida.jpg"));
		roupas.add(novaRoupa("Roupa de Verão", CategoriaEnum.ROUPA_FEMININA, 45.99f, "roupa-de-verao.jpg"));
	}

	
	private RoupaBean novaRoupa(String nome, CategoriaEnum categoriaEnum, Float preco, String nomeImagem) {

		
		Integer codigo = roupas.size();

		RoupaBean roupaBean = new RoupaBean();
		roupaBean.setCodigo(codigo);
		roupaBean.setNome(nome);
		roupaBean.setCodigoCategoria(categoriaEnum.getCodigo());
		roupaBean.setCategoria(categoriaEnum.getNome());
		roupaBean.setPreco(preco);
		roupaBean.setNomeImagem(nomeImagem);

		return roupaBean;
	}

	public List<RoupaBean> getRoupas() {
		return roupas;
	}

	public void setRoupas(List<RoupaBean> roupas) {
		this.roupas = roupas;
	}

	
	public List<RoupaBean> getRoupasFiltradas(String[] codigosCategoria) {

		
		if (codigosCategoria == null) {
			return roupas;
		}

		List<RoupaBean> roupasFiltradas = new ArrayList<>();

		
		List<Integer> codigos = new ArrayList<>();

		
		
		for (String codigoCategoria : codigosCategoria) {
			codigos.add(Integer.parseInt(codigoCategoria));
		}

		
		for (RoupaBean roupa : roupas) {
			if (codigos.contains(roupa.getCodigoCategoria())) {
				roupasFiltradas.add(roupa);
			}
		}

		return roupasFiltradas;

	}

}
