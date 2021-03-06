package com.henriqueoak.testetinnova.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henriqueoak.testetinnova.domain.Veiculo;
import com.henriqueoak.testetinnova.dto.VeiculoDTO;
import com.henriqueoak.testetinnova.repository.VeiculoRepository;
import com.henriqueoak.testetinnova.services.exception.ObjectNotFoundException;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository v;
	
	public List<Veiculo> findAll(){
		return v.findAll();
	}
	
	public Veiculo findById(String id) {
		Optional<Veiculo> obj = v.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		}
	
	public Veiculo insert(Veiculo obj) {
		return v.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		v.deleteById(id);
	}
	
	public Veiculo update(Veiculo obj) {
		Veiculo newObj = findById(obj.getId());
		updateData(newObj, obj);
		return v.save(newObj);
	}
	
	private void updateData(Veiculo newObj, Veiculo obj) {
		newObj.setVeiculo(obj.getVeiculo());
		newObj.setMarca(obj.getMarca());
		newObj.setAno(obj.getAno());
		newObj.setDescricao(obj.getDescricao());
		newObj.setVendido(obj.isVendido());
	}

	public Veiculo fromDTO(VeiculoDTO objDto) {
		return new Veiculo(objDto.getId(), objDto.getVeiculo(), objDto.getMarca(), objDto.getAno(), objDto.getDescricao(), objDto.isVendido());
	}
}
